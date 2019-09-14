package com.tanzuhao.core.mq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 通用配置类 rabbitmq 工具类 RabbitMQ相关概念 1.工作流程
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
public class RabbitMQBaseConfig {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQBaseConfig.class);
	// rabbitmq连接信息
	@Value("${spring.rabbitmq.host}")
	public String addresses;

	@Value("${spring.rabbitmq.port}")
	public String port;

	@Value("${spring.rabbitmq.username}")
	public String username;

	@Value("${spring.rabbitmq.password}")
	public String password;

	@Value("${spring.rabbitmq.virtual-host}")
	public String virtualHost;

	@Value("${spring.rabbitmq.publisher-confirms}")
	public boolean publisherConfirms;

	/**
	 * Direct类型
	 *
	 * @return
	 */
	@Bean
	public Queue directQueue() {
		// 第一个参数是队列名字， 第二个参数是指是否持久化
		return new Queue(RabbitMQConstant.DIRECT_QUEUE,true);
	}

	/**
	 * 创建Topic类型队列
	 * 
	 * @return
	 */
	@Bean
	public Queue topicQueue() {
		return new Queue(RabbitMQConstant.TOPIC_QUEUE,true);
	}

	/**
	 * 创建topic exchange交换器
	 * 
	 * @return
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
	}

	/**
	 * topic 声明绑定关系 生产者将消息发送到Exchange，由Exchange将消息按照匹配规则路由到一个或者多个Queue中（或者丢弃）
	 * topic exchange 交换器将符合匹配规则(tan.#)的消息路由到topcQueue1队列中
	 * 
	 * @return
	 */
	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTE_KEY);
	}

	/**
	 * Fanout 广播模式或者订阅模式
	 * 
	 * 创建Fanout类型队列名
	 * 
	 * @return
	 */
	@Bean
	public Queue fanoutQueue() {
		return new Queue(RabbitMQConstant.FANOUT_QUEUE,true);
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
	public Binding fanoutBinding() {
		return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses + ":" + port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		// connectionFactory.setVirtualHost(virtualHost);
		/** 如果要进行消息回调，则这里必须要设置为true */
		connectionFactory.setPublisherConfirms(publisherConfirms);
		return connectionFactory;
	}

	/**
	 * 因为要设置回调类，所以应是prototype类型， 如果是singleton类型，则回调类为最后一次设置
	 * 
	 * @return
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplatenew() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setDefaultReceiveQueue(RabbitMQConstant.TOPIC_ACK_QUEUE);// 设置默认接收队列
		return template;
	}
}