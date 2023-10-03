package org.springframework.samples.petclinic.mazoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class MazoFinalController {

    private final MazoFinalService mazoinicialservice;

    @Autowired
	public MazoFinalController(MazoFinalService mazoinicialservice) {
		this.mazoinicialservice = mazoinicialservice;
	}

	@GetMapping(value = { "/mazos" })
	public String showMazosFinales(Map<String, Object> model) {
		List<MazoFinal> mazosIniciales = new ArrayList<>();
        mazosIniciales = (List<MazoFinal>) mazoinicialservice.findAllMazosFinales();
		model.put("mazosfinales",  mazosIniciales);
		return "mazos/mazosfinales";
	}
    
}
