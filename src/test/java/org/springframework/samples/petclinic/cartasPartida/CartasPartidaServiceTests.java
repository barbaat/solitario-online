/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.cartasPartida;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.mazo.MazoService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaBuilder;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


///EJECUTANDO LOS TEST UNO A UNO EN VEZ DE TODOS JUNTOS FUNCIONAN PERFECTAMENTE

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CartasPartidaServiceTests {
	
	@Autowired
    protected CartasPartidaService cpService;

    @Autowired
    protected MazoService mazoService;

    @Autowired 
    protected PartidaBuilder pb;

    @Autowired
    protected PartidaService partidaService;

    @Autowired
    CartasPartidaRepository cpRepository;

    
    @BeforeEach
    void setup(){
        
        Partida partida = new Partida();
        partida.setId(1);
        partida.setNumMovimientos(0);
        partida.setMomentoInicio(LocalDateTime.now());
        partida.setVictoria(false);
        partidaService.save(partida);
		pb.crearMazosIntermedios(partida);
    }



    @Test
    void testMoverCartaEntreMazosIntermediosSuccess(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
        
        cpService.moverCartaInterInter(3, 4, 1, 1);
            
        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(2);
        assertThat(cartasDestino.size()).isEqualTo(5);
    }

    @Test
    void testMoverCartaEntreMazosIntermediosFail(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
        
        //AL INTENTAR MOVER CARTAS PERO INDICANDO CANTIDAD 0 NO SE MUEVE NADA
        cpService.moverCartaInterInter(3, 4, 0, 1);
        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
    }
        


    @Test
    @Transactional
    void testMoverCartaDeMazoIntermedioAMazoFinalSuccess(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(5, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalId(2);
        
        assertThat(cartasOrigen.size()).isEqualTo(5);
        assertThat(cartasDestino.size()).isEqualTo(0);

        cpService.moverCartaInterFin(5, 2, 1);

        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(5, 1);
        cartasDestino = cpService.findCartasPartidaByMazoFinalId(2);
        assertThat(cartasOrigen.size()).isEqualTo(4);
        assertThat(cartasDestino.size()).isEqualTo(1);

    }

    @Test
    @Transactional
    void testMoverCartaDeMazoInicialAMazoFinalSucces(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaMazoInicialByPartidaId(1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalId(3);

        assertThat(cartasOrigen.size()).isEqualTo(24);
        assertThat(cartasDestino.size()).isEqualTo(0);
        
        
        cpService.moverCartaInicialFinal(1, 3, 1);
        cartasOrigen = cpService.findCartasPartidaMazoInicialByPartidaId(1);
        cartasDestino = cpService.findCartasPartidaByMazoFinalId(3);

        assertThat(cartasOrigen.size()).isEqualTo(23);
        assertThat(cartasDestino.size()).isEqualTo(1);
        
    }

    @Test
    @Transactional
    void testSetCartaVisibleSuccess(){
        List<CartasPartida> mazo2 =cpService.findCartasPartidaByMazoIdAndPartidaId(2, 1);
        Collections.sort(mazo2, new ComparadorCartasPartidaPorPosCartaMazo());

        mazo2.get(1).setMazo(null);
        cpService.saveCartasPartida(mazo2.get(1));
        cpService.setCartaVisibleIntermedio(2, 1);
        assertThat(mazo2.get(0).getIsShow()).isEqualTo(true);
    }

    @Test
    void testCambiaPosCartaMazoIni(){
        //VOY A COMPROBAR LA POSICION DE UNA CARTA DEL MAZO ANTES Y DESPUES
        List<CartasPartida> mazoIni = cpService.findCartasPartidaMazoInicialByPartidaId(1);
        Map<String,Object> model = new HashMap<>();
        CartasPartida cp1 = mazoIni.get(0);
        cpService.cambiaPosCartaMazoIni(1,model);
        mazoIni = cpService.findCartasPartidaMazoInicialByPartidaId(1);
        CartasPartida cp2 = mazoIni.get(0);
        assertThat(cp1.getPosCartaMazo()).isNotEqualTo(cp2.getPosCartaMazo());
    }


    @Test
    void testCambiaPosCartaMazoIniFail(){
        List<CartasPartida> mazoIni = cpService.findCartasPartidaMazoInicialByPartidaId(2);
        assertThat(mazoIni.size()).isEqualTo(0);
        Map<String,Object> model = new HashMap<>();
        cpService.cambiaPosCartaMazoIni(2,model);
        assertThat(model.get("message")).isEqualTo("El mazo inicial está vacio");

    }

    @Test
    @Transactional
    public void testMoverCartaMazoInicialMazoIntermedioSuccess(){
            List<CartasPartida> cartasOrigen = cpService.findCartasPartidaMazoInicialByPartidaId(1);
            CartasPartida cartaMovida = cartasOrigen.get(0);
            assertThat(cartasOrigen.size()).isEqualTo(24);
            assertThat(cartaMovida.getMazoInicial().getId()).isEqualTo(1);

            cpService.moverCartaInicialInter(1, 3, 1);

            List<CartasPartida> cartasOrigen2 = cpService.findCartasPartidaMazoInicialByPartidaId(1);
            List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoId(3);
            assertThat(cartasOrigen2.size()).isEqualTo(23);
            assertThat(cartasDestino.size()).isEqualTo(4);
    }

   
}
