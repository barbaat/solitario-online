package org.springframework.samples.petclinic.mazoFinal;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mazo_final")
@Getter
@Setter

public class MazoFinal extends BaseEntity{
    @Column(name = "posicion")
    @NotNull
    private Integer posicion;

    @Column(name = "cantidad")
    @Max(52)
    private Integer cantidad;

    @OneToMany(mappedBy = "mazoFinal")
	private Set<CartasPartida> cartasPartida;

}
