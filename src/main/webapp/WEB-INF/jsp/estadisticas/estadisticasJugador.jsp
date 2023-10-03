<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<petclinic:layout pageName="stats">


<div class="container text-center" style="padding-left: 15%;">
    <h1 style="font-size: 2em;"><p>Usuario: <b>${jugador.user.username}</b></p></h1>
    </br>
</div>

<div class="row">

        <div class="col-md-5" style="font-size: 1.5em;">
            <div class="container" style="background-color:#cbc9c9;border-radius: 5%;border-color: black;border-width: 5px; width: 650px; height: 500px;">
            </br>
                <div class="row" align="center" style="font-size: 1.75em">
                    <strong>Estadísticas individuales</strong>
                </div>
            </br>
                    <div class="row" align="center">Partidas Jugadas:
                        <c:out value="${jugador.getPartidasJugadas()}" />
                    </div>

                    <div class="row" align="center">Partidas Ganadas:
                        <c:out value="${jugador.partidasGanadas}" />
                    </div>

                    <div class="row" align="center">Partidas Perdidas:
                        <c:out value="${jugador.partidasNoGanadas}" />
                    </div>

                    <div class="row" align="center">Tiempo total jugado:
                        <c:out value="${jugador.totalTiempoJugado}" />
                    </div>

                    <div class="row" align="center">Número total de movimientos:
                        <c:out value="${jugador.numTotalMovimientos}" />
                    </div>

                    <div class="row" align="center">Número total de puntos:
                        <c:out value="${jugador.numTotalPuntos}" />
                    </div>

                    <div class="row" align="center">Número máximo de movimientos en partida ganada:
                        <c:out value="${jugador.numMaxMovimientosPartidaGanada}" />
                    </div>

                    <div class="row" align="center">Número mínimo de movimientos en partida ganada:
                        <c:out value="${jugador.numMinMovimientosPartidaGanada}" />
                    </div>

                    <div class="row" align="center">Tiempo máximo en partida ganada:
                        <c:out value="${jugador.maxTiempoPartidaGanada}" />
                    </div>

                    <div class="row" align="center">Tiempo mínimo en partida ganada:
                        <c:out value="${jugador.minTiempoPartidaGanada}" />
                    </div>
                
            </div>
        </div>

        <div class="col-md-5" style="font-size: 1.5em; padding-left: 200px;">
            <div class="container" style="background-color:#cbc9c9;border-radius: 5%;border-color: black; border-width: 5px; width: 600px; height: 500px;">
            </br>
                <div class="row" align="center" style="font-size: 1.75em;">
                    <strong>Estadísticas generales</strong>
                </div>
            </br>
                    <div class="row" align="center">Partidas Jugadas:
                        <c:out value="${partidasTotalesJugadas}" />
                    </div>

                    <div class="row" align="center">Partidas Ganadas:
                        <c:out value="${partidasGanadasTotales}" />
                    </div>

                    <div class="row" align="center">Partidas Perdidas:
                        <c:out value="${partidasPerdidasTotales}" />
                    </div>

                    <div class="row" align="center">Puntos promedio:
                        <c:out value="${puntosPromedio}" />
                    </div>
 
                    <div class="row" align="center">Movimientos promedio:
                        <c:out value="${movimientosPromedio}" />
                    </div>
                    <div class="row" align="center">Tiempo total jugado:
                        <c:out value="${horas}"/> horas, <c:out value="${minutos}"/> minutos y <c:out value="${segundos}"/> segundos.
                    </div>
                    <div class="row" align="center">Tiempo promedio por partida:
                        <c:out value="${horasPromedio}"/> horas, <c:out value="${minutosPromedio}"/> minutos y <c:out value="${segundosPromedio}"/> segundos.
                    </div>
                </div>
            
        </div>

    </div>



</petclinic:layout>