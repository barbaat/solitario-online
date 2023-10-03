<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidasFinalizadasUsuario">
    <h2>Partidas finalizadas de ${jugador.user.username}</h2>
    
    <a class="btn btn-default" href="/partidas/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Crear partida nueva</a>
    <br></br>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Usuario</th>
            <th>Momento Inicio</th>
            <th>Momento Fin</th>
            <th>Victoria</th>
            <th>Número de Movimientos</th>
            <th>Puntos</th>
            <th>Duración</th>
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
                    <c:out value="${partida.momentoFinString()}"/>
                </td>
                <td>
                	<c:if test="${partida.victoria == true}"><a class="btn btn-success btn-sm">VICTORIA</a></c:if>
                    <c:if test="${partida.victoria == false}"><a class="btn btn-danger btn-sm">DERROTA</a></c:if>
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
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>