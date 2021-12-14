<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/2/2021
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script  type="application/javascript">
    $(function (){
        $("#rDob").datepicker({
            changeMonth: true,
            changeYear: true,
             dateFormat: 'yy-mm-dd',
        });

        $(document).on('keyup', '#rFName', function () {
            $("#NullReaderFirstName").css('display', 'none');
        });

        $(document).on('keyup', '#rLName', function () {
            $("#NullReaderLastName").css('display', 'none');
        });
        $(document).on('change', '#rGender', function () {
            $("#NullReaderGender").css('display', 'none');
        });
        $(document).on('change', '#rStatus', function () {
            $("#NullReaderStatus").css('display', 'none');
        });
        $(document).on('keyup', '#rEmail', function () {
            $("#NullReaderEmail").css('display', 'none');
        });

        $(document).on('keyup', '#rEmail', function () {
            $("#wrongReaderEmail").css('display', 'none');
        });

        $(document).on('change', '#rNation', function () {
            $("#NullReaderNationality").css('display', 'none');
        });
        $(document).on('change', '#rCountry', function () {
            $("#NullReaderCountry").css('display', 'none');
        });
        $(document).on('keyup', '#rPassword', function () {
            $("#NullReaderPassword").css('display', 'none');
        });
        $("#rFName").change(function () {
            if ($('#rFNameL').css('visibility') === 'hidden') {
                $('#rFNameL').css('visibility', 'visible');
            }
        });
        $("#rLName").change(function () {
            if ($('#rLNameL').css('visibility') === 'hidden') {
                $('#rLNameL').css('visibility', 'visible');
            }
        });
        $("#rFatName").change(function () {
            if ($('#rFatNameL').css('visibility') === 'hidden') {
                $('#rFatNameL').css('visibility', 'visible');
            }
        });
        $("#rDob").change(function () {
            if ($('#rDobL').css('visibility') === 'hidden') {
                $('#rDobL').css('visibility', 'visible');
            }
        });
        $("#rEmail").change(function () {
            if ($('#rGenderL').css('visibility') === 'hidden') {
                $('#rGenderL').css('visibility', 'visible');
            }
        });
        $("#rStatus").change(function () {
            if ($('#rStatusL').css('visibility') === 'hidden') {
                $('#rStatusL').css('visibility', 'visible');
            }
        });
        $("#rGender").change(function () {
            if ($('#rGenderL').css('visibility') === 'hidden') {
                $('#rGenderL').css('visibility', 'visible');
            }
        });
        $("#rEmail").change(function () {
            if ($('#rEmailL').css('visibility') === 'hidden') {
                $('#rEmailL').css('visibility', 'visible');
            }
        });


        $("#rPhone").change(function () {
            if ($('#rPhoneL').css('visibility') === 'hidden') {
                $('#rPhoneL').css('visibility', 'visible');
            }
        });
        $("#rNation").change(function () {
            if ($('#rNationL').css('visibility') === 'hidden') {
                $('#rNationL').css('visibility', 'visible');
            }
        });
        $("#rCountry").change(function () {
            if ($('#rCountryL').css('visibility') === 'hidden') {
                $('#rCountryL').css('visibility', 'visible');
            }
        });
        $("#rCity").change(function () {
            if ($('#rCityL').css('visibility') === 'hidden') {
                $('#rCityL').css('visibility', 'visible');
            }
        });
        $("#rAddress").change(function () {
            if ($('#rAddressL').css('visibility') === 'hidden') {
                $('#rAddressL').css('visibility', 'visible');
            }
        });
        $("#rPassword").change(function () {
            if ($('#rPasswordL').css('visibility') === 'hidden') {
                $('#rPasswordL').css('visibility', 'visible');
            }
        });
        $("#rNote").change(function () {
            if ($('#rNoteL').css('visibility') === 'hidden') {
                $('#rNoteL').css('visibility', 'visible');
            }
        });
        $(document).on('keyup', '#rEmail', function(){
            $('#rEmail').removeClass("hideWarning");
            $('#warningEmail').css('display','none');
        });
        $(document).on('keyup', '#rPassword', function(){
            $('#wrongPassword').css("display",'none');
            $('#rPassword').removeClass("hideWarning");
        });
    });

</script>
<label for="rFName" id="rFNameL">First Name</label><input type="text" id="rFName" name="rFName" placeholder="First Name" required><br/>
<h5 id="NullReaderFirstName" class="hideWarning">Add First Name</h5>
<label for="rLName" id="rLNameL">Last Name</label><input type="text" id="rLName" name="rLName" placeholder="Last Name" required><br/>
<h5 id="NullReaderLastName" class="hideWarning">Add Last Name</h5>

<label for="rFatName" id="rFatNameL">Father Name</label><input type="text" id="rFatName" name="rFatName" placeholder="Father Name" required><br/>
<label for="rDob" id="rDobL">Day of Birth</label><input type="text" id="rDob" name="rDob" placeholder="Day of Brith" ><br/>
<label for="rGender" id="rGenderL">Gender</label>
<h5 id="NullReaderGender" class="hideWarning">Select Gender</h5>

<div class="select">
    <select id="rGender">
        <option value="0" selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>
</div>
<label for="rStatus" id="rStatusL">Status</label>
<div class="select" >
    <select id="rStatus">
        <option value="0" selected>Select Status</option>
        <c:forEach var="sl" items="${statusList}">
            <option value="${sl.id}">${sl.status}</option>
        </c:forEach>
    </select>
</div>
<h5 id="NullReaderStatus" class="hideWarning">Select Status</h5>
<label for="rEmail" id="rEmailL">Email</label><input type="email" id="rEmail" name="rEmail" placeholder="example@gmail.com"><br/>
<h5 id="warningEmail" >Already registered with this email address</h5>
<h5 id="NullReaderEmail" class="hideWarning">Add Email Address</h5>
<h5 id="wrongReaderEmail" class="hideWarning">Incorrect Email Format</h5>

<label for="rPhone" id="rPhoneL">Phone</label><input type="tel" id="rPhone" name="rTel" placeholder="Mobile Phone"><br/>
<label for="rNation" id="rNationL">Nationality</label>
<div class="select">
    <select id="rNation">
        <option value="0" selected>Select Nationality</option>
        <c:forEach var="nl" items="${nationalityList}">
            <option value="${nl.id}">${nl.nationality}</option>
        </c:forEach>
    </select>
</div>
<h5 id="NullReaderNationality" class="hideWarning">Select Nationality</h5>

<label for="rCountry" id="rCountryL">Country</label>
<div class="select" >
    <select id="rCountry">
        <option value="0" selected>Select Country</option>
        <c:forEach var="cl" items="${countryList}">
            <option value="${cl.id}" >${cl.countryName}</option>
        </c:forEach>
    </select>
</div>
<h5 id="NullReaderCountry" class="hideWarning">Select Country</h5>

<label for="rCity" id="rCityL">City</label><input type="text" id="rCity" name="rCity" placeholder="City"><br/>
<label for="rAddress" id="rAddressL">Address</label><input type="text" id="rAddress" name="rAddress" placeholder="Address"><br/>
<label for="rPassword" id="rPasswordL">Password</label><input type="text" id="rPassword" name="rPassword" placeholder="Password"><br/>
<h5 id="wrongPassword">Minimum six characters, at least one letter, one number and one special character
    <h5 id="NullReaderPassword"  class="hideWarning">Add Password</h5>

</h5>
<label for="rNote" id="rNoteL">Note </label><textarea id="rNote" name="rNote" placeholder="Note"></textarea>
