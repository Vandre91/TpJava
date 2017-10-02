package fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response;

import java.util.List;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;

public class ChambresReserveeResponse {

	private List<Chambre> listChambreReservee;

	public ChambresReserveeResponse() {

	}

	public ChambresReserveeResponse(List<Chambre> listChambreReservee) {
		this.listChambreReservee = listChambreReservee;
	}

	public List<Chambre> getListChambreReservee() {
		return listChambreReservee;
	}

	public void setListChambreReservee(List<Chambre> listChambreReservee) {
		this.listChambreReservee = listChambreReservee;
	}
	
}
