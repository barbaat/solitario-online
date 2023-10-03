package org.springframework.samples.petclinic.carta;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends CrudRepository<Carta, Integer>{
    Collection<Carta> findAll() throws DataAccessException;
}
