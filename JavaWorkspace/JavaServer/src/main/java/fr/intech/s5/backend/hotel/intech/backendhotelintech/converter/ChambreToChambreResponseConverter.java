package fr.intech.s5.backend.hotel.intech.backendhotelintech.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Reservation;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ChambreResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.BackendConstantes;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.UriParameter;

public class ChambreToChambreResponseConverter
	implements Converter<Chambre, ChambreResponse> {

	@Override
	public ChambreResponse convert(Chambre chambre) {
		
		ChambreResponse chambreResponse = new ChambreResponse();
		
		chambreResponse.setChambreId(chambre.getChambreId());
		chambreResponse.setNumerChambre(chambre.getNumeroChambre());
		chambreResponse.setPrix(chambre.getPrix());
		chambreResponse.setImage(chambre.getImage());
		chambreResponse.setDescription(chambre.getDescription());
		
		List<Reservation> listReservation = chambre.getListeDesReservations();
		if(listReservation == null) return chambreResponse;
		
		List<UriParameter> listUriReservation = new ArrayList<UriParameter>();
		for(Reservation reservation : listReservation) {
			Long reservationId = reservation.getReservationId();
			listUriReservation.add(new UriParameter(reservationId, BackendConstantes.CHAMBRE_RESERVATION_V1_RESERVATION + "/" + reservationId));
		}
		chambreResponse.setListUriReservation(listUriReservation);
		
		return chambreResponse;
	}

}
