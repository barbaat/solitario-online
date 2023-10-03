package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaRepository;
import org.springframework.samples.petclinic.carta.Palo;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaRepository;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazo.MazoRepository;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;
import org.springframework.samples.petclinic.mazoFinal.MazoFinalRepository;
import org.springframework.samples.petclinic.mazoInicial.MazoInicial;
import org.springframework.samples.petclinic.mazoInicial.MazoInicialRepository;
import org.springframework.stereotype.Service;

@Service
public class PartidaBuilder {

	

	public List<Carta> inicializarCartas() {
		List<Carta> res = new ArrayList<>();
		String rutaCarta = "/resources/images/cards/";
		for (int palo = 1; palo <= 4; palo++) {
			for (int valor = 1; valor <= 13; valor++) {
				Carta carta = new Carta();
				carta.setValor(valor);
				if (palo == 1) {

					carta.setPalo(Palo.PICAS);
					carta.setImagen(rutaCarta+String.valueOf(valor)+String.valueOf(palo)+".png");
				}
				if (palo == 2) {

					carta.setPalo(Palo.TREBOLES);
					carta.setImagen(rutaCarta+String.valueOf(valor)+String.valueOf(palo)+".png");
				}
				if (palo == 3) {

					carta.setPalo(Palo.DIAMANTES);
					carta.setImagen(rutaCarta+String.valueOf(valor)+String.valueOf(palo)+".png");
				}
				if (palo == 4) {

					carta.setPalo(Palo.CORAZONES);
					carta.setImagen(rutaCarta+String.valueOf(valor)+String.valueOf(palo)+".png");
				}

				res.add(carta);

			}
		}
		return res;
	}

	public List<CartasPartida> inicializaCartasPartidas(Partida p) {
		List<CartasPartida> res = new ArrayList<>();
		List<Carta> cartas = inicializarCartas();
		for (Carta c : cartas) {
			cartaRepository.save(c);
			CartasPartida cp = new CartasPartida();
			cp.setCarta(c);
			cp.setPartida(p);
			cp.setPosCartaMazo(0);
			res.add(cp);
			
		}
		return res;
	}

	// 24 cartas a mazo inicial
	// 28 cartas a mazo intermedio
	// mazo intermedio tiene 7 mazos con 7,6,5,4,3,2,1 cartas
	public List<Mazo> inicializaMazoIntermedio() {
		List<Mazo> res = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			Mazo m = new Mazo();
			m.setPosicion(i);
			m.setCantidad(0);
			res.add(m);

		}
		return res;
	}

	public List<MazoFinal> inicializaMazoFinal() {
		List<MazoFinal> res = new ArrayList<>();

		for (int i = 0; i <= 3; i++) {
			MazoFinal mf = new MazoFinal();
			mf.setPosicion(i);
			mf.setCantidad(0);
			//mf.setCartasPartida(cp);
			mazoFinalRepository.save(mf);
			res.add(mf);
		}
		return res;
	}

	@Autowired
	private MazoRepository mazoRepository;


	@Autowired
	private MazoFinalRepository mazoFinalRepository;

	@Autowired
	private MazoInicialRepository mazoInicialRepository;


	@Autowired
	private CartaRepository cartaRepository;

	@Autowired
	private CartasPartidaRepository cartasPartidaRepository;

	// Añade cartas a los mazos intermedios, cada una a una posición
	public void crearMazosIntermedios(Partida p) {

		// Lista de cartasPartida que son 52
		List<CartasPartida> cartasP = inicializaCartasPartidas(p);
		// Lista de mazos que son 7
		List<Mazo> mazos = inicializaMazoIntermedio();
		// Lista mazos finales inicializados
		inicializaMazoFinal();

		MazoInicial mI = new MazoInicial();

		List<CartasPartida> auxCP = new ArrayList<>();
		// Obtengo uno de los mazos de la lista
		
		for (int j = 0; j < 7; j++) {
			Mazo mact = mazos.get(j);

			/*
			 * Recorro las posiciones del mazo para ir asignando
			 * a cada una de las posibles posiciones una carta
			 */
			Set<CartasPartida> setAux = new HashSet<>();
			for (int k = 1; k <= mact.getPosicion(); k++) {

				// Obterno un indice aleatorio entre 0 y 51
				int random = (int) (Math.random() * (cartasP.size() - 1));

				// Obtengo la cartaPartida con el indice aleatorio anterior
				CartasPartida cp = cartasP.get(random);

				// Asigno la posición actual al mazo
				cp.setPosCartaMazo(k);
				
				if(k==mact.getPosicion()){
					cp.setIsShow(true);
				}else{
					cp.setIsShow(false);
				}
			
				// Indico la cantidad de cartas que hay ahora en el mazo
				mact.setCantidad(k);

				mazoRepository.save(mact);
				// Asigno el mazo a la CartaPartida
				cp.setMazo(mact);
				cp.setPartida(p);
				cartasPartidaRepository.save(cp);
				auxCP.add(cp);
				setAux.add(cp);
				
				// Borro la carta de la lista para que no se repita
				cartasP.remove(random);
				
			}
			
			
		}
		
		Set<CartasPartida> aux = new HashSet<>(); 
		for (CartasPartida carta : cartasP){
			aux.add(carta);
			mI.setCartasPartida(aux);
			mazoInicialRepository.save(mI);
		}

			Collections.shuffle(cartasP);

		for (int k = 1; k <= aux.size(); k++){
			
			int random = (int) (Math.random() * (cartasP.size() - 1));
			CartasPartida cp = cartasP.get(random);
			cp.setMazoInicial(mI);
			cp.setPosCartaMazo(k);
			cp.setIsShow(true);
			cartasPartidaRepository.save(cp);
			cartasP.remove(cp);
		}


		
			
	}


	

}
