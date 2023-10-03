package org.springframework.samples.petclinic.estadisticas;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {
    
    private PartidaService serv;

	@Autowired
    public EstadisticasService(PartidaService serv){
        this.serv = serv;
    }


    public List<Partida> findAll(){
        return serv.findAllPartidas();
    }

    public void setEstadisticasJugador(Jugador jugador){
		Collection<Partida> lista = serv.findPartidasFinalizadasPorJugador(jugador);
		
		
		if(lista.size()>0){
			List<Partida> partidasGanadas = lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList());
			Comparator<Partida> comparador= Comparator.comparing(Partida::getNumMovimientos);
			Comparator<Partida> comparador2= Comparator.comparing(Partida::getDuracionMaxMin);	
			List<Partida> numMovLista = partidasGanadas.stream().sorted(comparador.reversed()).collect(Collectors.toList());
			List<Partida> timeLista = partidasGanadas.stream().sorted(comparador2.reversed()).collect(Collectors.toList());
			Integer sumaPuntos = lista.stream().mapToInt(x -> (int) x.puntos()).sum();
			Integer sumaMovimientos = lista.stream().mapToInt(x -> (int) x.getNumMovimientos()).sum();
			Integer sumaGanadas = (lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList())).size();
			Integer sumaPerdidas = lista.size() - sumaGanadas;
			long tiempoJugado = lista.stream().mapToInt(x -> (int) x.getDuracionMaxMin()).sum();
			LocalTime totalJugado = LocalTime.of(0, 0, 0);

			jugador.setPartidasGanadas(sumaGanadas);
			jugador.setPartidasNoGanadas(sumaPerdidas);
			jugador.setTotalTiempoJugado(totalJugado.plusSeconds(tiempoJugado));
			jugador.setNumTotalMovimientos(sumaMovimientos);
			jugador.setNumTotalPuntos(sumaPuntos);
			if(partidasGanadas.size()==0){
				jugador.setNumMaxMovimientosPartidaGanada((long) 0);
				jugador.setNumMinMovimientosPartidaGanada((long) 0);
			}
			if(partidasGanadas.size()>0){
				jugador.setMaxTiempoPartidaGanada(timeLista.get(0).duracion());
				jugador.setMinTiempoPartidaGanada(timeLista.get(timeLista.size()-1).duracion());
				jugador.setNumMaxMovimientosPartidaGanada(numMovLista.get(0).getNumMovimientos());
				jugador.setNumMinMovimientosPartidaGanada(numMovLista.get(numMovLista.size()-1).getNumMovimientos());
			}
		} else {
			jugador.setAllStats0();
		}

    }
	


	
}
