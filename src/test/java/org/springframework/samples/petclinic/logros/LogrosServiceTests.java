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
package org.springframework.samples.petclinic.logros;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.estadisticas.EstadisticasService;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class LogrosServiceTests {
	
	@Autowired
	protected EstadisticasService estadisticasService;

	@Autowired
	protected JugadorService jugadorService;

	@Autowired
	protected LogrosService logrosService;

	@BeforeEach
	void setup() {

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("barba");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");


		Logros logro = new Logros();
		logro.setId(1);
		logro.setName("Máquina de jugar");
		logro.setDescription("Has ganado 5 partidas");
		logro.setNumCondicion(5);
		logro.setIs_unlocked(false);
		logro.setImage("/resources/images/logro-img.png");
		logro.setJugador(george);
		List<Logros> lista = new ArrayList<>();
		lista.add(logro);
	}
 
	@Test
	void debeObtenerUnLogro() {
		Logros logro = this.logrosService.findByIdlOGRO(1);
		assertThat(logro.getName().equals("Máquina de jugar"));
	}

	@Test
	public void debeObtenerLosLogrosDeUnUsuario(){
		Jugador jugador = this.jugadorService.findJugadorByUsername("barba");
		List<Logros> res = this.logrosService.findById(jugador.getId());
		Logros logro = this.logrosService.findByIdlOGRO(1);
		List<Logros> aux = new ArrayList<>();
		aux.add(logro);
		assertThat(res.equals(aux));
	}

	@Test
	public void debeObtenerTodosLosLogros(){
		List<Logros> listaLogros = this.logrosService.findAll();
		Jugador jugador = this.jugadorService.findJugadorByUsername("barba");
		List<Logros> res = this.logrosService.findById(jugador.getId());
		assertThat(listaLogros.equals(res));
	}
	
	@Test
	void debeActualizarseElLogro() {
		Logros logro = this.logrosService.findByIdlOGRO(1);
		String oldName = logro.getName();
		String newName = oldName + "new";

		logro.setName(newName);
		this.logrosService.save(logro);

		logro = this.logrosService.findByIdlOGRO(1);
		assertThat(logro.getName().equals(newName));
	}

	@Test
	public void debeEliminarseLogro() {
		Logros logro = this.logrosService.findByIdlOGRO(1);
		List<Logros> listaLogros = new ArrayList<>();
		listaLogros.add(logro);
		
		this.logrosService.delete(logro);
		assertThat(listaLogros.size()==0);
	}


}
