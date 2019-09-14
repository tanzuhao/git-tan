package com.tanzuhao.core.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.HelloCloudRibbonService;
/**
 * 
 * @author tanzuhao
 *
 */
@Service
public class HelloCloudRibbonServiceImpl implements HelloCloudRibbonService {
	@Autowired
	private RestTemplate restTemplate;
    //生产者应用名
	@Value("${eureka.producer.application.name}")
	private String producerApplicationName;
    /**
     * 需要拼接参数
     */
	@Override
	public Result getMsgCloud(String id,String name) {
		Result msgResult = restTemplate.getForObject("http://" + producerApplicationName + "/cloud/msg?id="+id+",name="+name, Result.class);
		return msgResult;
	}

}
