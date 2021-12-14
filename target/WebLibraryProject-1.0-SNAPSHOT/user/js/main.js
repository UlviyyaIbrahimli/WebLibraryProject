let advSearchTitleForBookId;
let advSearchAuthorBookId;
let advSearchSubjectForBookId;
let advSearchLanguageForBookId;
$(document).ready(function () {
    $('body').layout({applyDemoStyles: true});
    randomImageGenerator();
    showImage();
    $('#contactBtn').click(function () {
        $('.ui-layout-center').scrollTop(1200)
    });
    $('#allBookInfo').click(function () {
        $('.ui-layout-center').load('ms?action=allBooksList');
    });
    $('#libraryStructure').click(function () {
        $('.ui-layout-center').load('user/firstPage/structureLibrary.jsp');
    });
    $('#searchBtn').click(function () {
        normalSearch();
    });
$('#sBook').css('backgroundColor','red');
    showDate = new Date();
    $('#display').innerHTML = showDate.toDateString();
    showDataTime();
    randomBook();
    $('.ui-layout-west').css('background', '#88BBD6');
    $('.ui-layout-center').css('background', 'linear-gradient(#CDCDCD,#CDCDCD)');
    $('.ui-layout-north').css('background', '#e9e9e9');
    booksCount();
    $('#musicMenu').click(function () {
        $('.ui-layout-center').load('ms?action=musicList');
    });
    $('#scienceMenu').click(function () {
        $('.ui-layout-center').load('ms?action=scienceList');
    });
    $('#fictionMenu').click(function () {
        $('.ui-layout-center').load('ms?action=fictionList');
    });
    $('#historyMenu').click(function () {
        $('.ui-layout-center').load('ms?action=historyList');
    });
    $('#artMenu').click(function () {
        $('.ui-layout-center').load('ms?action=artList');
    });
    $('#childMenu').click(function () {
        $('.ui-layout-center').load('ms?action=childList');
    });
    $('#recipeMenu').click(function () {
        $('.ui-layout-center').load('ms?action=recipeList');
    });
    $('#medicineMenu').click(function () {
        $('.ui-layout-center').load('ms?action=medicineList');
    });
    $('#fantacyMenu').click(function () {
        $('.ui-layout-center').load('ms?action=fantacyList');
    });
    $('#mysteryMenu').click(function () {
        $('.ui-layout-center').load('ms?action=mysteryList');
    });
    $('#religionMenu').click(function () {
        $('.ui-layout-center').load('ms?action=religionList');
    });
    $('#biologyMenu').click(function () {
        $('.ui-layout-center').load('ms?action=biologyList');
    });
    $('#romanceMenu').click(function () {
        $('.ui-layout-center').load('ms?action=romanceList');
    });
    $('#socialNetDialogId').dialog({
        height: 180,
        width: 10,
        resizable: false,
        draggable: false,
        show: {effect: 'drop', direction: "up"},
        autoOpen: false,
        modal: false,
        scroll: false,
        dialogClass: 'socialDialogClass',
        open: function () {
            $(this).css('overflow', 'hidden');
        },
        position: {my: "right top", at: "right-50 top+250", of: "body"},
    });
    $('#socialNetDialogId').load('user/firstPage/socialNetDialog.jsp', function () {
        $('#socialNetDialogId').dialog('open');
    });

    $("#advSearchDialogId").dialog({
        title: 'Advance Search',
        autoOpen: false,
        draggable: true,
        resizable: true,
        height: 550,
        width: 500,
        show: {effect: 'drop', direction: "up"},
        modal: true,
        dialogClass: 'advSearchDialogStyleClass',
        buttons: {
            'Search': {
                text: "Search",
                id: "searchBtnId",
                class: 'advSearchBtnStyleClass',
                click: function () {
                    advSearchBook();
                    $(this).dialog('open');
                }
            },
            'Clear': {
                text: "Clear",
                id: "newBookDClearBtn",
                class: 'advSearchBtnStyleClass',
                click: function () {
                    clearAdvSearchDialog();
                }
            },
            'Close': {
                text: "Close",
                id: "newBookDCloseBtn",
                class: 'advSearchBtnStyleClass',
                click: function () {
                    $(this).dialog('close');
                }
            }
        }
    });

    $('#advSearchBook').click(function () {
        $('#advSearchDialogId').load('user/firstPage/advanceSearch.jsp', function () {
            $('#advSearchDialogId').load("ms?action=advComboFill")
            $('#advSearchDialogId').dialog('open');
        });
    });


});

function randomBook() {
    $.ajax({
        url: 'ms?action=getBookList',
        dataType: 'html',
        type: 'POST',
        success: function (responce) {
            randomBookList(responce);
        },
        error: function () {
            alert("Error")
        }
    });
}

