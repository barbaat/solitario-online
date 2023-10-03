package org.springframework.samples.petclinic.estadisticas;

import java.time.Duration;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadisticasController {
    
	private static final String VIEWS_ESTADISTICAS_JUGADOR = "estadisticas/estadisticasJugador";
		private static final String VIEWS_RANKINGS = "ranking/rankingGeneral";

    private final EstadisticasService estadisticasService;
	private final JugadorService jugadorService;
    
	@Autowired
    public EstadisticasController(JugadorService jugadorService, EstadisticasService estadisticasService){
        this.jugadorService= jugadorService;
		this.estadisticasService=estadisticasService;
    }

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping(value = "/jugador/estadisticas/{id}")
	public ModelAndView mostrarEstadisticasAdmin(@PathVariable("id") int id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					ModelAndView result = new ModelAndView(VIEWS_ESTADISTICAS_JUGADOR);
					Jugador jugador = jugadorService.findJugadorById(id);
					estadisticasService.setEstadisticasJugador(jugador);
					setEstadisticasGenerales(result,jugador);
					return result;
				} else {
					ModelAndView result = new ModelAndView("redirect:/");
					return result;
				}
			}
		} else {
			ModelAndView result = new ModelAndView("redirect:/");
			return result;
		}
		return new ModelAndView("exception");
	}

	@GetMapping(value = "/jugador/estadisticas")
	public ModelAndView mostrarEstadisticasUsuario(){
		ModelAndView result = new ModelAndView(VIEWS_ESTADISTICAS_JUGADOR);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Jugador jugador = jugadorService.findJugadorByUsername(username);
		estadisticasService.setEstadisticasJugador(jugador);
		setEstadisticasGenerales(result,jugador);
		return result;
	}

	@GetMapping("/ranking")
    public String showRanking(Map<String, Object> model){
        
			List<Jugador> jugadores = jugadorService.findAllPlayer();
			
			Comparator<Jugador> comparador= Comparator.comparing(Jugador::getPartidasGanadas);
			Comparator<Jugador> comparador2= Comparator.comparing(Jugador::getNumTotalPuntos);
			Comparator<Jugador> comparador3= Comparator.comparing(Jugador::getNumTotalMovimientos);
			
			List<Jugador> ranking1 = jugadores.stream().sorted(comparador.reversed()).collect(Collectors.toList());
			List<Jugador> ranking2 = jugadores.stream().sorted(comparador2.reversed()).collect(Collectors.toList());
			List<Jugador> ranking3 = jugadores.stream().sorted(comparador3.reversed()).collect(Collectors.toList());

			model.put("jugadoresWIN", ranking1);
			model.put("jugadoresPTN", ranking2);
			model.put("jugadoresMOV", ranking3);
			
			return VIEWS_RANKINGS;
	}

	public void setEstadisticasGenerales(ModelAndView result,Jugador jugador){
		List<Partida> listPartidas = estadisticasService.findAll().stream().filter(x -> x.getMomentoFin() != null).collect(Collectors.toList());
		Integer ganadas = (int) listPartidas.stream().filter(x -> x.getVictoria()==true).count();
		Integer perdidas = (int) listPartidas.stream().filter(x -> x.getVictoria()==false).count();
		Integer puntos = (int) listPartidas.stream().mapToLong(x -> x.puntos()).sum();
		Integer movimientos = (int) listPartidas.stream().mapToLong(x -> x.getNumMovimientos()).sum();
		long duracionTotal = listPartidas.stream().mapToInt(x -> (int) x.getDuracionMaxMin()).sum();
		Duration duration = Duration.ofSeconds(duracionTotal);
		long horas = duration.toHours();
		long minutos = duration.toMinutes() % 60;
		long segundos = duration.getSeconds() % 60;


		if(listPartidas.size()==0){
			result.addObject("partidasTotalesJugadas", 0);
			result.addObject("partidasGanadasTotales", 0);
			result.addObject("partidasPerdidasTotales", 0);
			result.addObject("puntosPromedio", 0);
			result.addObject("movimientosPromedio", 0);
			result.addObject("horas",0);
			result.addObject("minutos",0);
			result.addObject("segundos",0);
			result.addObject("horasPromedio",0);
			result.addObject("minutosPromedio",0);
			result.addObject("segundosPromedio",0);
			result.addObject(jugador);
		
		} else {
			long duracionPromedioTotal = duracionTotal / listPartidas.size();
			Duration durationPromedio = Duration.ofSeconds(duracionPromedioTotal);
			long horasPromedio = durationPromedio.toHours();
			long minutosPromedio = durationPromedio.toMinutes() % 60;
			long segundosPromedio = durationPromedio.getSeconds() % 60;
			Integer puntosPromedio = puntos/listPartidas.size();
			Integer movPromedio = movimientos/listPartidas.size();
			result.addObject("partidasTotalesJugadas", listPartidas.size());
			result.addObject("partidasGanadasTotales", ganadas);
			result.addObject("partidasPerdidasTotales", perdidas);
			result.addObject("puntosPromedio", puntosPromedio);
			result.addObject("movimientosPromedio",movPromedio);
			result.addObject("horas",horas);
			result.addObject("minutos",minutos);
			result.addObject("segundos",segundos);
			result.addObject("horasPromedio",horasPromedio);
			result.addObject("minutosPromedio",minutosPromedio);
			result.addObject("segundosPromedio",segundosPromedio);
			result.addObject(jugador);
		}
	}
}



