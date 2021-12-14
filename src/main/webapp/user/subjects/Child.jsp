<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/9/2021
  Time: 2:23 PM
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
    <h2> Child books List</h2>
    <p id="count"><i>About Music found &nbsp;</i>${bookCount} <i>books</i></p>
    <hr>
</div>

<c:forEach var="bl" items="${bookList}">
    <div id="mainDivMusic">
        <img src="#" class="specialSubjectClass"/>
        <div class="bookItems">
            <b class="sInfoTitleClass">ISBN:</b><span id="isbnId">${bl.isbn}</span> <br>
            <b class="sInfoTitleClass">TITLE:</b> <span id="titleId">${bl.title}</span>
            <b class="sInfoTitleClass">By</b>
            <c:forEach var="al" items="${author}">
                <a href="javaScript:authorInfo(${al.author.id})" style="text-decoration: none"><u><span id="mAuthorId">${al.author.firstName} ${al.author.lastName} ${al.author.fatherName}</span></u></a>
                <span style="height: 3px"></span>
            </c:forEach>
            <br>
            <b class="sInfoTitleClass">PUBLISHER:</b><span id="mPublisherId">${bl.publisher}</span><br>
            <b class="sInfoTitleClass">PAGE:</b> <span id="mPageId">${bl.page}</span><br>
            <b class="sInfoTitleClass">EDITION:</b><span id="mEditionId">${bl.edition}</span><br>
            <b class="sInfoTitleClass">LANGUAGE:</b><span id="mLanguageId">${bl.language.bookLanguage}</span><br>
            <b class="sInfoTitleClass">STAR:</b><span id="mStarId">${bl.star}</span><br>
            <b class="sInfoTitleClass">RELEASE DATE:</b><span id="mRelDate">${bl.releaseDate}</span><br>
            <div id="accordionId" class="accordion">
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
</c:forEach>

