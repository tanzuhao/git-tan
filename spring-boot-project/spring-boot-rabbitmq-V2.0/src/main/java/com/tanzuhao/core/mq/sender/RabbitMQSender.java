package com.tanzuhao.core.mq.sender;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class RabbitMQSender implements  RabbitTemplate.ConfirmCallback{
	public static final Logger log = LoggerFactory.getLogger(RabbitMQSender.class);
	@Autowired
	private AmqpTemplate rabbitTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplatenew;
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
		rabbitTemplate.convertAndSend(RabbitMQConstant.DIRECT_QUEUE, user);
	}
	/**
	 * Topic类型
	 * 1.与direct类型的Exchange交换器相似，也是将消息路由到BindingKey(绑定键)与 RoutingKey(路由键)相匹配的Queue(队列)
	 * 2.不同点：topic类型的Exchange(交换器)在匹配规则上进行了扩展
	 * 3.匹配规则：
	 * 3.1 RoutingKey(路由键)用"."分割的字母或数字
	 * 3.2 *：匹配单个字母或数字 
	 * 3.3 #：匹配0~多个字母或数字.
	 * 3.4 BindingKey(绑定键)中以"*"与"#"，做模糊匹配。其中，* ：匹配一个单词，# ：匹配零或多个单词。
	 *  例如："*.*.rabbit" 表示："任意一个单词.任意一个单词.rabbit" ,
	 *  "#.rabbit.#" 表示:"任意n个单词.rabbit.任意n个单词",其中 n>=0。
	 */
	public void sendTopicQueue() {
        User user1 = new User();
        user1.setUserId(12l);
        user1.setUsername("赵哈哈");
        rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, "tan.123", user1 );

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
        rabbitTemplate.convertAndSend(RabbitMQConstant.FANOUT_EXCHANGE, "", user);		     
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
			rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_QUEUE,"tan.00", user);
		}
	}
	/**
	 * 信息回调和确认
	 */
	public void sendAckMessage() {      
        rabbitTemplatenew.setConfirmCallback(this);
        User user=new User();
        user.setUsername("谭好好");
        user.setUserId(100l);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());     
        rabbitTemplatenew.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE,"ack.1000", user, correlationData);  
    }
    /**
     * 信息回调处理
     * 只确认生产者消息发送成功(即只确认是否正确到达 Exchange 中)，消费者是否处理成功不做保证
     */
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(">>>>>>>>>>callbakck UUID: " + correlationData.getId());
        if (ack) {
			log.info(">>>>>>>>>>生产者消息发送确认成功");
		} else {
			log.info(">>>>>>>>>>消息发送确认失败:" + cause);
		}
    }
}
