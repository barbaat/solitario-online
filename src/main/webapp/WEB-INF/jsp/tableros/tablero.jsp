<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="solitario" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .vertical {
        writing-mode: vertical-rl;
        font-size: large;
        font-weight: bold;
    }
</style>

<solitario:layout pageName="partidas">

    <jsp:body>


        <div class="row" align="center">
            
        </div>


        <div class="container">

            <!-- MAZO INICIAL -->
            <c:url value="/partidas/moverCartaIni/${partidaId}" var="moverCartaIni"/>
            <form:form action="${moverCartaIni}" method="post">
            <div class="row">
                <div class="col-md-1">
                    <spring:url value="${mazInicial[fn:length(mazInicial)-1].carta.imagen}" htmlEscape="true" var="carta" />
                    <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                </div>

                <div class="col-md-1">
                    <c:if test="${fn:length(mazInicial) != 0}">
                        <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                    </c:if>
                </div>

                <button class="btn btn-default">PASAR CARTA</button>
            </div>
            </form:form>


            </br>
            </br>
            </br>

            <!-- MAZOSS -->
            <div class="row">

                <div class="col-md-1">
                    MAZO 1
                    <c:forEach items="${mazInt1}" var="cp1">
                        <c:if test="${cp1.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp1.isShow == true}">
                            <spring:url value="${cp1.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>

                <div class="col-md-1" style="text-align: center;">
                    MAZO 2
                    <c:forEach items="${mazInt2}" var="cp2">
                        <c:if test="${cp2.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp2.isShow == true}">
                            <spring:url value="${cp2.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">
                    MAZO 3
                    <c:forEach items="${mazInt3}" var="cp3">
                        <c:if test="${cp3.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp3.isShow == true}">
                            <spring:url value="${cp3.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">
                    MAZO 4
                    <c:forEach items="${mazInt4}" var="cp4">
                        <c:if test="${cp4.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp4.isShow == true}">
                            <spring:url value="${cp4.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">
                    MAZO 5
                    <c:forEach items="${mazInt5}" var="cp5">
                        <c:if test="${cp5.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp5.isShow == true}">
                            <spring:url value="${cp5.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">
                    MAZO 6
                    <c:forEach items="${mazInt6}" var="cp6">
                        <c:if test="${cp6.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp6.isShow == true}">
                            <spring:url value="${cp6.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>
                <div class="col-md-1">
                    MAZO 7
                    <c:forEach items="${mazInt7}" var="cp7">
                        <c:if test="${cp7.isShow == false}">
                            <spring:url value="/resources/images/cards/00.png" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="300" height="300" src="${carta}" />
                        </c:if>
                        <c:if test="${cp7.isShow == true}">
                            <spring:url value="${cp7.carta.imagen}" htmlEscape="true" var="carta" />
                            <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                        </c:if>
                    </c:forEach>

                </div>

                <div class="vertical col-md-1">
                    MAZOS FINALES
                </div>
        

                <!-- MAZOS FINALES -->
                <div class="col-md-1">
                    
                    <c:if test="${fn:length(mazoFinalCorazones) == 0}">
                        <spring:url value="/resources/images/cards/fondoCorazon.png" htmlEscape="true" var="mazoCorazones" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoCorazones}" /> 
                    </c:if>
                    <c:if test="${fn:length(mazoFinalCorazones) != 0}">
                        <spring:url value="${mazoFinalCorazones[fn:length(mazoFinalCorazones)-1].carta.imagen}" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                    </c:if>
                </div>

                
            
                <div class="col-md-1">
                    <c:if test="${fn:length(mazoFinalPicas) == 0}">
                        <spring:url value="/resources/images/cards/fondoPica.png" htmlEscape="true" var="mazoPicas" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoPicas}" /> 
                    </c:if>
                    <c:if test="${fn:length(mazoFinalPicas) != 0}">
                        <spring:url value="${mazoFinalPicas[fn:length(mazoFinalPicas)-1].carta.imagen}" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                    </c:if>
                </div>
                

               
                
                <div class="col-md-1">
                    
                    <c:if test="${fn:length(mazoFinalDiamantes) == 0}">
                        <spring:url value="/resources/images/cards/fondoDiamante.png" htmlEscape="true" var="mazoDiamantes" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoDiamantes}" /> 
                    </c:if>
                    <c:if test="${fn:length(mazoFinalDiamantes) != 0}">
                        <spring:url value="${mazoFinalDiamantes[fn:length(mazoFinalDiamantes)-1].carta.imagen}" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                    </c:if>
                </div>

                <div class="col-md-1">
                    
                    <c:if test="${fn:length(mazoFinalTreboles) == 0}">
                        <spring:url value="/resources/images/cards/fondoTrebol.png" htmlEscape="true" var="mazoTreboles" />
                        <img class="img-responsive mx-auto d-block" width="300" height="300" src="${mazoTreboles}" /> 
                    </c:if>
                    <c:if test="${fn:length(mazoFinalTreboles) != 0}">
                        <spring:url value="${mazoFinalTreboles[fn:length(mazoFinalTreboles)-1].carta.imagen}" htmlEscape="true" var="carta" />
                        <img class="img-responsive mx-auto d-block" width="200" height="200" src="${carta}" />
                    </c:if>
                </div>

        </div>


    </br>
</br>
        <div class="row">
            
           
            

            <table id="board1Game" class="table table-striped">
                <thead>
                    <tr>
                        <th>Mover Cartas</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>
            
            
            <c:url value="/partidas/moverCarta/${partidaId}" var="moverCarta"/>
            <form:form action="${moverCarta}" method="post">
                Mazo Origen
                <select name="mazoOrigen">
                   <c:forEach var="i" begin="0" end="7" step="1">
                    <c:choose>
                        <c:when test="${i==0}">
                            <option value="${i}">Mazo Inicial</option>             
                        </c:when>
                        <c:when test="${i>0}"> 
                   <option value="${i}">Mazo ${i}</option>        
                </c:when>
                    </c:choose>
                </c:forEach>
                   </select>

                   Mazo Destino
            <select name="mazoDestino" >
                <c:forEach var="i" begin="1" end="11" step="1">
                      
                    <c:choose>
                        <c:when test="${i==8}">
                            <option value="${i}">Mazo Final Corazones </option>  
                        </c:when>
                        <c:when test="${i==9}">
                            <option value="${i}">Mazo Final Picas</option>  
                        </c:when>
                        <c:when test="${i==10}">
                            <option value="${i}">Mazo Final Diamantes</option>  
                        </c:when>
                        <c:when test="${i==11}">
                            <option value="${i}">Mazo Final Treboles</option>  
                        </c:when>
                        <c:otherwise>
                            <option value="${i}">Mazo ${i}</option>  
                        </c:otherwise>
                    </c:choose>
                 </c:forEach>
                </select>
                Cantidad
                <select name="cantidad">
                    <c:forEach var="i" begin="1" end="13" step="1">
                        <option value="${i}">${i}</option>    
                     </c:forEach>
                    </select>
            

             
             <button class="btn btn-default" type="submit">Enviar</button>
            
            </form:form>
            
            
            </td>
                    </tr>
                </tbody>
            </table>


        </div>


        <br><br><br>
        </div>
        <a href="/partidas/fin/${partidaId}" class="btn btn-danger"> FINALIZAR PARTIDA</a>


    </jsp:body>
</solitario:layout>