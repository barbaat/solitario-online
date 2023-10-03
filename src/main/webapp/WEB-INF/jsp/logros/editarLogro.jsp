<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="logroEditar">
    <div class="mx-auto" style="justify-content: center; display: flex;">
        <p style="font-size: 2em;"><b>EDITAR LOGRO</b></p>
    </br>
    </br>
    </div>

</br>
</br>
</br>
</br>
    <form:form modelAttribute="logro" class="form-horizontal " id="add-jugador-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre del logro" name="name" />
            <petclinic:inputField label="Descripcion del logro" name="description" />
            <petclinic:inputField label="Numero de condicion" name="numCondicion" />
            <input type="hidden" name="logros.is_unlocked" value="${is_unlocked}">
            <input type="hidden" name="logros.image" value="${image}">
            <input type="hidden" name="logros.jugador" value="${jugador}">

        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Actualizar logro</button>

                <spring:url value="/jugador/logros/editar" var="editUrl"></spring:url>
                <a href="${editUrl}" class="btn btn-default">Volver</a>
            </div>
        </div>
        <form:errors></form:errors>
    </form:form>

</petclinic:layout>