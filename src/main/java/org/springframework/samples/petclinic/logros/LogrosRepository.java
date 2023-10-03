package org.springframework.samples.petclinic.logros;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogrosRepository extends CrudRepository<Logros, Integer>{
	
	@Query("SELECT logro FROM Logros logro WHERE logro.jugador.id = ?1")
	public List<Logros> findLogrosByIdJugador(Integer id);

	public List<Logros> findAll();

	@Query("SELECT logro FROM Logros logro WHERE logro.id = ?1")
	public Logros findLogrosByIdLogro(Integer id);
	

	List<Logros> findByjugadorIsNull();
}