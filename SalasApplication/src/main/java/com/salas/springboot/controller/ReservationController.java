package com.salas.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.salas.springboot.model.Reservation;
import com.salas.springboot.model.enums.EreservationStatus;
import com.salas.springboot.service.ReservationService;
import com.salas.springboot.util.CustomErrorType;

@Controller
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	public static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService reservaService;

	/**
	 * Retornar todas las reservas
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> listAllReservations() {
		List<Reservation> reservations = reservaService.findAllReservations();
		if (reservations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}

	/**
	 * Retornar todas las reservas pendientes
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> listAllPendingReservations() {
		List<Reservation> reservations = reservaService.findAllPendingReservations();
		if (reservations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}

	/**
	 * Retornar las reservas de una sala
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/room/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoomReservationS(@PathVariable("roomId") long roomId) {
		logger.info("consultando las reservas de la sala: " + roomId);
		List<Reservation> reservations = reservaService.findByRoomId(roomId);
		if (reservations == null || reservations.isEmpty()) {
			logger.error("Sala: " + roomId + " sin reservas.");
			return new ResponseEntity(new CustomErrorType("Sala: " + roomId + " sin reservas."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}

	/**
	 * Retornar una reserva por su id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getReservation(@PathVariable("id") long id) {
		logger.info("Consultando la reserva con id: " + id);
		Reservation reservation = reservaService.findById(id);
		if (reservation == null) {
			logger.error("No se encontró reserva con id: " + id);
			return new ResponseEntity(new CustomErrorType("No se encontró reserva con id: " + id),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	/**
	 * Crear una reserva
	 * 
	 * @param reservation
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody Reservation reservation, UriComponentsBuilder ucBuilder) {
		logger.info("Reserva a crear: " + reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH");
		if (reservaService.isReservaExist(reservation)) {
			logger.error("Ya existe una reserva de la sala " + reservation.getRoom().getName() + " para la fecha "
					+ sdf.format(reservation.getDate()));
			return new ResponseEntity(new CustomErrorType("Ya existe una reserva de la sala "
					+ reservation.getRoom().getName() + " para la fecha " + sdf.format(reservation.getDate())),
					HttpStatus.CONFLICT);
		}
		reservation.setStatus(EreservationStatus.SOLICITADA);
		reservaService.saveReservation(reservation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/reservation/{id}").buildAndExpand(reservation.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Actualizar el estado de una reserva
	 * 
	 * @param id
	 * @param reservation
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
		logger.info("Actualizando la reserva con id: " + id);

		Reservation currentReservation = reservaService.findById(id);

		if (currentReservation == null) {
			logger.error("Reserva no encontrada id: " + id);
			return new ResponseEntity(new CustomErrorType("Reserva no encontrada"), HttpStatus.NOT_FOUND);
		}

		// se actualiza el estado de la reserva a CONFIRMADA
		currentReservation.setStatus(EreservationStatus.CONFIRMADA);
		reservaService.updateReservation(currentReservation);

		// se valida si existen reservas adicionales para la sala y fecha en
		// estado pendiente
		List<Reservation> counterPart = reservaService.findCounterpart(reservation);

		// de existir
		if (Objects.nonNull(counterPart) && !counterPart.isEmpty()) {
			// se eliminan
			for (Reservation aux : counterPart) {
				reservaService.deleteReservationById(aux.getId());
			}
		}

		return new ResponseEntity<Reservation>(currentReservation, HttpStatus.OK);
	}

	/**
	 * Eliminar una reserva
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteReservation(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Reserva with id {}", id);

		Reservation reservation = reservaService.findById(id);
		if (reservation == null) {
			logger.error("Unable to delete. Reserva with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Reserva with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		reservaService.deleteReservationById(id);
		return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
	}

}