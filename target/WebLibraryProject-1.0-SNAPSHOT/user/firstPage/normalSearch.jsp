<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/15/2021
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
 $('.normalSearchClass').querySelector('j').css('color','red')
</script>
<div class="subjectHeaderClass">
    <h2> Books List</h2>
    <p id="count">Search Result <i>${listSize} </i>books</p>
    <hr>
</div>
<div class="normalSearchClass">
    <ul>
        <c:forEach items="${bookList}" var="bl">
            <li>
                <a style="text-decoration: none;" class="resultBookTitle" href="javaScript:getSearchBook(${bl.id})">
                  ${bl.title}</a>
                <span style="color:#105173; left: 35%;">(author)</span>
                <i>${bl.author.firstName} ${bl.author.lastName}</i>
                <span style="height: 10px"></span>
            </li>
        </c:forEach>
    </ul>
</div>

