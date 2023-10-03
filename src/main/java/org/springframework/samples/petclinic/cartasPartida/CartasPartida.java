package org.springframework.samples.petclinic.cartasPartida;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;
import org.springframework.samples.petclinic.mazoInicial.MazoInicial;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "cartasPartida",uniqueConstraints = {@UniqueConstraint(name="UniqueMazoCarta",columnNames = {"cartaId","mazoId"}),
@UniqueConstraint(name="UniqueMazoCarta",columnNames = {"cartaId","mazoInicialId"}),
@UniqueConstraint(name="UniqueMazoCarta",columnNames = {"cartaId","mazoFinalId"})
})

public class CartasPartida extends BaseEntity {
    
    //1 si se puede coger , n si no se puede coger
    @NotNull
    private Integer posCartaMazo;

    @ManyToOne(optional=false)
    private Partida partida;

    @JoinColumn(name="mazoId")
    @ManyToOne
    //(optional=false)
    private Mazo mazo;

    @JoinColumn(name="mazoInicialId")
    @ManyToOne
        //optional=false)
    private MazoInicial mazoInicial;

    @JoinColumn(name="mazoFinalId")
    @ManyToOne
    //(optional=false)
    private MazoFinal mazoFinal;

    @OneToOne
    @JoinColumn(name="cartaId")
    private Carta carta;

    private Boolean isShow;
    
}
