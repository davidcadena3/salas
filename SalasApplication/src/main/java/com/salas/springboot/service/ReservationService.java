package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.dto.ReservationDTO;
import com.salas.springboot.model.Reservation;

public interface ReservationService {

	ReservationDTO findById(Long id);

	void saveReservation(ReservationDTO reserva);

	void updateReservation(ReservationDTO reserva);

	void deleteReservationById(Long id);

	/**
	 * Todas las reservas de una sala por su id
	 * 
	 * @param id
	 * @return
	 */
	List<ReservationDTO> findByRoomId(Long id);

	/**
	 * Todas las reservas
	 * 
	 * @return
	 */
	List<ReservationDTO> findAllReservations();

	/**
	 * Se valida si la reservación existe
	 * 
	 * @param reservation
	 * @return
	 */
	boolean isReservaExist(ReservationDTO reservation);

	/**
	 * Todos las reservaciones pendientes
	 * 
	 * @return
	 */
	List<ReservationDTO> findAllPendingReservations();

	/**
	 * Reservas iguales
	 * 
	 * @param reservation
	 * @return
	 */
	List<ReservationDTO> findCounterpart(ReservationDTO reservation);

}
