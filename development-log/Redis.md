# 关于Redis序列化  
当我们的数据存储到Redis的时候，我们的键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。   
RedisTemplate默认使用的是JdkSerializationRedisSerializer，StringRedisTemplate默认使用的是StringRedisSerializer。 

spring-data-redis的序列化类有下面这几个:  
- JdkSerializationRedisSerializer： 使用JDK提供的序列化功能。 优点是反序列化时不需要提供类型信息(class)，但缺点是**需要实现Serializable接口**，还有序列化后的结果非常庞大，是JSON格式的5倍左右，这样就会消耗redis服务器的大量内存。
- Jackson2JsonRedisSerializer： 使用Jackson库将对象序列化为JSON字符串。优点是速度快，序列化后的字符串短小精悍，不需要实现Serializable接口。  
- StringRedisSerializer: 一般如果key-value都是string的话，使用StringRedisSerializer就可以了


# 参考链接
[Redis 序列化方式StringRedisSerializer、FastJsonRedisSerializer和KryoRedisSerializer](https://blog.csdn.net/xiaolyuh123/article/details/78682200)


# @Cacheable不起作用  
当Redis和Shiro配合使用的时候，shiro 相关的bean 中依赖 业务 bean 都要使用@Lazy 

