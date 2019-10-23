package com.ways.app.common.utils;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
/**
 * 将ContextPath写入application中，给静态文件引用时用、及URL链接地址用
 */
@Component
public class ApplicationContext implements ServletContextAware {

	@Override
	public void setServletContext(ServletContext servletContext) {
		String ctx = servletContext.getContextPath();
        System.out.println("ctx=" + ctx);
        servletContext.setAttribute("ctx", ctx);
		
	}

}
