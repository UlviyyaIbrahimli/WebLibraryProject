let advanceSearchSubjectForAuthor;
let advanceSearchNationalityForAuthor;
$(function () {

    $("#authorBtn").click(function () {
        globalBtnForNew = 'authorBtn';
        globalBtnForSearch='authorBtn';
        $('#advanceSearchBtn').css('display', 'block');
        getAuthorData();
    });

    $("#newAuthorDialogId").dialog({
        title: 'New Author',
        height: 600,
        width: 600,
        resizable: true,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Save': {
                text: 'Save',
                id: 'newAuthorBtnId',
                class: 'btnDialog',
                click: function () {
                    addAuthor();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'clearAuthorBtnId',
                class: 'btnDialog',
                click: function () {
                    clearAuthorAdd();
                }
            },
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


    $("#editAuthorDialogId").dialog({
        title: 'Update Author',
        height: 600,
        width: 600,
        resizable: false,
        draggable: true,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Update': {
                text: 'Update',
                id: 'updateAuthorBtnId',
                class: 'btnDialog',
                click: function () {
                    updateAuthor();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'clearAuthorBtnId',
                class: 'btnDialog',
                click: function () {
                    clearAuthorEdit();
                }
            },
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

    $("#authorAdvSearchDialogId").dialog({
        title: 'Advance Search',
        autoOpen: false,
        draggable: true,
        modal: true,
        resizable: false,
        show: {effect: 'drop', direction: "up"},
        height: 400,
        width: 600,
        dialogClass: "newDialogStyle",
        buttons: {
            'Search': {
                text: "Search",
                id: "advAuthorSearchBtnId",
                class: "btnDialog",
                click: function () {
                    advSearchAuthor();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "advSearchAuthorBtnId",
                class: "btnDialog",
                click: function () {
                    clearSearchAuthor();

                }
            },
            'Close': {
                text: "Close",
                id: "advReaderCloseBtnId",
                class: "btnDialog",
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });


    $("#newBtn").click(function () {
        if (globalBtnForNew === 'authorBtn') {
            $("#newAuthorDialogId").load('admin/views/addAuthor.jsp', function () {
                $(this).load("as?action=loadAuthorCmb", function () {
                    $(this).dialog('open');
                });
            });
        }

    });


    $("#advanceSearchBtn").click(function () {
        if (globalBtnForSearch === 'authorBtn') {
            $("#authorAdvSearchDialogId").load('admin/advance/authorAdvanceSearch.jsp', function () {
                $(this).load('as?action=fillAdvSearchCmb', function () {
                    $(this).dialog('open');
                });

            });
        }
    });


});

function getAuthorData() {
    $.ajax({
        url: 'as?action=getAuthorData',
        type: "GET",
        dataType: 'html',
        success: function (reponce) {
            $(".ui-layout-center").html(reponce)
        },
        error: function () {
            alert("Fail!")
        }
    });
}
function subjectOfAuthor(authorId){
    $.ajax({
        url: 'as?action=subjectOfAuthor',
        type: "GET",
        dataType: 'html',
        data:'authorId='+authorId,
        success: function (reponce) {
            $(".ui-layout-center").html(reponce)
        },
        error: function () {
            alert("Fail!")
        }
    });
}

function addAuthor() {
    let aFName = $("#aFName").val();
    let aLName = $("#aLName").val();
    let aFatName = $("#aFatName").val();
    let aDob = $("#aDob").val();
    let aDeathDay = $("#aDeathDay").val();
    let aGender = $("#aGender").val();
    let aNation = $("#aNationality").val();
    let aNote = $("#aNote").val();
    let aReward = $("#aReward").val();
    let aSubject = $("#aSubject").val();
    if (aFName === '' || aFatName ===null) {
        $('#NullFirstName').show();
    } else if (aLName === '' || aLName===null) {
        $('#NullLastName').show();
    } else if (aGender === '0') {
        $('#NullGender').show();
    } else if (aNation === '0') {
        $('#NullNation').show();
    } else {
//     let authorDataJson =
//         {
//             aFName: aFName,
//             aLName: aLName,
//             aFatName: aFatName,
//             aDob: aDob,
//             aDeathDay: aDeathDay,
//             aGender: aGender,
//             aNation: aNation,
//             aNote: aNote,
//             aReward: aReward,
//         };
        $.ajax({
            url: 'as?action=addAuthor',
            data: "aFName=" + aFName + "&aLName=" + aLName + "&aFatName=" + aFatName + "&aDob=" + aDob
                + "&aDeathDay=" + aDeathDay + "&aGender=" + aGender + "&aNote=" + aNote + "&aNation=" + aNation + "&aReward" + aReward + "&aSubject=" + aSubject,
            type: 'POST',
            dataType: 'text',
            success: function (responce) {
                if (responce === 'success') {
                    getAuthorData();
                    // $(".newDialogStyle .ui-dialog-buttonPane ").append($("<div style='width: 40px; height: 40px; background-color: #1add1a;left: 35%; position: absolute'> <i class='fa fa-check fa-2x ' style='color: #001800'></i></div>"));
                    alert("Author Added Successfully");
                    setTimeout(function () {
                        $("#newAuthorDialogId").dialog('close')
                    }, 3000);
                } else if (responce === 'warning') {
                    $('#warningForRepAuthorFull').html('The author already exist!');
                    $('#warningForRepAuthorFull').show();
                } else if (responce === 'warningSub') {
                    $('#warningAuthForSub').html('Select Subject!');
                    $('#warningAuthForSub').show();

                }
            },
            error: function () {
                alert("Fail!");
            }
        });
    }
}


function editAuthor(authorId) {
    glbAuthorId = authorId;
    $.ajax({
        url: 'as?action=editAuthor',
        type: 'GET',
        dataType: 'html',
        data: 'authorId=' + authorId,
        success: function (responce) {
            $("#editAuthorDialogId").html(responce);
            $("#editAuthorDialogId").dialog('open');
        },
        error: function () {
            alert("Fail");
        }
    });
}

function updateAuthor() {
    let aFName = $("#aFNameU").val();
    let aLName = $("#aLNameU").val();
    let aFatName = $("#aFatNameU").val();
    let aDob = $("#aDobU").val();
    let aDeathDay = $("#aDeathDayU").val();
    let aNation = $("#aNationalityU").val();
    let aGender = $("#aGenderU :selected").val();
    let aReward = $("#rewardU").val();
    let aNote = $("#aNoteU").val();
    let aSubject = $("#aSubjectU").val();
    if (aFName === '' || aFName ===null) {
        $('#eAuthorNullFName').show();
    } else if (aLName === '' || aLName===null) {
        $('#eAuthorNullLName').show();
    } else if (aGender === '0') {
        $('#eAuthorNullGender').show();
    } else if (aNation === '0') {
        $('#eAuthorNullNation').show();
    } else if (aSubject === null) {
        $('#eAuthorNullSubject').show();
    } else {
        // let authorDataJson =
        //     {
        //         aFName: aFName,
        //         aLName: aLName,
        //         aFatName: aFatName,
        //         aDob: aDob,
        //         aDeathDay: aDeathDay,
        //         aNation: aNation,
        //         aGender: aGender,
        //         aReward: aReward,
        //         aNote: aNote,
        //     }
        $.ajax({
            url: 'as?action=updateAuthor',
            data: "aFName=" + aFName + "&aLName=" + aLName + "&aFatName=" + aFatName + "&aDob=" + aDob
                + "&aDeathDay=" + aDeathDay + "&aGender=" + aGender + "&aNote=" + aNote + "&aNation=" + aNation + "&aReward=" + aReward + "&aSubject=" + aSubject +
                "&authorId=" + glbAuthorId,
            type: 'POST',
            dataType: 'text',
            success: function (responce) {
                if (responce === 'success') {
                    getAuthorData();
                    alert("Author Update Successfully");
                    setTimeout(function () {
                        $("#editAuthorDialogId").dialog('close')
                    }, 3000);
                } else if (responce === 'warningForExitAuthor') {
                }
            },
            error: function () {
                alert("Fail!")
            }
        });
    }
}

function confirmDeleteDialogAuthor(message, authorId, fname, lName) {
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
                        deleteAuthor(authorId, fname, lName);
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

function confirmDeleteAuthor(authorId, fname, lName) {
    confirmDeleteDialogAuthor("Are you sure delete " + '<i>' + fname + "    " + lName + '</i>' + " ?", authorId, fname, lName);
}

function deleteAuthor(authorId, fname, lName) {
    $.ajax({
        url: 'as?action=deleteAuthor',
        type: 'GET',
        dataType: 'text',
        data: 'authorId=' + authorId,
        success: function (responce) {
            if (responce === 'success') {
                alert(fname + "  " + lName + "  delete successfully");
                getAuthorData();
            }
        },
        error: function () {
            alert("Fail");
        }
    });
}

function clearAuthorEdit() {
    $(".editAuthorDialog").find("input").val("");
    $(".editAuthorDialog").find("textarea").val("");
    $(".editAuthorDialog").find("select").val($("option:first").val());

}

function clearAuthorAdd() {
    $(".newAuthorDialog").find("input").val("");
    $(".newAuthorDialog").find("textarea").val("");
    $(".newAuthorDialog").find("select").val($("option:first").val());

}

function clearSearchAuthor() {
    $(".authorAdvSearchDialog").find("input").val("");
    $(".authorAdvSearchDialog").find("select").val($("option:first").val());
}

function advSearchAuthor() {
    let aAdvFirstName = $("#autAdvName").val();
    let aAdvLastName = $("#autAdvSur").val();
    let advSearchJson = {
        aAdvFirstName: aAdvFirstName,
        aAdvLastName: aAdvLastName,
        aAdvSubject: advanceSearchSubjectForAuthor,
        aAdvNationality: advanceSearchNationalityForAuthor,
    }

    $.ajax({
        url: 'as?action=advanceSearchAuthor',
        type: 'POST',
        data: advSearchJson,
        dataType: 'html',
        success: function (responce) {
            $(".ui-layout-center").html(responce);
            $("#authorAdvSearchDialogId").dialog('close');
        },
        error: function () {
            alert("Fail!")
        }
    });
}