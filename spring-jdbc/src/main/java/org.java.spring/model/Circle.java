package org.java.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity // done to convert simple model class to hibernate table (no need to manual crete table in DB as in case of jdbcTemplate anymore after hibernate entity annotation)
public class Circle {
	@Id //done to convert simple model field to hibernate primary column
	private int id;
	private String name;

	public Circle(int id, String name){
		setId(id);
		setName(name);
	}

	public Circle() { // By default empty constructor is not necessary to declare but if we want to create constructor with param then it is must
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

