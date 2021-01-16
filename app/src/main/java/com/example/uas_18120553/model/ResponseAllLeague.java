package com.example.uas_18120553.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseAllLeague{

	@SerializedName("countrys")
	private List<CountrysItem> countrys;

	public List<CountrysItem> getCountrys(){
		return countrys;
	}
}