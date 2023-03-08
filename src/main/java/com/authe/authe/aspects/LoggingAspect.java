package com.authe.authe.aspects;

import org.aopalliance.intercept.Joinpoint;
import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.txt"));
        logger.setUseParentHandlers(false);
    }

    long t1,t2;
    @Pointcut("execution(* com.authe.authe.services..*(..))")
    public void pc1(){}

    @Before("pc1()")
    public void avant(JoinPoint joinPoint){
        t1 = System.currentTimeMillis();
        logger.info("------------------------------------------------");
        logger.info("Avant l'exécution de la methode " + joinPoint.getSignature());
    }
    @After("pc1()")
    public void apres(JoinPoint joinPoint) {

        Arrays.stream(joinPoint.getArgs()).findFirst().orElse(Optional.empty());
//        return joinPoint;
        logger.info("Après l'exécution de la methode " + joinPoint.getSignature());
        t2 = System.currentTimeMillis();
        logger.info("La duréé de methode est : "+(t2-t1)+"ms");
        logger.info("------------------------------------------------");


    }

//    Logger log = LoggerFactory.getLogger(LoggingAspect.class);
//    @Around(value = "execution(* com.authe.authe.services..*(..))")
//    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        StringBuilder sb = new StringBuilder("KPI:");
//        StringBuilder append = sb.append("[").append(joinPoint.getKind())
//                .append("]\tfor: ")
//                .append(joinPoint.getSignature());
////                .append("\twithArgs: ").append(StringUtils.join(joinPoint.getArgs(), ","))
////                .append(")");
//        sb.append(("\ttook: "));
//        Object returnValue = joinPoint.proceed();
//        log.info(append.append(System.currentTimeMillis() - startTime).append("ms").toString());
//        return returnValue;
//    }
}
