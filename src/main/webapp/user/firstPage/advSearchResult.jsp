<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/16/2021
  Time: 9:57 AM
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

</div>

<div id="bookForSubjectClass">
    <img src="#" class="specialSubjectClass"/>
    <div class="bookItems">
        <c:forEach var="bl" items="${resultBook}">
            <b class="sInfoTitleClass">ISBN:</b><span id="isbnId">${bl.isbn}</span> <br>
            <b class="sInfoTitleClass">TITLE:</b> <span id="titleId">${bl.title}</span>
            <b class="sInfoTitleClass">By</b>
<%--                    <a href="javaScript:authorInfo(${bl.author.id})" style="text-decoration: none"><u><span id="mAuthorId">--%>
                         ${bl.author.firstName} ${bl.author.lastName} ${bl.author.fatherName}</span></u>
<%--            </a>--%>
                    <span style="width: 4px"></span>

            <br>
            <b class="sInfoTitleClass">PUBLISHER:</b><span id="mPublisherId">${bl.publisher}</span><br>
            <b class="sInfoTitleClass">PAGE:</b> <span id="mPageId">${bl.page}</span><br>
            <b class="sInfoTitleClass">EDITION:</b><span id="mEditionId">${bl.edition}</span><br>
            <b class="sInfoTitleClass">LANGUAGE:</b><span id="mLanguageId">${bl.language.bookLanguage}</span><br>
            <b class="sInfoTitleClass">STAR:</b><span id="mStarId">${bl.star}</span><br>
            <b class="sInfoTitleClass">RELEASE DATE:</b><span id="mRelDate">${bl.releaseDate}</span><br>
            <b class="sInfoTitleClass">Subjects:</b>
                <span id="subjectOfBookSpId">
                        ${bl.subjects.subjectName}
                </span>
                <span style="width: 3px">,</span>
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
        </c:forEach>


