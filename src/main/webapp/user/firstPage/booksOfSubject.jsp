<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/14/2021
  Time: 12:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="subjectHeaderClass">
    <h4>Books</h4>
</div>
<c:forEach var="bl" items="${bookOfSubject}">
    <ul>
        <a  style="text-decoration: none" href="javascript:getBookById(${bl.id})">
            <li><span class="bookOfSubjectClass">${bl.title}</span>
        </a>
   <span style="color: white">By</span>
        <a  style="text-decoration: none" href="javaScript:authorInfo(${bl.author.id})">
            <i style="color: darkblue;"><span class="bookOfSubjectClass">${bl.author.firstName} ${bl.author.lastName}</span></i></a>
        </li>
    </ul>

</c:forEach>

