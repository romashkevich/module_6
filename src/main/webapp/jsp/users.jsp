<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Books</title>
    <link href="css/style.css" rel="stylesheet">
    <style>
        *{
            font-style: serif;
            text-align: center
            }
    </style>
</head>
<body>
    <h1>Users</h1>
    <table id="t1">
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>LOGIN</th>
            <th>F.NAME</th>
            <th>L.NAME</th>
            <th>EMAIL</th>
            <c:if test="${rol}">
            <th>ROLE</th>
            </c:if>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${email}</td>
                <c:if test="${rol}">
                <td>${user.roleDto}</td>
                </c:if>
            </tr>
        </c:forEach>

    </title>
</body>
</html>