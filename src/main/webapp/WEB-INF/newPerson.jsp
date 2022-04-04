<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <form action="/people" method="post">
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
            <input type="text" name="name"/>
            <br/>

            Enter age:
            <input type="text" name="age"/>
            <br/>

            Enter email:
            <input type="text" name="email"/>
            <br/>

            <input type="submit" value="create">
        </form>
    </body>
</html>
