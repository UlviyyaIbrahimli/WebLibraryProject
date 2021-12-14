<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/15/2021
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $("#authorTableId").dataTable();
</script>
<table class="tableStyle"  id="authorTableId">
    <thead>
    <tr>
        <th style="color: #e2848a">#</th>
        <th>Full Name</th>
        <th>Gender</th>
        <th>Dob</th>
        <th>Dead Date</th>
        <th>Nationality</th>
        <th>Reward</th>
        <th>Books</th>
        <th>Note</th>
                <th>Subjects</th>
        <th  style="color: red">Edit</th>
        <th  style="color: red">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="al" items="${authorList}">
        <tr>
            <td>${al.number}</td>
            <td>${al.firstName} ${al.lastName} ${al.fatherName}</td>
            <td>${al.gender}</td>
            <td>${al.dob}</td>
            <td>${al.deadDate}</td>
            <td>${al.nationality.nationality}</td>
            <td>${al.reward}</td>
            <td>${al.book.title}</td>
            <td>${al.authorInfo}</td>
            <td><a href="javascript:subjectOfAuthor('${al.id}')">Show Subjects</a> </td>
            <td ><a href="javascript:editAuthor('${al.id}')">Edit</a></td>
            <td id="del"><a href="javascript:confirmDeleteAuthor('${al.id}','${al.firstName}','${al.lastName}')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>