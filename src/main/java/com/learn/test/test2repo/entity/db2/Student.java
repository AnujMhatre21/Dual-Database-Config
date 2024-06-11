package com.learn.test.test2repo.entity.db2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	public String name;
}
