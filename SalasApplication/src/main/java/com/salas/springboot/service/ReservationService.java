package com.salas.springboot.service;

import java.util.List;

import com.salas.springboot.model.Reservation;

public interface ReservationService {

	Reservation findById(Long id);

	void saveReservation(Reservation reserva);

	void updateReservation(Reservation reserva);

	void deleteReservationById(Long id);

	/**
	 * Todas las reservas de una sala por su id
	 * 
	 * @param id
	 * @return
	 */
	List<Reservation> findByRoomId(Long id);

	/**
	 * Todas las reservas
	 * 
	 * @return
	 */
	List<Reservation> findAllReservations();

	/**
	 * Se valida si la reservación existe
	 * 
	 * @param reservation
	 * @return
	 */
	boolean isReservaExist(Reservation reservation);

	/**
	 * Todos las reservaciones pendientes
	 * 
	 * @return
	 */
	List<Reservation> findAllPendingReservations();

	/**
	 * Reservas iguales
	 * 
	 * @param reservation
	 * @return
	 */
	List<Reservation> findCounterpart(Reservation reservation);

}
