<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>All People</title>
</head>
<body>

<div>
    <c:forEach var="person" items="${people}">
        <a href='<c:url value="/people?id=${person.getId()}"/>'/>
                <c:out value="${person.getName()}, ${person.getAge()}"/>
        <br/>
    </c:forEach>
</div>

<br/>
<a href="/people/new">Create new Person</a>

</body>
</html>
