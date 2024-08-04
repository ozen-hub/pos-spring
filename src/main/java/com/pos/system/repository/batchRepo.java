package com.pos.system.repository;

import com.pos.system.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface batchRepo  extends JpaRepository<Batch,String> {
}
