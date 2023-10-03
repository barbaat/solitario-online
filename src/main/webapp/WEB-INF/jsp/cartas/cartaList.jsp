<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cartas">
    <h2>Cartas</h2>

    <table id="cartasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Valor</th>
            <th>Palo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartas}" var="carta">
            <tr>
                <td>
                    <c:out value="${carta.valor}"/>
                </td>
                <td>
                    <c:out value="${carta.palo}"/>
                </td>
                <td>
                    <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>