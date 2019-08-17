package com.zhaohy.app.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnLineCountListener implements  HttpSessionListener {

	public int count=0;//记录session的数量
	@Override
	public void sessionCreated(HttpSessionEvent se) {//监听session的创建
		count++;
        se.getSession().getServletContext().setAttribute("Count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {//监听session的撤销
		count--;
        se.getSession().getServletContext().setAttribute("Count", count);
	}

}
