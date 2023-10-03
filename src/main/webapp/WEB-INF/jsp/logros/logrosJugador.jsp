

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="logros">
   
	<div class="container text-center">
		<h1><b>Logros</b></h1>
		</br>
		<sec:authorize access="hasAuthority('admin')">
			<a href="/jugador/logros/editar" class ="btn btn-default" style="font-size: 1em;">EDITAR LOGROS</a>
		</br>
		</br>
		</br>	
		</sec:authorize>
	</div>
	
	<div class="container" style="width: 1600px;">

		<div class="row" style="display: flex; justify-content: space-between; padding-right: 460px;">
			<c:forEach items="${logros}" var="logro">
		
			<div class="col-md-3">
				<div class="card" style="width:300px; height: 300px; border: 10px solid-black; border-radius: 50px; background-color: beige;">
					<div class="card-header" style="text-align: center;">
						<img class="rounded d-block" src="${logro.image}" width="200" height="200">
					</div>
					<div class="card-body" style="text-align: center;">
						
						<c:if test="${logro.is_unlocked == false}">
							Logro -> <c:out value="${logro.name}" />	
						</br>
							<c:out value="${logro.description}" />
						</br>
					</br>
							<h2 style="color: red;">NO CONSEGUIDO</h2>
						</c:if>
						
						<c:if test="${logro.is_unlocked == true}">
							Logro -> <c:out value="${logro.name}" />	
						</br>
							<c:out value="${logro.description}" />
						</br>
					</br>
						<h2 style="color: green;">CONSEGUIDO</h2>
						</c:if>
					</div>
				</div>
	
			</div>
	
			</c:forEach>
	
		</div>

	</div>
	
	
	
</petclinic:layout>