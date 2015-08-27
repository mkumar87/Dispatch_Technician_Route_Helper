package com.verizon.dispatch.route.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.dispatch.route.domain.Album;
import com.verizon.dispatch.route.repositories.AlbumRepository;

@Repository
public interface JpaAlbumRepository extends JpaRepository<Album, String>, AlbumRepository {
}
