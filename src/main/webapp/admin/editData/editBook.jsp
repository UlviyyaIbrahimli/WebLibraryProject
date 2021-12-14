<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/1/2021
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">

    $("#bReleaseDateU").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd',
    });

    $(document).on('keyup', '#bIsbnU', function () {
        $("#bNullIsbnU").css('display', 'none');
    });
    $(document).on('keyup', '#bTitleU', function () {
        $("#bNullTitleU").css('display', 'none');
    });
    $(document).on('keyup', '#bPageU', function () {
        $("#bNullPageU").css('display', 'none');
    });
    $(document).on('keyup', '#bPublisherU', function () {
        $("#bNullPublisherU").css('display', 'none');
    });
    $(document).on('keyup', '#bReleaseDateU', function () {
        $("#bNullReleaseDateU").css('display', 'none');
    });
    $(document).on('change', '#bLanguageU', function () {
        $("#bNullLanguageU").css('display', 'none');
    });



    $("#bIsbn").change(function () {
        if ($('#bIsbnL').css('visibility') === 'hidden') {
            $('#bIsbnL').css('visibility', 'visible');
        }
    });
    let arraySubject = [];
    <c:forEach var="bs" items="${subjectOfBook}">
    arraySubject.push("${bs.subjects.id}")
    </c:forEach>
    $("#bSubjectU").val(arraySubject);

    $("#bLanguageU").val(${book.language.id});

    let arrayAuthor = [];
    <c:forEach  var="al" items="${authorOfBook}">
    arrayAuthor.push("${al.author.id}")
    </c:forEach>
    $("#bAuthorU").val(arrayAuthor);

    $("#bAbstU").val('${book.bAbstract}');

    $(document).on('keyup', '#bIsbnU', function () {
        $("#warningForBookRepU").css('display', 'none');
    });
    $(document).on('change', '#bAuthorU', function () {
        $("#warningAboutAutU").css('display', 'none');
    });
    $(document).on('change', '#bSubjectU', function () {
        $("#warningSubjectForBookU").css('display', 'none');
    });

</script>

<label for="bIsbnU" id="bIsbnLU">ISBN</label><input type="number" id="bIsbnU" name="bIsbnU" value="${book.isbn}"><br/>
<h5 id="warningForBookRepU"></h5>
<h5 id="bNullIsbnU" class="hideWarning">Add ISBN</h5>
<label for="bTitleU" id="bTitleLU">Title</label><input type="text" id="bTitleU" name="bTitleU"
                                                       value="${book.title}"><br/>
<h5 id="bNullTitleU" class="hideWarning">Add Title</h5>
<label for="bPageU" id="bPageLU">Page</label><input type="number" id="bPageU" name="bPageU" value="${book.page}"><br/>
<h5 id="bNullPageU" class="hideWarning">Add Page</h5>
<label for="bPublisherU" id="bPublisherLU">Publisher</label><input type="text" id="bPublisherU" name="bPublisherU"
                                                                   value="${book.publisher}"><br/>
<h5 id="bNullPublisherU" class="hideWarning">Add Publisher</h5>
<label for="bEditionU" id="bEditionLU">Edition</label><input type="text" id="bEditionU" name="bEdition"
                                                             value="${book.edition}"><br/>
<label for="bStarU" id="bStarLU">Star</label><input type="text" id="bStarU" name="bStarU" value="${book.star}"><br/>
<label for="bReleaseDateU" id="bReleaseDateLU">Release Date</label><input type="text" id="bReleaseDateU"
                                                                          name="bRealeseDate"
                                                                          value="${book.releaseDate}"><br/>
<label for="bLanguageU" id="bLanguageLU">Language</label>
<div class="select">
    <select id="bLanguageU">
        <option value="0">Select Language</option>
        <c:forEach var="ll" items="${languageList}">
            <option value="${ll.id}">${ll.bookLanguage}</option>
        </c:forEach>
    </select>
</div>
<h5 id="bNullLanguageU" class="hideWarning" >Add Language</h5>
<label for="bSubjectU" id="bSubjectLU">Subjects</label>
<select id="bSubjectU" multiple style="height: 150px">
    <c:forEach var="sl" items="${subjectList}">
        <option value="${sl.id}">${sl.subjectName}</option>
    </c:forEach>
</select>
<h5 id="warningSubjectForBookU">Select Subject</h5>
<label for="bAuthorU" id="bAuthorLU">Authors</label>
<select id="bAuthorU" multiple style="height: 150px">
    <c:forEach items="${authorList}" var="al">
        <option value="${al.id}">${al.firstName} ${al.lastName}</option>
    </c:forEach>
</select>
<h5 id="warningAboutAutU">Select Author</h5>
<label for="bAbstU" id="bAbstLU">Abstract</label><textarea id="bAbstU" name="bAbstU"></textarea>
