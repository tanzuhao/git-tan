package com.tanzuhao.core.mq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 工具类 RabbitMQ相关概念 1.工作流程
 * producer(生产者)-消息-->Exchange--->Queue(队列,n个)---->consumer(消费者C1/C2/../Cn)
 * 
 * 2.broker:缓存代理(一台或多台服务器（rabbitmq）的统称)
 * 它提供一种传输服务，它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输。
 * 
 * 3.Exchange:消息交换机 提供消息路由策略，指定消息按什么规则，路由到哪个队列。
 * 
 * 4.Queue:队列 消息的载体，每个消息都会 被投递到一个或者多个队列。
 * 
 * 5.Binding:绑定 把Exchange和Queue按照路由规则绑定起来。
 * 
 * 6.koutingKey:路由关键字 Exchange根据这个关键字进行消息投递。
 * 
 * 7.vhost:虚拟机 一个broker里可以有多个vhost,用作不同用户的权限分离。
 * 
 * 8.producer:消息生产者 投递消息的程序
 * 
 * 9.consumer: 消费者 接受消息的程序
 * 
 * 10.channel:消息通道 10.1.与RabbitMQ打交道的最重要的一个接口。 10.2.Channel是在connection内部建立的逻辑连接
 * 
 * 
 * 10.3.在客户端的每个连接里，可建立多个channel 10.4.AMQP method包含了channel id帮助客户端和message
 * broker识别channel，所以channel之间是完全隔离的。 Channel作为轻量级的Connection极大减少了操作系统建立TCP
 * connection的开销
 * 
 * @author tanzuhao
 *
 */
@Configuration
public class RabbitMQConfig {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

	/**
	 * Direct类型
	 *
	 * @return
	 */
	@Bean
	public Queue directQueue() {
		// 第一个参数是队列名字， 第二个参数是指是否持久化
		return new Queue(RabbitMQConstant.DIRECT_QUEUE, true);
	}

	// Topic类型
	/**
	 * 创建队列1
	 * 
	 * @return
	 */
	@Bean
	public Queue topicQueue1() {
		return new Queue(RabbitMQConstant.TOPIC_QUEUE1);
	}

	/**
	 * 创建队列2
	 * 
	 * @return
	 */
	@Bean
	public Queue topicQueue2() {
		return new Queue(RabbitMQConstant.TOPIC_QUEUE2);
	}

	/**
	 * 创建topic exchange 交换器
	 * 
	 * @return
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
	}

	/**
	 * 声明绑定关系 生产者将消息发送到Exchange，由Exchange将消息按照匹配规则路由到一个或者多个Queue中（或者丢弃） topic
	 * exchange 交换器将符合匹配规则(lzc.message)的消息路由到topcQueue1队列中
	 * 
	 * @return
	 */
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("lzc.message");
	}

	/**
	 * topic exchange 交换器将符合匹配规则(lzc.#)的消息路由到topcQueue2队列中
	 * 
	 * @return
	 */
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lzc.#");
	}

	/**
	 * Fanout 广播模式或者订阅模式
	 * 
	 * @return
	 */
	/**
	 * 创建队列名1
	 * 
	 * @return
	 */
	@Bean
	public Queue fanoutQueue1() {
		return new Queue(RabbitMQConstant.FANOUT_QUEUE1);
	}

	/**
	 * 创建队列名2
	 * 
	 * @return
	 */
	@Bean
	public Queue fanoutQueue2() {
		return new Queue(RabbitMQConstant.FANOUT_QUEUE2);
	}

	/**
	 * 
	 * 创建fanout类型交换器
	 * 
	 * @return
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE);
	}

	/**
	 * 将队列和fanout exchange交换器进行绑定
	 * 
	 * @return
	 */
	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
	}

	/**.
	 * 多个消费者同时订阅同一个queue的消息
	 * 
	 * @return
	 */
	@Bean
	public Queue sameQueue() {
		// 第一个参数是队列名字， 第二个参数是指是否持久化
		return new Queue(RabbitMQConstant.SAME_QUEUE, true);
	}

	// ----------------------开始复杂---------------------------------
	/**
	 * 队列
	 * 
	 * @return
	 */
	@Bean
	@Qualifier(RabbitMQConstant.PROGRAMMATICALLY_QUEUE)
	Queue queue() {
		return new Queue(RabbitMQConstant.PROGRAMMATICALLY_QUEUE, false, false, true);
	}

	/**
	 * 交换器
	 * 
	 * @return
	 */
	@Bean
	@Qualifier(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE)
	TopicExchange exchange() {
		return new TopicExchange(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE, false, true);
	}

	/**
	 * 声明绑定关系
	 * 
	 * @return
	 */
	@Bean
	Binding binding(@Qualifier(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE) TopicExchange exchange,
			@Qualifier(RabbitMQConstant.PROGRAMMATICALLY_QUEUE) Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with(RabbitMQConstant.PROGRAMMATICALLY_KEY);
	}
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory (ConnectionFactory connectionFactory) throws Exception{
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(2);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}
}