package org.springframework.samples.petclinic.carta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cartas")
@Getter
@Setter
public class Carta extends BaseEntity{
    
    @NotNull
    @Range(min = 1, max = 13)
    @Column(name = "valor")
    private Integer valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Palo palo;

    @OneToOne(mappedBy = "carta")
    private CartasPartida cartasPartida;
    
    private String imagen;

}
