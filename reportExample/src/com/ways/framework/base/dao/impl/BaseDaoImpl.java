package com.ways.framework.base.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ways.framework.base.dao.IBaseDao;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDaoImpl extends SqlMapClientDaoSupport implements IBaseDao{
	//通过bean名称注入  
	//	@Autowired
		@Resource(name="sqlMapClient")
	    private SqlMapClient sqlMapClient;  
		
		//完成sqlMapClient初始化工作  
	    @PostConstruct  
	    public void initSqlMapClient(){  
	        super.setSqlMapClient(sqlMapClient);  
	    } 
}
