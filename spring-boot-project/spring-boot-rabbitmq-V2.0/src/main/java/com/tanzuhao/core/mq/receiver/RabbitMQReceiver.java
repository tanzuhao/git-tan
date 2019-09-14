package com.tanzuhao.core.mq.receiver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.tanzuhao.core.cache.redis.RedisHelper;
import com.tanzuhao.core.mq.util.RabbitMQConstant;
import com.tanzuhao.core.system.dto.User;

/**
 * 消费者
 * 
 * @author tanzuhao
 *
 */
@Component
public class RabbitMQReceiver {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisHelper redisHelper;

	/**
	 * Direct类型(点对点模式) 发送者与接收者的Queue名字(这里是direct_qname)一定要相同，否则接收收不到消息。
	 * 
	 * @param user
	 */
	@RabbitListener(queues = RabbitMQConstant.DIRECT_QUEUE)
	public void directConsumer(User user) {
		log.info(">>>>>>>>>>direct exchange consumer accept message:" + user.getUsername() + "," + user.getUserId());
	}

	/**
	 * Topic类型(主题模式) 定义监听器消费消息
	 * 支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
	 * 
	 * @param user
	 */
	// @RabbitListener(queues = RabbitMQConstant.TOPIC_QUEUE)
	@RabbitListener(bindings = @QueueBinding(value = @Queue(name = RabbitMQConstant.TOPIC_QUEUE, durable = "true"), exchange = @Exchange(name = RabbitMQConstant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"), key = {
			RabbitMQConstant.TOPIC_ROUTE_KEY }))
	public void topicConsumer(User user) {
		log.info(">>>>>>>>>>topic 收到消息:" + user.getUsername() + "," + user.getUserId());
	}

	/**
	 * Fanout类型 queues是指要监听的队列的名字
	 */
	@RabbitListener(queues = RabbitMQConstant.FANOUT_QUEUE)
	public void fanoutConsumer(User user) {
		log.info(">>>>>>>>>>>fanout 监听到消息:" + user.getUsername() + "," + user.getUserId());
	}

	/**
	 * 多个消费者同时订阅同一个queue的消息 测试结果： 每个可以随机订阅到10条消息（共20条消息）
	 * 
	 * @param user
	 */
	@RabbitListener(bindings = @QueueBinding(value = @Queue(name = RabbitMQConstant.TOPIC_QUEUE, durable = "true"), exchange = @Exchange(name = RabbitMQConstant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"), key = {
			RabbitMQConstant.TOPIC_ROUTE_KEY }))
	public void topicConsumer2(User user) {
		// 延迟10s查看订阅效果
		new Thread() {
			public void run() {
				try {
					Thread.sleep(10000);
					log.info(">>>>>>>>>>消费者1号订阅队消息:" + user.getUsername() + "," + user.getUserId());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 需要ack消费者
	 * 
	 * @RabbitHandler:此注解加上之后可以接受对象型消息
	 * @durable 消费端是否持久化，true：是。
	 * @param user
	 * @param message
	 * @param channel
	 * @param heads
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RabbitListener(
	containerFactory = "listenerContainer", 
	errorHandler = "RabbitConsumerListenerErrorHandler", 
	bindings = @QueueBinding(
			value = @Queue(name = RabbitMQConstant.TOPIC_ACK_QUEUE, durable = "true"),
	        exchange = @Exchange(name = RabbitMQConstant.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true",durable = "true"), 
	        key = {RabbitMQConstant.TOPIC_ACK_ROUTE_KEY }
			)
	)
	@RabbitHandler
	public void onMessage(@Payload User user, Message message, Channel channel, @Headers Map<String, Object> heads)
			throws Exception {
		 /* 做幂等操作可用redis或者DB来存储唯一标识符，每次消费前先查询是否消费了，如果没有消费就在消费逻辑。
		 */
		Object count = redisHelper.get("user" + String.valueOf(user.getUserId()));
		try {
			if (message.getMessageProperties().getHeaders().get("error") != null) {
				throw new Exception();
			}
			// 业务逻辑处理........
			log.info(">>>>>>>>>>ack2 消费者收到消息:" + user.getUsername() + "," + user.getUserId());
			long deTag = (Long) heads.get(AmqpHeaders.DELIVERY_TAG);
			channel.basicAck(deTag, false);// 手动确认
			// log.info(">>>>>>>>>>手动确认成功");
			redisHelper.set("user" + String.valueOf(user.getUserId()), 0);
		} catch (Exception e) {
			e.printStackTrace();
			int countInt = 0;
			boolean cancelFlag = false;// 是否拒绝入列队,true:是
			if (count != null) {
				countInt = (int) count;
				if (countInt > 2) {
					cancelFlag = true;
				}
			}
			if (cancelFlag) {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);// 可以拒绝多条消息
				// log.info(">>>>>>>>>>重试超过3次，消息拒绝入队列!");		
				redisHelper.expirse("user" + String.valueOf(user.getUserId()), 0, TimeUnit.SECONDS);
				//失败消息入库，进行人工干预......
				return;
			}
			// deliveryTag:消息传送的次数（该消息的index），multiple:是否批量(true:将一次性拒绝所有小于deliveryTag的消息)
			// requeue：为是否重新回到队列
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);// 一次只能拒绝一条消息,重新入列
			redisHelper.set("user" + String.valueOf(user.getUserId()), countInt + 1);
			// log.info(">>>>>>>>>>>消息第" +(countInt+1)+ "次处理失败,重新入列!");

		}
	}
}