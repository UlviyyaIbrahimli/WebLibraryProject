<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/14/2021
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $(".accordion").accordion();
        $(".accordion").accordion({header: "h3", collapsible: true, active: false});
    });
</script>
<div class="subjectHeaderClass">
    <h4>Book</h4>
</div>
<div id="bookForSubjectClass">
    <img src="#" class="specialSubjectClass"/>
    <div class="bookItems">
        <b class="sInfoTitleClass">ISBN:</b><span id="isbnId">${book.isbn}</span> <br>
        <b class="sInfoTitleClass">TITLE:</b> <span id="titleId">${book.title}</span>
        <br>
        <b class="sInfoTitleClass">By</b>
        <c:forEach var="al" items="${authorOfBook}">
            <ul style="list-style-type: none;">
                <li><a style="text-decoration: none" href="javaScript:authorInfo(${al.author.id})">
                    <span id="mAuthorId">${al.author.firstName} ${al.author.lastName} ${al.author.fatherName}</span></a>
                </li>
            </ul>
        </c:forEach>
        <br>
        <b class="sInfoTitleClass">PUBLISHER:</b><span id="mPublisherId">${book.publisher}</span><br>
        <b class="sInfoTitleClass">PAGE:</b> <span id="mPageId">${book.page}</span><br>
        <b class="sInfoTitleClass">EDITION:</b><span id="mEditionId">${book.edition}</span><br>
        <b class="sInfoTitleClass">LANGUAGE:</b><span id="mLanguageId">${book.language.bookLanguage}</span><br>
        <b class="sInfoTitleClass">STAR:</b><span id="mStarId">${book.star}</span><br>
        <b class="sInfoTitleClass">RELEASE DATE:</b><span id="mRelDate">${book.releaseDate}</span><br>
        <b class="sInfoTitleClass">Subjects:</b>
        <c:forEach var="sl" items="${subjectOfBook}">
                <span id="subjectOfBookSpId">
                  <ul style="    list-style-type: none;">
                      <li>${sl.subjects.subjectName}</li>
                  </ul>
                </span>
        </c:forEach>
        <div id="accordionNormalSearchResultId" class="accordion">
            <h3>Abstract</h3>
            <div>
                <p>
                    ${bl.bAbstract}
                </p>
            </div>
        </div>
    </div>
</div>
<hr>
