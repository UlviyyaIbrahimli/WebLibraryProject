<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/18/2021
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Page</title>
    <link rel="shortcut icon" href="admin/js/images/favicon.ico">

    <link href="admin/hh/docsupport/style.css" rel="stylesheet"/>
    <link href="admin/hh/editable/dist/jquery-editable-select.css" rel="stylesheet">
    <link rel="stylesheet" href="admin/js/jquery/jquery-ui.css">
    <link href="admin/hh/chosen.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="application/javascript" rel="script" src="admin/hh/chosen.jquery.js"></script>    <script>
        $.fn.extend=$.fn.ext;
    </script>
    <link rel="stylesheet" href="admin/css/adminCss.css" type="text/css">
    <script type="application/javascript" rel="script" src="admin/js/adminjs.js"></script>
    <script type="application/javascript" rel="script" src="admin/js/jquery/jquery-latest.js"></script>
    <script type="application/javascript" rel="script" src="admin/js/jquery/jquery.layout-latest.js"></script>

    <%--    Bu olande list islemir--%>
    <link type="text/css" rel="stylesheet" href="admin/js/jquery/jquery.dataTables.min.css">
    <script rel="script" type="application/javascript" src="admin/js/jquery/jquery.dataTables.min.js"></script>
    <script src="admin/js/jquery/jquery-ui.js"></script>

    <script type="application/javascript" rel="script" src="admin/js/author.js"></script>
    <script type="application/javascript" rel="script" src="admin/js/subject.js"></script>
    <script type="application/javascript" rel="script" src="admin/js/book.js"></script>
    <script type="application/javascript" rel="script" src="admin/js/reader.js"></script>

    <script src="admin/hh/editable/dist/jquery-editable-select.min.js"></script>
    <script src="admin/hh/editable/dist/jquery-editable-select.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

<%--Multiple Select--%>
<%--    <script src="admin/chosen/chosen.jquery.min.js"></script>--%>
<%--    <link rel="stylesheet" href="/admin/chosen/chosen.min.css">--%>

<%--AuthoComplete--%>
    <script src="admin/js/jquery/autofill.js"></script>
<%----%>
</head>
<div class="ui-layout-center">
</div>
<div class="ui-layout-north">
    <p>Admin Page</p></div>
<div id="confirm"></div>
<div class="ui-layout-south">
<div id="warningForMenu" class="warningFormenu"></div>
    <div id="newAuthorDialogId" class="newAuthorDialog"></div>
    <div id="editAuthorDialogId" class="editAuthorDialog"></div>
    <div id="newSubjectDialogId" class="newSubjectDialog"></div>
    <div id="editSubjectDialogId" class="editSubjectDialog"></div>
    <div id="newBookDialogId" class="newBookDialog"></div>
    <div id="editBookDialogId" class="editBookDialog"></div>
    <div id="newReaderDialogId" class="newReaderDialog"></div>
    <div id="editReaderDialogId" class="editReaderDialog"></div>
    <div id="bookAdvSearchDialogId" class="advBookSearchDialog"></div>
    <div id="readerAdvSearchDialogId" class="readerAdvSearchDialog"></div>
    <div id="authorAdvSearchDialogId" class="authorAdvSearchDialog"></div>

</div>
<div class="ui-layout-east">
    <button id="newBtn" class="glow-on-hover"><i class="fa fa-plus" aria-hidden="true"></i>
        New</button>
    <button id="advanceSearchBtn" class="glow-on-hover"><i class="fa fa-search" aria-hidden="true"></i>
        Advance Search</button>

<%--<a href="index.jsp">Home Page</a>--%>
</div>
<div class="ui-layout-west">
    <button id="authorBtn" class="glow-on-hover"><i class="fa fa-user fa-lg" aria-hidden="true"></i>Author</button>
    <button id="subjectBtn" class="glow-on-hover"><i class="fa fa-tasks fa-lg" aria-hidden="true"></i>Subject
    </button>
    <button id="bookBtn" class="glow-on-hover"><i class="fa fa-book fa-lg" aria-hidden="true"></i>Book</button>
    <button id="readerBtn" class="glow-on-hover"><i class="fa fa-book-reader fa-lg"></i>Reader</button>

</div>
</body>
</html>
