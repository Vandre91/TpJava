package fr.intech.s5.backend.hotel.intech.backendhotelintech.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Reservation;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.request.ReservationRequest;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ChambreResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ChambresResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ReservationResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.repository.ChambreRepository;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.repository.ReservationRepository;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.BackendConstantes;

@RestController
@RequestMapping(BackendConstantes.CHAMBRE_RESERVATION_V1)
public class ReservationResource {
	
	@Autowired
	private ChambreRepository chambreRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ConversionService conversionService;
		
	@RequestMapping(path ="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ChambresResponse> getChambres() {
		List<Chambre> chambres;
		
		chambres = (List<Chambre>) chambreRepository.findAll();		
		ChambresResponse chambresResponse = conversionService.convert(chambres, ChambresResponse.class);
		
		return new ResponseEntity<>(chambresResponse, HttpStatus.OK);
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservation (
		@RequestBody
		ReservationRequest reservationRequest
	) {
		Reservation reservation = conversionService.convert(reservationRequest, Reservation.class);	
		Chambre chambre = chambreRepository.findByChambreId(reservationRequest.getChambreId());
		
		reservation.setChambre(chambre);
		reservationRepository.save(reservation);
		
		chambre.addReservation(reservation);
		chambreRepository.save(chambre);		
		
		ReservationResponse reservationResponse = conversionService.convert(reservation, ReservationResponse.class);
		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> updateReservation (
		@RequestBody
		ReservationRequest reservationRequest
	) {
		Reservation reservation = conversionService.convert(reservationRequest, Reservation.class);
		reservation.setChambre(chambreRepository.findByChambreId(reservationRequest.getChambreId()));
		reservationRepository.save(reservation);
		
		ReservationResponse reservationResponse = conversionService.convert(reservation, ReservationResponse.class);
		return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(
		@PathVariable
		long reservationId
	) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		Chambre chambre = reservation.getChambre();
		
		chambre.getListeDesReservations().remove(reservation);
		chambreRepository.save(chambre);
		
		reservationRepository.delete(reservation);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(path = "/reservation/{reservationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> getReservationById(
		@PathVariable
		Long reservationId
	) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		ReservationResponse reservationResponse = conversionService.convert(reservation, ReservationResponse.class);
		
		return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{chambreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public 	ResponseEntity<ChambreResponse> getChambreById(
		@PathVariable
		Long chambreId
	) {
		Chambre chambre = chambreRepository.findByChambreId(chambreId);
		if(chambre == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		ChambreResponse chambreResponse = conversionService.convert(chambre, ChambreResponse.class);
		
		return new ResponseEntity<>(chambreResponse, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/reservee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ChambresResponse> getChambresReservee() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		for(Chambre chambre : chambreRepository.findAll()) {
			List<Reservation> reservations = chambre.getListeDesReservations();
			if(reservations == null || reservations.isEmpty()) continue;
			chambres.add(chambre);
		}
		
		ChambresResponse chambresResponse = conversionService.convert(chambres, ChambresResponse.class);
				
		return new ResponseEntity<>(chambresResponse, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/disponibilite/{chambreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<LocalDate> getDisponibilite(
		@PathVariable
		Long chambreId
	) {
		LocalDate localDate = chambreRepository.getDisponibilite(chambreId, LocalDate.now());
		return new ResponseEntity<>(localDate == null ? LocalDate.now() : localDate, HttpStatus.OK);
	}
}
