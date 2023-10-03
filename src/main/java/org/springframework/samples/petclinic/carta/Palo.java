package org.springframework.samples.petclinic.carta;

public enum Palo {
    TREBOLES, CORAZONES, PICAS, DIAMANTES;
    public Color getColor(Palo p){
        if(p == Palo.CORAZONES || p == Palo.DIAMANTES){
            return Color.ROJO;
        }else{
            return Color.NEGRO;
        }
    }
}
