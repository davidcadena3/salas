package com.salas.springboot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salas.springboot.model.enums.ErolUsuario;

@Entity
@Table(name = "USUARIO")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotEmpty
	@Column(name = "NOMBRE", nullable = false, unique = true, length = 200)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROL")
	private ErolUsuario rol;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Reservation> reservas;

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

	public List<Reservation> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservation> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", rol=" + rol + ", reservas=" + reservas + "]";
	}

}
