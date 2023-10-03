package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<>();
		Person Mario = new Person();
		Mario.setFirstName("Mario ");
		Mario.setLastName("Sánchez Naranjo");
		Person Jorge = new Person();
		Jorge.setFirstName("Jorge ");
		Jorge.setLastName("Sillero Manchon");
		Person FranciscoJavier = new Person();
		FranciscoJavier.setFirstName("Francisco Javier ");
		FranciscoJavier.setLastName("Barba Trejo");
		Person Alvaro = new Person();
		Alvaro.setFirstName("Álvaro ");
		Alvaro.setLastName("Navarro Rivera");
		Person Alberto = new Person();
		Alberto.setFirstName("Alberto ");
		Alberto.setLastName("Gallego Huerta");
		Person Francisco = new Person();
		Francisco.setFirstName("Francisco Andres ");
		Francisco.setLastName("Caro Albarrán");
		persons.add(Mario);
		persons.add(Jorge);
		persons.add(FranciscoJavier);
		persons.add(Francisco);
		persons.add(Alvaro);
		persons.add(Alberto);


		model.put("persons", persons);
		model.put("title","SOLITARIO");
		model.put("group","L5-3");

	    return "welcome";
	  }
}
