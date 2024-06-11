package com.learn.test.test2repo.serviceImpl;

import java.awt.print.Printable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.test.test2repo.entity.db1.UserTest;
import com.learn.test.test2repo.repo.db1.UsersRepo;
import com.learn.test.test2repo.service.db1.UserService;

@Service
public class UserserviceImpl implements UserService {

	@Autowired
	UsersRepo repo;

	public UserTest getUser(String id) {
		Optional<UserTest> byId = repo.findById(id);
		System.out.println(byId);

		return null;

	}

}
