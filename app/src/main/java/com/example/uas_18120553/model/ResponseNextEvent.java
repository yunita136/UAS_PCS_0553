package com.example.uas_18120553.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNextEvent{

	@SerializedName("events")
	private List<EventsItem> event;
private void setEvents(List<EventsItem>Event){
	this.event=event;
}
	public List<EventsItem> getEvents(){

	return event;
	}
}