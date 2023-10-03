/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.estadisticas;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class EstadisticasServiceTests {
	
	@Autowired
	protected EstadisticasService estadisticasService;

	@Autowired
	protected JugadorService jugadorService;

	@BeforeEach
	void setup() {

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");

	}

	@Test
	public void shouldSetEstadisticasIndividualesJugadorSinPartidas(){
		Jugador jugador = this.jugadorService.findJugadorByUsername("barba");
		assertThat(jugador.getUser().getUsername().equals("barba"));
		estadisticasService.setEstadisticasJugador(jugador);
		assertThat(jugador.getPartidasJugadas().equals(0));
	}
	
	@Test
	public void shouldSetEstadisticasIndividualesJugadorConPartidasJugadas(){
		Jugador jugador = this.jugadorService.findJugadorByUsername("barba");
		assertThat(jugador.getUser().getUsername().equals("barba"));
		Partida p = new Partida();
		p.setJugador(jugadorService.findJugadorByUsername("barba"));
		p.setMomentoInicio(LocalDateTime.now().minusMinutes(15));
		p.setMomentoFin(LocalDateTime.now());
		p.setNumMovimientos(0);
		p.setVictoria(false);
		long tiempoJugado = p.getDuracionMaxMin();
		LocalTime totalJugado = LocalTime.of(0, 0, 0);
		estadisticasService.setEstadisticasJugador(jugador);
		assertThat(jugador.getPartidasJugadas().equals(1));
		assertThat(jugador.getPartidasGanadas().equals(0));
		assertThat(jugador.getPartidasNoGanadas().equals(1));
		assertThat(jugador.getTotalTiempoJugado().equals(totalJugado.plusSeconds(tiempoJugado)));
	}


}
