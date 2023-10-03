<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="jugador">

        <div class="m-0 row justify-content-center"> 
        
            <div class="row mx-auto justify-content-center text-center">
                    <h1 class="text-center"><h2>Información de <c:out value="${jugador.user.username}"/></h2></h1>
            </div>


    <div class="col-auto p-5 text-center"> 
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
                <a href="${editUrl}" class="btn btn-default">Editar jugador</a>
            </tr>

            <tr>
                <spring:url value="/partidas/jugador/{id}" var="gamesURL">
                    <spring:param name="id" value="${id}"/>
                </spring:url>
                <a href="${gamesURL}" class="btn btn-default">Sus partidas jugadas</a>
            </tr>
            <tr>
                <spring:url value="/jugador/estadisticas/{id}" var="statsURL">
                    <spring:param name="id" value="${id}"/>
                </spring:url>
                <a href="${statsURL}" class="btn btn-default">Sus estadísticas</a>
            </tr>

            <tr>
                <spring:url value="/jugador/delete/{id}" var="deleteURL">
                    <spring:param name="id" value="${id}"/>
                </spring:url>
                <a href="${deleteURL}" class="btn btn-default">Eliminar jugador</a>
            </tr>

</petclinic:layout>