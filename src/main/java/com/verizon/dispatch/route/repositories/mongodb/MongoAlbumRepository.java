package com.verizon.dispatch.route.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.dispatch.route.domain.Album;
import com.verizon.dispatch.route.repositories.AlbumRepository;

@Repository
public interface MongoAlbumRepository extends MongoRepository<Album, String>, AlbumRepository {
}