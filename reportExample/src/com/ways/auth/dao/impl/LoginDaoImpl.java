package com.ways.auth.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ways.auth.dao.ILoginDao;
import com.ways.auth.model.MyUserDetails;
import com.ways.framework.base.dao.impl.BaseDaoImpl;
@Repository("loginDaoImpl")
public class LoginDaoImpl extends BaseDaoImpl implements ILoginDao {

	@Override
	public MyUserDetails getUserInfo(Map<String, String> params) {
		MyUserDetails myUserDtails = null;
		try {
			@SuppressWarnings("unchecked")
			List<MyUserDetails> list = getSqlMapClient().queryForList("login.getUserInfo", params);
			if (list != null && list.size() > 0) {
				myUserDtails = list.get(0);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myUserDtails;
	}

}
