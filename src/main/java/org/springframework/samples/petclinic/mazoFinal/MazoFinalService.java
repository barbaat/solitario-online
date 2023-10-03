package org.springframework.samples.petclinic.mazoFinal;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MazoFinalService {
    
    private MazoFinalRepository mazofinalRepository;

    @Autowired
    public MazoFinalService(MazoFinalRepository mazofinalRepository){
        this.mazofinalRepository = mazofinalRepository;
    }

    @Transactional(readOnly = true)
    public Collection<MazoFinal> findAllMazosFinales() throws DataAccessException{
        return mazofinalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MazoFinal findMazoFinalById(Integer id) throws DataAccessException{
        return mazofinalRepository.findMazoFinalById(id);
    }

    public void save(MazoFinal mf){
        mazofinalRepository.save(mf);
    }

}
