<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">

    <div class="m-0 row justify-content-center">
        <div class="col-auto text-center">
            <h1 style="font-size: 3em; font-weight: bold;">${title}</h1>
        </br>
            <div class="mx-auto" style="justify-content: center; display: flex;">
                <spring:url value="/resources/images/gif-inicial.gif" htmlEscape="true" var="videoCartas" />
                <img id="f2" class="img-responsive" src="${videoCartas}" width="420" height="420" />
    
            </div>
        </br>
    </br>
            <p><h2>Group ${group}</h2></p>
            <p>
                <ul>   
                    <c:forEach items="${persons}" var="person">
                       ${person.firstName}    ${person.lastName}
                        </br>
                    </c:forEach>
                </ul>
            </p>
        </div>
    
    </div>

</petclinic:layout>
