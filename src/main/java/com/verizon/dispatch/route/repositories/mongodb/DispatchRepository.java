package com.verizon.dispatch.route.repositories.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.dispatch.route.domain.User;

@Repository
public interface DispatchRepository extends MongoRepository<User, String>,DispatchRepositoryCustom {	
	
	public List<User> findByProviderUserId(String providerUserId);
	
	public List<User> findByLocationNear(Point location, Distance distance);
	
}
