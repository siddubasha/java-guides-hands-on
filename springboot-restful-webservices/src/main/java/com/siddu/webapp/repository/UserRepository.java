package com.siddu.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddu.webapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
