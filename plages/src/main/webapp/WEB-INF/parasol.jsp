<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Parasol</h1> <%-- <h1><spring:message code="parasol"/></h1> --%>
<form:form method="post" modelAttribute="parasol" action="parasol">
<form:label path="numEmplacement">Numéro d'emplacement</form:label><form:input path="numEmplacement"/>
<br>
<form:errors cssClass="erreur" path="numEmplacement"/>
<form:label path="file">File</form:label>
<form:select path="file">
<form:option value="0">Merci de choisir une file</form:option>
<form:options items="${files}" itemValue="id" itemLabel="numero"/>
</form:select><form:errors cssClass="erreur" path="file"/>
<br>
<form:hidden path="id"/>
<form:button>Enregistrer</form:button>
</form:form>
</body>
</html>