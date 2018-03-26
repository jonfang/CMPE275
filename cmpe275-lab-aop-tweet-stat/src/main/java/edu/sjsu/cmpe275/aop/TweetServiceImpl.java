package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
    	System.out.printf("User %s tweeted message: %s\n", user, message);
    	if(user.equals("throw")){
    		throw new IOException();
		}
    }

    public void follow(String follower, String followee) throws IOException {
       	System.out.printf("User %s followed user %s \n", follower, followee);
		if(follower.equals("throw")){
			throw new IOException();
		}
    }

	public void block(String user, String follower) throws IOException {
       	System.out.printf("User %s blocked user %s \n", user, follower);
		if(user.equals("throw")){
			throw new IOException();
		}
    }

}
