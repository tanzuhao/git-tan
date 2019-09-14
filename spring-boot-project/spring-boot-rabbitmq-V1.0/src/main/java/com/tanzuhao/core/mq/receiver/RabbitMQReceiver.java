package com.tanzuhao.core.mq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.tanzuhao.core.mq.util.RabbitMQConstant;
import com.tanzuhao.core.system.dto.User;
/**
 * 消费者
 * @author tanzuhao
 *
 */
@Component
public class RabbitMQReceiver {
	public static final Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);
	/**
	 * Direct类型(点对点模式)
	 * 发送者与接收者的Queue名字(这里是direct_qname)一定要相同，否则接收收不到消息。
	 * @param user
	 */
    @RabbitListener(queues = RabbitMQConstant.DIRECT_QUEUE)
    public void receiverDirectQueue(User user) {
    	log.info(">>>>>>>>>>direct exchange consumer accept message:"+user.getUsername()+","+user.getUserId());
    }
    /**
     * Topic类型
     * @param user
     */
    //queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConstant.TOPIC_QUEUE1)
    public void receiveTopic1(User user) {
    	log.info(">>>>>>>>>>receiveTopic1监听到消息:" + user.getUsername()+","+user.getUserId());
    }
    @RabbitListener(queues = RabbitMQConstant.TOPIC_QUEUE2)
    public void receiveTopic2(User user) {
    	log.info(">>>>>>>>>>receiveTopic2监听到消息:" + user.getUsername()+","+user.getUserId());
    }
    //Fanout类型
    /**
     * queues是指要监听的队列的名字
     */
    @RabbitListener(queues = RabbitMQConstant.FANOUT_QUEUE1)
    public void receiveFanout1(User user) {
        log.info(">>>>>>>>>>>receiveFanout1监听到消息:" + user.getUsername()+","+user.getUserId());
    }
    @RabbitListener(queues = RabbitMQConstant.FANOUT_QUEUE2)
    public void receiveFanout2(User user) {
        log.info(">>>>>>>>>>>receiveFanout2监听到消息:" +user.getUsername()+","+user.getUserId());
    }
    /**
     * 多个消费者同时订阅同一个queue的消息
     * 测试结果：
     * 1.消费1号或消费2号可以随机订阅到10条消息（共20条消息）
     * 2.消费者2号会先处理完消息，并且空闲很久。消费者1号处理消息时间很长，一直在忙。
     * 3.提高效率策略：设置prefetch count,即限制queue每次发送给每个消费者的消费数。
     * @param user
     */
    @RabbitListener(queues = RabbitMQConstant.SAME_QUEUE)
    public void receiverSameQueue1(User user) {
    	//延迟10s查看订阅效果
    	new Thread(){
    		public void run(){
    			try {
					Thread.sleep(10000);
					log.info(">>>>>>>>>>消费者1号订阅队列名为：same.qname的消息:"+user.getUsername()+","+user.getUserId());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}.start();   	
    }
    @RabbitListener(queues = RabbitMQConstant.SAME_QUEUE)
    public void receiverSameQueue2(User user) {
    	log.info(">>>>>>>>>>消费者2号处理队列名为：same.qname的消息:"+user.getUsername()+","+user.getUserId());
    }
    /**
     * 设置prefetch count,即限制queue每次发送给每个消费者的消费数。
     * @param user
     */
    @RabbitListener(queues = RabbitMQConstant.PROGRAMMATICALLY_QUEUE)
    public void receiverPrefetchQueue1(User user) {
    	long userId=user.getUserId();
    	if(userId==0){
    	   	new Thread(){
        		public void run(){
        			try {
    					Thread.sleep(20000);
    					log.info(">>>>>>>>>>消费者1号订阅队列(programmatically_queue)消息:"+user.getUsername()+","+user.getUserId());
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
        		}
        	}.start();
    	}else{
    		log.info(">>>>>>>>>>####消费者1号订阅队列(programmatically_queue)消息:"+user.getUsername()+","+user.getUserId());   		   
    	}
      }
    @RabbitListener(queues = RabbitMQConstant.PROGRAMMATICALLY_QUEUE)
    public void receiverPrefetchQueue2(User user) {
    	log.info(">>>>>>>>>>消费者2号订阅队列(programmatically_queue)消息:"+user.getUsername()+","+user.getUserId());
    }
    @RabbitListener(queues = RabbitMQConstant.PROGRAMMATICALLY_QUEUE)
    public void receiverPrefetchQueue3(User user) {
    	log.info(">>>>>>>>>>消费者3号订阅队列(programmatically_queue)消息:"+user.getUsername()+","+user.getUserId());
    }

}