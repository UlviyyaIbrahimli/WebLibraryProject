<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/15/2021
  Time: 7:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('#accordionAuthorId').accordion();
    $("#accordionAuthorId").accordion({header: "h3", collapsible: true, active: false});

</script>
<div class="bookInfoDivClass">
<b class="sInfoTitleClass">First Name:</b> <span class="authorInfoEntityClass">${author.firstName}</span><br>
<b class="sInfoTitleClass">Last Name:</b><span class="authorInfoEntityClass">${author.lastName}</span> <br>
<b class="sInfoTitleClass">Father Name:</b> <span class="authorInfoEntityClass">${author.fatherName}</span><br>
<b class="sInfoTitleClass">Gender:</b> <span class="authorInfoEntityClass">${author.gender}</span> <br>
<b class="sInfoTitleClass">Day Of Birth:</b><span class="authorInfoEntityClass">${author.dob}</span><br>
<b class="sInfoTitleClass">Day Of Dead:</b><span class="authorInfoEntityClass">${author.deadDate}</span><br>
<b class="sInfoTitleClass">Rewards:</b><span class="authorInfoEntityClass">${author.reward}</span><br>
<b class="sInfoTitleClass">Nationality:</b><span class="authorInfoEntityClass">${author.nationality.nationality}</span><br><br>
<b class="sInfoTitleClass">Books:</b><br>
<ul>
    <c:forEach var="ba" items="${bookOfAuthor}">
        <li class="authorInfoEntityClass">${ba.book.title}   </li>
    </c:forEach>
</ul>
<b class="sInfoTitleClass">Subjects:</b><br>
<ul>
    <c:forEach var="sa" items="${subjectOfAuthor}">
        <li class="authorInfoEntityClass">${sa.subjects.subjectName}   </li>
    </c:forEach>
</ul>
<div id="accordionAuthorId" class="accordion">
    <h3>Author More Information</h3>
    <div>
        <p>
            ${author.authorInfo}
        </p>
    </div>
</div>
</div>