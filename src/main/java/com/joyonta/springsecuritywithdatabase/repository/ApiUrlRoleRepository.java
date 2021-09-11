package com.joyonta.springsecuritywithdatabase.repository;

import com.joyonta.springsecuritywithdatabase.model.ApiRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiUrlRoleRepository extends JpaRepository<ApiRole, Long> {
}
