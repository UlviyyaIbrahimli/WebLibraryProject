<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/23/2021
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    $(function () {
        $(document).on('keyup', '#subNameU', function () {
            $("#warningIdU").css('display', 'none');
        });

        $("#subInfoU").val('${subject.subjectInfo}')
    });
</script>


<label for="subNameU" id="subNameL">Subject Name:</label>
<input type="text" name="subNameU" id="subNameU" value="${subject.subjectName}"><br>
<h5 id="warningIdU" style="color: red"></h5>
<%--<h5 id="warningIdU2" style="color: red"></h5>--%>

<label for="subInfoU" id="subInfoL" style=" margin-top: 20px">Note:</label>
<textarea name="subjectInfo" id="subInfoU" style="height: 150px;"></textarea> <br>
