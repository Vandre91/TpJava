package fr.intech.s5.backend.hotel.intech.backendhotelintech.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.model.response.ChambresResponse;

public class ChambresToChambresResponse
	implements Converter<List<Chambre>, ChambresResponse>{

	@Override
	public ChambresResponse convert(List<Chambre> chambres) {

		ChambresResponse chambresResponse = new ChambresResponse();
		
		chambresResponse.setListChambres(chambres);
		
		return chambresResponse;
	}

}
