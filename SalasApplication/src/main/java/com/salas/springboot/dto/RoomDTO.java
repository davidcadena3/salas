package com.salas.springboot.dto;

import java.io.Serializable;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer capacity;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoomDTO [id=" + id + ", capacity=" + capacity + ", name=" + name + "]";
	}

}
