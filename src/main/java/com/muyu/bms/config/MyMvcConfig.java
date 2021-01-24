package com.muyu.bms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//输入前边的urlPath可以访问后边的login，会自动补全.html，例如setViewName("login.html")
		registry.addViewController("/toLoginPage").setViewName("login");
		registry.addViewController("/login.html").setViewName("login");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/toUpdateBook.html").setViewName("book/toUpdateBook");
		registry.addViewController("/toBorrowBook.html").setViewName("borrow/toBorrowBook");
		registry.addViewController("/returnBook.html").setViewName("borrow/returnBook");
		registry.addViewController("/queryDocument.html").setViewName("queryDocument");
	}

	/**
	 * 添加了拦截器
	 * 以session里是否有用户信息为判断条件
	 * 放行了登录页面以及登录操作
	 * 还放行了各种静态资源
	 * @param registry
	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//			registry.addInterceptor(new LoginHandlerIntercepor()).addPathPatterns("/**")
//					.excludePathPatterns("/","/login.html","/toLoginPage","/checkLogin","/css/**"
//							,"/font-awesome/**","/images/**","/img/**","/js/**","/mybatis/**","/resources/**");
//	}
}
