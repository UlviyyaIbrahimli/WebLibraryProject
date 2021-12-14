<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/27/2021
  Time: 3:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 style="text-align: center; color:blue;">${book.isbn}    ${author.title}</h2>
<ul style="list-style-type: upper-roman; color: #007fff">
    <h4 style="margin-bottom: 15px;color: blue;text-align: center">Subjects</h4>
    <c:forEach var="ss" items="${subjectOfBook}">
        <li>${ss.subjects.subjectName}</li>
    </c:forEach>
</ul>
