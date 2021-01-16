package com.example.uas_18120553.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailLeague {

	@SerializedName("leagues")
	private List<LeaguesItem> leagues;

	public List<LeaguesItem> getLeagues(){
		return leagues;
	}
}