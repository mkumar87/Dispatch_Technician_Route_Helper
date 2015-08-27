package com.verizon.dispatch.route.repositories.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.dispatch.route.domain.TechRoute;

@Repository
public interface TechRouteRepository extends MongoRepository<TechRoute, String> {	
	
	List<TechRoute> findByLocationNear(Point location, Distance distance);
	
	List<TechRoute> findByUserId(String userId);
	
}
