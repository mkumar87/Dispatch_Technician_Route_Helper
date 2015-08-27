package com.verizon.dispatch.route.repositories;

import org.springframework.data.repository.CrudRepository;

import com.verizon.dispatch.route.domain.Album;

public interface AlbumRepository extends CrudRepository<Album, String> {
}
