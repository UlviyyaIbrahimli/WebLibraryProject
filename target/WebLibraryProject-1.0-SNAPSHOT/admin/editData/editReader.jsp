<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/18/2021
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script  type="application/javascript">
$(function (){
    $("#rDobU").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd',
    });

    $(document).on('keyup', '#rFNameU', function () {
        $("#nullReaderFistNameU").css('display', 'none');
    });

    $(document).on('keyup', '#rLNameU', function () {
        $("#nullReaderLastNameU").css('display', 'none');
    });
    $(document).on('change', '#rGenderU', function () {
        $("#nullReaderGenderU").css('display', 'none');
    });
    $(document).on('change', '#rStatusU', function () {
        $("#nullReaderStatusU").css('display', 'none');
    });
    $(document).on('keyup', '#rEmailU', function () {
        $("#nullReaderEmailU").css('display', 'none');
    });

    $(document).on('keyup', '#rEmailU', function () {
        $("#wrongEmailU").css('display', 'none');
    });

    $(document).on('change', '#rNationU', function () {
        $("#NullReaderNationality").css('display', 'none');
    });
    $(document).on('change', '#rCountryU', function () {
        $("#NullReaderCountry").css('display', 'none');
    });
    $(document).on('keyup', '#rPasswordU', function () {
        $("#NullReaderPassword").css('display', 'none');
    });



    $(document).on('keyup', '#rEmailU', function(){
        $("#warningEmailU").css('display','none');
        $('#rEmailU').removeClass("warningClass");
   });

    $(document).on('keyup', '#rPasswordU', function(){
        $("#wrongPasswordU").css('display','none');
        $('#rPasswordU').removeClass("warningClass");
    });
    $('#rCountryU').val('${reader.country.id}');
    $('#rNationU').val('${reader.nationality.id}');
    $('#rGenderU').val('${reader.gender}');
    $('#rStatusU').val('${reader.status.id}');

});

</script>
<label for="rFNameU" id="rFNameLU">First Name</label><input type="text" id="rFNameU" name="rFNameU" value="${reader.firstName}" required><br/>
<h5 id="nullReaderFistNameU" class="hideWarning">Add First Name</h5>
<label for="rLNameU" id="rLNameLU">Last Name</label><input type="text" id="rLNameU" name="rLNameU" value="${reader.lastName}" required><br/>
<h5 id="nullReaderLastNameU" class="hideWarning">Add Last Name</h5>
<label for="rFatNameU" id="rFatNameLU">Father Name</label><input type="text" id="rFatNameU" name="rFatNameU" value="${reader.fatherName}" required><br/>
<label for="rDobU" id="rDobLU">Day of Birth</label><input type="text" id="rDobU" name="rDob" value="${reader.dob}" ><br/>
<label for="rGenderU" id="rGenderL">Gender</label>
<div class="select">
    <select id="rGenderU">
        <option value="0" selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>
</div>
<h5 id="nullReaderGenderU" class="hideWarning">Select Gender</h5>
<label for="rStatusU" id="rStatusL">Status</label>
<div class="select" >
    <select id="rStatusU">
        <option value="0" selected>Select Status</option>
        <c:forEach var="sl" items="${statusList}">
            <option value="${sl.id}">${sl.status}</option>
        </c:forEach>
    </select>
</div>
<h5 id="nullReaderStatusU" class="hideWarning">Select Status</h5>
<label for="rEmailU" id="rEmailLU">Email</label><input type="email" id="rEmailU" name="rEmailU" value="${reader.eMail}"><br/>
<h5 id="warningEmailU">Already registered with this email address</h5>
<h5 id="nullReaderEmailU" class="hideWarning">Add Email</h5>
<h5 id="wrongEmailU" class="hideWarning">Incorrect Email</h5>
<label for="rPhoneU" id="rPhoneLU">Phone</label><input type="tel" id="rPhoneU" name="rTelU" value="${reader.phone1}"><br/>
<label for="rNationU" id="rNationLU">Nationality</label>
<div class="select">
    <select id="rNationU">
        <option value="0" selected>Select Nationality</option>
        <c:forEach var="nl" items="${nationalityList}">
            <option value="${nl.id}">${nl.nationality}</option>
        </c:forEach>
    </select>
</div>
<h5 id=nullReaderNationalityU" class="hideWarning">Select Nationality</h5>

<label for="rCountryU" id="rCountryLU">Country</label>
<div class="select" >
    <select id="rCountryU">
        <option value="0" selected>Select Country</option>
        <c:forEach var="cl" items="${countryList}">
            <option value="${cl.id}" >${cl.countryName}</option>
        </c:forEach>
    </select>
</div>
<h5 id="nullReaderCountry" class="hideWarning">Select Country</h5>
<label for="rCityU" id="rCityLU">City</label><input type="text" id="rCityU" name="rCityU" value="${reader.city}"><br/>
<label for="rAddressU" id="rAddressLU">Address</label><input type="text" id="rAddressU" name="rAddressU" value="${reader.homeNumber}"><br/>
<label for="rPasswordU" id="rPasswordLU">Password</label><input type="text" id="rPasswordU" name="rPasswordU" value="${reader.password}"><br/>
<h5 id="wrongPasswordU">Minimum six characters, at least one letter, one number and one special character</h5>
<h5 id="nullReaderPasswordU" class="hideWarning">Add Password</h5>
<label for="rNoteU" id="rNoteLU">Note </label><textarea id="rNoteU" name="rNoteU" ></textarea>
