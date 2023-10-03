<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="jugadores">
    <h2>
        <c:if test="${jugador['new']}"><p class="align-left"> Nuevo Jugador</p></c:if><p class="align-left" style="font-size:1.5em;"> <strong><u>PERFIL</u></strong></p>
    </h2>
    
    <form:form modelAttribute="jugador" class="form-horizontal " id="add-jugador-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="firstName"/>
            <petclinic:inputField label="Apellido" name="lastName"/>
            <petclinic:inputField label="URL Image" name="image"/>
            <c:choose>
                    <c:when test="${jugador['new']}">
                        <petclinic:inputField label="Password" name="user.password"/>
                        <petclinic:inputField  label="Nombre de usuario" name="user.username"/>
                        
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="user.password" value="${pass}">
                        <input type="hidden" name="user.username" value="${username}">
                        
                    </c:otherwise>
                </c:choose>
            
            </div>
    
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${jugador['new']}">
                        <button class="btn btn-default" type="submit">Registrarse</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos</button>
                        <spring:url value="/jugador/perfil/{id}" var="editUrl">
                            <spring:param name="id" value="${id}"/>
                        </spring:url>
                        <a href="${editUrl}" class="btn btn-default">Volver</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <form:errors></form:errors>
    </form:form>

</petclinic:layout>
