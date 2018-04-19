package com.whyalwaysmea.account.aspect;

import com.whyalwaysmea.account.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Long
 * @Date: Create in 15:15 2018/4/19
 * @Description:    请求参数与返回结果的打印
 */
@Aspect   //定义一个切面
@Configuration
@Slf4j
public class LogRecordAspect {

    /**
     * 定义切点Pointcut
     * 第一个*， 表明返回类型
     * 包名， 需要拦截的包名
     * *Controller  表明匹配指定包下 Controller结尾的类
     * *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(* com.whyalwaysmea.account.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String authorization = request.getHeader("Authorization");

        if (pjp.getArgs().length > 0) {
            for (Object o : pjp.getArgs()) {
                if (o instanceof HttpServletRequest || o instanceof HttpServletResponse
                        || o instanceof BeanPropertyBindingResult) {
                    continue;
                }
                log.info("请求参数 : " + JsonUtil.obj2String(o));
            }
        }

        log.info("请求开始, 各个参数, url: {}, method: {}, params: {},  authorization: {}", url, method,  queryString, authorization);

        // result的值就是被拦截方法的返回值
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        // 如果方法执行时间大于5s
        long executeTime = endTime - startTime;
        if(executeTime > 50000) {
            log.error("方法需要优化 url: {}, method: {},  params: {}, authorization: {},  方法执行了：{}", url, method,  queryString, authorization, executeTime);

            if (pjp.getArgs().length > 0) {
                for (Object o : pjp.getArgs()) {
                    if (o instanceof HttpServletRequest || o instanceof HttpServletResponse
                            || o instanceof BeanPropertyBindingResult) {
                        continue;
                    }
                    log.error("请求参数 : " + JsonUtil.obj2String(o));
                }
            }
        }

        log.info("请求结束，controller的返回值是：{}, 执行时间： {} ", JsonUtil.obj2String(result), executeTime );
        return result;
    }

}
