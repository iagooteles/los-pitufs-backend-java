package com.los_pitufs.los_pitufs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.los_pitufs.los_pitufs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

} 
