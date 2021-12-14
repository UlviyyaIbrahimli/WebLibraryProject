<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/26/2021
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="application/javascript">
    $(function () {

        // $(window).load(function () {
        //     $(".chosen-select").chosen();/// iki chosen confilic yaradadir
        // });
        $(document).on('keyup', '#aFName', function () {
            $("#warningForRepAuthorFull").css('display', 'none');
        });
        $(document).on('keyup', '#aFName', function () {
            $("#NullFirstName").css('display', 'none');
        });
        $(document).on('keyup', '#aLName', function () {
            $("#NullLastName").css('display', 'none');
        });
        $(document).on('change', '#aGender', function () {
            $("#NullGender").css('display', 'none');
        });
        $(document).on('change', '#aNationality', function () {
            $("#NullNation").css('display', 'none');
        });
        $(document).on('keyup', '#aLName', function () {
            $("#warningForRepAuthorFull").css('display', 'none');
        });
        $(document).on('change', '#aSubject', function () {
            $("#warningAuthForSub").css('display', 'none');
        });
        $("#aFName").change(function () {
            if ($('#afNameL').css('visibility') === 'hidden') {
                $('#afNameL').css('visibility', 'visible');
            }
        });

        $("#aLName").change(function () {
            if ($('#aLNameL').css('visibility') === 'hidden') {
                $('#aLNameL').css('visibility', 'visible');
            }
        });
        $("#aFatName").change(function () {
            if ($('#aFatNameL').css('visibility') === 'hidden') {
                $('#aFatNameL').css('visibility', 'visible');
            }
        });

        $("#aGender").change(function () {
            if ($('#aGenderL').css('visibility') === 'hidden') {
                $('#aGenderL').css('visibility', 'visible');
            }
        });
        $("#aDob").change(function () {
            if ($('#aDobL').css('visibility') === 'hidden') {
                $('#aDobL').css('visibility', 'visible');
            }
        });
        $("#aDeathDay").change(function () {
            if ($('#aGenderL').css('visibility') === 'hidden') {
                $('#aGenderL').css('visibility', 'visible');
            }
        });
        $("#aNationality").click(function () {
            if ($('#aNationalityL').css('visibility') === 'hidden') {
                $('#aNationalityL').css('visibility', 'visible');
            }
        });
        $("#reward").change(function () {
            if ($('#aRewardL').css('visibility') === 'hidden') {
                $('#aRewardL').css('visibility', 'visible');
            }
        });

        $("#aSubject").change(function () {
            if ($('#aSubjectL').css('visibility') === 'hidden') {
                $('#aSubjectL').css('visibility', 'visible');
            }
        });
        $("#aNote").keypress(function () {
            if ($('#aNoteL').css('visibility') === 'hidden') {
                $('#aNoteL').css('visibility', 'visible');
            }
        });


        $("#aDob").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'yy-mm-dd',
        });

        $("#aDeathDay").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'yy-mm-dd',
        });

    });
</script>

<label for="aFName" id="afNameL">First Name</label><input type="text" id="aFName" name="aFName"
                                                          placeholder="First Name" required="required">
<span id="NullFirstName" class="hideWarning">Add Name!</span>
<label for="aLName" id="aLNameL">Last Name</label><input type="text" id="aLName" name="aLName" placeholder="Last Name"
                                                         required="required">
<span id="NullLastName" class="hideWarning">Add Last Name!</span>
<h5 id="warningForRepAuthorFull" class="hideWarning"></h5>

<label for="aFatName" id="aFatNameL">Father Name</label><input type="text" id="aFatName" name="aFatName"
                                                               placeholder="Father Name" required="required">
<label for="aGender" id="aGenderL">Gender</label>
<div class="select">
    <select id="aGender" required="required">
        <option value="0" selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>

</div>
<span id="NullGender" class="hideWarning">Select Gender!</span>

<br>
<label for="aDob" id="aDobL">Day of Birth</label><input type="text" id="aDob" name="aDob" placeholder="Day Of Birth">
<label for="aDeathDay" id="aDeathDayL">Dead</label><input type="text" id="aDeathDay" name="aDeathDay"
                                                          placeholder="Death Date">
<label for="aNationality" id="aNationalityL">Nationality</label>
<div class="select">
    <select id="aNationality" required="required">
        <option value="0" selected>---Select Nationality---</option>
        <c:forEach var="nat" items="${nationalityList}">
            <option value="${nat.id}">${nat.nationality}</option>
        </c:forEach>
    </select>
</div>
<span id="NullNation" class="hideWarning">Select Nationality!</span>

<label for="reward" id="aRewardL">Reward</label><input type="text" id="reward" name="reward" placeholder="Reward">
<label for="aSubject" id="aSubjectL">Subject</label>
<div id="output"></div>
<select id="aSubject" multiple="true" required="required">
    <option value="0" selected>---Select Subject---</option>
    <c:forEach var="sub" items="${subjectList}">
        <option value="${sub.id}">${sub.subjectName}</option>
    </c:forEach>
    <%--    multiselect Sehv olur--%>
</select>
<h5 id="warningAuthForSub"></h5>
<label for="aNote" id="aNoteL">Note</label><textarea id="aNote" name="aNote"></textarea>
<h5 id="warningForAuthName">This Author had been added</h5>

