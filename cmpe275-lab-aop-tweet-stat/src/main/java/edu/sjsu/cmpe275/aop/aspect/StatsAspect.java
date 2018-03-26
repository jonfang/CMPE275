package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

import java.io.IOException;

@Aspect
@Order(0)
public class StatsAspect {

    /*
    *record most productive user and longest tweet length and handle exception
    * param joinPoint
    */
	@Autowired TweetStatsImpl stats;
    @Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
    public void aroundTweetAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        try{
                joinPoint.proceed();
                Object[] args = joinPoint.getArgs();
                if(args.length>=2){
                    String user = args[0].toString();
                    int currentTweetLen = args[1].toString().length();
                    stats.recordProductive(user, currentTweetLen);
                    stats.recordTweetLength(currentTweetLen);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /*
     *record most followed user and handle exception
     * param joinPoint
     */
    @Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
    public void aroundFollowAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
       try {
           joinPoint.proceed();
           Object[] args = joinPoint.getArgs();
           if (args.length >= 2) {
               String follower = args[0].toString();
               String followee = args[1].toString();
               stats.recordFollowee(follower, followee);
           }
       }
       catch(IOException e){
           System.out.println(e.getClass().getName());
       }
    }

    /*
     *record most unpopular user and handle exception
     * param joinPoint
     */
    @Around("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..))")
    public void aroundBlockAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            joinPoint.proceed();
            Object[] args = joinPoint.getArgs();
            if (args.length >= 2) {
                String user = args[0].toString();
                String follower = args[1].toString();
                stats.recordBlocked(user, follower);
            }
        }
        catch(IOException e){
            System.out.println(e.getClass().getName());
        }
    }
}
