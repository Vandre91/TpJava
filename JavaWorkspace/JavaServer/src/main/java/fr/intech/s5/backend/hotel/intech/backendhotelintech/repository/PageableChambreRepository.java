package fr.intech.s5.backend.hotel.intech.backendhotelintech.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;

public interface PageableChambreRepository extends PagingAndSortingRepository<Chambre, Long>{
	
	Page<Chambre> findByChambreId(Long pId, Pageable pPage);
	
	/**
	 * Rechercher des chambres disponible
	 * @param dateDebut
	 * @param dateFin
	 * @param pageable
	 * @return
	 */
	@Query("select c from Chambre c left join c.listeDesReservations listr where "
			+ " listr.reservationId not in (select r from Reservation r where ((r.dateDebut > :datdeb and r.dateDebut < :datefin) or (r.dateFin > :datdeb and r.dateFin < :datefin) or (r.dateDebut < :datdeb and  r.dateFin > :datefin)))")
	public Page<Chambre> findChambresDisponibleByCriteria(
		@Param("datdeb") 
		LocalDate dateDebut,
		@Param("datefin")
		LocalDate dateFin,
		Pageable pageable
	);
}
