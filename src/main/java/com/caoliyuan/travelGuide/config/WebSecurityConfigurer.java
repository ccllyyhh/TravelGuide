package com.caoliyuan.travelGuide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebSecurityConfigurer extends WebMvcConfigurerAdapter {
		/**
		 * 登录session key
		 */
		public final static String SESSION_KEY = "userid";

		@Bean
		public SecurityInterceptor getSecurityInterceptor() {
				return new SecurityInterceptor();
		}

		public void addInterceptors(InterceptorRegistry registry) {
				InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

				// 拦截配置
				addInterceptor.addPathPatterns("/user/update");
				//addInterceptor.addPathPatterns("/comment");
		}

		private class SecurityInterceptor extends HandlerInterceptorAdapter {

				@Override
				public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
						throws Exception {
						HttpSession session = request.getSession();
						if (session.getAttribute(SESSION_KEY) != null)
								return true;

						// 跳转登录
//						String url = "/login";
//						response.sendRedirect(url);
						response.sendError(403,"login please");
						return false;
				}
		}
}
