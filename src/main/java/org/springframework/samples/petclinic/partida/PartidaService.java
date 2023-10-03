package org.springframework.samples.petclinic.partida;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
	
	private PartidaRepository partidaRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}
	
	@Transactional
	public List<Partida> findAllPartidas(){
		return partidaRepository.findAll();
	}

	@Transactional
	public Collection<Partida> findPartidasEnCurso(){
		return partidaRepository.findBymomentoFinIsNull();
	}

	@Transactional
	public Boolean jugadorTienePartidaEnCurso (Jugador jugador){
		return partidaRepository.findBymomentoFinIsNull().stream().anyMatch(x -> x.getJugador().equals(jugador));
	}
	
	@Transactional
	public Collection<Partida> findPartidasFinalizadas(){
		return partidaRepository.findBymomentoFinIsNotNull();
	}

	@Transactional
	public List<Partida> findPartidasFinalizadasPorJugador(Jugador jugador){
		Collection<Partida> lista = partidaRepository.findBymomentoFinIsNotNull();
		List<Partida> res= new ArrayList<>();
		for (Partida partida:lista){
			if(partida.getJugador().equals(jugador)){
				res.add(partida);
			}
		}
		return res;
	}
	

	@Transactional
	public void save(Partida partida) {
		partidaRepository.save(partida);
	}
	
	@Transactional
	public Partida findPartidaByUsername(String username) {
		return partidaRepository.findByUsername(username);
	}


	//ANTÍA
	@Transactional
	public Partida findById(Integer id) {
		return partidaRepository.findPartidayId(id);
	}

	@Transactional
	public void deletePartida(@Valid Partida partida) throws DataAccessException, DataIntegrityViolationException {
		partidaRepository.delete(partida);
	}


	public void establecerDerrotaPartida(Integer id){
		Partida partida = findById(id);
		partida.setMomentoFin(LocalDateTime.now());
		partida.setVictoria(false);
		long diffInSeconds = ChronoUnit.SECONDS.between(partida.getMomentoInicio(), partida.getMomentoFin());
		Jugador player = partida.getJugador();
		player.setNumTotalPuntos(player.getNumTotalPuntos()+(int) partida.puntos());
		player.setPartidasNoGanadas(player.getPartidasNoGanadas()+1);
		player.setTotalTiempoJugado(player.getTotalTiempoJugado().plusSeconds(diffInSeconds));
	}

	public void establecerVictoriaPartida(Integer id){
		Partida partida = findById(id);
		partida.setMomentoFin(LocalDateTime.now());
		partida.setVictoria(true);
		long diffInSeconds = ChronoUnit.SECONDS.between(partida.getMomentoInicio(), partida.getMomentoFin());
		Jugador player = partida.getJugador();
		player.setNumTotalMovimientos(player.getNumTotalMovimientos()+(int) partida.getNumMovimientos());
		player.setNumTotalPuntos(player.getNumTotalPuntos()+(int) partida.puntos());
		player.setPartidasGanadas(player.getPartidasGanadas()+1);
		player.setTotalTiempoJugado(player.getTotalTiempoJugado().plusSeconds(diffInSeconds));
	}
}
