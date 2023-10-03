package org.springframework.samples.petclinic.mazoFinal;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazoFinalRepository extends CrudRepository<MazoFinal, Integer>{
    Collection<MazoFinal> findAll() throws DataAccessException;

    @Query("select m from MazoFinal m where m.id =?1")
    public MazoFinal findMazoFinalById(Integer id);
}
