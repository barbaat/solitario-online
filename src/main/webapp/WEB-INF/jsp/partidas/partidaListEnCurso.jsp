<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidasCurso">
    <h2>Partidas en curso</h2>
    <a class="btn btn-default" href="/partidas/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Crear partida nueva</a>
    <br></br>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Jugador</th>
            <th>Momento Inicio</th>
            <th>Momento Fin</th>
            <th>Victoria</th>
            <th>Número de Movimientos</th>
            <th>Puntos</th>
            <th>Duración</th>
            <th>¿Finalizar?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.jugador.user.username}"/>
                </td>
                <td>
                    <c:out value="${partida.momentoInicioString()}"/>
                </td>
                <td>
                	<c:if test="${partida.momentoFin == null}"><c:out value="Partida en curso"/></c:if>
                	<c:if test="${partida.momentoFin != null}"><c:out value="${partida.momentoFinString()}"/></c:if>
                </td>
                <td>
                    <c:if test="${partida.momentoFin == null}"><a class="btn btn-primary btn-sm">En curso</a></c:if>
                </td>
                <td>
                    <c:out value="${partida.numMovimientos}"/>
                </td>
                <td>
                    <c:out value="${partida.puntos()}"/>
                </td>
                <td>
                    <c:out value="${partida.duracion()}"/>
                </td>
                <td>
                    <a href="http://localhost:8080/partidas/derrota/${partida.id}" class="btn btn-danger"> Finalizar</a>
                </td>
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>