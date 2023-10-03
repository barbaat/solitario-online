<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="partidaWin">

    <div class="m-0 row justify-content-center">
        
        <div class="mx-auto" style="justify-content: center; display: flex;">
            <p style="font-size: 2em;"><b>EDITAR LOGROS</b></p>
        </br>
        </br>
        </div>
    </div>
    </br>
    </br>
    <div class="container" style="width: 1600px;">
     <div class="row" style="display: flex; justify-content: space-between; padding-right: 460px;">
        <c:forEach items="${logros}" var="logro">
		
			<div class="col-md-3">
				<div class="card" style="width:300px; height: 300px; border: 10px solid-black; border-radius: 50px; background-color: beige;">
					<div class="card-header" style="text-align: center;">
						<img class="rounded d-block" src="${logro.image}" width="200" height="200">
					</div>
					<div class="card-body" style="text-align: center;">
							Logro -> <c:out value="${logro.name}" />	
						</br>
							<c:out value="${logro.description}" />
						</br>
                        
                        <a href="/jugador/logros/editar/${logro.id}" class="btn btn-default" style="padding-left: 5%;">EDITAR</a>
					    </br>
					</div>
				</div>
	
			</div>
	
			</c:forEach>
        </div>
    </div> 


</petclinic:layout>
