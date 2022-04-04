<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Person</title>
</head>
<body>

<form method="POST" action="/people?id=${person.getId()}">
    <input type="hidden" name="_method"value="PUT">
    <c:if test="${errors != null}">
        <div style="color: red">
            Incorrect input:
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>
                            ${error.getMessage()}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
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
