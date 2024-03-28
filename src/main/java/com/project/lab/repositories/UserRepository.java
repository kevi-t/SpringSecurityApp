package com.project.lab.repositories;

import com.project.lab.models.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUsersByEmail(String email);
    @Modifying
    @Query("UPDATE Users a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}