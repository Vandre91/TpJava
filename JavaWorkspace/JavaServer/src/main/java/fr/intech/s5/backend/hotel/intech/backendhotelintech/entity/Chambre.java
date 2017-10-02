package fr.intech.s5.backend.hotel.intech.backendhotelintech.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="chambre")
public class Chambre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long chambreId;
	
	@NotNull
	private Integer numeroChambre;
	
	@NotNull
	private Integer prix;
	
	@OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> listeDesReservations;
	
	@NotNull
	private String image;
	
	@NotNull
	private String description;	
	
	public Chambre() {
		
	}
	
	public Chambre(Integer numeroChambre, Integer prix, String image, String description) {
		this.numeroChambre = numeroChambre;
		this.prix = prix;
		this.image = image;
		this.description = description;
	}
	
	public Chambre(Long chambreId, Integer numeroChambre, Integer prix, String image, String description) {
		this.chambreId = chambreId;
		this.numeroChambre = numeroChambre;
		this.prix = prix;
		this.image = image;
		this.description = description;
	}
	
	public Long getChambreId() {
		return chambreId;
	}

	public void setChambreId(Long chambreId) {
		this.chambreId = chambreId;
	}

	public Integer getNumeroChambre() {
		return numeroChambre;
	}

	public void setNumeroChambre(Integer numeroChambre) {
		this.numeroChambre = numeroChambre;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public List<Reservation> getListeDesReservations() {
		return listeDesReservations;
	}

	public void setListeDesReservations(List<Reservation> listeDesReservations) {
		this.listeDesReservations = listeDesReservations;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addReservation(Reservation pReservation) {
		if(listeDesReservations == null)
			listeDesReservations = new ArrayList<Reservation>();
		listeDesReservations.add(pReservation);
	}
}
