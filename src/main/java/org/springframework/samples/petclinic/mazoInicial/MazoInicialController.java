package org.springframework.samples.petclinic.mazoInicial;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MazoInicialController {
    private final MazoInicialService mazoinicialservice;

    @Autowired
	public MazoInicialController(MazoInicialService mazoinicialservice) {
		this.mazoinicialservice = mazoinicialservice;
	}

	@GetMapping(value = { "/mazoinicial" })
	public String showMazoInicial(Map<String, Object> model,@PathVariable Integer id) {
        Optional<MazoInicial> mazosInicial = mazoinicialservice.findMazoInicialById(id);
		if(mazosInicial.isPresent()){
			model.put("mazosinicial",  mazosInicial);
		}else{
			model.put("message","No se pudo encontrar el mazo inicial con id <id>");
		}
		
		return "mazoinicial";
	}

}
