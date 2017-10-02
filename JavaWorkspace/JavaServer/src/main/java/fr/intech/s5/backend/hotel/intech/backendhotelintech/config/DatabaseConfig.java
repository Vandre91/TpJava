package fr.intech.s5.backend.hotel.intech.backendhotelintech.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("fr.intech.s5.backend.hotel.intech.backendhotelintech.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
