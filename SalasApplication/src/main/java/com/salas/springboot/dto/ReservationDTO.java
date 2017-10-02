package com.salas.springboot.dto;

import java.io.Serializable;
import java.util.Date;

import com.salas.springboot.model.enums.EreservationStatus;

public class ReservationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer reservedCapacity;

	private Date date;

	private EreservationStatus status;

	private RoomDTO room;

	private UserDTO user;

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

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ReservationDTO [id=" + id + ", reservedCapacity=" + reservedCapacity + ", date=" + date + ", status="
				+ status + ", room=" + room + ", user=" + user + "]";
	}

}
