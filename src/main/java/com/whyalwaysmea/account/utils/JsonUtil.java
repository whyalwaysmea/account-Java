package com.whyalwaysmea.account.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/5 22:03
 * @Description:
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }
    }

    public static <T> T string2Obj(String src, Class<T> objClass) {
        if (src == null || objClass == null) {
            return null;
        }
        try {
            return (T) (objClass.equals(String.class) ? src : objectMapper.readValue(src, objClass));
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, objClass.getClass(), e);
            return null;
        }
    }

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
