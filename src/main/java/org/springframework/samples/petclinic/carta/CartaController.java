package org.springframework.samples.petclinic.carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartaController {

    private final CartaService cartaService;

    @Autowired
	public CartaController(CartaService cartaService) {
		this.cartaService = cartaService;
	}

	@GetMapping(value = { "/cartas" })
	public String showVetList(Map<String, Object> model) {
		List<Carta> cartas = new ArrayList<>();
        cartas = (List<Carta>) cartaService.findCartas();
		model.put("cartas", cartas);
		return "cartas/cartaList";
	}
	
    
}
