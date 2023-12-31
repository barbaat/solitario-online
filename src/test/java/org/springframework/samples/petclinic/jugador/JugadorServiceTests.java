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
package org.springframework.samples.petclinic.jugador;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following
 * services provided
 * by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary
 * set up
 * time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning
 * that we
 * don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * JugadorServiceTests#clinicService clinicService}</code> instance variable,
 * which uses
 * autowiring <em>by type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is
 * executed in
 * its own transaction, which is automatically rolled back by default. Thus,
 * even if tests
 * insert or otherwise change database state, there is no need for a teardown or
 * cleanup
 * script.
 * <li>An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.</li>
 * </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class JugadorServiceTests {

	@Autowired
	protected JugadorService jugadorService;

	@Autowired
	protected PartidaService partidaService;

	@BeforeEach
	void setup() {

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		george.setUser(user);
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");
		jugadorService.saveJugador(george);
		Jugador jugador = this.jugadorService.findJugadorById(1);
		Partida p = new Partida();
		p.setJugador(jugador);
		p.setNumMovimientos(0); // PREDEFINIDO
		p.setMomentoInicio(LocalDateTime.now());
		p.setVictoria(false);
		partidaService.save(p);


	}

	@Test
	@Transactional
	public void shouldInsertAndDeleteJugador() {
		Collection<Jugador> jugadores = this.jugadorService.findJugadoresByLastName("Davis");
		int found = jugadores.size();

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		rol.setUser(user);
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");
		george.setUser(user);
		george.setId(1);
		this.jugadorService.saveJugador(george);
		assertThat(george.getId().longValue()).isNotEqualTo(0);
		jugadores = this.jugadorService.findJugadoresByLastName("Davis");
		assertThat(jugadores.size()).isEqualTo(found + 1);

	}

	@Test
	public void shoulFindJugadorByUsername() {
		Jugador jugador = this.jugadorService.findJugadorByUsername("jorge");
		assertThat(jugador.getUser().getUsername().equals("jorge"));
		Jugador jugador1 = this.jugadorService.findJugadorByUsername("jor");
		assertThat(jugador1).isNull();
	}

	@Test
	public void shoulFindJugadorById() {
		Jugador jugador = this.jugadorService.findJugadorById(1);
		assertThat(jugador.getUser().getUsername().equals("jorge"));
		Jugador jugador1 = this.jugadorService.findJugadorById(154);
		assertThat(jugador1).isNull();
	}

	@Test
	public void shouldFindJugadoresByLastName() {
		Collection<Jugador> jugadores = this.jugadorService.findJugadoresByLastName("sillero");
		assertThat(jugadores.size()).isNotEqualTo(0);
		Collection<Jugador> jugadores1 = this.jugadorService.findJugadoresByLastName("silleto");
		assertThat(jugadores1.size()).isEqualTo(0);
	}

	@Test
	public void shouldFindPartidaByJugadorId() {		
		Collection<Partida> jugadores = this.jugadorService.findPartidasByJugadorId(1);
		assertThat(jugadores.size()).isNotEqualTo(0);
	}

	@Test
	public void shouldFindAllPlayers() {		
		List<Jugador> jugadores = this.jugadorService.findAllPlayer();
		assertThat(jugadores.size()).isNotEqualTo(0);
		
	}
	
	
	
	
	
	/*
	 * @Test
	 * 
	 * @Transactional
	 * void shouldUpdateOwner() {
	 * Owner owner = this.ownerService.findOwnerById(1);
	 * String oldLastName = owner.getLastName();
	 * String newLastName = oldLastName + "X";
	 * 
	 * owner.setLastName(newLastName);
	 * this.ownerService.saveOwner(owner);
	 * 
	 * // retrieving new name from database
	 * owner = this.ownerService.findOwnerById(1);
	 * assertThat(owner.getLastName()).isEqualTo(newLastName);
	 * }
	 * 
	 */
}
