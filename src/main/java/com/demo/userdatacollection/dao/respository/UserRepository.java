package com.demo.userdatacollection.dao.respository;

import com.demo.userdatacollection.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User searchByEmail(String email);
}
