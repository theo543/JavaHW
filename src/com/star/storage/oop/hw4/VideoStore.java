package com.star.storage.oop.hw4;

import java.util.Hashtable;
import java.util.stream.Collectors;

public class VideoStore{
	private final Hashtable<String, Video> videos = new Hashtable<>();
	public void addVideo(String title){
		if(!videos.containsKey(title))
			videos.put(title, new Video(title));
	}
	public void checkOut(String title){
		if(videos.containsKey(title))
			videos.get(title).setCheckedOut(true);
	}
	public void returnVideo(String title){
		if(videos.containsKey(title))
			videos.get(title).setCheckedOut(false);
	}
	public void receiveRating(String title, int rating){
		if(videos.containsKey(title))
			videos.get(title).updateRating(rating);
	}
	public void listVideos(){
		System.out.print(videos.values().stream().map(Video::toString).collect(Collectors.joining("\n")));
	}
}
