package com.verizon.dispatch.route.repositories.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.verizon.dispatch.route.domain.User;

public class DispatchRepositoryImpl  implements DispatchRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<String> getProvidersList(){
		
		List<User> users = mongoTemplate.find(Query.query(Criteria.where("poolMode").is("P").and("availableCount").gt(0).and("isEnrolled").is("Y")), User.class);
		
		List<String> userList = new ArrayList<String>();
		if(users!=null && users.size()>0)
		for(User u:users)
			userList.add(u.getId());
		
		return userList;
		
	}
	
	public List<User> getAvUsersList(Set<String> avUserSet){
		
		List<User> users = mongoTemplate.find(Query.query(Criteria.where("Id").in(avUserSet)), User.class);		
		return users;
		
	}
}
