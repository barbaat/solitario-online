<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="jugador">

        <div class="m-0 row justify-content-center"> 

    <div class="col-auto p-5 text-center"> 
        
    </br>
        <c:if test="${jugador.image == ''}"><img class="rounded d-block" src="/resources/images/defecto.png" width="250" height="250"></c:if>
        <c:if test="${jugador.image != ''}"><img class="rounded d-block" src="${jugador.image}" width="250" height="250"></c:if>
        
</br>
</br>
        <h4><b>@<c:out value="${jugador.user.username}"/></b></h4>
        <h4>Nombre: <b><c:out value="${jugador.firstName}"/></b></h4>
        <h4>Apellidos: <b><c:out value="${jugador.lastName}"/></b></h4></br>


        <tr>
            <spring:url value="/jugador/edit/{id}" var="editUrl">
                <spring:param name="id" value="${id}"/>
            </spring:url>
            <a href="${editUrl}" class="btn btn-default">Editar Jugador</a>
        </tr>
   
        <tr>
            <spring:url value="/jugador/estadisticas/" var="statsUrl">
                <spring:param name="id" value="${id}"/>
            </spring:url>
            <a href="${statsUrl}" class="btn btn-default">Estadísticas</a>
        </tr>

        <tr>
            <spring:url value="/jugador/logros" var="logrosUrl">
                <!-- <spring:param name="id" value="${id}"/> -->
            </spring:url>
            <a href="${logrosUrl}" class="btn btn-default">Logros</a>
        </tr>
        <tr>
            <spring:url value="/partidas/jugador/{id}" var="partidasURL">
                <!-- <spring:param name="id" value="${id}"/> -->
            </spring:url>
            <a href="${partidasURL}" class="btn btn-default">Mis partidas</a>
        </tr>
    </table>

</petclinic:layout>