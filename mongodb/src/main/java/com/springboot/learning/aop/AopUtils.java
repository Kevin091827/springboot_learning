package com.springboot.learning.aop;

import com.springboot.learning.entity.LogInfo;
import com.springboot.learning.service.LogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AopUtils
 * @Description: TODO
 */
@Component
@Aspect
@Slf4j
public class AopUtils {

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 切入点表达式
     *      1.格式：方法修饰符 + 返回值类型 + 包名 + 类名 + 方法名 +方法参数
     */
    @Pointcut("execution(public * com.springboot.learning.controller.*.*(..))")
    public void log(){

    }

    //环绕通知
    @Around("log()")
    public Object arround(ProceedingJoinPoint joinPoint) {
        LogInfo logInfo = new LogInfo();
        try {
            log.info("前置通知");
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //请求方式
            String requestMethod = request.getMethod();
            //请求的ip
            String requestIp = request.getRemoteAddr();
            //请求的url
            String requestUrl = request.getRequestURI().toString();
            //请求的参数
            Map<String,String[]> parameters = request.getParameterMap();
            Object[] args = joinPoint.getArgs();
            logInfo.setArgs(args);
            //请求时间
            Date requestDate = new Date();
            //请求精确时间
            logInfo.setGmtCreate(requestDate);
            //请求时间：yyyy-mm-dd
            DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            //格式转换
            String requestTime = dateFormat.format(requestDate);
            logInfo.setRequestTime(requestTime);
            logInfo.setParameters(parameters);
            logInfo.setRequestIp(requestIp);
            logInfo.setRequestMethod(requestMethod);
            logInfo.setRequestUrl(requestUrl);

            //调用目标方法
            /**
             * Spring AOP的环绕通知和前置、后置通知有着很大的区别，主要有两个重要的区别：
             *      1）目标方法的调用由环绕通知决定，即你可以决定是否调用目标方法，
             *         而前置和后置通知是不能决定的，它们只是在方法的调用前后执行通知而已，
             *         即目标方法肯定是要执行的。joinPoint.proceed()就是执行目标方法的代码。
             *      2）环绕通知可以控制返回对象，即可以返回一个与目标对象完全不同的返回值。虽然这很危险，但是却可以做到。
             */
            Object result = joinPoint.proceed();
            log.info("后置通知");
            //目标方法返回值
            logInfo.setReturnValue(result);
            return result;
        } catch (Throwable e) {
            //异常信息
            String errorMsg = e.getMessage();
            logInfo.setErrorMsg(errorMsg);
            return null;
        }finally {
            //插入MongoDB
            logInfoService.insert(logInfo);
        }
    }




//
//    /**
//     * 前置通知
//     *      方法执行前通知
//     */
//    @Before("log()")
//    public void before(JoinPoint joinPoint) throws Exception {
//
//    }
//
//
//    /**
//     * 后置通知
//     *      方法执行后通知
//     */
//    @After("log()")
//    public void after(JoinPoint joinPoint){
//        log.info("后置通知");
//
//    }
//
//    /**
//     * 方法返回通知
//     *      接收方法返回值
//     * @param result
//     */
//    @AfterReturning(returning = "result",pointcut = "log()")
//    public void afterReturning(Object result){
//        //获取方法返回值
//        LogInfo logInfo  = new LogInfo();
//        logInfo.setReturnValue(result);
//    }
//
//    /**
//     * 后置异常通知
//     *      当方法抛出异常时调用
//     */
//    @AfterThrowing("log()")
//    public void afterThrowing(JoinPoint joinPoint){
//
//    }

}
