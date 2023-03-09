package ma.veviosys.recordsmanager.aspects;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class PatchAspect {
//    @Pointcut("execution(* service.AccountServiceImpl.addNewUser(..))")
//    public void pc1(){}
//
//    @Around("pc1() && args(username)")
//    public AppUser autour(String username, ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
//        AccountServiceImpl accountService = (AccountServiceImpl) joinPoint.getTarget();
//        AppUser user = accountService.loadUserByUsername(username);
//        if(user.getUsername() == "user") throw new RuntimeException("admin makhdamch");
//        return (AppUser) proceedingJoinPoint.proceed();
//
//    }




//    Logger logger = Logger.getLogger(LoggingAspect.class.getName());
//    public PatchAspect() throws IOException {
//        logger.addHandler(new FileHandler("logs.txt"));
//        logger.setUseParentHandlers(false);
//    }
//
//
//    @Pointcut(value = "execution(* ma.veviosys.recordsmanager.services..*(..))")
//    public void loggingPointCut(){
//
//    }

//    @Around("loggingPointCut()")
//    public Object LoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//
//        ObjectMapper mapper = new ObjectMapper();
//        String methodName = proceedingJoinPoint.getSignature().getName();
//        String className = proceedingJoinPoint.getTarget().getClass().toString();
//        Object array[] = proceedingJoinPoint.getArgs();
//        logger.info("Inside "+className+" class "+methodName+"method , with request: "+mapper.writeValueAsString(array));
//        Object response = proceedingJoinPoint.proceed();
//        logger.info("Inside "+className+" class "+methodName+"method , with request: "+mapper.writeValueAsString(array));
//        return response;
//    }
}
