package com.tanzuhao.core.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;

/**
 * 限流过滤类
 * @author admin
 *
 */
/**
 * 订单限流 其它和上面都一样，只是run()中逻辑不一样
 * 
 * 每一个请求进来先到桶里去拿令牌，拿到令牌的请求放行， 假设你设置了1000个令牌，如果拿完了，
 * 那么后面来调接口的请求就需要排队等有新的令牌才能调用该接口。
 */
@Component
public class OrderRateLimiterFilter extends ZuulFilter {
	private static final Logger log = LoggerFactory.getLogger(OrderRateLimiterFilter.class);
	// 每秒产生1000个令牌
	// private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);// 每秒不超过1个任务产生

	/*
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，
	 * 
	 * 具体如下：自定义过滤器的实现， 需要继承ZuulFilter，需要重写实现下面四个方法：
	 * 
	 * pre：可以在请求被路由之前调用
	 * 
	 * routing：在路由请求时候被调用
	 * 
	 * post：在routing和error过滤器之后被调用
	 * 
	 * error：处理请求时发生错误时被调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}
	/**
	 * filterOrder：通过int值来定义过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		return -4;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		// 只对订单接口限流
		if ("/apigateway/order/cloud/getZuulMsg".equalsIgnoreCase(request.getRequestURI())) {
			log.info(">>>>>>>>>>>>需要限流URL:" + request.getRequestURI());
			return true;
		}
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();

		// 就相当于每调用一次tryAcquire()方法，令牌数量减1，当1000个用完后，那么后面进来的用户无法访问上面接口
		// 当然这里只写类上面一个接口，可以这么写，实际可以在这里要加一层接口判断。
		if (!RATE_LIMITER.tryAcquire()) {
			log.info(">>>>>>>>>>>>>>>>>每秒不能超过1个任务产生");
			requestContext.setSendZuulResponse(false);
			// 设置页面响应代码
			requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
			// 响应中文乱码
			requestContext.getResponse().setCharacterEncoding("UTF-8");
			requestContext.getResponse().setContentType("text/html;cahrset=UTF-8");
			// 设置页面响应数据
			Result ret = Results.successWithData("抢购的人太多，你被挤出来了，稍后重试！", BaseEnums.FAILURE.code(),
					BaseEnums.FAILURE.desc());
			Gson gson = new Gson();
			String retJson = gson.toJson(ret);
			requestContext.setResponseBody(retJson);

		}
		return null;
	}
}
