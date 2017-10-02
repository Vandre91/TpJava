package fr.intech.s5.backend.hotel.intech.backendhotelintech.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.converter.ChambreToChambreResponseConverter;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.converter.ChambreToReservableChambreResponseConverter;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.converter.ChambresToChambresResponse;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.converter.ReservationRequestToReservationConverter;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.converter.ReservationToReservationResponseConverter;

@Configuration
public class ConversionConfig {

	@SuppressWarnings("rawtypes")
	private Set<Converter> getConverters() {		
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new ChambresToChambresResponse());
		converters.add(new ChambreToChambreResponseConverter());
		converters.add(new ChambreToReservableChambreResponseConverter());
		converters.add(new ReservationRequestToReservationConverter());
		converters.add(new ReservationToReservationResponseConverter());
		return converters;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		return bean.getObject();
	}
}

