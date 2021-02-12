package com.goals.repository;

import com.goals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
}