function randomBookList(responce) {
    let arr = responce.split(',')
    let ex = [];
    let random1 = arr[Math.floor(Math.random() * arr.length)];
    let random2 = arr[Math.floor(Math.random() * arr.length)];
    let random3 = arr[Math.floor(Math.random() * arr.length)];
    let random4 = arr[Math.floor(Math.random() * arr.length)];
    $('#innerFirst').html(random1);
    ex.push(random1)
    if (random1 !== random2) {
        $('#innerSecond').html(random2);
        ex.push(random2);
    }
    if (!ex.includes(random3)) {
        $('#innerThird').html(random3);
        ex.push(random3);
    }
    if (!ex.includes(random4)) {
        $('#innerFourth').html(random4)
        ex.push(random4)
    }
}
function randomImageGenerator(){
    let imageArray=new Array();
    imageArray[1]='user/randomImage/image1.png';
    imageArray[2]='user/randomImage/image2.png';
    imageArray[3]='user/randomImage/image3.png';
    imageArray[4]='user/randomImage/image4.png';
    imageArray[5]='user/randomImage/image5.png';
    imageArray[6]='user/randomImage/image6.png';
    imageArray[7]='user/randomImage/image7.png';
    imageArray[8]='user/randomImage/image8.png';

let rnd=Math.floor(Math.random()*imageArray.length);
$('#innerFirst').prepend('<img id="theImg" src="'+imageArray[rnd]+'" />');
}
function booksCount() {

    $.ajax({
        url: 'ms?action=booksCount',
        type: 'GET',
        dataType: 'html',
        success: function (responce) {
            showBookCount(responce)
        },
        error: function () {
            alert('Book Count Error!')
        }
    });
}

function showBookCount(responce) {
    let arr = responce.substring(1, responce.length - 1);
    let array = arr.split(',');
    $('#mSpan').text(array[0]);
    $('#secSpan').text(array[1]);
    $('#fSpan').text(array[2]);
    $('#recSpan').text(array[3]);
    $('#hSpan').text(array[4]);
    $('#rSpan').text(array[5]);
    $('#aSpan').text(array[6]);
    $('#medSpan').text(array[7]);
    $('#fanSpan').text(array[8]);
    $('#cSpan').text(array[9]);
    $('#bSpan').text(array[10]);
    $('#relSpan').text(array[11]);
    $('#mySpan').text(array[12]);
}

function showDataTime() {
    let x = document.getElementsByClassName("start")[0].innerText;
    let z = document.getElementsByClassName("end")[0].innerText;
    let startTime = Date.parseExact(x, "h:mm tt");
    let endTime = Date.parseExact(z, "h:mm tt");
    if (showDate.between(startTime, endTime)) {
        $(".open").show();
        $(".closed").hide();
    } else {
        $(".closed").show();
        $(".open").hide();
    }
}


function bookInfo(bookId) {
    $.ajax({
        url: 'ms?action=bookInfo',
        type: 'GET',
        dataType: 'html',
        data: "bookId=" + bookId,
        success: function (responce) {
            $('.ui-layout-center').html(responce)
        }, error: function () {
            alert("Sorry,Error!")
        }
    });
}

function getBooksOfSubjectId(subjectId) {
    $.ajax({
        url: 'ms?action=booksOfSubjectId',
        dataType: 'html',
        type: 'GET',
        data: 'subjectId=' + subjectId,
        success: function (responce) {
            $('.ui-layout-center').html(responce)
        }, error: function () {
            alert('Sorry,Error')
        }
    });
}

function authorInfo(authorId) {

    $.ajax({
        url: 'ms?action=authorInfo',
        dataType: 'html',
        type: 'GET',
        data: 'authorId=' + authorId,
        success: function (responce) {
            $('.ui-layout-center').html(responce)
        }, error: function () {
            alert('Sorry,Error')
        }
    });
}

// function authorInfo(authorId) {
//     $.ajax({
//         url: 'ms?action=authorInfo',
//         data: 'authorId=' + authorId,
//         dataType: 'html',
//         type: 'GET',
//         success: function (responce) {
//             $('.ui-layout-center').html(responce);
//         },
//         error: function () {
//             alert('Sorry Error!')
//         }
//     });
// }

function normalSearch() {
    let searchData = $('#searchId').val();
    $.ajax({
        url: 'ms?action=normalSearch',
        type: 'GET',
        data: 'searchData=' + searchData,
        dataType: 'html',
        success: function (responce) {
            $('.ui-layout-center').html(responce);
        }, error: function () {
            alert("Error!")
        }
    });
}

function getSearchBook(bookId) {

    $.ajax({
        url: 'ms?action=resultOfNormalSearch',
        type: 'GET',
        data: 'bookId=' + bookId,
        dataType: 'html',
        success: function (responce) {
            $('.ui-layout-center').html(responce);
        }, error: function () {
            alert("Error!")
        }
    });
}

function advSearchBook() {
    let page = $('#sPage').val();
    let publisher = $("#sPublisher").val();
    let starDate = $('#sStartRelDate').val();
    let endDate = $('#sEndRelDate').val();
    let advSearchJsonData = {
        bookId: advSearchTitleForBookId,
        authorId: advSearchAuthorBookId,
        subjectId: advSearchSubjectForBookId,
        languageId: advSearchLanguageForBookId,
        page: page,
        publisher: publisher,
        startDate: starDate,
        endDate: endDate,
    }
    $.ajax({
        url: 'ms?action=advanceSearch',
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
function clearAdvSearchDialog(){
    $('#sBook').val('');
    $('#sAuthor').val('');
    $('#sSubject').val('');
    $('#sLanguage').val('');
    $('#sPage').val('');
    $('#sPublisher').val('');
    $('#sStartRelDate').val('');
    $('#sEndRelDate').val();

}
function getBookById(bookId){
    $.ajax({
       url:'ms?action=bookById',
       data:'bookId='+bookId,
       dataType:'html',
       type:'GET',
        success:function (responce){
           $('.ui-layout-center').html(responce);
        },
        error:function (){
           alert("Error!");
        }
    });
}
function  showImage(){
    $.ajax({
        url:'ms?action=showImage',
        dataType:'html',
        type:'GET',
        success:function (responce){
        },error:function (){
            alert("error");
        }
    })
}