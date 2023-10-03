package org.springframework.samples.petclinic.mazo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MazoRepository extends CrudRepository<Mazo,Integer> {

    @Query("select m from Mazo m where m.id =?1")
    public Mazo findMazoById(Integer id);
    
}
