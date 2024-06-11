package com.learn.test.test2repo.repo.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.test.test2repo.entity.db1.UserTest;

@Repository
public interface UsersRepo extends JpaRepository<UserTest, String>{

}
