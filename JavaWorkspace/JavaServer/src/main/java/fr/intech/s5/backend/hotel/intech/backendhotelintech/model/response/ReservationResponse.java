package fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response;

import java.time.LocalDate;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.UriParameter;

public class ReservationResponse {
	
	private Long reservationId;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;

	private UriParameter uriParameter;
	
	public ReservationResponse() {

	}

	public ReservationResponse(LocalDate dateDebut, LocalDate dateFin) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public ReservationResponse(Long reservationId, LocalDate dateDebut, LocalDate dateFin) {
		this.reservationId = reservationId;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public UriParameter getUriParameter() {
		return uriParameter;
	}

	public void setUriParameter(UriParameter uriParameter) {
		this.uriParameter = uriParameter;
	}
}
