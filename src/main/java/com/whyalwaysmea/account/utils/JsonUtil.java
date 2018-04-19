package com.whyalwaysmea.account.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/5 22:03
 * @Description: Json工具类
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        // 美化JSON输出
        // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * 将Json对象转换成string
     * @param src   需要转换的对象
     * @return  json字符串
     */
    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }
    }

    /**
     * 将json字符串转换为对象
     * @param src           json字符串
     * @param objClass      需要转换的对象class
     * @return              对象
     */
    public static <T> T string2Obj(String src, Class<T> objClass) {
        if (src == null || objClass == null) {
            return null;
        }
        try {
            return objectMapper.readValue(src, objClass);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, objClass.getName(), e);
            return null;
        }
    }

    /**
     * 将json字符串转换为对象 （多用于集合）
     * @param src               json字符串
     * @param typeReference     TypeReference包装的需要转换的对象，如：new TypeReference<List<User>>(){}
     * @return                  对象
     */
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
