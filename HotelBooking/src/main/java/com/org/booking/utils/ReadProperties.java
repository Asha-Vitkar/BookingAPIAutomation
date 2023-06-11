package com.org.booking.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadProperties {
	
	public static String getBaseUrl(String url) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.Properties");
		prop.load(fs);
		return  prop.getProperty(url);
	}
	public static HashMap<String,String> getCredentials(String username,String password) throws IOException
	{
		String userName;
		String passWord;
		Properties prop =new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.Properties");
		prop.load(fs);
		userName=prop.getProperty(username);
		passWord=prop.getProperty(password);
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("username", userName);
		hm.put("password", passWord);
		return hm;
	}
   
}
