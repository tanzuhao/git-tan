package com.tanzuhao.core.cloud.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.ZuulService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;
/**
 * 实现的方法是服务调用的降级方法
 * @author tanzuhao
 *
 */
@Service
public class ZuulFallbackService implements ZuulService {
	private static final Logger log = LoggerFactory.getLogger(ZuulFallbackService.class);

	@Override
	public Result getZuulOrderMsg(String id, String name) {
		log.info(">>>>>>>>>>>>>>>>>>> fall back message from order server,id:" + id + ",name:" + name);
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

	@Override
	public Result getZuulProducerMsg(String id, String name) {
		log.info(">>>>>>>>>>>>>>>>>>>fall back message from producer server, id:" + id + ",name:" + name);
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

	@Override
	public Result findByToken(String token) {
		log.info(">>>>>>>>>>>>>>>>>>>fall back message from order server token:" + token);
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

	@Override
	public Result get() {
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

}
