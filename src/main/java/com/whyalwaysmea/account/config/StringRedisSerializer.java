package com.whyalwaysmea.account.config;

import com.whyalwaysmea.account.utils.JsonUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/5 22:03
 * @Description:
 */
public class StringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    private final String target = "\"";

    private final String replacement = "";

    public StringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public StringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        String string = JsonUtil.obj2String(object);
        if (string == null) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }
}
