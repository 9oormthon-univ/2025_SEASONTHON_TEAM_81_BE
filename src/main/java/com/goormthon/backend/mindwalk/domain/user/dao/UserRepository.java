package com.goormthon.backend.mindwalk.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
