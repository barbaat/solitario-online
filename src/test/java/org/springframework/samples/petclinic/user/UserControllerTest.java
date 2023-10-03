package org.springframework.samples.petclinic.user;


/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;*/
import static org.mockito.BDDMockito.given;


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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class UserControllerTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
	private AuthoritiesService authoritiesService;

    @MockBean
    private UserService userService;

    @MockBean
	private JugadorService jugadorService;

	@MockBean
	private LogrosService logrosService;

	@MockBean
	private UserServicePageable userServicePageable;

    @BeforeEach
    void setup(){
        Jugador player = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("admin");
		user.setEnabled(true);
		user.setUsername("NuevoJugadorTest");
		user.setPassword("123");
		player.setFirstName("User");
		player.setLastName("AdminPrueba");
		given(this.userService.findByUser("NuevoJugadorTest")).willReturn(user);
    }


    @WithMockUser(value = "spring", username = "admin1", authorities = "admin")
	@Test
	public void testShowListaDeUsuariosPositive() throws Exception {
		mockMvc.perform(get("/users/all"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("usersTest"))
				.andExpect(view().name("users/UsersList"));
	}

     @WithMockUser(value = "spring", username="barba", authorities = "jugador")
	 @Test
	 void testShowListaDeUsuariosNegative() throws Exception {
	 	mockMvc.perform(get("/users/all"))
	 			.andExpect(status().isOk())
	 			.andExpect(model().attributeDoesNotExist("usersTest"))
	 			.andExpect(view().name("welcome"));
	 }
}
