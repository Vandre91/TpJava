package fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response;

import java.util.List;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.UriParameter;

public class ChambreResponse {
	
	private Long chambreId;
	
	private Integer numerChambre;
	
	private Integer prix;

	private List<UriParameter> listUriReservation;
	
	private String image;
	
	private String description;

	public ChambreResponse() {

	}

	public ChambreResponse(Integer numerChambre, Integer prix, List<UriParameter> listUriReservation, String image, String description) {
		this.numerChambre = numerChambre;
		this.prix = prix;
		this.listUriReservation = listUriReservation;
		this.image = image;
		this.description = description;
	}

	public ChambreResponse(Long chambreId, Integer numerChambre, Integer prix, List<UriParameter> listUriReservation, String image, String description) {
		this.chambreId = chambreId;
		this.numerChambre = numerChambre;
		this.prix = prix;
		this.listUriReservation = listUriReservation;
		this.image = image;
		this.description = description;
	}

	public Long getChambreId() {
		return chambreId;
	}

	public void setChambreId(Long chambreId) {
		this.chambreId = chambreId;
	}

	public Integer getNumerChambre() {
		return numerChambre;
	}

	public void setNumerChambre(Integer numerChambre) {
		this.numerChambre = numerChambre;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public List<UriParameter> getListUriReservation() {
		return listUriReservation;
	}

	public void setListUriReservation(List<UriParameter> listUriReservation) {
		this.listUriReservation = listUriReservation;
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
}
