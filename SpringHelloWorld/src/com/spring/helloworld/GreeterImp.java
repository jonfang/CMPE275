package com.spring.helloworld;

public class GreeterImp implements Greeter {
    private String name;

    /**
     * Sets name of greeting bean
     * @param  name  bean name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Return greeting message
     * @return      greeting message
     */
    public String getGreeting(){
        if(name.toLowerCase().equals("alice")) {
            return "Hello world from Alice!";
        }
        else{
            return "Hello world!";
        }
    }
}
