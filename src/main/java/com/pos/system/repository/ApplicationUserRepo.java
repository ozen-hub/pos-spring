package com.pos.system.repository;

import com.pos.system.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ApplicationUserRepo  extends JpaRepository<ApplicationUser,String> {
}
