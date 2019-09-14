package com.tanzuhao.core.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tanzuhao.core.base.Result;
import com.tanzuhao.core.constants.BaseEnums;
import com.tanzuhao.core.util.Results;

/**
 * 登录过滤器 记得类上加Component注解
 */
@Component
public class LoginFilter extends ZuulFilter {
	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

	/**
	 * 过滤器类型，前置过滤器 filterType : filter类型,分为pre、error、post、 route
	 * 
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：自定义过滤器的实现，
	 * 需要继承ZuulFilter，需要重写实现下面四个方法：
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
	 * 过滤器顺序，越小越先执行
	 */
	@Override
	public int filterOrder() {
		return 4;
	}

	/**
	 * 过滤器是否生效 返回true代表需要权限校验，false代表不需要用户校验即可访问
	 */
	@Override
	public boolean shouldFilter() {
		// 共享RequestContext，上下文对象
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		System.out.println(request.getRequestURI());
		// 需要权限校验URL
		if ("/apigateway/order/cloud/findZuulMsg".equalsIgnoreCase(request.getRequestURI())) {
			log.info(">>>>>>>>>>>>需要权限校验该URL:" + request.getRequestURI());
			return true;
		}
		return false;
	}

	/**
	 * 业务逻辑 只有上面返回true的时候，才会进入到该方法
	 * 
	 * 过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
	 * 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，
	 * 通过ctx.setResponseBody(body)对返回body内容进行编辑等。
	 */
	@Override
	public Object run() throws ZuulException {
		// JWT
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		// token对象,有可能在请求头传递过来，也有可能是通过参数传过来，实际开发一般都是请求头方式
		String token = request.getHeader("token");
		if (StringUtils.isBlank((token))) {
			token = request.getParameter("token");
		}
		log.info(">>>>>>>>>>>>>页面传来的token值为：" + token);
		// 登录校验逻辑 如果token为null，则直接返回客户端，而不进行下一步接口调用
		if (StringUtils.isBlank(token)) {
			// 过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			// 返回错误代码
			requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			// 响应中文乱码
			requestContext.getResponse().setCharacterEncoding("UTF-8");
			requestContext.getResponse().setContentType("text/html;cahrset=UTF-8");
			// 响应信息,此次应该有"token不能为空"响应到页面,但是实际显示降级信息??????
			Result ret = Results.successWithData("token不能为空！", BaseEnums.FAILURE.code(), BaseEnums.FAILURE.desc());
			Gson gson = new Gson();
			String retJson = gson.toJson(ret);
			requestContext.setResponseBody(retJson);

		}
		return null;
	}

}
