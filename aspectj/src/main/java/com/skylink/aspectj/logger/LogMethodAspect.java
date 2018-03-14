package com.skylink.aspectj.logger;

import com.google.gson.Gson;
import com.skylink.android.commonlibrary.util.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author：guoq on 2018/3/14 10:52
 * @e-mail：guoq@myimpos.com
 * @describe:
 */
@Aspect
public class LogMethodAspect {

    private static final String TAG=LogMethodAspect.class.getSimpleName();

    private static final String POINTCUT_METHOD = "execution(@com.skylink.aspectj.logger.LogMethodTrace * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}


    @Around("methodAnnotatedWithDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        LogMethodTrace logMethodTrace = method.getAnnotation(LogMethodTrace.class);

        String[] paraNameArr=methodSignature.getParameterNames();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Object result = joinPoint.proceed();
        Object[] params=joinPoint.getArgs();

        Log(className,methodName,paraNameArr,params,result);

        return result;
    }


    /**
     * 打印日志信息
     * @param className
     * @param methodName
     * @param params
     * @param result
     */
    private void Log(String className,String methodName,String[] paraNameArr,Object[] params,Object result){

        StringBuilder paramStr=new StringBuilder();
        paramStr.append("Class Name:"+className+"\r\n");
        paramStr.append("Method Name:"+methodName+"\r\n");
        paramStr.append("Params:"+"\r\n");
        if(params!=null){
            for(int i=0;i<params.length;i++){
                paramStr.append(paraNameArr[i]+":"+new Gson().toJson(params[i])+"\r\n");
            }
        }

        paramStr.append("Result:"+new Gson().toJson(result));

        LogUtils.d(className+"."+methodName,paramStr);
    }
}
