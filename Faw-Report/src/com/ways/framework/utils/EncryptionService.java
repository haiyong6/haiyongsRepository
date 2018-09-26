/***********************************************************************************************
 * 
 * Class name: EncryptionService.java	
 * Copyright 2009 Ways Company in GuangZhou. All rights reserved.
 * author: Wilkins Tang
 * Date: Dec 30, 2009
 * @version 1.0
 * 
 ***********************************************************************************************/
package com.ways.framework.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public final class EncryptionService 
{
	private static EncryptionService instance;

	private EncryptionService() 
	{
		
	}

	public String encrypt(String password) throws Exception 
	{
		MessageDigest md = null;
		try 
		{
			//step 2: get instance for MD5
			md = MessageDigest.getInstance("MD5"); 
		} catch (NoSuchAlgorithmException e)
		{
			throw new Exception(e.getMessage());
		}
		try 
		{
			//step 3: convert the password into bytes
			md.update(password.getBytes("UTF-8")); 
		} catch (UnsupportedEncodingException e) 
		{
			throw new Exception(e.getMessage());
		}

		//step 4
		byte raw[] = md.digest(); 
		
		//step 5: encrypt password
		String hash = (new BASE64Encoder()).encode(raw);
		
		return hash; 
	}

	/**
	 * get a instance for <code>EncryptionService</code>
	 * @return EncryptionService
	 */
	public static EncryptionService getInstance() 
	{
		if (instance == null)
		{
			instance = new EncryptionService();
		}
		return instance;
	}
	
	public static void main(String[] args)
	{
		EncryptionService es = EncryptionService.getInstance();
		String cryption = null;
		try
		{
			cryption = es.encrypt("ways123");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(cryption);
	}
}
