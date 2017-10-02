package com.salas.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salas.springboot.dto.ReservationDTO;
import com.salas.springboot.model.Reservation;
import com.salas.springboot.model.enums.EreservationStatus;
import com.salas.springboot.repositories.ReservationRepository;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservaRepository;

	public ReservationDTO findById(Long id) {
		return convertToDTO(reservaRepository.findById(id));
	}

	public void saveReservation(ReservationDTO reserva) {
		reservaRepository.save(convertToEntity(reserva));
	}

	public void updateReservation(ReservationDTO reserva) {
		saveReservation(reserva);
	}

	public void deleteReservationById(Long id) {
		reservaRepository.delete(id);
	}

	public List<ReservationDTO> findAllReservations() {
		List<ReservationDTO> reservations = null;
		List<Reservation> entitys = reservaRepository.findAll();

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			reservations = new ArrayList<>();
			for (Reservation reservation : entitys) {
				reservations.add(convertToDTO(reservation));
			}
		}

		return reservations;
	}

	public List<ReservationDTO> findByRoomId(Long id) {
		List<ReservationDTO> reservations = null;
		List<Reservation> entitys = reservaRepository.findByRoomId(id);

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			reservations = new ArrayList<>();
			for (Reservation reservation : entitys) {
				reservations.add(convertToDTO(reservation));
			}
		}
		return reservations;
	}

	public boolean isReservaExist(ReservationDTO reservationDTO) {
		// se valida la sala ya esta con reserva confirmada para la fecha
		boolean exist = false;

		Reservation reservation = convertToEntity(reservationDTO);

		List<Reservation> prev = reservaRepository.findByRoomAndStatusAndDate(reservation.getRoom(),
				EreservationStatus.CONFIRMADA, reservation.getDate());

		exist = (prev != null && !prev.isEmpty());

		if (!exist) {
			// se valida que el mismo usuario no tenga una solicitud sobre la
			// sala
			exist = reservaRepository.findByRoomAndUserAndStatusAndDate(reservation.getRoom(), reservation.getUser(),
					EreservationStatus.SOLICITADA, reservation.getDate()) != null;
		}

		return exist;
	}

	public List<ReservationDTO> findAllPendingReservations() {
		List<ReservationDTO> reservations = null;
		List<Reservation> entitys = reservaRepository.findByStatus(EreservationStatus.SOLICITADA);

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			reservations = new ArrayList<>();
			for (Reservation reservation : entitys) {
				reservations.add(convertToDTO(reservation));
			}
		}
		return reservations;
	}

	public List<ReservationDTO> findCounterpart(ReservationDTO reservationDTO) {
		List<ReservationDTO> reservations = null;
		Reservation reservation = convertToEntity(reservationDTO);
		List<Reservation> entitys = reservaRepository.findByRoomAndStatusAndDate(reservation.getRoom(),
				EreservationStatus.SOLICITADA, reservation.getDate());

		if (Objects.nonNull(entitys) && !entitys.isEmpty()) {
			reservations = new ArrayList<>();
			for (Reservation aux : entitys) {
				reservations.add(convertToDTO(aux));
			}
		}
		return reservations;
	}

	public static ReservationDTO convertToDTO(Reservation reservation) {
		ReservationDTO dto = null;
		if (Objects.nonNull(reservation)) {
			dto = new ReservationDTO();
			dto.setDate(reservation.getDate());
			dto.setId(reservation.getId());
			dto.setReservedCapacity(reservation.getReservedCapacity());
			dto.setRoom(RoomServiceImpl.convertToDTO(reservation.getRoom()));
			dto.setStatus(reservation.getStatus());
			dto.setUser(UserServiceImpl.convertToDTO(reservation.getUser()));
		}
		return dto;
	}

	private Reservation convertToEntity(ReservationDTO reserva) {
		Reservation entity = null;
		if (Objects.nonNull(reserva)) {
			entity = new Reservation();
			entity.setDate(reserva.getDate());
			entity.setId(reserva.getId());
			entity.setReservedCapacity(reserva.getReservedCapacity());
			entity.setRoom(RoomServiceImpl.convertToEntity(reserva.getRoom()));
			entity.setStatus(reserva.getStatus());
			entity.setUser(UserServiceImpl.convertToEntity(reserva.getUser()));
		}
		return entity;
	}

}
