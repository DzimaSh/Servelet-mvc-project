<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 01/04/2022
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Person</title>
</head>
<body>

<form method="POST" action="/people?id=${person.getId()}">
    <input type="hidden" name="_method"value="PUT">
    Enter name:
    <input type="text" name="name" value="${person.getName()}"/>
    <br/>
    Enter age:
    <input type="text" name="age" value="${person.getAge()}"/>
    <br/>
    Enter email:
    <input type="text" name="email" value="${person.getEmail()}"/>
    <br/>
    <input type="submit" value="Update!"/>
</form>

</body>
</html>
