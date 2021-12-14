<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/31/2021
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Free Library</title>


    <%--css--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <%--css--%>


    <%--My css--%>
    <link type="text/css" rel="stylesheet" href="user/css/Register.css">
    <link type="text/css" rel="stylesheet" href="user/firstPage/css/home.css">
    <link type="text/css" rel="stylesheet" href="user/firstPage/css/structureLibrary.css">
    <%--My css--%>



    <script type="application/javascript" src="user/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="user/js/jquery/jquery-latest.js"></script>
    <script type="text/javascript" src="user/js/jquery/jquery.layout-latest.js"></script>

    <%--My js--%>
    <script type="application/javascript" src="user/js/main.js"></script>
    <script type="application/javascript" src="user/js/userRegistration.js"></script>
    <%--My js--%>

    <script type="application/javascript" src="user/js/jquery/jquery-ui.js"></script>
    <script src="//rawgithub.com/indrimuska/jquery-editable-select/master/dist/jquery-editable-select.min.js"></script>
    <link href="//rawgithub.com/indrimuska/jquery-editable-select/master/dist/jquery-editable-select.min.css" rel="stylesheet">
    <script type="application/javascript" src="user/js/jquery/date.min.js"></script>

</head>
<body>
<div class="ui-layout-center">
    <jsp:include page="firstPage/centerInner.jsp"/>

</div>

<%--<div class="ui-layout-south" >--%>
<%--<jsp:include page="firstPage/footer.jsp"></jsp:include>--%>
<%--</div>--%>

<%--<div class="ui-layout-east">East</div>--%>
<div class="ui-layout-west">
    <jsp:include page="firstPage/subjectMenu.jsp"/>
</div>
<div class="ui-layout-north">
   <jsp:include page="firstPage/headerMenu.jsp"/>
    <div id="chRegisterTypeDialogId"></div>
    <div id="registrationDialogId" class="registrationDialogClass"></div>
    <div id="loginDialogId" class="loginDialogId"></div>
    <div id="userProfileDialogId" class="userProfileDialogClass"></div>
    <div id="socialNetDialogId" class="socialNetDialogClass"></div>
    <div id="advSearchDialogId" class="advSearchDialogClass"></div>
</div>

</body>
</html>