package com.salas.springboot.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salas.springboot.model.Reservation;
import com.salas.springboot.model.Room;
import com.salas.springboot.model.User;
import com.salas.springboot.model.enums.EreservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Reservation findById(Long id);

	List<Reservation> findByStatus(EreservationStatus status);

	List<Reservation> findByRoomId(Long id);

	List<Reservation> findByRoomAndStatusAndDate(Room room, EreservationStatus status, Date date);

	List<Reservation> findByDate(Date date);

	Reservation findByRoomAndUserAndStatusAndDate(Room room, User user, EreservationStatus solicitada, Date date);

}