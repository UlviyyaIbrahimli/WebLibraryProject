<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/26/2021
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<script>
    $(function () {
        $("#aDobU").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'yy-mm-dd',
        });
        $("#aDeathDayU").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'yy-mm-dd',

        });

        $(window).load(function(){
            $(".chosen-select").chosen()
        });
        $(document).on('keyup', '#aFNameU', function () {
            $("#eAuthorNullFName").css('display', 'none');
        });
        $(document).on('keyup', '#aLNameU', function () {
            $("#eAuthorNullLName").css('display', 'none');
        });
        $(document).on('keyup', '#aLNameU', function () {
            $("#eAuthorRep").css('display', 'none');
        });
        $(document).on('keyup', '#aFNameU', function () {
            $("#eAuthorRep").css('display', 'none');
        });
        $(document).on('change', '#aGenderU', function () {
            $("#eAuthorNullGender").css('display', 'none');
        });

        $(document).on('change', '#aNationalityU', function () {
            $("#eAuthorNullNation").css('display', 'none');
        });
        $(document).on('change', '#aSubjectU', function () {
            $("#eAuthorNullSubject").css('display', 'none');
        });

        $("#aNationalityU").val('${author.nationality.id}');
        $("#aGenderU").val('${author.gender}');
        let arr=[];
        <c:forEach var="sl" items="${ssss}">
        arr.push(${sl.subjects.id})
        </c:forEach>

        $("#aSubjectU").val(arr);
        $("#aNote").val('${author.authorInfo}');
    });
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<label for="aFNameU">First Name</label><input type="text" id="aFNameU" name="aFName" value="${author.firstName}">
<span id="eAuthorNullFName" class="hideWarning">Add First Name</span>
<label for="aLNameU">Last Name</label><input type="text" id="aLNameU" name="aLName" value="${author.lastName}">
<span id="eAuthorNullLName" class="hideWarning">Add Last Name</span>
<span id="eAuthorRep" class="hideWarning">This Author is exits in base!</span>
<label for="aFatNameU">Father Name</label><input type="text" id="aFatNameU" name="aFatName"
                                                 value="${author.fatherName}">
<label for="aGenderU">Gender</label>
<div class="select">
    <select id="aGenderU">
        <option value="0" selected disabled>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>
</div>
<span id="eAuthorNullGender" class="hideWarning">Select Gender</span>
<label for="aDobU">Day of Birth</label><input type="text" id="aDobU" name="aDob" value="${author.dob}">
<label for="aDeathDayU">Dead</label><input type="text" id="aDeathDayU" name="aDeathDay" value="${author.deadDate}">
<label for="aNationalityU">Nationality</label>
<div class="select">
    <select id="aNationalityU">
        <option value="0" selected disabled>Select Nationality</option>
        <c:forEach var="nat" items="${nationalityList}">
            <option value="${nat.id}">${nat.nationality}</option>
        </c:forEach>
    </select>
</div>
<label id="eAuthorNullNation" style="color: red;display: none">Select Nationality</label>
<br>
<label for="rewardU">Reward</label><input type="text" id="rewardU" name="reward" value="${author.reward}">
<label for="aSubjectU">Subject</label>
<div id="output"></div>
<select id="aSubjectU" multiple="true" >
    <c:forEach var="sub" items="${subjectList}">
        <option value="${sub.id}">${sub.subjectName}</option>
    </c:forEach>
    <%--    multiselect Sehv olur--%>
</select>

<label id="eAuthorNullSubject" style="color: red;display: none">Select Subject</label>
<br>
<label for="aNoteU">Note</label><textarea id="aNoteU" name="aNote"></textarea>
