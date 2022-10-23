<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>DELETE</title>
    <style>
        body
        {
            color : black;
            font-style: serif;
            text-align: center;
        }
        div
        {
            color : green;
            font-style: serif;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>DELETE</h1>
    <div> Status delete: ${answer} </div>

    <c:choose>
        <c:when test="${answer}">
            <div>Book with ID ${id} delete!!!</div>
        </c:when>
        <c:otherwise>
            <div>Maybe there is no such ID in BD or it was deleted earlier</div>
        </c:otherwise>
    </c:choose>

</body>
</html>