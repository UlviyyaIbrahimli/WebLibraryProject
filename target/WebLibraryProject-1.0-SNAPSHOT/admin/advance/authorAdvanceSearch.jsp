<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/7/2021
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>

    $(function () {
        $("#autAdvSubjectForAuthor").editableSelect();
        $("#autAdvNationalityForAuthor").editableSelect();
        $('#autAdvSubjectForAuthor').editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advanceSearchSubjectForAuthor = li.val()
                }
            });

        $('#autAdvNationalityForAuthor').editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advanceSearchNationalityForAuthor = li.val()
                }
            });
    });
</script>

<label for="autAdvName">Author First Name</label><input type="text" id="autAdvName" name="autAdvName"><br/>
<label for="autAdvSur">Author Last Name</label><input type="text" id="autAdvSur" name="autAdvDur"><br/>
<label for="autAdvSubjectForAuthor">Subject</label>
<select id="autAdvSubjectForAuthor">
    <c:forEach var="sl" items="${subjectList}">
        <option value="${sl.id}">${sl.subjectName}</option>
    </c:forEach>
</select>
<label for="autAdvNationalityForAuthor">Nationality</label>
<select id="autAdvNationalityForAuthor">
    <c:forEach var="nl" items="${nationalityList}">
        <option value="${nl.id}"> ${nl.nationality}</option>
    </c:forEach>
</select>
