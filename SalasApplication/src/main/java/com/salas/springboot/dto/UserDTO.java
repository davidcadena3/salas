package com.salas.springboot.dto;

import java.io.Serializable;

import com.salas.springboot.model.enums.ErolUsuario;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private ErolUsuario rol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ErolUsuario getRol() {
		return rol;
	}

	public void setRol(ErolUsuario rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", rol=" + rol + "]";
	}

}
