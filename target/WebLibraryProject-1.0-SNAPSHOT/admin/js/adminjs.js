glbAuthorId = 0;
currentAuthorId = 0;
let globalBtnForSearch = null;
let globalBtnForNew = null;
$(function () {

    $("#warningForMenu").dialog({
        title: 'Warning!!!',
        height: 200,
        width: 300,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'warningDialogForMenuClass',
        buttons: {
            'Close': {
                text: 'Close',
                id: 'closeAuthorBtnId',
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }

        }
    });
    $('#warningForMenu').load('admin/warningForMenu.jsp');

    $('#advanceSearchBtn').css('display', 'block');

    $('#newBtn').click(function () {
        if (globalBtnForNew === null) {
            $('#warningForMenu').dialog('open');
        }
    });

    $('#advanceSearchBtn').click(function () {
        if (globalBtnForSearch === null) {
            $('#warningForMenu').dialog('open');
        }
    });

    jQuery.support.cors = true;
    $('body').layout({applyDemoStyles: true});
    $('.ui-layout-north').css('background-color', 'lightgrey');
    $('.ui-layout-center').css('background-color', 'darkgrey');
    $('.ui-layout-south').css('background-color', 'lightgrey');
    $('.ui-layout-west').css('background-color', 'grey');
    $('.ui-layout-east').css('background-color', 'grey');

});
