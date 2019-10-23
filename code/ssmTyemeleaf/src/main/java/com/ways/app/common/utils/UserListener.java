package com.ways.app.common.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationListener;

public class UserListener implements HttpSessionListener {
    int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if(null != se.getSession().getServletContext().getAttribute("count")) {
            count = (int) se.getSession().getServletContext().getAttribute("count");
        }
        count++;
        se.getSession().getServletContext().setAttribute("count", count);
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if(null != se.getSession().getServletContext().getAttribute("count")) {
            count = (int) se.getSession().getServletContext().getAttribute("count");
        }
        count--;
        se.getSession().getServletContext().setAttribute("count", count);
    }

}
