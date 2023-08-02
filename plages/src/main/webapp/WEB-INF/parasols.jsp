<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Liste des parasols</h1>
<form>
<select name="ID_FILE">
    <option value="0">Toutes les files</option>
    <c:forEach items="${files}" var="file">
        <option value="${file.id}"<c:if test="${file.id eq idFile}"> SELECTED</c:if>>${file.numero}</option>
    </c:forEach>
</select>
<input type="submit" value="Filtrer">
</form>
<ul>
<!-- Le forEach ci-dessous parcourt le contenu de la page de parasols -->
<c:forEach items="${pageDeParasols.content}" var="parasol">
    <li>${parasol.numEmplacement} en file ${parasol.file.numero} (${parasol.file.prixJournalier} &euro;) <a href="parasol?ID_PARASOL=${parasol.id}">Modifier</a></li>
</c:forEach>
</ul>
Page ${pageDeParasols.getNumber()+1}
<br><br>
<a href="parasol">Ajouter un parasol</a>
</body>
</html>
