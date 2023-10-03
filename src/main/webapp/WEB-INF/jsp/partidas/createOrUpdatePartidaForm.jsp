<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidasNueva">
    
    <jsp:body>
   		<div class="container">
   		<br>
   		<br>
        <div class="row" align="center" >
        <h2 style="font-size: 4rem" >
            Nueva Partida
        </h2>
        <form:form modelAttribute="partida"
                   class="form-horizontal">
        
        <form:hidden  path="id" />
        <form:hidden  path="momentoInicio" />
        <form:hidden  path="momentoFin" />
        <form:hidden  path="numMovimientos" />
        <form:hidden  path="victoria" />
        <form:hidden  path="jugador" />
        
        <br>
        
        <div class="form-group">
            
                
		<button class="btn btn-default" style="font-size: 4rem"type="submit">Crear Partida</button>                        
            
        </div>
        </form:form>
        <br>
        </div>  
        </div> 
    </jsp:body>
</petclinic:layout>
