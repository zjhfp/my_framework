package com.pan.bbf.common;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * @description 解决Date类型返回json格式为自定义格式 
 */  
public class CustomObjectMapper extends ObjectMapper{
	
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		this.setDateFormat(sdf);
    }
}