package com.techacademy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

}
