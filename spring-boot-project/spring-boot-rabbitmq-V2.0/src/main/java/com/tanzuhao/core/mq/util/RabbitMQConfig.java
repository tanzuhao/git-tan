package com.tanzuhao.core.mq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 业务配置类
 * 
 * @author tanzuhao
 *
 */
@Configuration
public class RabbitMQConfig extends RabbitMQBaseConfig {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

	@Bean
	public Queue topicAckQueue() {
		return new Queue(RabbitMQConstant.TOPIC_ACK_QUEUE, true);// true:队列持久化
	}

	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicAckQueue()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ACK_ROUTE_KEY);
	}

	@Bean(name = "listenerContainer")
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> listenerContainer() {
		// 消息的统一过滤器
		// MessageConverter messageConverter = new ObjConsert();
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConcurrentConsumers(5);// 允许同时消费数量为5
		factory.setMaxConcurrentConsumers(50);// 允许同时最大消费数量为50
		factory.setReceiveTimeout(10000L);// 10秒
		factory.setPrefetchCount(1);//每次从一次性从broker里面取的待消费的消息的个数,即每个消费者每次会预取1个消息准备消费
		// factory.setMessageConverter(messageConverter);//具体的逻辑要自己在ObjConsert里面写
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);// 设置手动提交
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}
}