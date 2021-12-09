package com.cd.project.gateway.filter;

import com.cd.project.gateway.comm.Contant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 *@description  自定义GlobalFilter，GlobalFilter会作用于所有的路由上；
 *@author zjbing
 */
@Component
public class CustomRequestGlobalFilter implements GlobalFilter, Ordered {


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest().mutate()
				.headers(httpHeaders -> {httpHeaders.remove(Contant.FROM_FLAG);})
				.build();

		addOriginalRequestUrl(exchange, request.getURI());
		String rawPath = request.getURI().getRawPath();
		String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/"))
				.skip(1L).collect(Collectors.joining("/"));
		ServerHttpRequest newRequest = request.mutate()
				.path(newPath)
				.build();
		exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());
		//exchange.getRequest().getHeaders().set("server-module",getServerModule(rawPath));
		request.mutate().header("server-module",getServerModule(rawPath));
		return chain.filter(exchange.mutate()
				.request(newRequest.mutate()
						.build()).build());
	}

	/**
	 * 获取服务模块
	 * 从"/auth/oauth/token"中获取auth
	 * @param path
	 * @return
	 */
	protected String getServerModule(String path){
		if (org.apache.commons.lang3.StringUtils.isBlank(path)){
			return null;
		}
		try{
			return path.split("/")[1];
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getOrder() {
		return -1000;
	}
}
