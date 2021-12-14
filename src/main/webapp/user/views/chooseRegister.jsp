<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/31/2021
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
$(function (){
$('#newAccountBtnId').click(function (){
   $('#registrationDialogId').load('user/views/registration.jsp',function (){
       $(this).load('us?action=userCombo');
   });
   $('#chRegisterTypeDialogId').dialog('close');
   $('#registrationDialogId').dialog('open');
});

});


</script>


    <button class="chBtnDsg"><div class="faDsg"><i class="fa fa-facebook" aria-hidden="true" style="font-size:18px"></i></div> Sign with Facebook</button>
    <button class="chBtnDsg"><div  class="faDsg"><i class="fa fa-google-plus" aria-hidden="true" style="font-size:18px"></i></div>Sign with Google</button>
    <button  class="chBtnDsg"> <div class="faDsg"><i class="fa fa-twitter" aria-hidden="true" style="font-size:18px" ></i></div>Sign with Twitter</button>
    <button class="chBtnDsg"  id="newAccountBtnId" tabindex="1"><div class="faDsg" ><i class="fa fa-user-plus" style="font-size:18px"></i></div>Create New Account</button>

