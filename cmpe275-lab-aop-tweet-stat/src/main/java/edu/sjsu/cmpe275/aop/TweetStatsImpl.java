package edu.sjsu.cmpe275.aop;

import java.util.HashSet;
import java.util.HashMap;

public class TweetStatsImpl implements TweetStats {
	private static int lengthOfLongestTweetAttempted = 0;
	private static HashMap<String, HashSet<String>> mostFollowed = new HashMap<String, HashSet<String>>();
    private static HashMap<String, HashSet<String>> mostBlocked = new HashMap<String, HashSet<String>>();
	private static HashMap<String, Integer> mostProductive = new HashMap<String, Integer>();

	public void resetStatsAndSystem() {
		lengthOfLongestTweetAttempted = 0;
		mostFollowed.clear();
        mostBlocked.clear();
        mostProductive.clear();
	}

	public void recordTweetLength(int currentTweetLen){
	    if(currentTweetLen>lengthOfLongestTweetAttempted) {
	        lengthOfLongestTweetAttempted = currentTweetLen;
	    }
    }

    public void recordFollowee(String follower, String followee){
	    if(!mostFollowed.containsKey(followee)){
	        mostFollowed.put(followee, new HashSet<>());
        }
	    mostFollowed.get(followee).add(follower);
    }

    public void recordBlocked(String user, String follower){
        if(!mostBlocked.containsKey(follower)){
            mostBlocked.put(follower, new HashSet<>());
        }
        mostBlocked.get(follower).add(user);
    }

    public void recordProductive(String user, int tweetLength){
	    if(!mostProductive.containsKey(user)){
	        mostProductive.put(user, 0);
        }
        mostProductive.put(user, mostProductive.get(user)+tweetLength);
    }

    public int getLengthOfLongestTweetAttempted() {
		return lengthOfLongestTweetAttempted;
	}

	public String getMostFollowedUser() {
        return getMost(mostFollowed);
	}

	public String getMostProductiveUser() {
        return getMostP(mostProductive);
	}

	public String getMostBlockedFollower() {
		return getMost(mostBlocked);
	}

	private String getMost(HashMap<String, HashSet<String>> map){
	    String most = null;
	    if(map.isEmpty()){
	        return most;
        }
        else{
	        for(String key:map.keySet()){
	            if(most == null){
	                most = key;
                }
                else{
	                if(map.get(key).size()>map.get(most).size()){
	                    most = key;
                    }
                    else if(map.get(key).size()==map.get(most).size() && key.compareTo(most)<0){
                        //pick the lexgraphically smaller one
                        most = key;
                    }
                }
            }
        }
        return most;
    }

    private String getMostP(HashMap<String, Integer> map){
	    String most = null;
	    if(map.isEmpty()){
	        return most;
        }
        else{
	        for(String key:map.keySet()){
	            if(most==null){
	                most = key;
                }
                else{
	                if(map.get(key)>map.get(most)){
	                    most = key;
                    }
                    else if(map.get(key)==map.get(most)&&key.compareTo(most)<0){
	                    most=key;
                    }
                }
            }
            return most;
        }
    }
}


