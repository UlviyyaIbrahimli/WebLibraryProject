globalReaderId = 0;
glbAdvNation = 0;
glbAdvCountry = 0;
let countryForReaderAdvanceSearch;
let nationalityForAdvanceSearch;
$(function () {

    $("#readerBtn").click(function () {
        globalBtnForNew = 'readerBtn';
        globalBtnForSearch = 'readerBtn';
        getReaderData();
        $('#advanceSearchBtn').css('display', 'block');

    });
    // $("#conf").find(".ui-widget-header").append("<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>");

    $('.tableStyle').dataTable({
        "bInfo": false
    });

    $("#newReaderDialogId").dialog({
        title: 'New Reader',
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
                id: 'saveReaderSaveBtnId',
                class: 'btnDialog',
                click: function () {
                    addReader();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'saveReaderClearBtnId',
                class: 'btnDialog',
                click: function () {
                    clearReaderAdd();
                }
            },
            'Close': {
                text: 'Close',
                id: 'saveReaderCloseBtnId',
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });

    $("#editReaderDialogId").dialog({
        title: 'Edit Reader',
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
                id: 'saveReaderSaveBtnId',
                class: 'btnDialog',
                click: function () {
                    updateReader();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: 'Clear',
                id: 'saveReaderClearBtnId',
                class: 'btnDialog',
                click: function () {
                    clearReaderEdit();
                }
            },
            'Close': {
                text: 'Close',
                id: 'saveReaderCloseBtnId',
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });


    $("#readerAdvSearchDialogId").dialog({
        title: 'Advance Search For  Reader',
        autoOpen: false,
        resizable: true,
        draggable: true,
        height: 600,
        width: 600,
        show: {effect: 'drop', direction: "up"},
        modal: true,
        dialogClass: "newDialogStyle",
        buttons: {
            'Search': {
                text: "Search",
                id: "advReaderSearchBtn",
                class: "btnDialog",
                click: function () {
                    advSearchReader();
                    $("#readerAdvSearchDialogId").dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "advReaderClearBtn",
                class: "btnDialog",
                click: function () {
                    addClearReaderSearch();
                }
            },
            'Close': {
                text: "Close",
                id: "adbReaderCloseBtn",
                class: "btnDialog",
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });

    $("#newBtn").click(function () {
        if (globalBtnForNew === 'readerBtn') {
            $("#newReaderDialogId").load('admin/views/addReader.jsp', function () {
                $(this).load('rs?action=readerCmb', function () {
                    $(this).dialog('open');
                });
            });
        }
    });

    $("#advanceSearchBtn").click(function () {
        if (globalBtnForSearch === 'readerBtn') {
            $("#readerAdvSearchDialogId").load('admin/advance/readerAdvanceSearch.jsp', function () {
                $(this).load('rs?action=readerAdvanceCmb', function () {
                    $(this).dialog('open');
                });
            });
        }
    });
});

function getReaderData() {
    $.ajax({
        url: 'rs?action=readerData',
        type: 'GET',
        dataType: 'html',
        success: function (responce) {
            $(".ui-layout-center").html(responce);
        },
        error: function () {
            alert("Fail");
        }
    });
}

function clearReaderEdit() {
    $('.editReaderDialog').find('input').val('');
    $('#rStatusU').val($('#rStatusU option:first').val());
    $('#rCountryU').val($('#rCountryU option:first').val());
    $('#rNationU').val($('#rNationU option:first').val());
    $('#rGenderU').val($('#rGenderU option:first').val());
}

function clearReaderAdd() {
    $(".newReaderDialog").find("input").val('');
    $("#rGender").val($('#rGender option:first').val());
    $('#rStatus').val($('#rStatus option:first').val());
    $('#rNation').val($('#rNation option:first').val());
    $('#rCountry').val($('#rCountry option:first').val());
}

function addReader() {
    let fName = $("#rFName").val();
    let lName = $("#rLName").val();
    let fatName = $("#aFatName").val();
    let dob = $("#rDob").val();
    let country = $("#rCountry").val();
    let status = $("#rStatus").val();
    let city = $("#rCity").val();
    let phone = $("#rPhone").val();
    let address = $("#rAddress").val();
    let email = $("#rEmail").val();
    let password = $("#rPassword").val();
    let gender = $("#rGender").val();
    let nation = $("#rNation").val();
    let note = $('#rNote').val();
    if (fName === '' || fName === null) {
        $('#NullReaderFirstName').show();
    } else if (lName === '' || lName === null) {
        $('#NullReaderLastName').show();
    } else if (gender === '0') {
        $('#NullReaderGender').show();
    } else if (status === '0') {
        $('#NullReaderStatus').show();
    } else if (email === '') {
        $('#NullReaderEmail').show();
    } else if (!emailValidation(email)) {
        $('#wrongReaderEmail').show();
    } else if (nation === '0') {
        $('#NullReaderNationality').show();
    } else if (country === '0') {
        $('#NullReaderCountry').show();
    } else if (password === '') {
        $('#NullReaderPassword').show();
    } else {
        let readerJson = {
            fName: fName,
            lName: lName,
            fatName: fatName,
            dob: dob,
            country: country,
            status: status,
            city: city,
            phone: phone,
            email: email,
            address: address,
            password: password,
            gender: gender,
            nation: nation,
            note: note,

        }

        $.ajax({
            url: 'rs?action=addReader',
            dataType: 'text',
            type: 'POST',
            data: readerJson,
            success: function (responce) {
                if (responce === 'success') {
                    getReaderData();
                    setTimeout(function () {
                        $("#newReaderDialogId").dialog('close')
                    }, 3000);
                } else if (responce === 'RepeatedEmailWarning') {
                    $('#warningEmail').show();
                    $('#warningEmail').addClass('hideWarning');
                } else if (responce === 'wrongPassword') {
                    $('#wrongPassword').show();
                    $('#rPassword').addClass("hideWarning");
                }
            },
            error: function () {
                alert("Fail");
            }
        });
    }
}


function editReader(readerId) {
    globalReaderId = readerId;
    $.ajax({
        url: 'rs?action=editReader',
        dataType: 'html',
        type: 'GET',
        data: 'readerId=' + readerId,
        success: function (responce) {
            $("#editReaderDialogId").html(responce)
            $('#editReaderDialogId').dialog('open');
        }
    });
}

function updateReader() {
    let fName = $("#rFNameU").val();
    let lName = $("#rLNameU").val();
    let fatName = $("#aFatNameU").val();
    let dob = $("#rDobU").val();
    let country = $("#rCountryU").val();
    let status = $("#rStatusU").val();
    let city = $("#rCityU").val();
    let phone = $("#rPhoneU").val();
    let address = $("#rAddressU").val();
    let email = $("#rEmailU").val();
    let password = $("#rPasswordU").val();
    let gender = $("#rGenderU").val();
    let nation = $("#rNationU").val();
    let note = $('#rNoteU').val();
    if (fName === '' || fName === null) {
        $('#nullReaderFistNameU').show();
    } else if (lName === '' || lName === null) {
        $('#nullReaderLastNameU').show();
    } else if (gender === '0') {
        $('#nullReaderGenderU').show();
    } else if (status === '0') {
        $('#nullReaderStatusU').show();
    } else if (email === '' || email === null) {
        $('#nullReaderEmailU').show();
    } else if (!emailValidation(email)) {
        $('#wrongEmailU').show();
    } else if (nation === '0') {
        $('#nullReaderNationalityU').show();
    } else if (country === '0') {
        $('#nullReaderCountry').show();
    } else if (password === '') {
        $('#nullReaderPasswordU').show();
    } else {
        let readerJson = {
            fName: fName,
            lName: lName,
            fatName: fatName,
            dob: dob,
            country: country,
            status: status,
            city: city,
            phone: phone,
            email: email,
            address: address,
            password: password,
            gender: gender,
            nation: nation,
            note: note,
            readerId: globalReaderId,
        }

        $.ajax({
            url: 'rs?action=updateReader',
            type: 'POST',
            dataType: 'text',
            data: readerJson,
            success: function (responce) {
                if (responce === 'success') {
                    getReaderData();
                    alert(fName + "  " + lName + " update successfully");
                    setTimeout(function () {
                        $('#editReaderDialogId').dialog('close');
                    }, 3000)
                } else if (responce === 'warningEmail') {
                    $('#warningEmailU').show();
                    $('#rEmailU').addClass("warningClass");
                } else if (responce === 'wrongPassword') {
                    $('#wrongPasswordU').show();
                    $('#rPasswordU').addClass("warningClass");
                }
            },
            error: function () {
                alert("Fail!");
            }

        });
    }
}

function advSearchReader() {
    let firstName = $("#rAdvName").val();
    let lastName = $("#rAdvSurname").val();
    let dob = $("#rAdvDob").val();
    let gender = $('#rAdvGender').val();
    let status = $("#rAdvStatus").val();
    let activity = $("#rAdvActivity").val();
    let startDate = $("#rAdvStartDate").val();
    let endDate = $('#rAdvEndDate').val();
    let readerJson =
        {
            firstName: firstName,
            lastName: lastName,
            dob: dob,
            status: status,
            gender: gender,
            activity: activity,
            startDate: startDate,
            endDate: endDate,
            nationality: nationalityForAdvanceSearch,
            country: countryForReaderAdvanceSearch,
        }
    $.ajax({
        url: 'rs?action=advReader',
        dataType: 'html',
        type: 'POST',
        data: readerJson,
        success: function (responce) {
            $(".ui-layout-center").html(responce);
            $("#readerAdvSearchDialogId").dialog('close');
        }
    });
}


function addClearReaderSearch() {
    $(".readerAdvSearchDialog").find("input").val("");
    // $(".newReaderDialog").find("input[type=number]").val('0');
    $("#rAdvGender").val($('#rGender option:first').val());
    $('#rAdvStatus').val($('#rStatus option:first').val());
    $('#rAdvNationality').val($('#rNation option:first').val());
    $('#rAdvCountry').val($('#rCountry option:first').val());
}

function confirmDeleteDialogReader(message, readerId, fName, lName) {
    $('<div id="conf"></div>').appendTo('body').html('<div style="color: #0a0add;font-size: 18px"> <i class="fa fa-exclamation-triangle" aria-hidden="true" id="attention"></i>' + message + ' </div>')
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
                        deleteReader(readerId, fName, lName);
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

function confirmDeleteReader(readerId, fName, lName) {
    confirmDeleteDialogReader("Are you sure  to delete " + '<u>' + fName + "    " + lName + '</u>' + " ?", readerId, fName, lName);
}

function deleteReader(readerId, fName, lName) {
    $.ajax({
        url: 'rs?action=deleteReader',
        data: 'readerId=' + readerId,
        type: 'GET',
        dataType: 'text',
        success: function (responce) {
            if (responce === 'success') {
                alert(fName + '  ' + lName + ' delete successfully!');
                getReaderData();
            }
        },
        error: function () {
            alert('Fail');
        }
    });
}

function emailValidation(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}