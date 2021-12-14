<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/15/2021
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $("#subjectTableId").dataTable({
        "bPaginate": true,
        "pagingType": "full_numbers",
        "bLengthChange": true,
        "bFilter": true,
        "bInfo": true,
        "bAutoWidth": true
    });

</script>

<table border="1" class="tableStyle"  id="subjectTableId">
    <thead>
    <tr>
        <th style="color: #e2848a">#</th>
        <th>Subject Title</th>
        <th>Note</th>
        <th style="color: red">Edit</th>
        <th style="color: red">Delete</th>
    </tr>
    </thead>
<tbody>
<c:forEach items="${subjectList}" var="sl">
    <tr>
        <td>${sl.number}</td>
        <td>${sl.subjectName}</td>
        <td>${sl.subjectInfo}</td>
        <td><a href="javascript: editSubject('${sl.id}');">Edit</a> </td>
        <td><a href="javascript: confirmDeleteSubject('${sl.id}','${sl.subjectName}' );">Delete</a> </td>
    </tr>
</c:forEach>
</tbody>
 </table>
