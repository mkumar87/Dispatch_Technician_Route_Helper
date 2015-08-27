package com.verizon.dispatch.route.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
 public class User {
  
  @Id
  private String id;
  private String firstName;
  private String lastName; 
  private String phoneNumber;
  private String email;
  private int zipCode;
  private String addressDesc;
  @GeoSpatialIndexed
  private double[] location;  
  private Date startDate;  
  private String poolMode;  
  private String vehicleType;
  private int vehicleCapacity;
  private String isEnrolled;
  private int pickCount; 
  private String providerUserId;
  //private TechRoute[] points;
  private int availableCount;
    
    
public int getAvailableCount() {
	return availableCount;
}

public void setAvailableCount(int availableCount) {
	this.availableCount = availableCount;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public String getAddressDesc() {
	return addressDesc;
}

public void setAddressDesc(String addressDesc) {
	this.addressDesc = addressDesc;
}

/*public TechRoute[] getPoints() {
	return points;
}

public void setPoints(TechRoute[] points) {
	this.points = points;
}*/

public String getProviderUserId() {
	return providerUserId;
}

public void setProviderUserId(String providerUserId) {
	this.providerUserId = providerUserId;
}

public String getVehicleType() {
	return vehicleType;
}

public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}

public int getZipCode() {
	return zipCode;
}

public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}

public String getPoolMode() {
	return poolMode;
}

public void setPoolMode(String poolMode) {
	this.poolMode = poolMode;
}

public String getIsEnrolled() {
	return isEnrolled;
}

public void setIsEnrolled(String isEnrolled) {
	this.isEnrolled = isEnrolled;
}

public int getPickCount() {
	return pickCount;
}

public void setPickCount(int pickCount) {
	this.pickCount = pickCount;
}

public int getVehicleCapacity() {
	return vehicleCapacity;
}

public void setVehicleCapacity(int vehicleCapacity) {
	this.vehicleCapacity = vehicleCapacity;
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }  

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public double[] getLocation() {
	return location;
}

public void setLocation(double[] location) {
	this.location = location;
}
  
 }
