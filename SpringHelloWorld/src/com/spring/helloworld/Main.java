package com.spring.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        GreeterImp helloWorld = (GreeterImp) context.getBean("greeter");
        System.out.println(helloWorld.getGreeting());
    }
}