package com.goals.repository;

import com.goals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>
{
    <Optional>User findByEmail(String email);
}
