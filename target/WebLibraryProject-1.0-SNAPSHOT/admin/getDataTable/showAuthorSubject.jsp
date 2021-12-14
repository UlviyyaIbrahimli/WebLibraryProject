<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/27/2021
  Time: 7:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    // $("#showSubjectOfAuthor").dataTable();
</script>
<%--<table border="1" class="tableStyle" id="showSubjectOfAuthor">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th style="color: #e2848a">#</th>--%>
<%--        <th>Full Name</th>--%>
<%--        <th>Subject Name</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <c:forEach items="${subjectOfAuthor}" var="bl">--%>
<%--        <tr>--%>
<%--            <td>${bl.number}</td>--%>
<%--            <td>${bl.firstName} ${bl.lastName} ${bl.fatherName}</td>--%>
<%--            <td>${bl.subjects.subjectName}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>

<h2 style="text-align: center; color:blue;">${author.firstName}  ${author.lastName} ${author.fatherName} </h2>
<ul style="list-style-type: upper-roman; color: #007fff">
    <h4 style="margin-bottom: 15px;color: blue;text-align: center">Subjects</h4>
    <c:forEach var="ss" items="${subjectOfAuthor}">
        <li>${ss.subjects.subjectName}</li>
    </c:forEach>
</ul>