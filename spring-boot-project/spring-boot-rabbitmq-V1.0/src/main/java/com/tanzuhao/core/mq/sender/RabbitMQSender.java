package com.tanzuhao.core.mq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanzuhao.core.mq.util.RabbitMQConstant;
import com.tanzuhao.core.system.dto.User;
/**
 * 生产者
 * RabbitMQ提供了四种Exchange类型：
 * 1.direct（直接类型）(又称点对点模式)
 * 2.topic(主题类型)
 * 3.fanout(广播类型/订阅类型，需要绑定)
 * 4.header(头部类型，headers类型的Exchange(交换器)性能很差,所以使用中较少)
 * @author tanzuhao
 *
 */
@Component
public class RabbitMQSender {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQSender.class);
	@Autowired
	private AmqpTemplate rabbitTemplate;
    /**
     *Direct类型(直接类型,RabbitMQ默认类型)
     *1.消息发给唯一一个节点使用。
     *2.可以使用rabbitMQ自带的Exchange(default Exchange)。
     *3.Direct类型下不需要将Exchange进行任何绑定(binding)操作。
     *4.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
     *5.如果vhost(虚拟主机)中不存在RouteKey中指定的队列名，则该消息会被抛弃。
     */
	public void sendDirectQueue() {
		User user = new User();
		user.setUserId(10l);
		user.setUsername("tan haohao");
		log.info(">>>>>>>>>>direct exchange producer send message username:"+user.getUsername());
		this.rabbitTemplate.convertAndSend(RabbitMQConstant.DIRECT_QUEUE, user);
	}
	/**
	 * Topic类型
	 * 1.与direct类型的Exchange交换器相似，也是将消息路由到BindingKey(绑定键)与 RoutingKey(路由键)相匹配的Queue(队列)
	 * 2.不同点：topic类型的Exchange(交换器)在匹配规则上进行了扩展
	 * 3.匹配规则：
	 * 3.1.RoutingKey(路由键)以"."作为分隔标识.
	 * 3.2.BindingKey(绑定键)中以"*"与"#"，做模糊匹配。其中，* ：匹配一个单词，# ：匹配零或多个单词。
	 *  例如："*.*.rabbit" 表示："任意一个单词.任意一个单词.rabbit" ,
	 *  "#.rabbit.#" 表示:"任意n个单词.rabbit.任意n个单词",其中 n>=0。
	 */
	public void sendTopicQueue() {
        User user1 = new User();
        user1.setUserId(12l);
        user1.setUsername("赵哈哈");

        User user2 = new User();
        user2.setUserId(13l);
        user2.setUsername("张三");
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, "lzc.message", user1 );
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, "lzc.lzc", user2);

    }
	 /**
     * Fanout模式
     * Fanout 广播模式或者订阅模式，
     * 1.给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     * 2.fanout Exchange 不需要处理RouteKey 。只需要简单的将队列绑定到exchange上
     * 3.发送到exchange的消息都会被转发到与该交换机绑定的所有队列上
     * 4.Fanout Exchange 转发消息是最快的
     * @return
     */
	public void sendFanoutQueue() {
		User user = new User();
        user.setUserId(100l);
        user.setUsername("赵哈哈");
        /**
         * 注意， 这里的第2个参数为空。
         * 因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
         * 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.FANOUT_EXCHANGE, "", user);		     
    }
	/**
	 * 多个消费者同时订阅同一个queue中的消息,queue中的消息会被平摊给多个消费者。
	 * 拓展：多个消费者可以是在不同的系统中。
	 */
	public void sendSameQueue() {
		for (long i = 1; i <= 20l; i++) {
			User user = new User();
			user.setUserId(10l);
			user.setUsername("赵" + String.valueOf(i) + "号");
			this.rabbitTemplate.convertAndSend(RabbitMQConstant.SAME_QUEUE, user);
		}
	}
	public void sendPrefetchQueue() {
		for (long i = 1l; i <= 20l; i++) {
			User user = new User();
			user.setUserId(i);
			if(i%2==0){
				user.setUserId(0l);
			}
			user.setUsername("赵" + String.valueOf(i) + "号");
			this.rabbitTemplate.convertAndSend(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE, "tan.message", user);
		}
	}

}
