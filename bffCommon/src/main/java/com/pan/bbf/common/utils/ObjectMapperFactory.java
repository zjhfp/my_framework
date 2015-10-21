package com.pan.bbf.common.utils;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory extends BasePooledObjectFactory<ObjectMapper>{

	@Override
	public ObjectMapper create() throws Exception {
		return new ObjectMapper();
	}

	@Override
	public PooledObject<ObjectMapper> wrap(ObjectMapper obj) {
		return new DefaultPooledObject<ObjectMapper>(obj);
	}

}
