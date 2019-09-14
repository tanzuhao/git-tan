package com.tanzuhao.core.mq.util;

public class RabbitMQConstant {
	// 1.直接类型队列名
	public static final String DIRECT_QUEUE = "direct.qname";
	// 2.Topic类型
	// 2.1.队列名
	public static final String TOPIC_QUEUE1 = "topic.qname1";
	public static final String TOPIC_QUEUE2 = "topic.qname2";
	// 2.2.交换器名
	public static final String TOPIC_EXCHANGE = "topic.exchange";
	// 3.Fanout类型
	// 3.1.队列名
	public static final String FANOUT_QUEUE1 = "fanout.qname1";
	public static final String FANOUT_QUEUE2 = "fanout.qname2";
	// 3.2.交换器名
	public static final String FANOUT_EXCHANGE = "fanout.exchange";
	// 1.多个消费者订阅同一个队列名
	public static final String SAME_QUEUE = "same.qname";
	// 原始队列
	public static final String PROGRAMMATICALLY_QUEUE = "programmatically_queue";
	//交换器
	public static final String PROGRAMMATICALLY_EXCHANGE = "programmatically_exchange";
	//匹配规则
	public static final String PROGRAMMATICALLY_KEY="tan.#";

}