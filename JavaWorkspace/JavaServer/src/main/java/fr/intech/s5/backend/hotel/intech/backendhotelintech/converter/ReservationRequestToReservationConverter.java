package fr.intech.s5.backend.hotel.intech.backendhotelintech.converter;

import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Reservation;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.request.ReservationRequest;

public class ReservationRequestToReservationConverter
	implements Converter<ReservationRequest, Reservation> {

	@Override
	public Reservation convert(ReservationRequest reservationRequest) {
		
		Reservation reservation = new Reservation();
		
		reservation.setDateDebut(reservationRequest.getDateDebut());
		reservation.setDateFin(reservationRequest.getDateFin());

		if(reservationRequest.getReservationId() != null)
			reservation.setReservationId(reservationRequest.getReservationId());
			
		return reservation;
	}
}
