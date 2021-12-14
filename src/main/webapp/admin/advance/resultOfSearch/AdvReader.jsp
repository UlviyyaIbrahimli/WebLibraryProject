
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/9/2021
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $("#advReaderTable").dataTable();
</script>
<table border="1" class="tableStyle" id="advReaderTable">
    <thead>
    <tr>
        <th>#</th>
        <th>Full Name</th>
        <th>Gender</th>
        <th>Dob</th>
        <th>Nationality</th>
        <th>Status</th>
        <th>Start Memeber Date</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="rl" items="${readerList}">
        <tr>
            <td>${rl.number}</td>
            <td>${rl.firstName} ${rl.lastName} ${rl.fatherName}</td>
            <td>${rl.gender} </td>
            <td>${rl.dob} </td>
            <td>${rl.nationality.nationality}</td>
            <td>${rl.status.status}</td>
            <td>${rl.startMemberDate}</td>
            <td>${rl.eMail}</td>
            <td>${rl.phone1}</td>
            <td>${rl.city}/ ${rl.country.countryName}</td>
            <td><a href="editReader('${rl.id}')">Edit</a></td>
            <td><a href="deleteReader('${rl.id}' '${rl.firstName}' '${rl.lastName}')">Delete</a></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
