package com.verizon.dispatch.route.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRoute {  
	
  @Id
  private String id;
  private String userId;
  @GeoSpatialIndexed	
  private double[] location;
  
  

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public double[] getLocation() {
	return location;
}
public void setLocation(double[] location) {
	this.location = location;
}  
  
 }
