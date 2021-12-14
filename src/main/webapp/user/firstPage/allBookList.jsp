<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/14/2021
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="subjectListForMenu">
    <h2>Subjects</h2>
    <ul>
        <c:forEach items="${subjectList}" var="sl">
            <a href="javascript:getBooksOfSubjectId(${sl.id})">
                <li value="${sl.id}">${sl.subjectName}</li>
            </a>
        </c:forEach>
    </ul>

</div>
<hr>
<div id="bookListForMenu">
    <h2>Books</h2>
    <ul>
        <c:forEach var="bl" items="${bookList}">
            <a href="javascript:bookInfo(${bl.id})"><li value="${bl.id}">${bl.isbn}  --- ${bl.title}
            </a>
        </c:forEach>
        </li>
    </ul>
</div>
<hr>
<div id="authorListForMenu">
    <h2>Authors</h2>
    <ul>
        <c:forEach var="al" items="${authorList}">
            <a href="javaScript:authorInfo(${al.id})">
                <li value="${al.id}">${al.firstName} ${al.lastName} ${al.fatherName}</li>
            </a>
        </c:forEach>
    </ul>

</div>

