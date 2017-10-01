package com.salas.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SALA")
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CAPACIDAD", nullable = false)
	private Integer capacity;

	@NotEmpty
	@Column(name = "NOMBRE", nullable = false, unique = true, length = 200)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "room")
	private List<Reservation> reservas;

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

	public List<Reservation> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservation> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", capacity=" + capacity + ", name=" + name + ", reservas=" + reservas + "]";
	}

}
