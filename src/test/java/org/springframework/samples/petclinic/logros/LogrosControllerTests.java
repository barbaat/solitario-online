package org.springframework.samples.petclinic.logros;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.estadisticas.EstadisticasService;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
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

@WebMvcTest(controllers = LogrosController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class LogrosControllerTests {

	private static final int TEST_Logro_ID = 1;

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
		Jugador barba = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("test");
		user.setPassword("123");
		barba.setFirstName("barba");
		barba.setLastName("Franklin");
		barba.setId(10);

		Logros logro = new Logros();
		logro.setId(1);
		logro.setName("Máquina de jugar");
		logro.setDescription("Has ganado 5 partidas");
		logro.setNumCondicion(5);
		logro.setIs_unlocked(false);
		logro.setImage("/resources/images/logro-img.png");
		logro.setJugador(barba);
		List<Logros> lista = new ArrayList<>();
		lista.add(logro);

		given(this.jugadorService.findJugadorByUsername("test")).willReturn(barba);
		given(this.logrosService.setLogrosJugadorCreado(barba)).willReturn(lista);
		given(this.logrosService.findByIdlOGRO(1)).willReturn(lista.get(0));
	}


	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testMostrarLogrosAdminPositivo() throws Exception {
		mockMvc.perform(get("/jugador/logros/{id}",TEST_Logro_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("logros/logrosJugador"));
	}
	
	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testMostrarLogrosAdminNegativo() throws Exception {
		mockMvc.perform(get("/jugador/logros/{id}",TEST_Logro_ID))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testMostrarLogros() throws Exception {
		mockMvc.perform(get("/jugador/logros"))
				.andExpect(status().isOk())
				.andExpect(view().name("logros/logrosJugador"));
	}


	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testEditarListaLogrosPositivo() throws Exception {
		mockMvc.perform(get("/jugador/logros/editar"))
				.andExpect(status().isOk())
				.andExpect(view().name("logros/editarLogrosGeneral"));
	}

	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testEditarListaLogrosNegativo() throws Exception {
		mockMvc.perform(get("/jugador/logros/editar"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testEditarLogroIniciar() throws Exception {
		mockMvc.perform(get("/jugador/logros/editar/{id}",TEST_Logro_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("logro"))
				.andExpect(view().name("logros/editarLogro"));
	}

	@WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	void testEditarLogroProcesar() throws Exception {
		mockMvc.perform(post("/jugador/logros/editar/{id}",TEST_Logro_ID)
		.param("name", "Máquina de jugar")
		.param("description", "Has ganado 5 partidas")
		.param("numCondicion","5")
		.param("logros.is_unlocked","false")
		.param("logros.image", "/resources/images/logro-img.png")
		//.param("logros.jugador","jugador")
		.with(csrf())
				).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/jugador/logros/editar"));
	}

}
