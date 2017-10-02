package fr.intech.s5.backend.hotel.intech.backendhotelintech.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservationId;

	@NotNull
	private LocalDate dateDebut;
	
	@NotNull
	private LocalDate dateFin;
	
	@ManyToOne
	private Chambre chambre;
	
	public Reservation() {

	}

	public Reservation(LocalDate dateDebut, LocalDate dateFin) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Reservation(Long reservationId, LocalDate dateDebut, LocalDate dateFin) {
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

	@JsonIgnore
	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
}
