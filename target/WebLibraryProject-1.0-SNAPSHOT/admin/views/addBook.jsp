<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/1/2021
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">

    $("#bReleaseDate").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd',
    });

    $(document).on('keyup', '#bIsbn', function () {
        $("#bNullIsbn").css('display', 'none');
    });
    $(document).on('keyup', '#bTitle', function () {
        $("#bNullTitle").css('display', 'none');
    });
    $(document).on('keyup', '#bPage', function () {
        $("#bNullPage").css('display', 'none');
    });
    $(document).on('keyup', '#bPublisher', function () {
        $("#bNullPublisher").css('display', 'none');
    });
    $(document).on('keyup', '#bReleaseDate', function () {
        $("#bNullReleaseDate").css('display', 'none');
    });
    $(document).on('change', '#bLanguage', function () {
        $("#bNullLanguage").css('display', 'none');
    });
    $("#bIsbn").change(function () {
        if ($('#bIsbnL').css('visibility') === 'hidden') {
            $('#bIsbnL').css('visibility', 'visible');
        }
    });

    $("#bTitle").change(function () {
        if ($('#bTitleL').css('visibility') === 'hidden') {
            $('#bTitleL').css('visibility', 'visible');
        }
    });
    $("#bPage").change(function () {
        if ($('#bPageL').css('visibility') === 'hidden') {
            $('#bPageL').css('visibility', 'visible');
        }
    });
    $("#bPublisher").change(function () {
        if ($('#bPublisherL').css('visibility') === 'hidden') {
            $('#bPublisherL').css('visibility', 'visible');
        }
    });
    $("#bEdition").change(function () {
        if ($('#bEditionL').css('visibility') === 'hidden') {
            $('#bEditionL').css('visibility', 'visible');
        }
    });

    $("#bAuthor").change(function () {
        if ($('#bAuthorL').css('visibility') === 'hidden') {
            $('#bAuthorL').css('visibility', 'visible');
        }
    });
    $("#bSubject").change(function () {
        if ($('#bSubjectL').css('visibility') === 'hidden') {
            $('#bSubjectL').css('visibility', 'visible');
        }
    });
    $("#bLanguage").change(function () {
        if ($('#bLanguageL').css('visibility') === 'hidden') {
            $('#bLanguageL').css('visibility', 'visible');
        }
    });

    $("#bStar").change(function () {
        if ($('#bStarL').css('visibility') === 'hidden') {
            $('#bStarL').css('visibility', 'visible');
        }
    });
    $("#bAbst").change(function () {
        if ($('#bAbstL').css('visibility') === 'hidden') {
            $('#bAbstL').css('visibility', 'visible');
        }
    });

    $("#bReleaseDate").change(function () {
        if ($('#bReleaseDateL').css('visibility') === 'hidden') {
            $('#bReleaseDateL').css('visibility', 'visible');
        }
    });

    $(document).on('keyup', '#bIsbn', function () {
        $("#warningForBookRep").css('display', 'none');
    });
    $(document).on('change', '#bAuthor', function () {
        $("#warningAboutAut").css('display', 'none');
    });
    $(document).on('change', '#bSubject', function () {
        $("#warningSubjectForBook").css('display', 'none');
    });
</script>


<label for="bIsbn" id="bIsbnL">ISBN</label><input type="number" id="bIsbn" name="bIsbn" placeholder="ISBN"><br/>
<h5 id="warningForBookRep"></h5>
<h5 id="bNullIsbn" class="hideWarning">Add ISBN</h5>
<label for="bTitle" id="bTitleL">Title</label><input type="text" id="bTitle" name="bTitle" placeholder="Title"><br/>
<h5 id="bNullTitle" class="hideWarning">Add Title</h5>
<label for="bPage" id="bPageL">Page</label><input type="number" id="bPage" name="bPage" placeholder="Page"><br/>
<h5 id="bNullPage" class="hideWarning">Add Page</h5>
<label for="bPublisher" id="bPublisherL">Publisher</label><input type="text" id="bPublisher" name="bPublisher"
                                                                 placeholder="Publisher"><br/>
<h5 id="bNullPublisher" class="hideWarning">Add Publisher</h5>

<label for="bEdition" id="bEditionL">Edition</label><input type="text" id="bEdition" name="bEdition"
                                                           placeholder="Edition"><br/>
<label for="bStar" id="bStarL">Star</label><input type="text" id="bStar"  name="bStar"
                                                  placeholder="star"><br/>

<label for="bLanguage" id="bLanguageL">Language</label>
<div class="select">
    <select id="bLanguage">
        <option value="0" selected>Select Language</option>
        <c:forEach var="ll" items="${languageList}">
            <option value="${ll.id}">${ll.bookLanguage}</option>
        </c:forEach>
    </select>
</div>
<h5 id="bNullLanguage" class="hideWarning">Select Language</h5>

<label for="bReleaseDate" id="bReleaseDateL">Release Date</label><input type="text" id="bReleaseDate"
                                                                        name="bRealeseDate"
                                                                        placeholder="Release Date"><br/>
<h5 id="bNullReleaseDate" class="hideWarning">Add Release Date</h5>
<label for="bSubject" id="bSubjectL">Subjects</label>
<select id="bSubject" multiple>
    <option value="0" selected>Select Subject</option>
    <c:forEach var="sl" items="${subjectList}">
        <option value="${sl.id}">${sl.subjectName}</option>
    </c:forEach>
</select>
<h5 id="warningSubjectForBook">Select Subject</h5>
<label for="bAuthor" id="bAuthorL">Authors</label>
<select id="bAuthor" multiple>
    <option value="0" selected>Select Author</option>
    <c:forEach items="${authorList}" var="al">
        <option value="${al.id}">${al.firstName} ${al.lastName}</option>
    </c:forEach>
</select>
<h5 id="warningAboutAut">Select Author</h5>
<label for="bAbst" id="bAbstL">Abstract</label><textarea id="bAbst" name="bAbst"></textarea>