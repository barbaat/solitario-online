package org.springframework.samples.petclinic.jugador;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@WebMvcTest(controllers = JugadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class JugadorControllerTests {

	private static final int TEST_Jugador_ID = 1;

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

		Jugador admin = new Jugador();
		User adminUser = new User();
		Authorities rolAdmin = new Authorities();
		rolAdmin.setAuthority("admin");
		adminUser.setEnabled(true);
		adminUser.setUsername("testAdmin");
		adminUser.setPassword("123");
		admin.setFirstName("Admin");
		admin.setLastName("Test");
		given(this.jugadorService.findJugadorByUsername("testAdmin")).willReturn(admin);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/jugador/new")).andExpect(status().isOk()).andExpect(model().attributeExists("jugador"))
				.andExpect(view().name("jugador/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/jugador/new")
		.param("firstName", "Joe")
		.param("lastName", "Bloggs")
		.param("user.username","aaa")
		.param("user.password","123")
		.param("user.enables", "true")
		.param("user.authorities","jugador")
		.with(csrf())
				).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/jugador/new").with(csrf())
		.param("firstName", "")
		.param("lastName", "Bloggs")
		.param("user.username","aaa")
		.param("user.password","123")
		.param("user.enables", "true")
		.param("user.authorities","jugador")
		)
		.andExpect(status().isOk()).andExpect(model().attributeHasErrors("jugador"))	
		.andExpect(view().name("jugador/createOrUpdateJugadorForm"));
	}

	//Test Actualizar datos juagdor
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateJugadorForm() throws Exception {
		mockMvc.perform(get("/jugador/edit/{id}", TEST_Jugador_ID)).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateJugadorFormSuccess() throws Exception {
		mockMvc.perform(post("/jugador/new")
		.param("firstName", "Holaa")
		.param("lastName", "Testt")
		.with(csrf())
				).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateJugadorFormHasErrors() throws Exception {
		mockMvc.perform(post("/jugador/edit/{id}", TEST_Jugador_ID).with(csrf()).param("firstName", "")
				.param("lastName", "")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("jugador"))
				.andExpect(model().attributeHasFieldErrors("jugador", "firstName"))
				.andExpect(model().attributeHasFieldErrors("jugador", "lastName"))
				.andExpect(view().name("jugador/createOrUpdateJugadorForm"));
	}

	//Ver tu propio perfil de jugador
	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testShowJugador() throws Exception {
		mockMvc.perform(get("/jugador/perfil")).andExpect(status().isOk())
				.andExpect(view().name("jugador/showJugador"));
	}

	//Ver el perfil de otro jugador
	@WithMockUser(value = "spring", username = "test", authorities = "jugador")
	@Test
	void testShowJugadorAOtroJugador() throws Exception {
		mockMvc.perform(get("/jugador/perfil/3")).andExpect(status().isOk())
				.andExpect(view().name("welcome"));
	}
/*
	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/jugador/find")).andExpect(status().isOk()).andExpect(model().attributeExists("Jugador"))
				.andExpect(view().name("jugador/findJugadors"));
	}

	*/
/*	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.jugadorService.findJugadorByLastName("")).willReturn(Lists.newArrayList(george, new Jugador()));

		mockMvc.perform(get("/Jugadors")).andExpect(status().isOk()).andExpect(view().name("Jugadors/JugadorsList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormByLastName() throws Exception {
		given(this.jugadorService.findJugadorByLastName(george.getLastName())).willReturn(Lists.newArrayList(george));

		mockMvc.perform(get("/Jugadors").param("lastName", "Franklin")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/Jugadors/" + TEST_Jugador_ID));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormNoJugadorsFound() throws Exception {
		mockMvc.perform(get("/Jugadors").param("lastName", "Unknown Surname")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("Jugador", "lastName"))
				.andExpect(model().attributeHasFieldErrorCode("Jugador", "lastName", "notFound"))
				.andExpect(view().name("Jugadors/findJugadors"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateJugadorForm() throws Exception {
		mockMvc.perform(get("/Jugadors/{JugadorId}/edit", TEST_Jugador_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("Jugador"))
				.andExpect(model().attribute("Jugador", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("Jugador", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("Jugador", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("Jugador", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("Jugador", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("Jugadors/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateJugadorFormSuccess() throws Exception {
		mockMvc.perform(post("/Jugadors/{JugadorId}/edit", TEST_Jugador_ID).with(csrf()).param("firstName", "Joe")
				.param("lastName", "Bloggs").param("address", "123 Caramel Street").param("city", "London")
				.param("telephone", "01616291589")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/Jugadors/{JugadorId}"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateJugadorFormHasErrors() throws Exception {
		mockMvc.perform(post("/Jugadors/{JugadorId}/edit", TEST_Jugador_ID).with(csrf()).param("firstName", "Joe")
				.param("lastName", "Bloggs").param("city", "London")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("Jugador"))
				.andExpect(model().attributeHasFieldErrors("Jugador", "address"))
				.andExpect(model().attributeHasFieldErrors("Jugador", "telephone"))
				.andExpect(view().name("Jugadors/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowJugador() throws Exception {
		mockMvc.perform(get("/Jugadors/{JugadorId}", TEST_Jugador_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("Jugador", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("Jugador", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("Jugador", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("Jugador", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("Jugador", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("Jugadors/JugadorDetails"));
	}
*/
}
