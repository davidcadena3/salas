package com.salas.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.salas.springboot.model.enums.EreservationStatus;

@Entity
@Table(name = "RESERVA")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CAPACIDAD_RESERVADA", nullable = false)
	private Integer reservedCapacity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIO", nullable = false)
	private Date date;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_RESERVA")
	private EreservationStatus status;

	@ManyToOne
	@JoinColumn(name = "SALA_ID", nullable = false)
	private Room room;

	@ManyToOne
	@JoinColumn(name = "USUARIO_ID", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getReservedCapacity() {
		return reservedCapacity;
	}

	public void setReservedCapacity(Integer reservedCapacity) {
		this.reservedCapacity = reservedCapacity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EreservationStatus getStatus() {
		return status;
	}

	public void setStatus(EreservationStatus status) {
		this.status = status;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", reservedCapacity=" + reservedCapacity + ", date=" + date + ", status=" + status
				+ ", room=" + room + ", user=" + user + "]";
	}

}
