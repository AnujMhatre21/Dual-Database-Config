package com.learn.test.test2repo.entity.db1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTest {
	
	@Id
	public String name;
	public String surname;

}
