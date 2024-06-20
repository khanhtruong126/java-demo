package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("FROM user u join fetch u.userAddresses")
    List<User> getAllUserWithAddress();

    @Query("SELECT u FROM user u join fetch u.userAddresses join fetch u.roles WHERE u.id = ?1 ")
    Optional<User> findUserIdWithFullRelation(String userId);
}
