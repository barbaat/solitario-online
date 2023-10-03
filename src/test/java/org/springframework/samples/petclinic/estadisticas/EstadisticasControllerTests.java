package org.springframework.samples.petclinic.estadisticas;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.logros.LogrosService;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.user.UserServicePageable;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link JugadorController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers = EstadisticasController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class EstadisticasControllerTests {

	private static final int TEST_Jugador_ID = 10;

	@MockBean
	private JugadorService jugadorService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;
	
	@MockBean
	private LogrosService logrosService;

	@MockBean
	private PartidaService partidaService;

	@MockBean
	private UserServicePageable userServicePageable;

	@MockBean
	private EstadisticasService estadisticasService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Franklin");
		george.setId(10);
		given(this.jugadorService.findJugadorByUsername("test")).willReturn(george);
		given(this.jugadorService.findJugadorById(10)).willReturn(george);
	}


	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testMostrarEstadisticasAdminPositivo() throws Exception {
		mockMvc.perform(get("/jugador/estadisticas/{id}",TEST_Jugador_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("estadisticas/estadisticasJugador"));
	}
	
	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testMostrarEstadisticasAdminNegativo() throws Exception {
		mockMvc.perform(get("/jugador/estadisticas/{id}",TEST_Jugador_ID))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}


	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testMostrarEstadisticas() throws Exception {
		mockMvc.perform(get("/jugador/estadisticas"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("partidasTotalesJugadas"))
				.andExpect(view().name("estadisticas/estadisticasJugador"));
	}


	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testMostrarRanking() throws Exception {
		mockMvc.perform(get("/ranking"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("jugadoresWIN"))
				.andExpect(model().attributeExists("jugadoresPTN"))
				.andExpect(model().attributeExists("jugadoresMOV"))
				.andExpect(view().name("ranking/rankingGeneral"));
	}

}
