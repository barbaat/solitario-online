<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<petclinic:layout pageName="users">

<div class="row text-center">
    <p><h1><b>Users</b></h1>
    </br>
        <spring:url value="/users/new" var="newURL"></spring:url>
        <a href="${newURL}" class="btn btn-default">Crear nuevo jugador</a>
    </p>
</br>
</br>
</div>  
<body>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            
            <th style="width: 150px;">Jugador Id</th>
            <th style="width: 150px;">Imagen</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px;">Name</th>
            <th style="width: 200px;">Lastname</th>
            <th style="width: 200px;">Rol</th>
            <th style="width: 200px;">Eliminar</th>
        </tr>
        </thead>
        <tbody>
        
            <c:forEach items="${users.content}" var="user">
            <tr>
                
                <td>
                    <c:out value="${user.jugador.id}"/>
                </td>  
                <td>
                    <c:if test="${user.jugador.image == ''}"><img class="rounded d-block" src="/resources/images/defecto.png" width="50" height="50"></c:if>
                    <c:if test="${user.jugador.image != ''}"><img class="rounded d-block" src="${user.jugador.image}" width="50" height="50"></c:if>
                </td>
                <td>
                    <a href="http://localhost:8080/jugador/perfil/${user.jugador.id}" class="btn btn-warning btn-sm" style="color: black;">${user.username}</a>
                </td>
                <td>
                    <c:out value="${user.jugador.firstName}"/>
                </td>
                <td>
                    <c:out value="${user.jugador.lastName}"/>
                </td>   
                <td>
                    <c:out value="${user.getAuthorities()}"/>
                </td>
                <td>
                    <spring:url value="/jugador/delete/${user.jugador.id}" var="deleteURL"></spring:url>
                    <a href="${deleteURL}" class="btn btn-danger">Eliminar</a>
                </td>  
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <div class="pagination">
        <c:if test="${users.hasPrevious()}">
          <a href="?page=${users.number - 1}" class="previous">
            <button class="btn btn-default">
                Anterior
            </button>
        </a>
        </c:if>
        <c:if test="${!users.isLast()}">
            <a href="?page=${users.number + 1}" class="next">
                <button class="btn btn-default">
                    Siguiente
                </button>
            </a>
        </c:if>
      </div>
      
      <p>Página ${users.number + 1} de ${users.totalPages}</p>

</body>
</petclinic:layout>
