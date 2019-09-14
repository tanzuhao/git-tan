package com.tanzuhao.core.cloud.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.cloud.service.FeignService;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.system.dto.User;
import com.tanzuhao.core.util.Results;

/**
 * 实现的方法是服务调用的降级方法
 * 
 * @author tanzuhao
 *
 */
@Service
public class FeignFallbackService implements FeignService {

	@Override
	public Result getMsgFeignCloud(String id, String name) {
		// TODO Auto-generated method stub
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

	@Override
	public Result getMsgFeignCloud(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

	@Override
	public Result getMsgFeignCloud(User user) {
		// TODO Auto-generated method stub
		return Results.successWithData(BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
	}

}
