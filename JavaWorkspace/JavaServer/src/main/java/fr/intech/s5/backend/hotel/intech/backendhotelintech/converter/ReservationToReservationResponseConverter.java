package fr.intech.s5.backend.hotel.intech.backendhotelintech.converter;

import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Reservation;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ReservationResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.BackendConstantes;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.util.UriParameter;

public class ReservationToReservationResponseConverter
	implements Converter<Reservation, ReservationResponse> {

	@Override
	public ReservationResponse convert(Reservation reservation) {
		
		ReservationResponse reservationResponse = new ReservationResponse();

		reservationResponse.setDateDebut(reservation.getDateDebut());
		reservationResponse.setDateFin(reservation.getDateFin());

		Long chambreId = reservation.getChambre().getChambreId();
		UriParameter uriParameter = new UriParameter(chambreId, BackendConstantes.CHAMBRE_RESERVATION_V1 + "/" + chambreId);		
		reservationResponse.setUriParameter(uriParameter);
	
		if(reservation.getReservationId() != null)
			reservationResponse.setReservationId(reservation.getReservationId());
		
		return reservationResponse;
	}
}
