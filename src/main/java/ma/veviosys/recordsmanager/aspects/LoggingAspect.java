package ma.veviosys.recordsmanager.aspects;

import lombok.extern.slf4j.Slf4j;
import ma.veviosys.recordsmanager.entities.AppUser;
import ma.veviosys.recordsmanager.services.AccountServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("logs.txt"));
        logger.setUseParentHandlers(false);
    }

//    long t1,t2;
//    @Pointcut("execution(* ma.veviosys.recordsmanager.services..*(..))")
//    public void pc1(){}
//
//    @Before("pc1()")
//    public void avant(JoinPoint joinPoint){
//        t1 = System.currentTimeMillis();
//        logger.info("------------------------------------------------");
//        logger.info("Avant l'exécution de la methode " + joinPoint.getSignature());
//    }
//    @After("pc1()")
//    public AppUser apres(String username, ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
//
////        Arrays.stream(joinPoint.getArgs()).findFirst().orElse(Optional.empty());
////        return joinPoint;
//        logger.info("Après l'exécution de la methode (ismail) " + joinPoint.getSignature());
//        t2 = System.currentTimeMillis();
//        logger.info("La duréé de methode est (ismail) : "+(t2-t1)+"ms");
//        logger.info("------------------------------------------------");
//        AccountServiceImpl accountService = (AccountServiceImpl) joinPoint.getTarget();
//        AppUser user = accountService.loadUserByUsername(username);
//        if(user.getUsername() == "user") throw new RuntimeException("admin makhdamch");
//        return (AppUser) proceedingJoinPoint.proceed();
//
//
//    }

//    Logger log = Logger.getLogger(LoggingAspect.class.getName());
//    @Around(value = "execution(* ma.veviosys.recordsmanager.services..*(..))")
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


//    @Pointcut("execution(* ma.veviosys.recordsmanager.services..*(..))")
//    public void pc1(){}
//    @Around("pc1()")
////    @Around("pc1() && args(joinPoint)")
//    public Object autour(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long t1 = System.currentTimeMillis();
//        logger.info("------------------------------------------------");
//        logger.info("Avant l'exécution de la methode " + proceedingJoinPoint.getSignature());
//        Object result = proceedingJoinPoint.proceed();
//        logger.info("Après l'exécution de la methode " + proceedingJoinPoint.getSignature());
//        long t2 = System.currentTimeMillis();
//        logger.info("La duréé de methode est : "+(t2-t1)+"ms");
//        logger.info("------------------------------------------------");
//        return result;
//    }

    @Pointcut("execution(* ma.veviosys.recordsmanager.services.AccountServiceImpl.loadByUserName(..))")
    public void pc1(){}
//    @Around("pc1()")
    @Around("pc1()")
    public Object autour(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        AccountServiceImpl accountService = (AccountServiceImpl) proceedingJoinPoint.getTarget();
//        AppUser user = accountService.loadUserByUsername(name);
        Object response = proceedingJoinPoint.proceed();
//        if(user.getUsername() == "user") throw new RuntimeException("admin makhdamch");
        return response;



    }
}
