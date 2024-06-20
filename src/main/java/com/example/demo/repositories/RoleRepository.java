package com.example.demo.repositories;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query( "from role r where r.id in :ids" )
    List<Role> findByRoleByIds(@Param("ids") List<String> roleIds);
}
