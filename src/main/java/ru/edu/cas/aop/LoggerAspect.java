package ru.edu.cas.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(* ru.edu.cas.clients_account.controller.ClientsAccountController.*(..))")
    public void clientRestControllerPointcut() {
    }

    @Before("clientRestControllerPointcut()")
    public void clientBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " started! " + LocalDateTime.now());
    }


    @AfterReturning("clientRestControllerPointcut()")
    public void clientAfterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " finished! " + LocalDateTime.now());
    }

    @AfterThrowing(value = "clientRestControllerPointcut()", throwing = "ex")
    public void clientAfterException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        String message = String.format("ERROR! Failed to method: %s args: %s error: %s", methodName, args, ex);
        System.out.println(message + " " + LocalDateTime.now());
    }

    @Pointcut("execution(* ru.edu.cas.user.controller.AdminController.*(..))")
    public void adminRestControllerPointcut() {
    }

    @Before("adminRestControllerPointcut()")
    public void adminBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " started! " + LocalDateTime.now());
    }


    @AfterReturning("adminRestControllerPointcut()")
    public void adminAfterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " finished! " + LocalDateTime.now());
    }

    @AfterThrowing(value = "adminRestControllerPointcut()", throwing = "ex")
    public void adminAfterException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        String message = String.format("ERROR! Failed to method: %s args: %s error: %s", methodName, args, ex);
        System.out.println(message + " " + LocalDateTime.now());
    }

    @Pointcut("execution(* ru.edu.cas.user.controller.UserController.*(..))")
    public void userRestControllerPointcut() {
    }

    @Before("userRestControllerPointcut()")
    public void userBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " started! " + LocalDateTime.now());
    }


    @AfterReturning("userRestControllerPointcut()")
    public void userAfterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method: " + methodName + " args: " + args + " finished! " + LocalDateTime.now());
    }

    @AfterThrowing(value = "userRestControllerPointcut()", throwing = "ex")
    public void userAfterException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        String message = String.format("ERROR! Failed to method: %s args: %s error: %s", methodName, args, ex);
        System.out.println(message + " " + LocalDateTime.now());
    }

    private List<String> getArgs(JoinPoint joinPoint) {
        List<String> args = new ArrayList<>();
        for (int i = 0; i < joinPoint.getArgs().length; ++i) {
            Object argValue = joinPoint.getArgs()[i];
            args.add("arg." + i + " = " + argValue);
        }
        return args;
    }
}
