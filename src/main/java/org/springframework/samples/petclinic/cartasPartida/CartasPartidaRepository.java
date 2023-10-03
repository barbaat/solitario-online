package org.springframework.samples.petclinic.cartasPartida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;

public interface CartasPartidaRepository extends CrudRepository<CartasPartida,Integer> {

    @Query("select cp from CartasPartida cp where cp.partida.id = ?1")
    public List<CartasPartida>findCartasPartidaByPartidaId(Integer partidaId);

    @Query("select cp from CartasPartida cp WHERE cp.mazo.id = ?1 ORDER BY cp.posCartaMazo")
    public List<CartasPartida> findCartasPartidaByMazoId(Integer mazoId);

    @Query("select cp from CartasPartida cp WHERE cp.mazo.id = ?1 and cp.partida.id = ?2 ORDER BY cp.posCartaMazo")
    public List<CartasPartida> findCartasPartidaByMazoIdAndPartidaId(Integer mazoId, Integer partidaId);

    @Query("select cp from CartasPartida cp WHERE cp.mazoFinal.id = ?1 and cp.partida.id = ?2")
    public List<CartasPartida> findCartasPartidaByMazoFinalIdAndPartidaId(Integer mazoId, Integer partidaId);

    @Query("select DISTINCT(cp.mazoFinal.id) from CartasPartida cp WHERE cp.partida.id = ?1")
    public List<Integer> findMazoFinalIdListByPartidaId(Integer partidaId);

    @Query("select m from Mazo m WHERE m.id = ?1")
    public List<Mazo> findMazoByMazoId(Integer mazoId);

    @Query("select cp from CartasPartida cp WHERE cp.mazoInicial.id =?1 ORDER BY cp.posCartaMazo")
    public List<CartasPartida> findCartasPartidaMazoInicial(Integer partidaId);

    
    @Query("select cp.carta from CartasPartida cp where cp.mazo.id = ?1 ORDER BY cp.posCartaMazo")
    public List<Carta> findCartasByMazoIdList(Integer mazoId);

    @Query("select cp.carta from CartasPartida cp where cp.mazo.id = ?1 and cp.partida.id = ?2")
    public List<Carta> findCartasByMazoIdAndPartidaIdList(Integer mazoId, Integer partidaId);

    @Query("select cp from CartasPartida cp where cp.carta.id = ?1")
    public CartasPartida findCartasPartidaByCartaId(Integer cartaId);

    @Query("select cp from CartasPartida cp where cp.carta.id = ?1 and cp.partida.id = ?2")
    public CartasPartida findCartasPartidaByCartaIdAndPartidaId(Integer cartaId, Integer partidaId);

    @Query("select cp from CartasPartida cp WHERE cp.mazoFinal.id = ?1 ORDER BY cp.posCartaMazo")
    public List<CartasPartida> findCartasPartidaByMazoFinalId(Integer idMazo);

    @Query("select cp from CartasPartida cp WHERE cp.mazoInicial.id = ?1 and cp.partida.id = ?2")
    public List<CartasPartida> findCartasPartidaByMazoInicialIdAndPartidaId(int mazoOrigenId, int partidaId);
    
    
    @Query("select cp.mazoFinal from CartasPartida cp WHERE cp.mazoFinal.id = ?1 and cp.partida.id = ?2")
    public MazoFinal findMazoFinalByIdAndPartidaId(Integer mazoId, Integer partidaId);

}
