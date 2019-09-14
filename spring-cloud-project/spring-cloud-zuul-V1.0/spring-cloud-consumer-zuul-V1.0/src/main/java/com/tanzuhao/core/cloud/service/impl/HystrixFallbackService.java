package com.tanzuhao.core.cloud.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.HystrixService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;

/**
 * 回调失败处理 实现的方法是服务调用的降级方法
 * 
 * @author tanzuhao
 *
 */
@Service
public class HystrixFallbackService implements HystrixService {
	private static final Logger log = LoggerFactory.getLogger(HystrixFallbackService.class);

	@Override
	public Result getHystrixMsg(String id, String name) {
		log.info(">>>>>>>>>>>>>>>>>>> fall back message id:" + id + ",name:" + name);
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}
}