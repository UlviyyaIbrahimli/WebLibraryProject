<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/23/2021
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="application/javascript">
      $(function (){

      $(document).on('keyup', '#subName', function(){
       $("#warningId").css('display','none');
      });
        $("#subName").change(function () {
            if ($('#subNameL').css('visibility') === 'hidden') {
                $('#subNameL').css('visibility', 'visible');
            }
        });

        $("#subjectInfo").change(function () {
            if ($('#subInfoL').css('visibility') === 'hidden') {
                $('#subInfoL').css('visibility', 'visible');
            }
        });
    })


  </script>


<label for="subName" id="subNameL">Subject Name:</label>
<input type="text" name="subName" id="subName" placeholder="Subject Title" required="" /><br>
<h5 id="warningId" style="color: red"></h5>
<label for="subjectInfo" id="subInfoL">Note:</label>
<textarea name="subjectInfo" id="subjectInfo" placeholder="Note" style="height: 100px"></textarea> <br>
