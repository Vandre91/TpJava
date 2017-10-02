package fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response;

public class ReservableChambreResponse {

	private Long chambreId;
	
	private Integer numeroChambre;
	
	private Integer prix;
	
	private String uri;

	public ReservableChambreResponse() {

	}

	public ReservableChambreResponse(Integer numeroChambre, Integer prix, String uri) {
		this.numeroChambre = numeroChambre;
		this.prix = prix;
		this.uri = uri;
	}

	public ReservableChambreResponse(Long chambreId, Integer numeroChambre, Integer prix, String uri) {
		this.chambreId = chambreId;
		this.numeroChambre = numeroChambre;
		this.prix = prix;
		this.uri = uri;
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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
