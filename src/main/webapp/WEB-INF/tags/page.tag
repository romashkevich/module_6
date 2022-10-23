<%@tag description="Wrapper" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" type="java.lang.String" %>

<c:if test="${empty title}">
    <c:set var="title" value="Bookstore" />
</c:if>
<html>
<head>
    <title>${title}</title>
    <base href="/bookstore/">
    <link href="css/style.css" rel="stylesheet">

</head>
<body>
<jsp:doBody/>
</body>
</html>