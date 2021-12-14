$(document).ready(function () {

    $('#registrationBtn').click(function () {
        $("#chRegisterTypeDialogId").load("user/views/chooseRegister.jsp", function () {
            $(this).dialog('open');
        });
    });
$('#userProfileDialogId').dialog({
    title: 'Registration',
    height: 1000,
    width: 1000,
    resizable: true,
    draggable: true,
    show: {effect: 'drop', direction: "up"},
    autoOpen: false,
    modal: true,
    dialogClass: 'registrationDClass',
    buttons: {
        'Update': {
            text: 'Save',
            id: 'userRegisterDialogId',
            class: 'btnDialogInReg',
            click: function () {
                updateProfile();
                $(this).dialog('open');
            }
        },

        'Close': {
            text: 'Close',
            id: 'editCloseSubjectBtnId',
            class: 'btnDialogInReg',
            click: function () {
                $(this).dialog('close');
            }
        }
    }
});
$('#profileBtn').click(function (){
    $('#userProfileDialogId').load('user/views/userProfile.jsp',function (){
        $(this).dialog('open');
    })
});
    $('#registrationDialogId').dialog({
        title: 'Registration',
        height: 600,
        width: 600,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'registrationDClass',
        buttons: {
            'Save': {
                text: 'Save',
                id: 'userRegisterDialogId',
                class: 'btnDialogInReg',
                click: function () {
                    userRegister();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'editClearSubjectBtnId',
                class: 'btnDialogInReg',
                click: function () {

                }
            },
            'Close': {
                text: 'Close',
                id: 'editCloseSubjectBtnId',
                class: 'btnDialogInReg',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });
    $('#chRegisterTypeDialogId').dialog({
        title: 'Registration',
        height: 500,
        width: 400,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'chRegisterContainer',
        buttons: {
            'Close': {
                text: 'Close',
                id: 'editCloseSubjectBtnId',
                class: 'chOfDialogBtn',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });
    $('#newAccountPId').click(function (){
        $('#loginDialogId').dialog('close');
        // $('.ui-layout-center').load('views/chooseRegister.jsp');
    });
    $('#loginDialogId').dialog({
        title: 'Login',
        height: 500,
        width: 400,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'registrationDClass',
        buttons: {
            'Login': {
                text: 'Login',
                id: 'loginDialogBtnId',
                class: 'loginDialogBtn',
                click: function () {
                    // loginUser();
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'clearLoginBtnId',
                class: 'loginDialogBtn',
                click: function () {
                    // clearLogin();
                }
            },
            'Close': {
                text: 'Close',
                id: 'editCloseSubjectBtnId',
                class: 'loginDialogBtn',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });
    $('#loginBtn').click(function () {
        $('#loginDialogId').load('user/views/login.jsp', function () {
            $(this).dialog('open');
        })
    });

});

function userRegister() {
    let userFirstName = $('#userFName').val();
    let userLastName = $('#userLName').val();
    let userFatherName = $('#userFatherName').val();
    let userDob = $('#userDob').val();
    let userGender = $('#userGender').val();
    let userNationality = $('#userNation').val();
    let userCountry = $('#userCountry').val();
    let userEmail = $('#userEmail').val();
    let userPhone = $('#userPhone').val();
    let userAddress = $('#userAddress').val();
    let userStatus = $('#userStatus').val();
    let userPassword = $('#userPassword').val();
    let userConfPassword = $('#userConfPassword').val();
    if (userFirstName === '') {
        alert("Add First Name");
    } else if (userLastName === '') {
        alert("Add Last Name");
    } else if (userGender === '0') {
        alert("Select Gender");
    } else if (userNationality === '0') {
        alert("Select Nationality");
    } else if (userCountry === '0') {
        alert("Select Country");
    } else if (userEmail === '') {
        alert("Add Email");
    } else if (userStatus === '0') {
        alert("Select Status");
    } else if (userPassword === '') {
        alert("Add Password");
    } else {
        let userData = {
            userFirstName: userFirstName,
            userLastName: userLastName,
            userFatherName: userFatherName,
            userDob: userDob,
            userGender: userGender,
            userNationality: userNationality,
            userCountry: userCountry,
            userEmail: userEmail,
            userPhone: userPhone,
            userAddress: userAddress,
            userStatus: userStatus,
            userPassword: userPassword,
            userConfPassword: userConfPassword
        }
        $.ajax({
            url: 'us?action=addUser',
            data: userData,
            dataType: 'text',
            type: 'GET',
            success: function (responce) {
                if (responce === 'repeatedEmail') {
                    $('#userEmail').addClass("warningClassForUser");
                    $('#emailWarning').show();
                } else if (responce === 'invalidConfPass') {
                    $('#userConfPassword').addClass("warningClassForUser");
                    $('#wrongConfPassword').show();
                } else if (responce === 'invalidPassword') {
                    $('#userPassword').addClass("warningClassForUser");
                    $('#wrongPassword').show();
                } else if (responce === 'success') {
                    setTimeout(function () {
                        $('#registrationDialogId').dialog('close');
                    }, 3000);
                }
            },
            error: function () {
                alert("Fail");
            }
        });
    }
}
function showPassword() {

    let x = document.getElementById('loginPassword');
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }

}
