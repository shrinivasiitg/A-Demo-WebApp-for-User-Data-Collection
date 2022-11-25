package com.demo.userdatacollection.dao.respository;

import com.demo.userdatacollection.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity searchByEmail(String email);
}
