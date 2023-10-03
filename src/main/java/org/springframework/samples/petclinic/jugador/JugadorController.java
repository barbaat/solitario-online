package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.logros.Logros;
import org.springframework.samples.petclinic.logros.LogrosService;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.user.UserServicePageable;
import org.springframework.security.core.Authentication;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JugadorController {

    private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugador/createOrUpdateJugadorForm";


    private final JugadorService jugadorService;
	private final LogrosService logrosService;
	private final UserServicePageable pageUser;
    
	@Autowired
    public JugadorController(JugadorService jugadorService, UserService userService, LogrosService logrosService, PartidaService partidaService,UserServicePageable pageUser){
        this.jugadorService= jugadorService;
		this.logrosService=logrosService;
		this.pageUser=pageUser;
    }


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
    @GetMapping(value = "/jugador/new")
	public String initCreationForm(Map<String, Object> model) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}


    @PostMapping(value = "/jugador/new")
	public String processCreationForm(@Valid Jugador jugador, BindingResult result) {
		if (result.hasErrors()) {			
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			User user = jugador.getUser();
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			

			if(violations.isEmpty()){
				try{
					UsernamePasswordAuthenticationToken authReq= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
				
					SecurityContextHolder.getContext().setAuthentication(authReq);
					jugador.setAllStats0();
					this.jugadorService.saveJugador(jugador);
					List<Logros> logros = logrosService.setLogrosJugadorCreado(jugador);
					logrosService.save(logros.get(0));
					logrosService.save(logros.get(1));
					logrosService.save(logros.get(2));
					
					
					return "redirect:/";
				}catch (DataIntegrityViolationException ex){
					result.rejectValue("user.username", "Nombre de usuario duplicado","Este nombre de usuario ya esta en uso");
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
			}

			else{
				for(ConstraintViolation<User> v : violations){
					result.rejectValue("user."+ v.getPropertyPath(),v.getMessage(),v.getMessage());
				}
								
				return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
		}
	}
	//Editar jugador
	@GetMapping(value = "/jugador/edit/{id}")
	public String initEditForm(Model model, @PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				String user = currentUser.getUsername();
				try{
					Jugador player = jugadorService.findJugadorByUsername(user);
					Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
					for (GrantedAuthority usuarioR : usuario){
					String credencial = usuarioR.getAuthority();
						if(player.getId()==id || credencial.equals("admin")){
							Jugador jugador = jugadorService.findJugadorById(id);
							String username = jugador.getUser().getUsername();
							String pass = jugador.getUser().getPassword();
							
							model.addAttribute("pass", pass);
							model.addAttribute("username", username);
							
							model.addAttribute(jugador);
							return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
						}else{
							return "welcome";
						}
					}
				} catch (DataIntegrityViolationException ex){
					
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
				
				
			}return "welcome";
		}
		return "welcome";
	
	}
	
	
	@PostMapping(value = "/jugador/edit/{id}")
	public String processEditForm(@Valid Jugador jugador, BindingResult result, @PathVariable("id") int id, Map<String, Object> model){
		if(result.hasErrors()){
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			User user = jugador.getUser();
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			

			if(violations.isEmpty()){
				try{
					//SI SE EDITA UN JUGADOR, SE PIERDEN LAS STATS Y LOS LOGROS : REGLA DE NEGOCIO
					jugador.setId(id);
					jugador.setAllStats0();
					this.jugadorService.saveJugador(jugador);
					List<Logros> conjunto = logrosService.findLogrosJugadorNull();
					for (Logros logro:conjunto){
						logro.setJugador(jugador);
					}
					jugadorService.setCreatorYCreatedDate(jugador);
					model.put("message","Jugador editado correctamente");
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
					Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
					for (GrantedAuthority usuarioR : usuario){
						String credencial = usuarioR.getAuthority();
						if(credencial.equals("admin")){
							return "redirect:/jugador/perfil/" + jugador.getId();
						} else {
							return "redirect:/jugador/perfil";
						}
					}
					return "welcome";
				
				}catch (DataIntegrityViolationException ex){
					result.rejectValue("user.username", "Nombre de usuario duplicado","Este nombre de usuario ya esta en uso");
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
			}else{
				for(ConstraintViolation<User> v : violations){
					result.rejectValue("user."+ v.getPropertyPath(),v.getMessage(),v.getMessage());
				}
								
				return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
		}
	}

	//Ver tu perfil siendo jugador
	@GetMapping(value = "/jugador/perfil")
	public String verPerfilJugador(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				String usuario = currentUser.getUsername();
				Jugador jugador = jugadorService.findJugadorByUsername(usuario);
				model.addAttribute("id", jugador.getId());
				model.addAttribute(jugador);
				return "jugador/showJugador";
			}
			return "redirect:/";
		}
		return "redirect:/";
	
	}

	@GetMapping(value = "/jugador/perfil/{id}")
	public String verPerfilJugadorADMIN(@PathVariable("id") int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					Jugador jugador = jugadorService.findJugadorById(id);
					model.addAttribute("id", jugador.getId());
					model.addAttribute(jugador);
					return "jugador/showJugadorByIdADMIN";
				} else {
					return "welcome";
				}
			}	
		} else {
		return "redirect:/";
		}
		return "exception";
	}
	

	
	@GetMapping(path = "/jugador/delete/{id}")
	public String deleteJugador(@PathVariable("id") int id, Map<String, Object> model,@RequestParam(defaultValue="0") int page) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if(auth != null){
		org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
		for (GrantedAuthority usuarioR : usuario){
			String credencial = usuarioR.getAuthority();
			if (credencial.equals("admin")) {
				Jugador jugador = jugadorService.findJugadorById(id);
				if(jugador.getUser().getUsername().equals(currentUser.getUsername())){ //para que el admin no pueda eliminarse a sí mismo
					Pageable pageable = PageRequest.of(page,4);
					Page<User> users = pageUser.findAllUsers(pageable);
					model.put("users", users);
					model.put("message","Un administrador no puede eliminarse a sí mismo");
					return "redirect:/users/all?page=0";
				} else {
					List<Logros> listaLogros = logrosService.findById(jugador.getId());
					for(Logros logro : listaLogros){
						logrosService.delete(logro);
					}
					jugadorService.deleteJugador(jugador);
					Pageable pageable = PageRequest.of(page,4);
					Page<User> users = pageUser.findAllUsers(pageable);
					model.put("users", users);
					model.put("message","Jugador eliminado correctamente");
					return "redirect:/users/all?page=0";
				}
				
			}
		}
		 
	}else{
		return "exception";
	}
	return "exception";
	}


	

}
