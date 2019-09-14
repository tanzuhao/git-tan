package com.tanzuhao.core.mq.util;
/**
 * 
 * @author tanzuhao
 * @date: 2019年9月10日 下午3:34:55
 */
public class RabbitMQConstant {
	// 1.direct类型
	public static final String DIRECT_QUEUE = "tan.amq.direct";
	// 2.topic类型
	public static final String TOPIC_EXCHANGE = "tan.amq.topic";//交换器名
	public static final String TOPIC_QUEUE = "tan.amq.topic";//队列名
	public static final String TOPIC_ROUTE_KEY = "tan.#";// topic类型匹配规则1
	// 3.Fanout类型
	public static final String FANOUT_QUEUE = "tan.amq.fanout";
	public static final String FANOUT_EXCHANGE = "tan.amq.fanout";
	//需要ack(确认)的topic类型
	public static final String TOPIC_ACK_QUEUE = "tan.ack.amq.topic";//队列名
	public static final String TOPIC_ACK_ROUTE_KEY = "ack.#";// topic类型匹配规则
}