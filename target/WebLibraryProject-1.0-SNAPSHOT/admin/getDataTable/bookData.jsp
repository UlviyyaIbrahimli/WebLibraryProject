<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/11/2021
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $("#bookTableId").dataTable();
</script>
<table border="1" class="tableStyle" id="bookTableId">
    <thead>
    <tr>
        <th style="color: #e2848a">#</th>
        <th>ISBN</th>
        <th>Title</th>
        <th>Author FullName</th>
        <th>Publisher</th>
        <th>Page</th>
        <th>Language</th>
        <th>Edition</th>
        <th>Star</th>
        <th>Release  Date</th>
        <th>Abstract</th>
        <th>Show Subjects</th>
        <th style="color: red">Edit</th>
        <th style="color: red">Delete</th>
    </tr>
    </thead>
<tbody>
<c:forEach items="${bookList}" var="bl">
    <tr>
        <td>${bl.number}</td>
        <td>${bl.isbn}</td>
        <td>${bl.title}</td>
        <td>${bl.author.firstName} ${bl.author.lastName}</td>
        <td>${bl.publisher}</td>
        <td>${bl.page}</td>
        <td>${bl.language.bookLanguage}</td>
        <td>${bl.edition}</td>
        <td>${bl.star}</td>
        <td>${bl.releaseDate}</td>
        <td>${bl.bAbstract}</td>
        <td><a href="javascript:showSubjects('${bl.id}')" >Show subjects</a></td>
        <td><a href="javascript:editBook('${bl.id}')" >Edit</a></td>
        <td><a href="javascript:confirmDeleteBook('${bl.id}','${bl.title}')">Delete</a> </td>

    </tr>
</c:forEach>
</tbody>

</table>
