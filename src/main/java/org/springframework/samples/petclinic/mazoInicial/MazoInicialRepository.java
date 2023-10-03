package org.springframework.samples.petclinic.mazoInicial;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MazoInicialRepository extends CrudRepository<MazoInicial, Integer> {
    Optional<MazoInicial> findById(Integer id) throws DataAccessException;
}
