package com.skylink.aspectj.methodmonitoring;


import com.skylink.android.commonlibrary.util.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author：guoq on 2018/3/14 19:02
 * @e-mail：guoq@myimpos.com
 * @describe:
 */
@Aspect
public class MethodMonitoringAspect {

    private String TAG = MethodMonitoringAspect.class.getSimpleName();

    private static Object currentObject = null;

    private static final String POINTCUT_METHOD = "(execution(* *..Activity+.*(..)) ||execution(* *..Layout+.*(..))) && target(Object) && this(Object)";

    private static final String POINTCUT_METHOD_MAINACTIVITY = "execution(* *..Activity+.onCreate(..))";



    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotated(){}

    @Pointcut(POINTCUT_METHOD_MAINACTIVITY)
    public void methodCallAnnotated(){}

    @Around("methodAnnotated() || methodCallAnnotated()")
    public Object aronudWeaverPoint(ProceedingJoinPoint joinPoint) throws Throwable {


        if (currentObject == null){
            currentObject = joinPoint.getTarget();
        }
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getName();

        String methodName = methodSignature.getName();

        StringBuilder msg =  new StringBuilder();
        msg.append("ClassName:"+className+"\r\n");
        msg.append("MethodName:"+methodName+"--");
        msg.append("execution time:"+stopWatch.getTotalTimeMillis()+"ms");

        LogUtils.d(className+"."+methodName,msg);

        return  result;
    }



}
