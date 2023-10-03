package org.springframework.samples.petclinic.mazo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mazos")
public class Mazo extends BaseEntity{
    
    @NotNull
    @Range(min = 1, max = 7)
    private Integer posicion;

    private Integer cantidad;

    @OneToMany(mappedBy = "mazo")
	private Set<CartasPartida> cartasPartida;
}
