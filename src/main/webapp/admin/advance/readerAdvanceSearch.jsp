<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/8/2021
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {

        $("#rAdvStartDate").datepicker({
            dateFormat: 'yy-mm-dd',
        });
        $('#rAdvDob').datepicker({
            dateFormat:'yy-mm-dd'
        });
        $('#rAdvEndDate').datepicker({
            dateFormat: 'yy-mm-dd',
        });
        $("#rAdvNationality").editableSelect();
        $("#rAdvCountry").editableSelect();
        $('#rAdvNationality').editableSelect().on('select.editable-select', function (e, li) {
            if (li.val() != undefined) {
                nationalityForAdvanceSearch = li.val()
            }
        });
        $('#rAdvCountry').editableSelect().on('select.editable-select', function (e, li) {
            if (li.val() != null) {
                countryForReaderAdvanceSearch = li.val()
            }
        });


    });

</script>
<label for="rAdvName">First Name</label><input type="text" id="rAdvName" name="rAdvName"><br/>
<label for="rAdvSurname">Last Name</label><input type="text" id="rAdvSurname" name="rAdvSurname"><br/>
<label for="rAdvDob">Day of Birth</label><input type="text" id="rAdvDob" name="rAdvDob"><br/>
<label for="rAdvStatus">Status</label>
<div class="select">
    <select id="rAdvStatus">
        <option value="0" selected>Select Status</option>
        <c:forEach var="stl" items="${statusList}">
            <option value="${stl.id}">${stl.status}</option>
        </c:forEach>
    </select>
</div>
<label for="rAdvGender">Gender</label>
<div class="select" >
    <select id="rAdvGender">
        <option value="0" selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>
</div>
<label for="rAdvNationality">Nationality</label>
<select id="rAdvNationality">
    <c:forEach var="nl" items="${nationalityList}">
        <option value="${nl.id}">${nl.nationality}</option>
    </c:forEach>
</select>
<label for="rAdvCountry">Country</label>
<select id="rAdvCountry">
    <c:forEach var="cl" items="${countryList}">
        <option value="${cl.id}">${cl.countryName}</option>
    </c:forEach>
</select>
<label for="rAdvActivity">Activity</label><input type="number" id="rAdvActivity"><br/>
<label for="rAdvStartDate" style="display: block">Date</label>
<input type="text" id="rAdvStartDate" style="width: 48%; display: inline-block">
<input type="text" id="rAdvEndDate" style="width: 48%">

