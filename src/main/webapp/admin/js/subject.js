globalSubjectId = 0;
$(function () {

    $("#subjectBtn").click(function () {
        globalBtnForNew = 'subjectBtn';
        getSubjectData();
        $('#advanceSearchBtn').css('display','none');

    });

    $("#newSubjectDialogId").dialog({
        title: 'New Subject Panel',
        height: 400,
        width: 400,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Save': {
                text: 'Save',
                id: 'saveSubjectSaveBtnId',
                class: 'btnDialog',
                click: function () {
                    addSubject();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'saveClearSubjectSaveBtnId',
                class: 'btnDialog',
                click: function () {
                    clearSubjectAdd();
                }
            },
            'Close': {
                text: 'Close',
                id: 'saveCloseSubjectSaveBtnId',
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });


    $("#editSubjectDialogId").dialog({
        title: 'Update Subject',
        height: 400,
        width: 400,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Save': {
                text: 'Save',
                id: 'editSaveSubjectBtnId',
                class: 'btnDialog',
                click: function () {
                    updateSubject();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'editClearSubjectBtnId',
                class: 'btnDialog',
                click: function () {
                    clearSubjectEdit();
                }
            },
            'Close': {
                text: 'Close',
                id: 'editCloseSubjectBtnId',
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });



    $("#newBtn").click(function () {
        if (globalBtnForNew === 'subjectBtn') {
            $("#newSubjectDialogId").load("admin/views/newSubject.jsp", function () {
                $(this).dialog('open');

            });
        }
    });


});

function clearSubjectAdd() {
    $("#newSubjectDialogId").find("input").val("");
    $("#newSubjectDialogId").find("textarea").val("");
}

function clearSubjectEdit() {
    $("#editSubjectDialogId").find("input").val("");
    $("#editSubjectDialogId").find("textarea").val("");
}

function getSubjectData() {
    $.ajax({
        url: 'ss?action=subjectData',
        dataType: 'html',
        type: 'GET',
        success: function (reponce) {
            $(".ui-layout-center").html(reponce);
        },
        error: function () {
            alert("Fail");
        }
    });
}

function addSubject() {
    let subjectName = $("#subName").val();
    let subInfo = $("#subjectInfo").val();
    if (subjectName == null || subjectName === '') {
        $("#warningId").html("Add Subject Name!");
        $('#warningId').show();
    } else {
        let subData = {
            subName: subjectName,
            subInfo: subInfo,
        }
        $.ajax({
            url: 'ss?action=addSubject',
            data: subData,
            type: 'POST',
            dataType: 'text',
            success: function (responce) {
                if (responce === 'success') {
                    alert("Subject Added Successfully");
                    getSubjectData();
                    setTimeout(function () {
                        $("#newSubjectDialogId").dialog('close')
                    }, 3000);
                    getSubjectData();
                } else if (responce === 'warning') {
                    // alert("This Subject had been added!");
                    $("#warningId").html("This Subject had been added!");
                    $('#warningId').show();
                }
            },
            error: function () {
                alert("Fail");
            }
        });
    }
}

function editSubject(subjectId) {
    globalSubjectId = subjectId;
    $.ajax({
        url: 'ss?action=editSubject',
        data: 'subjectId=' + subjectId,
        type: 'GET',
        dataType: 'html',
        success: function (responce) {
            $("#editSubjectDialogId").html(responce);
            $("#editSubjectDialogId").dialog('open');

        },
        error: function () {
            alert("Error");
        }
    });
}

function updateSubject() {
    let subName = $("#subNameU").val();
    let subInfo = $("#subInfoU").val();
    if (subName === '' || subName === null) {
        // alert("Please add Subject Name!");
        $("#warningIdU").html("Add Subject Name!");
        $("#warningIdU").show();
    } else {
        let subData =
            {
                subName: subName,
                subInfo: subInfo,
                subjectId: globalSubjectId,
            }
        $.ajax({
            url: 'ss?action=updateSubject',
            data: subData,
            type: 'POST',
            dataType: 'text',
            success: function (responce) {
                if (responce === 'success') {
                    alert(subName + "  update Successfully");
                    getSubjectData();
                    setTimeout(function () {
                        $("#editSubjectDialogId").dialog('close')
                    }, 3000);
                } else if (responce === 'warning') {
                    $('#warningIdU').html('This subject had been added!');
                    $("#warningIdU").show();

                }
            },
            error: function () {
                alert("Error!")
            }
        });
    }
}

function confirmDeleteDialogSubject(message, subjectId, subjectName) {
    $('<div></div>').appendTo('body').html('<div style="color: #0a0add;font-size: 20px">' + message + '? </div>')
        .dialog({
            modal: true,
            title: 'Confirm Delete',
            zIndex: 10000,
            autoOpen: true,
            width: 250,
            height: 250,
            resizable: false,
            dialogClass: 'newDialogStyle',
            buttons: {
                'Yes': {
                    text: 'Yes',
                    id: 'confYes',
                    class: 'btnDialog',
                    click: function () {
                        deleteSubject(subjectId, subjectName);
                        $(this).dialog("close");
                    }
                },
                'No': {
                    text: 'No',
                    id: 'confNo',
                    class: 'btnDialog',
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            },
            'Close': {
                text: 'Close',
                id: 'confClose',
                class: 'btnDialog',
                function(event, ui) {
                    $(this).remove();
                }
            }
        });
}

function confirmDeleteSubject(subjectId, subjectName) {
    confirmDeleteDialogSubject("Are you sure delete " + '<i>' + subjectName + " ?", subjectId, subjectName);
}

function deleteSubject(subjectId, subjectName) {
    $.ajax({
        url: 'ss?action=deleteSubject',
        type: 'GET',
        dataType: 'text',
        data: 'subjectId=' + subjectId,
        success: function (responce) {
            if (responce === 'success') {
                getSubjectData();
                alert(subjectName + '  delete successfully');
            }
        },
        error: function () {
            alert("Fail");
        }
    });
}