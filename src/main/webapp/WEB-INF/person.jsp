<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Person</title>
</head>
<body>

<div>
    <p> ${person.getName()}, ${person.getAge()}</p>
    <p> Email: ${person.getEmail()}</p>
    <p> ${person.getId()}</p>

<a href="/people/edit?id=${person.getId()}">Edit person</a>
    <br/>
    <a href="/people">To start page</a>
    <br/>
</div>
<form method="post" action="/people?id=${person.getId()}">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="Delete this person"/>
</form>
</body>
</html>
