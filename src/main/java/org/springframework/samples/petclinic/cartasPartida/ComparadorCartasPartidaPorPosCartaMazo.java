package org.springframework.samples.petclinic.cartasPartida;
import java.util.Comparator;

public class ComparadorCartasPartidaPorPosCartaMazo implements Comparator<CartasPartida> {

    @Override
    public int compare(CartasPartida o1, CartasPartida o2) {
        return Integer.compare(o1.getPosCartaMazo(), o2.getPosCartaMazo());
       
    }
    
}
