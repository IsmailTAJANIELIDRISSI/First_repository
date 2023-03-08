package com.authe.authe.aspects;

import org.aopalliance.intercept.Joinpoint;
import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//    long t1,t2;
//    @Pointcut("execution(*services.AccountServiceImpl.*(..))")
//    public void pc1(){}
//
//    @Before("pc1()")
//    public void avant(JoinPoint joinPoint){
//        t1 = System.currentTimeMillis();
//        System.out.println("------------------------------------------------");
//        System.out.println("Avant l'exécution de la methode " + joinPoint.getSignature());
//    }
//    @After("pc1()")
//    public void apres(JoinPoint joinPoint) {
//        System.out.println("Après l'exécution de la methode " + joinPoint.getSignature());
//        t2 = System.currentTimeMillis();
//        System.out.println("La duréé de methode est : ["+(t2-t1)+"]");
//        System.out.println("------------------------------------------------");
//
//
//    }

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    @Around(value = "execution(* services..*(..))")
    public void logTime(JoinPoint joinPoint){
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("KPI:");
        sb.append("[").append(joinPoint.getKind())
                .append("]\tfor: ")
                .append(joinPoint.getSignature());
//                .append("\twithArgs: ").append(StringUtils.join(joinPoint.getArgs(), ","))
//                .append(")");
        sb.append(("\ttook: "));
//        Object returnValue = joinPoint.();
        log.info(sb.append(System.currentTimeMillis() - startTime).append("ms").toString());

    }
}
