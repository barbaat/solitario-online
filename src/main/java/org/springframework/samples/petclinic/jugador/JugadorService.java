package org.springframework.samples.petclinic.jugador;


import java.time.LocalDateTime;
import java.util.Collection;

import java.util.List;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

	@Autowired
    private JugadorRepository jugadorRepository;


    @Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
    public JugadorService(JugadorRepository jugadorRepository){
        this.jugadorRepository = jugadorRepository;
    }

    @Transactional
	public Jugador findJugadorByUsername(String username) throws DataAccessException {
		return jugadorRepository.findByUsername(username);
	}


	@Transactional
	public Collection<Jugador> findJugadoresByLastName(String lastName) throws DataAccessException{
		return jugadorRepository.findByLastName(lastName);
	}

	@Transactional
	public Jugador findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findJugadorById(id);
	}
    
	
    @Transactional
	public void saveJugador(@Valid Jugador jugador) throws DataAccessException, DataIntegrityViolationException {
		
		jugadorRepository.save(jugador);
		userService.saveUser(jugador.getUser());
		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "jugador");
	}
	
	@Transactional
	public void deleteJugador(@Valid Jugador jugador) throws DataAccessException, DataIntegrityViolationException {
		jugadorRepository.delete(jugador);
	}


	@Transactional
	public Collection<Partida> findPartidasByJugadorId(int id ) throws DataAccessException{
		return jugadorRepository.findPartidasByJugador(id);
	}

	@Transactional
	public List<Jugador> findAllPlayer() {
		return jugadorRepository.findAll();
	}

	public void setCreatorYCreatedDate(Jugador jugador) {
		jugador.setCreatedDate(LocalDateTime.of(2023, 1, 7, 0, 0));
		jugador.setCreator("admin1");;
		saveJugador(jugador);
	}

}
