package com.verizon.dispatch.route.util;

public class EmailTemplate {
	
	public static final String RECEIPIENT = "{receipient}";
	public static final String FIRST_NAME = "{firstname}";
	public static final String LAST_NAME = "{lastname}";
	public static final String MOBILE = "{mobile}";
	public static final String EMAIL = "{email}";
	public static final String ADDRESS = "{address}";
	public static final String USER_LIST = "{userlist}";
	
	public static final String SUB_CAR_POOL_DROPPED_PROVIDER = "Alert - one of your car pool user's dropped";
	
	public static final String SUB_CAR_POOL_ENROLLED_PROVIDER = "Alert - A new user enrolled in your car pool";
	
	public static final String SUB_CAR_POOL_DROPPED_USER = "Alert - You are dropped from your existing car pool";
	
	public static final String SUB_CAR_POOL_ENROLLED_USER = "Alert - You have enrolled in a new car pool";
	
	public static final String SUB_CAR_POOL_LOCATION_SET_PROVIDER = "Alert - Possible users who can enroll in your car pool";
	
	public static final String SUB_CAR_POOL_LOCATION_SET_USER = "Alert - Possible providers who can offer you car pool";

	public static final String TEXT_CAR_POOL_DROPPED_PROVIDER = 
			"Dear {receipient},\nThe following user is no longer part of your car pool.\n"+
			"{firstname} {lastname}|{mobile}|{email}|{address}\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";
	
	public static final String TEXT_CAR_POOL_ENROLLED_PROVIDER = 
			"Dear {receipient},\nThe following user have enrolled in your car pool.\n"+
			"{firstname} {lastname}|{mobile}|{email}|{address}\n\n You can contact the concerned.\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";
	
	public static final String TEXT_CAR_POOL_DROPPED_USER = 
			"Dear {receipient},\nYou are no longer part of the below provider's car pool.\n"+
			"{firstname} {lastname}|{mobile}|{email}|{address}\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";
	
	public static final String TEXT_CAR_POOL_ENROLLED_USER = 
			"Dear {receipient},\nYou are now enrolled in the below provider's car pool.\n"+
			"{firstname} {lastname}|{mobile}|{email}|{address}\n\n You can contact the concerned.\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";
	
	public static final String TEXT_CAR_POOL_LOCATION_SET_PROVIDER = 
			"Dear {receipient},\nFollowing users are located in your drive route.\n"+
			"{userlist}\n\n You can contact the concerned.\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";
	
	public static final String TEXT_CAR_POOL_LOCATION_SET_USER = 
			"Dear {receipient},\nFollowing providers have their drive route through your location.\n"+
			"{userlist}\n\n You can enrol in the application and contact the concerned.\n"+
			"Thanks\nVDSI Car Pooling Team.\n\n This is a system generated email. Please do not reply.";	
}