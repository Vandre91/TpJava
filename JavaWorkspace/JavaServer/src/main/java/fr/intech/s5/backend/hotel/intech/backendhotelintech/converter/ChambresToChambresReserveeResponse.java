package fr.intech.s5.backend.hotel.intech.backendhotelintech.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ChambresReserveeResponse;

public class ChambresToChambresReserveeResponse
	implements Converter<List<Chambre>, ChambresReserveeResponse>{

	@Override
	public ChambresReserveeResponse convert(List<Chambre> chambres) {

		ChambresReserveeResponse chambresReserveeResponse = new ChambresReserveeResponse();
		
		chambresReserveeResponse.setListChambreReservee(chambres);
		
		return chambresReserveeResponse;
	}

}
