<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page title="Books">
    <h1>Books</h1>
    <table id="t1">
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>TITLE</th>
            <th>AUTHOR</th>
            <th>PAGES</th>
            <th>ISBN</th>
            <th>PRICE</th>
            <th>COVER</th>
        </tr>
        <c:forEach items="${books}" var="book" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.pages}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.cover}</td>
            </tr>
        </c:forEach>

    </table>
</t:page>