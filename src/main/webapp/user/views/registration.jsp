<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/31/2021
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
        $("#userFName").change(function () {
            if ($('#userFNameL').css('visibility') === 'hidden') {
                $('#userFNameL').css('visibility', 'visible');
            }
        });
        $("#userLName").change(function () {
            if ($('#userLnameL').css('visibility') === 'hidden') {
                $('#userLnameL').css('visibility', 'visible');
            }
        });
        $("#userFName").change(function () {
            if ($('#userFNameL').css('visibility') === 'hidden') {
                $('#userFNameL').css('visibility', 'visible');
            }
        });
        $("#userDob").change(function () {
            if ($('#userDobL').css('visibility') === 'hidden') {
                $('#userDobL').css('visibility', 'visible');
            }
        });
        $("#userGender").change(function () {
            if ($('#userGenderL').css('visibility') === 'hidden') {
                $('#userGenderL').css('visibility', 'visible');
            }
        });
        $("#userNation").change(function () {
            if ($('#userNationL').css('visibility') === 'hidden') {
                $('#userNationL').css('visibility', 'visible');
            }
        });
        $("#userCountry").change(function () {
            if ($('#userCountryL').css('visibility') === 'hidden') {
                $('#userCountryL').css('visibility', 'visible');
            }
        });
        $("#userEmail").change(function () {
            if ($('#userEmailL').css('visibility') === 'hidden') {
                $('#userEmailL').css('visibility', 'visible');
            }
        });
        $("#userPhone").change(function () {
            if ($('#userPhoneL').css('visibility') === 'hidden') {
                $('#userPhoneL').css('visibility', 'visible');
            }
        });
        $("#userAddress").change(function () {
            if ($('#userAddressL').css('visibility') === 'hidden') {
                $('#userAddressL').css('visibility', 'visible');
            }
        });
        $("#userStatus").change(function () {
            if ($('#userStatusL').css('visibility') === 'hidden') {
                $('#userStatusL').css('visibility', 'visible');
            }
        });
        $("#userPassword").change(function () {
            if ($('#userPasswordL').css('visibility') === 'hidden') {
                $('#userPasswordL').css('visibility', 'visible');
            }
        });
        $("#userConfPassword").change(function () {
            if ($('#userConfPasswordL').css('visibility') === 'hidden') {
                $('#userPasswordL').css('visibility', 'visible');
            }
        });
        $(document).on('keyup', '#userEmail', function () {
            $('#emailWarning').css("display", 'none');
            $('#userEmail').removeClass("warningClassForUser");
        });
        $(document).on('keyup', '#userPassword', function () {
            $('#wrongPassword').css("display", 'none');
            $('#userPassword').removeClass("warningClassForUser");
        });
        $(document).on('keyup', '#userConfPassword', function () {
            $('#wrongConfPassword').css("display", 'none');
            $('#userConfPassword').removeClass("warningClassForUser");
        });
    });
</script>
<div class="regStyleClass">
<label for="userFName" id="userFNameL">First Name</label><input type="text" id="userFName"
                                                                placeholder="First Name"><br/>
<label for="userLName" id="userLnameL">Last Name</label><input type="text" id="userLName" placeholder="Last Name"><br/>
<label for="userFatName" id="userFatNameL">Father Name</label><input type="text" id="userFatName"
                                                                     placeholder="Father Name"><br/>
<label for="userDob" id="userDobL">Day of Birth</label><input type="text" id="userDob" placeholder="Day of Birth"><br/>
<label for="userGender" id="userGenderL">Gender</label>
<select id="userGender">
    <option value="0">---Select Gender----</option>
    <option value="male">Male</option>
    <option value="female">Female</option>
</select> <br/>
<label for="userNation" id="userNationL">Nationality </label>
<select id="userNation">
    <option value="0">---Select Nationality---</option>
    <c:forEach var="nl" items="${nationalityList}">
        <option value="${nl.id}">${nl.nationality}</option>
    </c:forEach>
</select>
<label for="userCountry" id="userCountryL">Country</label>
<select id="userCountry">
    <option value="0">---Select Country</option>
    <c:forEach var="cl" items="${countryList}">
        <option value="${cl.id}">${cl.countryName}</option>
    </c:forEach>
</select>
<label for="userEmail" id="userEmailL">Email</label><input type="email" id="userEmail" placeholder="excample@gmail.com"><br/>
<h5 id="emailWarning">Registered with this email address</h5>
<label for="userPhone" id="userPhoneL">Phone</label><input type="tel" id="userPhone" placeholder="Mobile Phone"><br/>
<label for="userAddress" id="userAddressL">Address</label><input type="tel" id="userAddress" placeholder="Address"><br/>
<label for="userStatus" id="userStatusL">Status</label>
<select id="userStatus">
    <option value="0">---Select Status</option>
    <c:forEach var="sl" items="${statusList}">
        <option value="${sl.id}">${sl.status}</option>
    </c:forEach>
</select>
<label id="userPicureId" for="userPicture">Picture</label><input type="file" id="userPicture"><br/>
<label for="userPassword" id="userPasswordL">Password</label><input type="password" id="userPassword"
                                                                    placeholder="Password"><br/>
<h5 id="wrongPassword">Minimum six characters, at least one letter, one number and one special character</h5>

<label for="userConfPassword" id="userConfPasswordL">Confirm Password</label><input type="password"
                                                                                    id="userConfPassword"
                                                                                    placeholder="Confirm Password"><br/>
<h5 id="wrongConfPassword">Invalid Conform Password</h5>
</div>