<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/8/2021
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userPictureId" ></div>
<label for="userFNameP" id="userFNameLP">First Name</label><input type="text" id="userFNameP"><br/>
<label for="userLNameP" id="userLnameLP">Last Name</label><input type="text" id="userLNameP" ><br/>
<label for="userFatNameP" id="userFatNameLP">Father Name</label><input type="text" id="userFatNameP"
                                                                     ><br/>
<label for="userDobP" id="userDobLP">Day of Birth</label><input type="text" id="userDobP"><br/>
<label for="userGenderP" id="userGenderLP">Gender</label>
<select id="userGenderP">
    <option value="0">---Select Gender----</option>
    <option value="male">Male</option>
    <option value="female">Female</option>
</select> <br/>
<label for="userNationP" id="userNationLP">Nationality </label>
<select id="userNationP">
    <option value="0">---Select Nationality---</option>
    <c:forEach var="nl" items="${nationalityList}">
        <option value="${nl.id}">${nl.nationality}</option>
    </c:forEach>
</select>
<label for="userCountryP" id="userCountryLP">Country</label>
<select id="userCountryP">
    <option value="0">---Select Country</option>
    <c:forEach var="cl" items="${countryList}">
        <option value="${cl.id}">${cl.countryName}</option>
    </c:forEach>
</select>
<label for="userEmailP" id="userEmailLP">Email</label><input type="email" id="userEmailP" ><br/>
<h5 id="emailWarning">Registered with this email address</h5>
<label for="userPhoneP" id="userPhoneLP">Phone</label><input type="tel" id="userPhoneP" ><br/>
<label for="userAddressP" id="userAddressLP">Address</label><input type="tel" id="userAddressP"><br/>
<label for="userStatusP" id="userStatusLP">Status</label>
<select id="userStatusP">
    <option value="0">---Select Status</option>
    <c:forEach var="sl" items="${statusList}">
        <option value="${sl.id}">${sl.status}</option>
    </c:forEach>
</select>
<%--<label id="userPicureIdP" for="userPicture">Picture</label><input type="file" id="userPicture"><br/>--%>
<label for="userPasswordP" id="userPasswordLP">Password</label><input type="password" id="userPasswordP"
                                                                   ><br/>
<h5 id="wrongPassword">Minimum six characters, at least one letter, one number and one special character</h5>

