<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/7/2021
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    // window.onload=function (){
    $(function () {
        $('#sStartRelDateForAdmin').datepicker({
            dateFormat: 'yy-mm-dd',
        });
        $('#sEndRelDateForAdmin').datepicker();
        $('#sBookForAdminForAdmin').editableSelect({
            filter:true,
            effects:'default',
            duration:'fast',
            trigger:'focus',
        });
        $('#sAuthorForAdmin').editableSelect();
        // $('#sSubjectForAdmin').editableSelect();
        $('#sLanguageForAdmin').editableSelect();


        $('#sBookForAdmin')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    titleForAdvanceSearchBook = li.text()
                }

            });


        $('#sAuthorForAdmin')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    authorForAdvanceSearchBook = li.val()
                }
            });

        // $('#sSubjectForAdmin')
        //     .editableSelect()
        //     .on('select.editable-select', function (e, li) {
        //         if (li != undefined) {
        //             subjectForAdvanceSearchBook = li.val()
        //         }
        //     });
        $('#sLanguageForAdmin')
            .editableSelect()
            .on('select.editable-select', function (e, li) {
                if (li != undefined) {
                    languageForAdvanceSearchBook = li.val()
                }
            });
    });
    // }
</script>
<div class="advSearchBookClassForAdmin" id="advSearchBookIdForAdmin">

    <div class="advEntityClassForAdmin editableSelectClassForAdmin">
        <label for="sBookForAdmin">Title</label>
        <select id="sBookForAdmin" class="editable-select">
            <c:forEach var="bl" items="${bookList}">
                <option value="${bl.id}">${bl.title}</option>
            </c:forEach>
        </select></div>

    <div class="advEntityClassForAdmin editableSelectClassForAdmin"><label for="sPageForAdmin">Page</label><input type="text" id="sPageForAdmin">
    </div>
    <div class="advEntityClassForAdmin editableSelectClassForAdmin">
        <label for="sAuthorForAdmin">Author</label>
        <select id="sAuthorForAdmin" class="editable-select">
            <c:forEach var="al" items="${authorList}">
                <option value="${al.id}">${al.firstName} ${al.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="advEntityClassForAdmin">
        <label for="sPublisherForAdmin">Publisher</label>
        <input type="text" id="sPublisherForAdmin">
    </div>
<%--    <div class="advEntityClassForAdmin editableSelectClassForAdmin">--%>
<%--        <label for="sSubjectForAdmin">Subject</label>--%>
<%--        <select id="sSubjectForAdmin">--%>
<%--            <c:forEach var="sl" items="${subjectList}">--%>
<%--                <option value="${sl.id}">${sl.subjectName}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>
    <div class="advEntityClassForDateForAdmin">
        <label for="sStartRelDateForAdmin">Date</label>
        <input type="text" id="sStartRelDateForAdmin">        &nbsp;
        <input type="text" id="sEndRelDateForAdmin"><br>
    </div>

    <div class="advEntityClassForAdmin editableSelectClassForAdmin">
        <label for="sLanguageForAdmin">Language</label>
        <select id="sLanguageForAdmin">
            <c:forEach var="ll" items="${languageList}">
                <option value="${ll.id}">${ll.bookLanguage}</option>
            </c:forEach>
        </select>
    </div>

</div>
