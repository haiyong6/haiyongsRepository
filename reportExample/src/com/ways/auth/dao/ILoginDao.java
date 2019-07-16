package com.ways.auth.dao;

import java.util.Map;

import com.ways.auth.model.MyUserDetails;

public interface ILoginDao {
	public MyUserDetails getUserInfo(Map<String,String> params) ;
}
