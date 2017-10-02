package fr.intech.s5.backend.hotel.intech.backendhotelintech.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.intech.s5.backend.hotel.intech.backendhotelintech.entity.Chambre;

public interface ChambreRepository extends CrudRepository<Chambre, Long> {

	/**
	 * @param pId
	 * @return
	 */
	Chambre findByChambreId(Long pId);
	
	/**
	 * @param chambreId
	 * @return
	 */
	@Query("select r.dateFin from Chambre c left join c.listeDesReservations r"
			+ " where c.chambreId = :id"
			+ " and (r.dateDebut <= :curDate and r.dateFin >= :curDate)")
	public LocalDate getDisponibilite(
		@Param("id")
		Long chambreId,
		@Param("curDate")
		LocalDate currentDate
	);
}
