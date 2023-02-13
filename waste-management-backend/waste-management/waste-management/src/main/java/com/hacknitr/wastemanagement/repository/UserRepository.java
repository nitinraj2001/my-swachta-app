package com.hacknitr.wastemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hacknitr.wastemanagement.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

    public User findByEmail(String email);
}
