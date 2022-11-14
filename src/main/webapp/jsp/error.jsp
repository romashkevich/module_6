<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>ERROR</title>--%>
<%--    <style>--%>
<%--        body--%>
<%--        {--%>
<%--            color : red;--%>
<%--            font-style: serif;--%>
<%--            text-align: center;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <h1>ERROR/h1>--%>
<%--    <div>${message}</div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Error">
    <h1>ERROR</h1>
    <div>${message}</div>
</t:page>