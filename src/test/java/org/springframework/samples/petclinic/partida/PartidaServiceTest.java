package org.springframework.samples.petclinic.partida;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaServiceTest {

    @Autowired
    protected PartidaService partidaService;

    @Autowired
    protected JugadorService jugadorService;

    @Test
    void testFindAllPartidas(){
        //DARA 0 
        List<Partida> partidas = partidaService.findAllPartidas();
        assertThat(partidas.size()).isEqualTo(0);

        Partida p = new Partida();
        p.setId(1);
        p.setMomentoInicio(LocalDateTime.now());
        p.setVictoria(false);
        partidaService.save(p);
        partidas = partidaService.findAllPartidas();        
        assertThat(partidas.size()).isNotEqualTo(0);

    }

    @Test
    void testFindPartidasEnCurso(){
        //DARA 0 
        Partida p = new Partida();
        p.setId(1);
        p.setMomentoInicio(LocalDateTime.now());
        p.setVictoria(false);
        partidaService.save(p);
        Collection<Partida> partidas = partidaService.findPartidasEnCurso();        
        assertThat(partidas.size()).isNotEqualTo(0);

    }
    @Test
    void testEstadoPartidas(){
        //COMPRUEBA QUE TIENE UNA PARTIDA EN CURSO
        Jugador j = jugadorService.findJugadorByUsername("gallego");
        Partida p = new Partida();
        p.setId(1);
        p.setJugador(j);
        p.setMomentoInicio(LocalDateTime.now());
        p.setVictoria(false);
        partidaService.save(p);
        //COMPRUEBO AMBOS METODOS
        Boolean estadoPartidaJugador = partidaService.jugadorTienePartidaEnCurso(j);        
        Collection<Partida> partidasFinalizadas= partidaService.findPartidasFinalizadas();
        assertThat(estadoPartidaJugador).isEqualTo(true);
        assertThat(partidasFinalizadas.size()).isEqualTo(0);
        
        
        //COMPRUEBA QUE NO TIENE NINGUNA PARTIDA EN CURSO
        Jugador j2 = jugadorService.findJugadorByUsername("jorge");
        Partida p2 = new Partida();
        p2.setId(2);
        p2.setJugador(j2);
        p2.setMomentoInicio(LocalDateTime.now());
        p2.setMomentoFin(LocalDateTime.of(2023, 1, 10, 17, 0));
        p2.setVictoria(false);
        partidaService.save(p2);
        //COMPRUEBO AMBOS METODOS
        estadoPartidaJugador = partidaService.jugadorTienePartidaEnCurso(j2);        
        partidasFinalizadas= partidaService.findPartidasFinalizadas();
        assertThat(estadoPartidaJugador).isEqualTo(false);
        assertThat(partidasFinalizadas.size()).isNotEqualTo(0);

    }

    @Test
    void testPartidasAcabadasPorUnJugador(){
        Jugador j2 = jugadorService.findJugadorByUsername("jorge");
        
        List<Partida> partidasAcabasPorJugador=partidaService.findPartidasFinalizadasPorJugador(j2);
        assertThat(partidasAcabasPorJugador.size()).isEqualTo(0);

        Partida p2 = new Partida();
        p2.setId(2);
        p2.setJugador(j2);
        p2.setMomentoInicio(LocalDateTime.now());
        p2.setMomentoFin(LocalDateTime.of(2023, 1, 10, 17, 0));
        p2.setVictoria(false);
        partidaService.save(p2);

        partidasAcabasPorJugador=partidaService.findPartidasFinalizadasPorJugador(j2);
        assertThat(partidasAcabasPorJugador.size()).isNotEqualTo(0);

    }

    @Test
    void testFindPartidaByUsername(){
        Jugador j2 = jugadorService.findJugadorByUsername("jorge");

        Partida p2 = new Partida();
        p2.setId(1);
        p2.setJugador(j2);
        p2.setMomentoInicio(LocalDateTime.now());
        p2.setMomentoFin(LocalDateTime.of(2023, 1, 10, 17, 0));
        p2.setVictoria(false);
        partidaService.save(p2);

        Partida part = partidaService.findPartidaByUsername("jorge");
        assertThat(part.getId()).isEqualTo(p2.getId());
    }

    @Test
    void testDeletePartida(){
        Jugador j2 = jugadorService.findJugadorByUsername("jorge");

        Partida p2 = new Partida();
        p2.setId(1);
        p2.setJugador(j2);
        p2.setMomentoInicio(LocalDateTime.now());
        p2.setMomentoFin(LocalDateTime.of(2023, 1, 10, 17, 0));
        p2.setVictoria(false);
        partidaService.save(p2);

        Partida p = partidaService.findById(1);
        assertThat(p).isNotNull();
        partidaService.deletePartida(p);
        Partida p3 = partidaService.findById(1);
        assertThat(p3).isNull();
    }

    @Test
    void testEstablecerResultadoPartida(){
        Jugador j2 = jugadorService.findJugadorByUsername("jorge");

        Partida p = new Partida();
        p.setId(1);
        p.setJugador(j2);
        p.setMomentoInicio(LocalDateTime.now());
        partidaService.save(p);

        //COMPRUEBO DERROTA
        partidaService.establecerDerrotaPartida(1);
        Partida partida = partidaService.findById(1);
        assertThat(partida.getVictoria()).isEqualTo(false);

        //COMPRUEBO VICTORIA
        partidaService.establecerVictoriaPartida(1);
        partida = partidaService.findById(1);
        assertThat(partida.getVictoria()).isEqualTo(true);


    }



  




    
}
