package fr.intech.s5.backend.hotel.intech.backendhotelintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class BackendHotelIntechApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendHotelIntechApplication.class, args);
	}
}
