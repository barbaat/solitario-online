package org.springframework.samples.petclinic.cartasPartida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.Palo;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazo.MazoService;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;
import org.springframework.samples.petclinic.mazoFinal.MazoFinalService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.util.Tuple3;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartasPartidaService {
    //==============================INYECCION DE DEPENDENCIAS==============================
    private CartasPartidaRepository cartasPartidaRepository;
    

    @Autowired
    private MazoService mazoService;

    @Autowired
    private MazoFinalService mazoFinalService;

    @Autowired
    private PartidaService partidaService;



    //==============================METODOS BASICOS DE OBTENCION DE DATOS==============================
    @Autowired
    public CartasPartidaService(CartasPartidaRepository cartasPartidaRepository) {
        this.cartasPartidaRepository = cartasPartidaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<CartasPartida> findCartasPartidaById(Integer id) throws DataAccessException {
        return cartasPartidaRepository.findById(id);
    }

    public List<CartasPartida> findCartasPartidaByPartidaId(int partidaId) {
        List<CartasPartida> res = cartasPartidaRepository.findCartasPartidaByPartidaId(partidaId);
        return res;

    }

    public CartasPartida findCartasPartidaByCartaId(int cartaId) {
        return cartasPartidaRepository.findCartasPartidaByCartaId(cartaId);
    }

    public List<Carta> findCartasByMazoIdList(int mazoId) {
        return cartasPartidaRepository.findCartasByMazoIdList(mazoId);
    }
    public List<CartasPartida> findCartasPartidaByMazoFinalId(Integer idMazo) {
        return cartasPartidaRepository.findCartasPartidaByMazoFinalId(idMazo);
    }

    public List<CartasPartida> findCartasPartidaByMazoFinalIdAndPartidaId(Integer idMazo, Integer partidaId) {
        return cartasPartidaRepository.findCartasPartidaByMazoFinalIdAndPartidaId(idMazo, partidaId);
    }

    @Transactional
    public void saveCartasPartida(CartasPartida cp) throws DataAccessException {
        cartasPartidaRepository.save(cp);
    }

    public List<CartasPartida> findCartasPartidaByMazoId(Integer mazoId) {
        List<CartasPartida> res = cartasPartidaRepository.findCartasPartidaByMazoId(mazoId);
        return res;
    }

    public List<CartasPartida> findCartasPartidaMazoInicialByPartidaId(Integer partidaId) {
        List<CartasPartida> res = cartasPartidaRepository.findCartasPartidaMazoInicial(partidaId);
        return res;
    }

    public List<CartasPartida> findCartasPartidaByMazoIdAndPartidaId(Integer mazoId, Integer partidaId){
        return cartasPartidaRepository.findCartasPartidaByMazoIdAndPartidaId(mazoId,partidaId);
    }





    //==============================OBTENER ID DE MAZOS==============================
    public List<Integer> getMazosIdSorted(Integer partidaId) {

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (partidaId > 1) {
                res.add(i + ((partidaId - 1) * 7));

            } else {
                res.add(i);
            }
        }

        return res;
    }
    public List<Integer> getMazosFinalIdSorted(Integer partidaId) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            if (partidaId > 1) {
                res.add(i + ((partidaId - 1) * 4));

            } else {
                res.add(i);
            }
        }
        return res;
    }

    //==============================VALIDACIONES==============================
    
    
    @Transactional
    public boolean validarVictoria(Integer partidaId){
        Integer res =0;
        List<Integer> listaMazosFinales = getMazosFinalIdSorted(partidaId);

        for (Integer m : listaMazosFinales) {
            MazoFinal mf = cartasPartidaRepository.findMazoFinalByIdAndPartidaId(m,partidaId);
            if(mf!=null && mf.getCantidad()==13){
                
                    res++;
            }
        }
        if (res==4){
            return true;
        }else{
            return false;
        }
    }
    
    
    
    @Transactional
    public boolean mazoFinalCompleto(Integer mazoId, Integer partidaId){
        if(mazoId<8){
            return false;
        }else{
            MazoFinal mazoFinal = mazoFinalService.findMazoFinalById((mazoId-7) +((partidaId-1)*4));
            boolean res = mazoFinal.getCantidad() == 13;
            return res;
        }
    }

    public boolean mazosCompletos(Integer partidaId){
        boolean res = true;
        int primerMazoFinalId = 1+((partidaId-1)*4);
        List<Integer> mazoFinalIds = new ArrayList<>();
        mazoFinalIds.add(primerMazoFinalId);
        mazoFinalIds.add(primerMazoFinalId+1);
        mazoFinalIds.add(primerMazoFinalId+2);
        mazoFinalIds.add(primerMazoFinalId+3);
        for(Integer mazoFinalId: mazoFinalIds){
            List<CartasPartida> mazoFinal = cartasPartidaRepository.findCartasPartidaByMazoFinalIdAndPartidaId(mazoFinalId, partidaId);
            if(mazoFinal.size()==13){
                res = res && true;
            }else{
                res = res && false;
            }
        } 
        return res;
    }


    // Solo se debe validar cuando esté algún mazo intermedio como destino y se debe
    // validar la primera carta de la cantidad con la última carta del mazo destino
    public Boolean validaColorYEscaleraDesdeMazoIntermedioAMazoIntermedio (int mazoOrigen, int mazoDestino, int cantidad, int partidaId){
        
        Boolean res = false;
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaByMazoId(mazoOrigen);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoId(mazoDestino);
        List<CartasPartida> cartasVisibles= new ArrayList<>();
        for(CartasPartida cp:cpOrigen){
            if(cp.getIsShow()==true){
                cartasVisibles.add(cp);
            }
        }     
        if(cpOrigen.size()==0 || cpDestino==null || cartasVisibles.size()<cantidad){
            return false;
        }else{

            int startIndex = cpOrigen.size() - cantidad;
            Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
            //Collections.sort(cpDestino, new ComparadorCartasPartidaPorPosCartaMazo());

            List<CartasPartida> cartasMovidas = cpOrigen.subList(startIndex, cpOrigen.size());

            CartasPartida cartaPrimeraAMover = cartasMovidas.get(0);
            if(cpDestino.size()==0){
                if(cartaPrimeraAMover.getCarta().getValor()==13){
                    res = true;
                }
            }else{
                CartasPartida cartaUltimaMazoDestino = cpDestino.get(cpDestino.size()-1); 
            
                Carta cartaAbajo = cartaPrimeraAMover.getCarta();
                Carta cartaArriba = cartaUltimaMazoDestino.getCarta();
                
                if(!(cartaAbajo.getPalo().getColor(cartaAbajo.getPalo()).equals(cartaArriba.getPalo().getColor(cartaArriba.getPalo())))){
                    if (cartaAbajo.getValor() + 1 == cartaArriba.getValor()){
                        res = true;
                    }
                }
            }

            return res;
        }
    }


    
    public Boolean validaColorYEscaleraDesdeMazoInicialAMazoIntermedio (int mazoOrigen, int mazoDestino, int cantidad, int partidaId){
        
        Boolean res = false;
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaMazoInicial(partidaId);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoId(mazoDestino);
        
            if(cpOrigen.size()==0 || cpDestino==null ){
                return false;
            }else{


            int startIndex = cpOrigen.size() - 1;
            Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
            List<CartasPartida> cartasMovidas = cpOrigen.subList(startIndex, cpOrigen.size());

            CartasPartida cartaPrimeraAMover = cartasMovidas.get(0);
            if(cpDestino.size()==0){
                if(cartaPrimeraAMover.getCarta().getValor()==13){
                    res = true;
                }
            }else{
                CartasPartida cartaUltimaMazoDestino = cpDestino.get(cpDestino.size()-1); 
            
                Carta cartaAbajo = cartaPrimeraAMover.getCarta();
                Carta cartaArriba = cartaUltimaMazoDestino.getCarta();

                if(cartaAbajo.getPalo().getColor(cartaAbajo.getPalo()) != cartaArriba.getPalo().getColor(cartaArriba.getPalo())){
                    if (cartaAbajo.getValor() + 1 == cartaArriba.getValor()){
                        res = true;
                    }
                }
            }
            
            return res;
        }
    }

    public Boolean validaMovimientoIntermedioMazoFinal (int mazoOrigen, int mazoDestino, int cantidad, int partidaId){
        boolean res = true;
        

        // Obtiene lista de cartas partida de los mazos origen y destino
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaByMazoId(mazoOrigen);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoFinalId(mazoDestino);
        if(cpOrigen.size()==0 || cpDestino==null){
            return false;
        }else{
        
        List<Integer> mazosFinales = getMazosFinalIdSorted(partidaId);

        Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
        Carta cartaMovida = cpOrigen.get(cpOrigen.size()-1).getCarta();
        
        if(cpDestino.size()==0){
            if(mazoDestino==mazosFinales.get(0) && cartaMovida.getPalo() == Palo.CORAZONES && cartaMovida.getValor()==1){
                res = true;
            }else if(mazoDestino==mazosFinales.get(1) && cartaMovida.getPalo() == Palo.PICAS && cartaMovida.getValor()==1){
                res = true;
            }else if(mazoDestino==mazosFinales.get(2) && cartaMovida.getPalo() == Palo.DIAMANTES && cartaMovida.getValor()==1){
                res = true;
            }else if(mazoDestino==mazosFinales.get(3) && cartaMovida.getPalo() == Palo.TREBOLES && cartaMovida.getValor()==1){
                res = true;
            }else{
                res = false;
            }
        }   
        else{
            Carta ultCartaMazoFinal = cpDestino.get(cpDestino.size()-1).getCarta();
            if(cartaMovida.getValor()==ultCartaMazoFinal.getValor()+1 && cartaMovida.getPalo()==ultCartaMazoFinal.getPalo()){
                res = true;
            }else{
                res = false;
            }
        }
        

        return res;
    }
    }

    public Boolean validaMovimientoInicialMazoFinal (int mazoOrigen, int mazoDestino, int partidaId){
        boolean res = true;

        // Obtiene lista de cartas partida de los mazos origen y destino
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaMazoInicial(partidaId);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoFinalId(mazoDestino);
        if(cpOrigen.size()==0 || cpDestino==null){
                return false;
            }else{

            Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
            Collections.sort(cpDestino, new ComparadorCartasPartidaPorPosCartaMazo());
            Carta cartaMovida = cpOrigen.get(cpOrigen.size()-1).getCarta();
            
            List<Integer> mazosFinales = getMazosFinalIdSorted(partidaId);


            if(cpDestino.size()==0){
                if(mazoDestino==mazosFinales.get(0) && cartaMovida.getPalo() == Palo.CORAZONES && cartaMovida.getValor()==1){
                    res = true;
                }else if(mazoDestino==mazosFinales.get(1) && cartaMovida.getPalo() == Palo.PICAS && cartaMovida.getValor()==1){
                    res = true;
                }else if(mazoDestino==mazosFinales.get(2) && cartaMovida.getPalo() == Palo.DIAMANTES && cartaMovida.getValor()==1){
                    res = true;
                }else if(mazoDestino==mazosFinales.get(3) && cartaMovida.getPalo() == Palo.TREBOLES && cartaMovida.getValor()==1){
                    res = true;
                }else{
                    res = false;
                }
            }   
            else{
                Carta ultCartaMazoFinal = cpDestino.get(cpDestino.size()-1).getCarta();
                if(cartaMovida.getValor()==ultCartaMazoFinal.getValor()+1 && cartaMovida.getPalo()==ultCartaMazoFinal.getPalo()){
                    res = true;
                }else{
                    res = false;
                }
            }
            

            return res;
            }
    }

    public Boolean validacionMovimiento(int mazoOrigenId, int mazoDestinoId, int cantidadCartas, int partidaId) {
        Boolean res = true;
        
        if(mazoOrigenId==mazoDestinoId){
            res=false;
        }
        
        if (partidaId > 1) {

            if (mazoOrigenId > 0) {
                mazoOrigenId = mazoOrigenId + ((partidaId - 1) * 7);
                if (mazoDestinoId <= 7) {
                    mazoDestinoId = mazoDestinoId + ((partidaId - 1) * 7);
                    res = res && validaColorYEscaleraDesdeMazoIntermedioAMazoIntermedio(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1 + ((partidaId - 1) * 4);
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2 + ((partidaId - 1) * 4);
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3 + ((partidaId - 1) * 4);
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4 + ((partidaId - 1) * 4);
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
            } else {
                if (mazoDestinoId <= 7) {
                    mazoDestinoId = mazoDestinoId + ((partidaId - 1) * 7);
                    mazoOrigenId = partidaId;
                    res = res && validaColorYEscaleraDesdeMazoInicialAMazoIntermedio(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                }
            }
        } else {

            if (mazoOrigenId > 0) {

                if (mazoDestinoId <= 7) {
                    res = res && validaColorYEscaleraDesdeMazoIntermedioAMazoIntermedio(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1;
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2;
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3;
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4;
                    res = res && validaMovimientoIntermedioMazoFinal(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
            } else {
                if (mazoDestinoId <= 7) {
                    mazoOrigenId = partidaId;
                    mazoOrigenId = partidaId;
                    res = res && validaColorYEscaleraDesdeMazoInicialAMazoIntermedio(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1;
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2;
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3;
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4;
                    mazoOrigenId = partidaId;
                    res = res && validaMovimientoInicialMazoFinal(mazoOrigenId, mazoDestinoId, partidaId);
                }
            }
        }

        return res;
    }

    //==============================MOVIMIENTOS DE CARTAS==============================
public Tuple3 moverCartas(int mazoOrigenId, int mazoDestinoId, int cantidadCartas, int partidaId) {
        Tuple3 res = new Tuple3(null, null, null);
        if (partidaId > 1) {

            if (mazoOrigenId > 0) {
                mazoOrigenId = mazoOrigenId + ((partidaId - 1) * 7);
                if (mazoDestinoId <= 7) {
                    mazoDestinoId = mazoDestinoId + ((partidaId - 1) * 7);
                    res = moverCartaInterInter(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1 + ((partidaId - 1) * 4);
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2 + ((partidaId - 1) * 4);
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3 + ((partidaId - 1) * 4);
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4 + ((partidaId - 1) * 4);
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                }
            } else {
                if (mazoDestinoId <= 7) {
                    mazoDestinoId = mazoDestinoId + ((partidaId - 1) * 7);
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialInter(mazoOrigenId, mazoDestinoId, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4 + ((partidaId - 1) * 4);
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                }
            }
        } else {

            if (mazoOrigenId > 0) {

                if (mazoDestinoId <= 7) {
                    res = moverCartaInterInter(mazoOrigenId, mazoDestinoId, cantidadCartas, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1;
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2;
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3;
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4;
                    res = moverCartaInterFin(mazoOrigenId, mazoDestinoId, partidaId);
                }
            } else {
                if (mazoDestinoId <= 7) {
                    mazoOrigenId = partidaId;
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialInter(mazoOrigenId, mazoDestinoId, partidaId);
                }
                else if (mazoDestinoId == 8) {
                    mazoDestinoId = 1;
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 9) {
                    mazoDestinoId = 2;
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 10) {
                    mazoDestinoId = 3;
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                } else if (mazoDestinoId == 11) {
                    mazoDestinoId = 4;
                    mazoOrigenId = partidaId;
                    res = moverCartaInicialFinal(mazoOrigenId, mazoDestinoId, partidaId);
                }
            }
        }
        Partida p = partidaService.findById(partidaId);
        p.setNumMovimientos(p.getNumMovimientos()+1);
        partidaService.save(p);
        return res;
    }

    //MOVIMIENTOS MAZOS INTERMEDIOS
    @Transactional
    public Tuple3 moverCartaInterInter(int mazoOrigenId, int mazoDestinoId, int cantidadCartas, int partidaId) {
        Mazo mazoDest = mazoService.findMazoById(mazoDestinoId);

        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaByMazoIdAndPartidaId(mazoOrigenId,
                partidaId);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoIdAndPartidaId(mazoDestinoId,
                partidaId);

        int startIndex = cpOrigen.size() - cantidadCartas;

        Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
        List<CartasPartida> cartasMovidas = cpOrigen.subList(startIndex, cpOrigen.size());

        int indexUltCarta = cpDestino.size();
        int i = 1;
        for (CartasPartida cp : cartasMovidas) {
            cp.setMazo(mazoDest);
            cp.setPosCartaMazo(indexUltCarta + i);
            i++;
            cartasPartidaRepository.save(cp);
        }
        setCartaVisibleIntermedio(mazoOrigenId, partidaId);
        // Obtengo los mazos actuales
        List<Integer> listaMazos = getMazosIdSorted(partidaId);
        List<Integer> listaMazosFinales = getMazosFinalIdSorted(partidaId);
        Map<Integer, List<CartasPartida>> mazosFinales = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazoInicial = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazosInter = new HashMap<>();

        for (Integer idMazo : listaMazos) {
            List<CartasPartida> aux = findCartasPartidaByMazoId(idMazo);
            mazosInter.put(idMazo, aux);
        }
        for (Integer idMazo : listaMazosFinales) {
            List<CartasPartida> aux = findCartasPartidaByMazoFinalId(idMazo);
            mazosFinales.put(idMazo, aux);
        }
        mazoInicial.put(partidaId, findCartasPartidaMazoInicialByPartidaId(partidaId));

        return new Tuple3(mazosInter, mazosFinales, mazoInicial);
    }

    //MOVIMIENTOS MAZOS INTERMEDIOS CON MAZOS FINALES
    @Transactional
    public Tuple3 moverCartaInterFin(int mazoOrigenId, int mazoDestinoId, int partidaId) {
        MazoFinal mazoDest = mazoFinalService.findMazoFinalById(mazoDestinoId);
        

        // Obtiene lista de cartas partida de los mazos origen y destino
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaByMazoIdAndPartidaId(mazoOrigenId,
                partidaId);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoFinalIdAndPartidaId(mazoDestinoId, partidaId);

        // Indice de la ultima carta del mazo origen
        int startIndex = cpOrigen.size() - 1;

        Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
        List<CartasPartida> cartasMovidas = cpOrigen.subList(startIndex, cpOrigen.size());

        int indexUltCarta = cpDestino.size();
        
        for (CartasPartida cp : cartasMovidas) {
            cp.setMazo(null);
            cp.setMazoFinal(mazoDest);
            cp.setPosCartaMazo(indexUltCarta+1);
            mazoDest.setCantidad(mazoDest.getCantidad()+1);
            
            cartasPartidaRepository.save(cp);
        }
        setCartaVisibleIntermedio(mazoOrigenId, partidaId);

        List<Integer> listaMazos = getMazosIdSorted(partidaId);
        List<Integer> listaMazosFinales = getMazosFinalIdSorted(partidaId);
        Map<Integer, List<CartasPartida>> mazosFinales = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazoInicial = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazosInter = new HashMap<>();
        for (Integer idMazo : listaMazos) {
            List<CartasPartida> aux = findCartasPartidaByMazoId(idMazo);
            mazosInter.put(idMazo, aux);
        }
        for (Integer idMazo : listaMazosFinales) {
            List<CartasPartida> aux = findCartasPartidaByMazoFinalId(idMazo);
            mazosFinales.put(idMazo, aux);
        }
        mazoInicial.put(partidaId, findCartasPartidaMazoInicialByPartidaId(partidaId));

        // return mazosInter;
        return new Tuple3(mazosInter, mazosFinales, mazoInicial);

    }

    //MOVIMIENTO MAZO INICIAL A MAZOS INTERMEDIOS
    public Tuple3 moverCartaInicialInter(int mazoOrigenId, int mazoDestinoId, int partidaId) {
        Mazo mazoDest = mazoService.findMazoById(mazoDestinoId);
        // Obtiene lista de cartas partida de los mazos origen y destino
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaMazoInicial(partidaId);
        List<CartasPartida> cpDestino = cartasPartidaRepository.findCartasPartidaByMazoId(mazoDestinoId);

        //Ordena las cartas de manera ascendente;
        Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());

        //Indice ultima carta del mazo destino intermedio
        int indexUltCarta = cpDestino.size();
        //Se obtiene la ultima carta del mazo 
        CartasPartida cp = cpOrigen.get(cpOrigen.size()-1);
        //Se elimina la carta del mazo inicial
        cp.setMazoInicial(null);
        //Se establece el nuevo mazo a la cartapartida
        cp.setMazo(mazoDest);
        //Se añade la posicion de la carta
        cp.setPosCartaMazo(indexUltCarta + 1);
        cartasPartidaRepository.save(cp);
    
        //Obtengo las id de los mazos
        List<Integer> listaMazos = getMazosIdSorted(partidaId);
        List<Integer> listaMazosFinales = getMazosFinalIdSorted(partidaId);
        
        Map<Integer, List<CartasPartida>> mazosFinales = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazoInicial = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazosInter = new HashMap<>();
        //Se recorren los mazos asignando como clave la id del mazo y como valor las cartas partida de ducho mazo
        for (Integer idMazo : listaMazos) {
            List<CartasPartida> aux = findCartasPartidaByMazoId(idMazo);
            mazosInter.put(idMazo, aux);
        }
        for (Integer idMazo : listaMazosFinales) {
            List<CartasPartida> aux = findCartasPartidaByMazoFinalId(idMazo);
            mazosFinales.put(idMazo, aux);
        }
        // Asigna el diccionario el mazo inicial con las cartas
        mazoInicial.put(mazoOrigenId, findCartasPartidaMazoInicialByPartidaId(partidaId));

        // return mazos;
        return new Tuple3(mazosInter, mazosFinales, mazoInicial);
    }

    //MOVIMIENTOS MAZO INICIAL A MAZOS FINALES
    
 
    @Transactional
    public Tuple3 moverCartaInicialFinal(int mazoOrigenId, int mazoDestinoId, int partidaId) {
        MazoFinal mazoDest = mazoFinalService.findMazoFinalById(mazoDestinoId);

        // Obtiene lista de cartas partida de los mazos origen y destino
        List<CartasPartida> cpOrigen = cartasPartidaRepository.findCartasPartidaMazoInicial(partidaId);
        

        Collections.sort(cpOrigen, new ComparadorCartasPartidaPorPosCartaMazo());
        Set<CartasPartida> cpMovida = new HashSet<>();
        cpMovida.add(cpOrigen.get(cpOrigen.size()-1));
        cpOrigen.get(cpOrigen.size()-1).setMazoInicial(null);
        cpOrigen.get(cpOrigen.size()-1).setMazoFinal(mazoDest);
        mazoDest.setCartasPartida(cpMovida);
        // cp.getMazoFinal().setCantidad(i);
        cpOrigen.get(cpOrigen.size()-1).setPosCartaMazo(mazoDest.getCartasPartida().size() + 1);
        mazoFinalService.save(mazoDest);
        cartasPartidaRepository.save(cpOrigen.get(cpOrigen.size()-1));
        


        List<Integer> listaMazos = getMazosIdSorted(partidaId);
        List<Integer> listaMazosFinales = getMazosFinalIdSorted(partidaId);
        Map<Integer, List<CartasPartida>> mazosFinales = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazoInicial = new HashMap<>();
        Map<Integer, List<CartasPartida>> mazosInter = new HashMap<>();
        for (Integer idMazo : listaMazos) {
            List<CartasPartida> aux = findCartasPartidaByMazoId(idMazo);
            mazosInter.put(idMazo, aux);
        }
        for (Integer idMazo : listaMazosFinales) {
            List<CartasPartida> aux = findCartasPartidaByMazoFinalId(idMazo);
            mazosFinales.put(idMazo, aux);
        }
        // Asigna el diccionario el mazo inicial con las cartas
        mazoInicial.put(partidaId, findCartasPartidaMazoInicialByPartidaId(partidaId));

        // return mazosInter;
        return new Tuple3(mazosInter, mazosFinales, mazoInicial);

    }


    public void setCartaVisibleIntermedio(int mazoId, int partidaId) {
        List<CartasPartida> mazo = cartasPartidaRepository.findCartasPartidaByMazoIdAndPartidaId(mazoId, partidaId);
        Collections.sort(mazo, new ComparadorCartasPartidaPorPosCartaMazo());
        if (mazo.size() != 0) {
            CartasPartida ultCarta = mazo.get(mazo.size() - 1);
            if (!ultCarta.getIsShow() || ultCarta.getIsShow() == null) {
                ultCarta.setIsShow(true);
                cartasPartidaRepository.save(ultCarta);
            }
        }
    }
    
    
    @Transactional
    public void cambiaPosCartaMazoIni(int mazoIniId,Map<String, Object> model){
        List<CartasPartida> mazoInicial = cartasPartidaRepository.findCartasPartidaByMazoInicialIdAndPartidaId(mazoIniId, mazoIniId);
        if(mazoInicial==null||mazoInicial.size()==0){
            model.put("message","El mazo inicial está vacio");
        }else{
        for (CartasPartida cp : mazoInicial){
            cp.setPosCartaMazo(cp.getPosCartaMazo()+1);
            cartasPartidaRepository.save(cp);
        }
        
        Collections.sort(mazoInicial, new ComparadorCartasPartidaPorPosCartaMazo());
        CartasPartida ultCarta = mazoInicial.get(mazoInicial.size()-1);
        ultCarta.setPosCartaMazo(1);
        cartasPartidaRepository.save(ultCarta);
        Partida p = partidaService.findById(mazoIniId);
        p.setNumMovimientos(p.getNumMovimientos()+1);
        partidaService.save(p);
        
    }

}
    

    
}