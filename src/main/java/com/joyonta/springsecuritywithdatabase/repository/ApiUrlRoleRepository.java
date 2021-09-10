package com.joyonta.springsecuritywithdatabase.repository;

import com.joyonta.springsecuritywithdatabase.model.ApiUrlRole;
import com.joyonta.springsecuritywithdatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiUrlRoleRepository extends JpaRepository<ApiUrlRole, Long> {
}
