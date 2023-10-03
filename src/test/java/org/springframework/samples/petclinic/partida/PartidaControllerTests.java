package org.springframework.samples.petclinic.partida;

import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.user.UserServicePageable;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class PartidaControllerTests {
	
	@MockBean
	private JugadorService jugadorService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private PartidaService partidaService;

	@MockBean
	private PartidaBuilder pb;

	@MockBean
	private CartasPartidaService cartasPartidaService;

	@MockBean
	private UserServicePageable userServicePageable;

	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	void setup() {

		Jugador fekir = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("test");
		user.setPassword("123");
		fekir.setFirstName("Nabil");
		fekir.setLastName("Fekir");
		given(this.jugadorService.findJugadorByUsername("test")).willReturn(fekir);

	}
	
	@Test
	public void testCreatePartidaPositive() {
		Partida p = new Partida();
		p.setJugador(jugadorService.findJugadorByUsername("test"));
		p.setMomentoInicio(LocalDateTime.now());
		p.setNumMovimientos(0);
		p.setVictoria(false);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(p);
		
		
	}
	@Test
	public void testCreatePartidaNegative(){
		Partida p1 = new Partida();
		p1.setJugador(jugadorService.findJugadorByUsername("test"));
		p1.setMomentoInicio(LocalDateTime.now());
		p1.setVictoria(false);
		p1.setNumMovimientos(-2);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(null);
		
		Partida p2 = new Partida();
		p2.setMomentoInicio(LocalDateTime.now());
		p2.setVictoria(false);
		p2.setNumMovimientos(0);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(null);
	}

	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testShowPartidasListEnCursoPositive() throws Exception {
		mockMvc.perform(get("/partidas/enCurso"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("partidas"))
				.andExpect(view().name("partidas/partidaListEnCurso"));
	}

	@WithMockUser(value = "spring", username = "barba", authorities = "jugador")
	@Test
	void testShowPartidasListEnCursoNegative() throws Exception {
		mockMvc.perform(get("/partidas/enCurso"))
				.andExpect(status().is(302))
				.andExpect(model().attributeDoesNotExist("partidas"))
				.andExpect(view().name("redirect:/"));
	}

	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testShowPartidasListFinalizadasPositive() throws Exception {
		mockMvc.perform(get("/partidas/finalizadas"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("partidas"))
				.andExpect(view().name("partidas/partidaListFinalizadas"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowPartidasListFinalizadasNegative() throws Exception {
		mockMvc.perform(get("/partidas/finalizadas"))
		.andExpect(status().is(302))
		.andExpect(model().attributeDoesNotExist("partidas"))
		.andExpect(view().name("redirect:/"));
	}


}
