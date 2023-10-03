package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.jugador.Jugador;


import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.cartasPartida.CartasPartida;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Partida extends BaseEntity {

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false,mappedBy = "partida")
	private Set<CartasPartida> cartasPartida;
	
	@NotNull
	@Column(name = "momento_inicio")
	private LocalDateTime momentoInicio;
	
	
	@Column(name = "momento_fin")
	private LocalDateTime momentoFin;
	
	
	@Column(name = "victoria")
	private Boolean victoria;
		
	@Column(name = "num_movimientos")
	private long numMovimientos;

	public long puntos() {
		if(numMovimientos==0) {
			return 0;
		}else {
			if(momentoFin == null){
				long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, LocalDateTime.now());
				return numMovimientos*10/diffInSeconds;
			} else {
				long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, momentoFin);
				return numMovimientos*10/diffInSeconds;
			}
			
		}
	}
	
	public long getDuracionMaxMin (){
		if(momentoFin == null){
			return ChronoUnit.SECONDS.between(momentoInicio, LocalDateTime.now());
		} else {
			return ChronoUnit.SECONDS.between(momentoInicio, momentoFin);
		}
	}
	
	public String duracion() {
		if(momentoFin == null) {
			long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, LocalDateTime.now());
			long minutos = diffInSeconds/60;
			long segundos = diffInSeconds%60;
			return String.valueOf(minutos) + " minutos y " + String.valueOf(segundos) + " segundos";
		}else {
			long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, momentoFin);
			long minutos = diffInSeconds/60;
			long segundos = diffInSeconds%60;
			return String.valueOf(minutos) + " minutos y " + String.valueOf(segundos) + " segundos";
		}
		
		
	}

	public String momentoInicioString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm dd'/'MM'/'yyyy");
		return formato.format(momentoInicio);
	}	
	public String momentoFinString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm dd'/'MM'/'yyyy");
		return formato.format(momentoFin);
	}
	
	@OneToOne
    @JoinColumn(name="jugadorId")
    Jugador jugador;


}
