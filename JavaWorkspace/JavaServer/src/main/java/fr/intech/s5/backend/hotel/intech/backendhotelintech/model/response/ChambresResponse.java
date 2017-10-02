package fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response;

import java.util.List;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;

public class ChambresResponse {

	private List<Chambre> chambres;

	public ChambresResponse() {

	}

	public ChambresResponse(List<Chambre> chambres) {
		this.chambres = chambres;
	}

	public List<Chambre> getListChambres() {
		return chambres;
	}

	public void setListChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}
	
}
