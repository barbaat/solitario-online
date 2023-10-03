<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ranking">

    <div class="row">
        <div class="col-md-1" style="display: flex; text-align: center; justify-content: center; padding-left: 150px;">
            <h2>Ranking partidas ganadas</h2>
        </div>
        <div class="col-md-1" style="display: flex; text-align: center; justify-content: center; padding-left: 420px;">
            <h2>Ranking puntos ganados</h2>
        </div>
        <div class="col-md-1" style="display: flex; text-align: center; justify-content: center; padding-left: 420px;">
            <h2>Ranking movimientos alcanzados</h2>
        </div>
    </div>
</br>
    <div class="row">

        <div class="col-md-1">

            <table id="usersTable" class="table table-striped">
                <thead>
                <tr>
                    
                    <th style="width: 150px;">Clasificacion</th>
                    <th style="width: 200px;">Username</th>
                    <th style="width: 200px;">Partidas ganadas</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${jugadoresWIN}" var="jugador" varStatus="status">
                    <tr>
                        
                        <td>
                            <c:if test="${status.count == 1}"><img class="rounded d-block" src="/resources/images/oro.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 2}"><img class="rounded d-block" src="/resources/images/plata.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 3}"><img class="rounded d-block" src="/resources/images/bronce.png" width="25" height="25"></c:if>
                            <c:if test="${status.count > 3}"><a style="color:black; padding-left: 8px;">${status.count}</a></c:if>
                        </td>  
                        <td>
                            <c:out value="${jugador.user.username}"/>
                        </td>
                        <td>
                            <c:out value="${jugador.partidasGanadas}"/>
                        </td> 
                        
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>

        <div class="col-md-1" style="padding-left: 350px;">

            
            <table id="usersTable" class="table table-striped">
                <thead>
                <tr>
                    
                    <th style="width: 150px;">Clasificacion</th>
                    <th style="width: 200px;">Username</th>
                    <th style="width: 200px;">Puntos ganados</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${jugadoresPTN}" var="jugador2"  varStatus="status">
                    <tr>
                        
                        <td>
                            <c:if test="${status.count == 1}"><img class="rounded d-block" src="/resources/images/oro.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 2}"><img class="rounded d-block" src="/resources/images/plata.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 3}"><img class="rounded d-block" src="/resources/images/bronce.png" width="25" height="25"></c:if>
                            <c:if test="${status.count > 3}"><a style="color:black; padding-left: 8px;">${status.count}</a></c:if>
                        </td>   
                        <td>
                            <c:out value="${jugador2.user.username}"/>
                        </td>
                        <td>
                            <c:out value="${jugador2.numTotalPuntos}"/>
                        </td> 
                        
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            
        </div>

        <div class="col-md-1" style="padding-left: 400px;">

            <table id="usersTable" class="table table-striped">
                <thead>
                <tr>
                    
                    <th style="width: 150px;">Clasificacion</th>
                    <th style="width: 200px;">Username</th>
                    <th style="width: 200px;">Movimientos realizados</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${jugadoresMOV}" var="jugador3" varStatus="status">
                    <tr>
                        
                        <td>
                            <c:if test="${status.count == 1}"><img class="rounded d-block" src="/resources/images/oro.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 2}"><img class="rounded d-block" src="/resources/images/plata.png" width="25" height="25"></c:if>
                            <c:if test="${status.count == 3}"><img class="rounded d-block" src="/resources/images/bronce.png" width="25" height="25"></c:if>
                            <c:if test="${status.count > 3}"><a style="color:black; padding-left: 8px;">${status.count}</a></c:if>
                        </td>  
                        <td>
                            <c:out value="${jugador3.user.username}"/>
                        </td>
                        <td>
                            <c:out value="${jugador3.numTotalMovimientos}"/>
                        </td> 
                        
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            
        </div>

    </div>
    
</br>
</br>
</br>
    
</br>
</br>
</br>

</petclinic:layout>
