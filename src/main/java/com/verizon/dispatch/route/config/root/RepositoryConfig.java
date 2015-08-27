package com.verizon.dispatch.route.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.verizon.dispatch.route.config.data.LocalJpaRepositoryConfig;
import com.verizon.dispatch.route.repositories.AlbumRepository;

@Configuration
@ComponentScan(basePackageClasses = {AlbumRepository.class, LocalJpaRepositoryConfig.class})
public class RepositoryConfig {
}

