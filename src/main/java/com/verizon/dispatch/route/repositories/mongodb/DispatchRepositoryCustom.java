package com.verizon.dispatch.route.repositories.mongodb;

import java.util.List;
import java.util.Set;

import com.verizon.dispatch.route.domain.User;

public interface DispatchRepositoryCustom {

	public List<String> getProvidersList();
	
	public List<User> getAvUsersList(Set<String> avUserSet);
	
}
