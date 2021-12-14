<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/14/2021
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(".authorOfBookAbs").accordion();
    $(".authorOfBookAbs").accordion({header: "h3", collapsible: true, active: false});
</script>
<c:forEach var="al" items="${bookOfAuthor}">
    <h4>Auhtor</h4>
    ${al.firstName}---${al.lastName}---${al.fatherName}<br/>
    <p>Isbn</p> ${al.book.isbn}
    <p>Title</p>${al.book.title}
    <p>Publisher</p>${al.book.publisher}
    <p>Page</p>${al.book.page}
    <p>Edition</p>${al.book.edition}
    <p>Star</p>${al.book.star}
    <p>Language</p>${al.book.language.bookLanguage}
    <div id="authorOfBookAbs" class="authorOfBookAbs">
        <h3>Abstract</h3>
        <div>
            <p>
                    ${al.book.bAbstract}
            </p>
        </div>
    </div>
    <hr>
</c:forEach>
