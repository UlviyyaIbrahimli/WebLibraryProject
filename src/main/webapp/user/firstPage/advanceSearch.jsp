<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/14/2021
  Time: 4:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    // window.onload=function (){
    $(function () {
        $('#sStartRelDate').datepicker();
        $('#sEndRelDate').datepicker();
        $('#sBook').editableSelect({
            filter:true,
                      effects:'default',
                       duration:'fast',
                        trigger:'focus',




        });
        $('#sAuthor').editableSelect();
        $('#sSubject').editableSelect();
        $('#sLanguage').editableSelect();


        $('#sBook')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advSearchTitleForBookId = li.text()
                }

            });


        $('#sAuthor')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advSearchAuthorBookId = li.val()
                }
            });

        $('#sSubject')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advSearchSubjectForBookId = li.val()
                }
            });
        $('#sLanguage')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    advSearchLanguageForBookId = li.val()
                }
            });
    });
    // }
</script>
<div class="advSearchBookClass" id="advSearchBookID">

    <div class="advEntityClass editableSelectClass"><label for="sBook">Title</label>
        <select id="sBook" class="editable-select">
            <c:forEach var="bl" items="${bookList}">
                <option value="${bl.id}">${bl.title}</option>
            </c:forEach>
        </select></div>

    <div class="advEntityClass editableSelectClass"><label for="sPage">Page</label><input type="text" id="sPage"><br>
    </div>
    <div class="advEntityClass">
        <label for="sAuthor">Author</label>
        <select id="sAuthor">
            <c:forEach var="al" items="${authorList}">
                <option value="${al.id}">${al.firstName} ${al.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="advEntityClass"><label for="sPublisher">Publisher</label><input type="text" id="sPublisher"></div>
    <div class="advEntityClass editableSelectClass"><label for="sSubject">Subject</label>
        <select id="sSubject">
            <c:forEach var="sl" items="${subjectList}">
                <option value="${sl.id}">${sl.subjectName}</option>
            </c:forEach>
        </select></div>
    <div class="advEntityClassForDate"><label for="sStartRelDate">Date</label><input type="text" id="sStartRelDate">
        &nbsp;
        <input type="text" id="sEndRelDate"><br>
    </div>

    <div class="advEntityClass editableSelectClass">
        <label for="sLanguage">Language</label>
        <select id="sLanguage">
            <c:forEach var="ll" items="${languageList}">
                <option value="${ll.id}">${ll.bookLanguage}</option>
            </c:forEach>
        </select>
    </div>

</div>
