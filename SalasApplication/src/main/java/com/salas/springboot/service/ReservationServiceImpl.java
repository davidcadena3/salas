package com.salas.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.model.Reservation;
import com.salas.springboot.model.enums.EreservationStatus;
import com.salas.springboot.repositories.ReservationRepository;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservaRepository;

	public Reservation findById(Long id) {
		return reservaRepository.findById(id);
	}

	@Override
	public void saveReservation(Reservation reserva) {
		reservaRepository.save(reserva);
	}

	@Override
	public void updateReservation(Reservation reserva) {
		saveReservation(reserva);
	}

	@Override
	public void deleteReservationById(Long id) {
		reservaRepository.delete(id);
	}

	@Override
	public List<Reservation> findAllReservations() {
		return reservaRepository.findAll();
	}

	@Override
	public List<Reservation> findByRoomId(Long id) {
		return reservaRepository.findByRoomId(id);
	}

	@Override
	public boolean isReservaExist(Reservation reservation) {
		// se valida la sala ya esta con reserva confirmada para la fecha
		boolean exist = reservaRepository.findByRoomAndStatusAndDate(reservation.getRoom(),
				EreservationStatus.CONFIRMADA, reservation.getDate()) != null;
		if (!exist) {
			// se valida que el mismo usuario no tenga una solicitud sobre la
			// sala
			exist = reservaRepository.findByRoomAndUserAndStatusAndDate(reservation.getRoom(), reservation.getUser(),
					EreservationStatus.SOLICITADA, reservation.getDate()) != null;
		}

		return exist;
	}

	public List<Reservation> findAllPendingReservations() {
		return reservaRepository.findByStatus(EreservationStatus.SOLICITADA);
	}

	@Override
	public List<Reservation> findCounterpart(Reservation reservation) {
		return reservaRepository.findByRoomAndStatusAndDate(reservation.getRoom(), EreservationStatus.SOLICITADA,
				reservation.getDate());
	}

}
