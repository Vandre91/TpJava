package fr.intech.s5.backend.hotel.intech.backendhotelintech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;
import fr.intech.s5.backend.hotel.intech.backendhotelintech.repository.ChambreRepository;

@Component
public class H2Bootstrap implements CommandLineRunner {
	
	@Autowired
	ChambreRepository chambreRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Bootstrapping data:");
		
		chambreRepository.save(new Chambre(405, 200, "405.jpg", "Pour les amoureux de la mer !"));
		chambreRepository.save(new Chambre(406, 205, "406.jpg", "Paris dans toute sa splendeur !"));
		chambreRepository.save(new Chambre(407, 260, "407.jpg", "La modernité au rendez-vous !"));
		
		Iterable<Chambre> itr = chambreRepository.findAll();
		
		System.out.println("Printing out data:");
		for(Chambre chambre : itr) System.out.println(chambre.getNumeroChambre());
	}
}
