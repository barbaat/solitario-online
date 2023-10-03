package org.springframework.samples.petclinic.logros;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "logros")
public class Logros extends BaseEntity{
	
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_unlocked")
	private Boolean is_unlocked;
	
	@Column(name="image")
	private String image;
	
	@Column(name="num_condicion")
	private Integer numCondicion;

	@OneToOne
    @JoinColumn(name="jugador_id")
    Jugador jugador;
}