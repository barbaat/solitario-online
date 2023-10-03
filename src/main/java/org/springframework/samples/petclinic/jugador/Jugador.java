package org.springframework.samples.petclinic.jugador;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.logros.Logros;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jugador extends AuditableEntity{


	@Column(name = "first_name")
	@Length(min = 3, max = 20)
	@NotEmpty
	protected String firstName;

	@Column(name = "last_name")
	@Length(min = 3, max = 20)
	@NotEmpty
	protected String lastName;

	@Column(name = "image")
	protected String image;

    @Column(name = "win")
    private Integer partidasGanadas;
    @Column(name = "lost")
    private Integer partidasNoGanadas;
    @Column(name = "time")
    private LocalTime totalTiempoJugado;
    @Column(name = "mov")
    private Integer numTotalMovimientos;
    @Column(name = "points")
    private Integer numTotalPuntos;
    @Column(name = "max_movs")
    private Long numMaxMovimientosPartidaGanada;
    @Column(name = "min_movs")
    private Long numMinMovimientosPartidaGanada;
    @Column(name = "max_time")
    private String maxTiempoPartidaGanada;
    @Column(name = "min_time")
    private String minTiempoPartidaGanada;

    public void setAllStats0() {
		this.partidasGanadas = 0;
        this.partidasNoGanadas = 0;
        this.totalTiempoJugado = LocalTime.of(0, 0, 0);
        this.numTotalMovimientos = 0;
        this.numTotalPuntos = 0;
        this.numMaxMovimientosPartidaGanada=(long) 0;
        this.numMinMovimientosPartidaGanada=(long) 0;
        this.maxTiempoPartidaGanada="0 minutos y 0 segundos";
        this.minTiempoPartidaGanada="0 minutos y 0 segundos";
	}
    

    

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = false)
    @JoinColumn(name = "username", referencedColumnName = "username")
	User user;  
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false,mappedBy = "jugador")
    Set<Partida> partidasJugadas;

    public Integer getPartidasJugadas(){
        return partidasJugadas.stream().filter(x -> x.getMomentoFin()!=null).collect(Collectors.toList()).size();
    }
    
    @OneToOne
    @JoinColumn(name="jugador_id")
    Logros logros;
    

}
