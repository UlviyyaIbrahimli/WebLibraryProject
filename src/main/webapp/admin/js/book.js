glbBookId = 0;
glbAdvSubject = 0;
glbAdvLanguage = 0;
glbAdvAuthor = 0;
let languageForAdvanceSearchBook;
let subjectForAdvanceSearchBook;
let authorForAdvanceSearchBook;
let titleForAdvanceSearchBook;
$(function () {

    $("#bookBtn").click(function () {
        globalBtnForNew = 'bookBtn';
        globalBtnForSearch = 'bookBtn';
        $('#advanceSearchBtn').css('display', 'block');
        getBookData();
    });

    $("#newBookDialogId").dialog({
        title: 'New Book',
        autoOpen: false,
        draggable: true,
        resizable: true,
        height: 600,
        width: 600,
        show: {effect: 'drop', direction: "up"},
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Save': {
                text: "Save",
                id: "newBookDSaveBtn",
                class: 'btnDialog',
                click: function () {
                    addBook();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "newBookDClearBtn",
                class: 'btnDialog',
                click: function () {
                    clearNewBook();
                }
            },
            'Close': {
                text: "Close",
                id: "newBookDCloseBtn",
                class: 'btnDialog',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });


    $("#editBookDialogId").dialog({
        title: 'Edit Book',
        autoOpen: false,
        draggable: true,
        resizable: true,
        height: 600,
        width: 600,
        show: {effect: 'drop', direction: "up"},
        modal: true,
        dialogClass: 'newDialogStyle',
        buttons: {
            'Update': {
                text: "Update",
                id: "editDUpdateBtn",
                class: 'btnDialog',
                click: function () {
                    updateBook();
                    $("#editBookDialogId").dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "editBDClearbtn",
                class: 'btnDialog',
                click: function () {
                    clearEditBook();
                }
            },
            'Close': {
                text: "Close",
                id: "editBookCloseBtn",
                class: 'btnDialog',
                click: function () {
                    $("#editBookDialogId").dialog('close');
                }
            }
        }
    });

    $("#bookAdvSearchDialogId").dialog({
        title: 'Advance Search for Book',
        autoOpen: false,
        draggable: true,
        modal: true,
        resizable: false,
        show: {effect: 'drop', direction: "up"},
        height: 550,
        width: 500,
        dialogClass: 'advSearchDialogStyleClassForAdmin',
        buttons: {
            'Search': {
                text: "Search",
                id: "advBookSearch",
                class: 'btnDialog',
                click: function () {
                    advanceSearchBookForAdmin();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "advBookClear",
                class: "btnDialog",
                click: function () {
                    clearSearchBook();
                }
            },
            'Close': {
                text: "Close",
                id: "advSearchBookId",
                class: "btnDialog",
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });

    $("#newBtn").click(function () {
        if (globalBtnForNew === 'bookBtn') {
            $("#newBookDialogId").load("admin/views/addBook.jsp", function () {
                $(this).load("bs?action=bookCmb", function () {
                    $(this).dialog('open');
                });
            });
        }
    });

    $("#advanceSearchBtn").click(function () {
        if (globalBtnForSearch === 'bookBtn') {
            $("#bookAdvSearchDialogId").load('admin/advance/bookAdvanceSearch.jsp', function () {
                $(this).load("bs?action=fillSearchCmb", function () {
                    $(this).dialog('open');
                });
            });
        }
    });


});

function getBookData() {
    $.ajax({
        url: 'bs?action=bookData',
        dataType: 'html',
        type: 'GET',
        success: function (responce) {
            $(".ui-layout-center").html(responce);
        },
        error: function () {
            alert("Fail");
        }
    });
}

function showSubjects(bookId) {
    $.ajax({
        url: 'bs?action=showSubject',
        dataType: 'html',
        type: 'GET',
        data: 'bookId=' + bookId,
        success: function (responce) {
            $('.ui-layout-center').html(responce);
        },
        error: function () {
            alert('Error!');
        }
    });
}

function addBook() {
    let title = $("#bTitle").val();
    let isbn = $("#bIsbn").val();
    let page = $("#bPage").val();
    let edition = $("#bEdition").val();
    let publisher = $("#bPublisher").val();
    let abst = $("#bAbst").val();
    let release = $("#bReleaseDate").val();
    let author = $("#bAuthor").val();
    let subject = $("#bSubject").val();
    let language = $("#bLanguage").val();
    let star = $("#bStar").val();

    if (isbn === '' || isbn === null) {
        $('#bNullIsbn').show();
    } else if (title === '' || title === null) {
        $('#bNullTitle').show();
    } else if (page === '' || page === null) {
        $('#bNullPage').show();
    } else if (publisher === '' || publisher === null) {
        $('#bNullPublisher').show();
    } else if (language === '0') {
        $('#bNullLanguage').show();
    } else if (release === '' || release === null) {
        $('#bNullReleaseDate').show();
    } else {
        let bookJson = {};
        $.ajax({
            url: 'bs?action=addBook',
            data: 'isbn=' + isbn + '&title=' + title + '&page=' + page + '&edition=' + edition + '&publisher=' + publisher +
                '&abst=' + abst + '&release=' + release + '&author=' + author + '&subject=' + subject + '&language=' + language +
                '&star=' + star,
            dataType: "text",
            type: 'POST',
            success: function (responce) {
                if (responce === 'success') {
                    alert(isbn + " - " + title + " added successfully");
                    getBookData();
                    setTimeout(function () {
                        $("#newBookDialogId").dialog('close')
                    }, 3000);
                } else if (responce === 'warningForBookRepeated') {
                    $('#warningForBookRep').html('Repeated <b>isbn</b>');
                    $('#warningForBookRep').show();
                } else if (responce === 'wrongIsbn') {
                    $('#warningForBookRep').html('Wrong format <b>ISBN !</b>');
                    $('#warningForBookRep').show();
                } else if (responce === 'warningAuthor') {
                    $('#warningAboutAut').show();
                } else if (responce === 'warningSubject') {
                    $('#warningSubjectForBook').show();
                }
            },
            error: function () {
                alert("Fail");
            }
        });
    }
}

function editBook(bookId) {
    glbBookId = bookId;
    $.ajax({
        url: 'bs?action=editBook',
        dataType: 'html',
        type: 'GET',
        data: 'bookId=' + bookId,
        success: function (responce) {
            $("#editBookDialogId").html(responce);
            $("#editBookDialogId").dialog('open');
        },
        error: function () {
            alert("Fail");
        }
    });
}

function updateBook() {
    let isbn = $("#bIsbnU").val();
    let title = $("#bTitleU").val();
    let page = $("#bPageU").val();
    let edition = $("#bEditionU").val();
    let publisher = $("#bPublisherU").val();
    let subject = $("#bSubjectU").val();
    let language = $("#bLanguageU").val();
    let star = $("#bStarU").val();
    let author = $("#bAuthorU").val();
    let abst = $("#bAbstU").val();
    let releaseDate = $("#bReleaseDateU").val();

    if (isbn === null || isbn === '') {
        $('#bNullIsbnU').show();
    } else if (title === '' || title === null) {
        $('#bNullTitleU').show();
    } else if (page === '' || page === null) {
        $('#bNullPageU').show();
    } else if (publisher === '' || publisher === null) {
        $('#bNullPublisherU').show();
    } else if (language === '0') {
        $('#bNullLanguageU').show();
    } else if (releaseDate === '' || releaseDate === null) {
        $('#bNullPublisherU').show();
    } else if (subject === null){
        $('#warningSubjectForBookU').show();
    }else if (author===null) {
        $('#warningAboutAutU').show();
    }    else {
        $.ajax({
            url: 'bs?action=updateBook',
            type: 'POST',
            dataType: 'text',
            data: 'isbn=' + isbn + '&title=' + title + '&page=' + page + '&edition=' + edition +
                '&publisher=' + publisher + '&subject=' + subject + '&language=' + language +
                '&star=' + star + '&author=' + author + '&abst=' + abst + '&releaseDate=' + releaseDate +
                '&bookId=' + glbBookId,
            success: function (response) {
                if (response === 'success') {
                    alert(title + "update successfully");
                    getBookData();
                    $("#editBookDialogId").dialog('close');
                } else if (responce === 'warningForBookUIsbn') {
                    $('#warningForBookRepU').html("Repeated <b>ISBN</b>");
                    $('#warningForBookRepU').show();
                } else if (responce === 'wrongIsbn') {
                    $('#warningForBookRepU').html("Wrong  <b>ISBN</b> format");
                    $('#warningForBookRepU').show();
                } else if (responce === 'warningUAuthor') {
                    $('#warningAboutAutU').show();
                }
            },
            error: function () {
                alert("Error");
            }
        });
    }
}

function deleteBook(bookId, bookTitle) {
    $.ajax({
        url: 'bs?action=deleteBook',
        data: 'bookId=' + bookId,
        type: 'GET',
        dataType: 'text',
        success: function (responce) {
            if (responce === 'success') {
                alert(bookTitle + " delete successfully");
                getBookData();
            }
        },
        error: function () {
            alert("Fail");
        }
    });
}

function clearNewBook() {
    $(".newBookDialog").find("input").val("");
    $(".newBookDialog").find("textarea").val("");
    $("#bLanguage").val($("#bLanguage option:first").val());
    $("#bSubject").val($("#bSubject option:first").val());
    $("#bAuthor").val($("#bAuthor option:first").val());

}

function clearEditBook() {
    $(".editBookDialog").find("input").val("");
    $(".editBookDialog").find("textarea").val("");
    $("#bLanguageU").val($("#bLanguageU option:first").val());
    $("#bSubjectU").val($("#bSubjectU option:first").val());
    $("#bAuthorU").val($("#bAuthorU option:first").val());
}

function clearSearchBook() {
    $(".advBookSearchDialog").find('input').val("");
    $("#bAdvSubject").val($("#bAdvSubject option:first").val());
    $("#bAdvAuthor").val($("#bAdvAuthor option:first").val());

}

function advanceSearchBookForAdmin() {
    let page = $('#sPageForAdmin').val();
    let publisher = $("#sPublisherForAdmin").val();
    let starDate = $('#sStartRelDateForAdmin').val();
    let endDate = $('#sEndRelDateForAdmin').val();
    let advSearchJsonData = {
        book: titleForAdvanceSearchBook,
        authorId: authorForAdvanceSearchBook,
        // subjectId: subjectForAdvanceSearchBook,
        languageId: languageForAdvanceSearchBook,
        page: page,
        publisher: publisher,
        startDate: starDate,
        endDate: endDate,
    }
    $.ajax({
        url: 'bs?action=advSearchBook',
        type: 'POST',
        dataType: 'html',
        data: advSearchJsonData,
        success: function (responce) {
            $('.ui-layout-center').html(responce)
            $('#advSearchDialogId').dialog('close');
        },
        error: function () {
            alert('Error!')
        }
    });

}

function confirmDeleteDialogBook(message, bookId, bookTitle) {
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
                        deleteBook(bookId, bookTitle);
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

function confirmDeleteBook(bookId, booktitle) {
    confirmDeleteDialogBook("Are you sure delete " + '<i>' + booktitle + '</i>' + " ?", bookId, booktitle);
}
