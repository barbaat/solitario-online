package org.springframework.samples.petclinic.mazoInicial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MazoInicialService {
    
    private MazoInicialRepository mazoinicialRepository;

    @Autowired
    public MazoInicialService(MazoInicialRepository mazoinicialRepository){
        this.mazoinicialRepository = mazoinicialRepository;
    }

    @Transactional(readOnly = true)
    public Optional<MazoInicial> findMazoInicialById(Integer id) throws DataAccessException{
        return mazoinicialRepository.findById(id);
    }


}