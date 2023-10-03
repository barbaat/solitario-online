package org.springframework.samples.petclinic.mazoInicial;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mazo_inicial")
@Getter
@Setter


public class MazoInicial extends BaseEntity{
    
    @Column(name = "cantidad")
    @NotNull
    private Integer cantidad = 24;

    @OneToMany(mappedBy = "mazoInicial")
	private Set<CartasPartida> cartasPartida;

}
