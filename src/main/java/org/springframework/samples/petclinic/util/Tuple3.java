package org.springframework.samples.petclinic.util;

import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.cartasPartida.CartasPartida;

public class  Tuple3 {
    private Map<Integer, List<CartasPartida>> first;
    private Map<Integer, List<CartasPartida>> second;
    private Map<Integer, List<CartasPartida>> third;
  
    public Tuple3(Map<Integer, List<CartasPartida>> first, Map<Integer, List<CartasPartida>> second,Map<Integer, List<CartasPartida>> third) {
      this.first = first;
      this.second = second;
      this.third = third;
    }
  
    public Map<Integer, List<CartasPartida>> getFirst() {
      return first;
    }
  
    public Map<Integer, List<CartasPartida>> getSecond() {
      return second;
    }
    public Map<Integer, List<CartasPartida>> getThird() {
        return third;
      }
    }