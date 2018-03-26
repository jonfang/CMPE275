package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

import java.io.IOException;

/*
 *Retry up to 3 times when failing a request
 * param joinPoint
 */

@Aspect
@Order(1)
public class RetryAspect {
	private static final int MAX_RETRIES = 3;

	@Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.*(..))")
	public void retryAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            joinPoint.proceed();
        } catch (IOException e) {
            System.out.println("Network Failure, retrying...");
            int retries = 0;
            while(true) {
                try{
                    retries++;
                    System.out.println("Retrying " + retries + " attempt");
                    joinPoint.proceed();
                }
                catch (IOException e_i){
                    if (retries == MAX_RETRIES) {
                        System.out.println("*********Network error*********");
                        throw e_i;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("*********Argument error*********");
            throw e;
        }
	}
}
