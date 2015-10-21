package com.pan.bbf.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.pan.bbf.common.Exception.BffException;

public class JSONUtil {
	public static GenericObjectPool<ObjectMapper> pool;

    static {
        initObjectMapperPool();
    }

    public static void initObjectMapperPool() {
    	ObjectMapperFactory factory = new ObjectMapperFactory();
    	
    	pool = new GenericObjectPool<ObjectMapper>(factory);
        //能从池中借出的对象的最大数目
        pool.setMaxTotal(5000);
        //池中闲置链接最大数
        pool.setMaxIdle(50);
        //调用borrowObject方法时最大等待时间
        pool.setMaxWaitMillis(60000);
        //调用borrowObject方法时是否进行有效性检查
        pool.setTestOnBorrow(false);
        //调用returnObject时是否进行有效性检查
        pool.setTestOnReturn(true);
        //在进行后台对象清理时，是否还对没有过期的池内对象进行有效性检查。不能通过有效性检查的对象也将被回收
        pool.setTestWhileIdle(true);
        //在进行后台对象清理时，视休眠时间超过多少毫秒的对象为过期
        pool.setMinEvictableIdleTimeMillis(1800000);
        //进行后台对象清理的时间间隔
        pool.setTimeBetweenEvictionRunsMillis(60000);
    }

    /**
     * 获取ObjectMapper对象
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new BffException("获取mapper出错", e);
        }
    }
    
    /**
     * 归还ObjectMapper对象
     * @param objectMapper
     */
    public static void returnObjectMapper(ObjectMapper objectMapper){
    	if(objectMapper != null){
    		pool.returnObject(objectMapper);
    	}
    }

    /**
     *
     * 把json字符串转换成map
     * @category
     * @author: huangfupan
     * @since: 2014-9-3
     * @param json
     * @return
     */
    public static Map<String, Object> transJsonStringToMap(String jsonValue, Map<String, Object> map) {
        if (map == null)
            map = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(jsonValue)) {
                return map;
            }
            JsonNode origNode = getObjectMapper().readTree(jsonValue);
            if (origNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) origNode;
                JsonNode rootNode = arrayNode.get(0);
                convertJsonNodeToMap(rootNode, map);
            } else {
                convertJsonNodeToMap(origNode, map);
            }

            return map;
        } catch (Exception e) {
            throw new BffException("json转换错误,json数据：" + jsonValue, e);
        }
    }

    /**
     *
     * 把json对象转换成map
     * @category
     * @author: huangfupan
     * @since: 2014-9-17
     * @param rootNode
     * @param map
     * @return
     */
    public static Map<String, Object> convertJsonNodeToMap(JsonNode rootNode, Map<String, Object> map) {
        if (map == null)
            map = new HashMap<String, Object>();
        for (Iterator<Entry<String, JsonNode>> iter = rootNode.fields(); iter.hasNext();) {
            Entry<String, JsonNode> entry = iter.next();
            String key = entry.getKey();
            JsonNode node = entry.getValue();
            map.put(key, node.textValue());
        }
        return map;
    }

    /**
     *
     * 把json字符串转换成map
     * @category
     * @author: huangfupan
     * @since: 2014-9-17
     * @param jsonValue
     * @return
     */
    public static Map<String, Object> transJsonStringToMap(String jsonValue) {
        return transJsonStringToMap(jsonValue, null);
    }
}
