package com.tanzuhao.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.mq.sender.RabbitMQSender;
import com.tanzuhao.core.util.Results;

/**
 * rabbitmq基本操作demo
 * 
 * @author tanzuhao
 *
 */
@RequestMapping("/rabbitmq")
@RestController
public class RabbitMQController {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQController.class);
	@Autowired
	private RabbitMQSender rabbitMQSender;

	/**
	 * Direct类型 访问地址：http://localhost:8086/rabbitmq/direct
	 * 
	 * @return
	 */
	@RequestMapping("/direct")
	public Result directExchange() {
		rabbitMQSender.sendDirectQueue();
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());

	}

	/**
	 * Topic类型 访问地址：http://localhost:8086/rabbitmq/topic
	 * 
	 * @return
	 */
	@RequestMapping("/topic")
	public Result topicExchange() {
		rabbitMQSender.sendTopicQueue();
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());

	}

	/**
	 * fanout类型 访问地址:http://localhost:8086/rabbitmq/fanout
	 * 
	 * @return
	 */
	@RequestMapping("/fanout")
	public Result fanoutExchange() {
		rabbitMQSender.sendFanoutQueue();
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());

	}

	/**
	 * 多个消费者同时订阅同一个queue中的消息,queue中消息会被平摊给多个消费者。
	 * 访问地址:http://localhost:8086/rabbitmq/sameQueue
	 * 
	 * @return
	 */
	@RequestMapping("/sameQueue")
	public Result sameQueue() {
		rabbitMQSender.sendSameQueue();
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());

	}

	/**
	 * 访问地址:http://localhost:8086/rabbitmq/prefetchQueue
	 * 
	 * @return
	 */
	@RequestMapping("/prefetchQueue")
	public Result prefetchQueue() {
		rabbitMQSender.sendPrefetchQueue();
		return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());

	}
	
}
