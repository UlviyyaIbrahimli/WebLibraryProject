package com.example.WebLibraryProject;

import project.library.desktop.dao.AuthorDao;
import project.library.desktop.dao.BookDao;
import project.library.desktop.dao.LanguageDao;
import project.library.desktop.dao.SubjectDao;
import project.library.desktop.model.*;
import project.library.desktop.service.AuthorService;
import project.library.desktop.service.BookService;
import project.library.desktop.service.LanguageService;
import project.library.desktop.service.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "mainPageServlet", value = "/ms")
public class mainPageServlet extends HttpServlet {
    public void init() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;
        String action = null;
        PrintWriter pr = response.getWriter();
        BookDao bookDao = new BookDao();
        BookService bookService = new BookService(bookDao);
        SubjectDao subjectDao = new SubjectDao();
        AuthorDao authorDao = new AuthorDao();
        AuthorService authorService = new AuthorService(authorDao);
        SubjectService subjectService = new SubjectService(subjectDao);
        LanguageDao languageDao = new LanguageDao();
        LanguageService languageService = new LanguageService(languageDao);
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        try {
            if (action.equalsIgnoreCase("getBookList")) {
                List<Book> bookList = bookService.allBookList(1);
                List<Long> bookName = new ArrayList<>();
                for (Book b : bookList) {
                    bookName.add(b.getIsbn());
                }
                request.setAttribute("bookList", bookName);
                response.setContentType("text/html");
                pr.write(bookName.toString());
            }
            if (action.equalsIgnoreCase("booksCount")) {
                Float countOfMusic = bookService.getCount(2, (long) 31);
                Float countOfScience = bookService.getCount(2, (long) 32);
                Float countOfFiction = bookService.getCount(2, (long) 33);
                Float countOfRecipe = bookService.getCount(2, (long) 34);
                Float countOfHistory = bookService.getCount(2, (long) 35);
                Float countOfRomance = bookService.getCount(2, (long) 36);
                Float countOfArt = bookService.getCount(2, (long) 40);
                Float countOfMed = bookService.getCount(2, (long) 37);
                Float countOfFant = bookService.getCount(2, (long) 38);
                Float countOfChild = bookService.getCount(2, (long) 39);
                Float countOfBiog = bookService.getCount(2, (long) 41);
                Float countOfRel = bookService.getCount(2, (long) 42);
                Float countOfMy = bookService.getCount(2, (long) 43);
                List<Integer> arrayLists = new ArrayList<>();
                if (countOfMusic != null) {
                    arrayLists.add(Math.round(countOfMusic));
                } else {
                    arrayLists.add(0);
                }

                if (countOfScience != null) {
                    arrayLists.add(Math.round(countOfScience));
                } else {
                    arrayLists.add(0);
                }
                if (countOfFiction != null) {
                    arrayLists.add(Math.round(countOfFiction));
                } else {
                    arrayLists.add(0);
                }
                if (countOfRecipe != null) {
                    arrayLists.add(Math.round(countOfRecipe));
                } else {
                    arrayLists.add(0);
                }
                if (countOfHistory != null) {
                    arrayLists.add(Math.round(countOfHistory));
                } else {
                    arrayLists.add(0);
                }
                if (countOfRomance != null) {
                    arrayLists.add(Math.round(countOfRomance));
                } else {
                    arrayLists.add(0);
                }
                if (countOfArt != null) {
                    arrayLists.add(Math.round(countOfArt));
                } else {
                    arrayLists.add(0);
                }
                if (countOfMed != null) {
                    arrayLists.add(Math.round(countOfMed));
                } else {
                    arrayLists.add(0);
                }
                if (countOfFant != null) {
                    arrayLists.add(Math.round(countOfFant));
                } else {
                    arrayLists.add(0);
                }
                if (countOfChild != null) {
                    arrayLists.add(Math.round(countOfChild));
                }
                if (countOfBiog != null) {
                    arrayLists.add(Math.round(countOfBiog));
                } else {
                    arrayLists.add(0);
                }
                if (countOfRel != null) {
                    arrayLists.add(Math.round(countOfRel));
                } else {
                    arrayLists.add(0);
                }
                if (countOfMy != null) {
                    arrayLists.add(Math.round(countOfMy));
                } else {
                    arrayLists.add(0);
                }
                response.setContentType("text/html");
                pr.write(arrayLists.toString());

            }
            if (action.equalsIgnoreCase("musicList")) {
                Float countOfMusic = bookService.getCount(2, (long) 31);
                List<Book> author = bookService.authorListOfBookSub((long) 31);
                List<Book> bookList = bookService.bookBySubjectId((long) 31);
                int bookCount = 0;
                if (countOfMusic != null) {
                    bookCount = Math.round(countOfMusic);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", bookCount);
                request.setAttribute("author", author);
                address = "user/subjects/Music.jsp";
            }
            if (action.equalsIgnoreCase("scienceList")) {
                Float countOfScinece = bookService.getCount(2, (long) 32);
                List<Book> author = bookService.authorListOfBookSub((long) 32);
                List<Book> bookList = bookService.bookBySubjectId((long) 32);
                int bookCount = 0;
                if (countOfScinece != null) {
                    bookCount = Math.round(countOfScinece);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Science.jsp";
            }
            if (action.equalsIgnoreCase("fictionList")) {
                Float countOfFiction = bookService.getCount(2, (long) 33);
                List<Book> author = bookService.authorListOfBookSub((long) 33);
                List<Book> bookList = bookService.bookBySubjectId((long) 33);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Fiction.jsp";
            }
            if (action.equalsIgnoreCase("historyList")) {
                Float countOfFiction = bookService.getCount(2, (long) 35);
                List<Book> author = bookService.authorListOfBookSub((long) 35);
                List<Book> bookList = bookService.bookBySubjectId((long) 35);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/History.jsp";
            }
            if (action.equalsIgnoreCase("childList")) {
                Float countOfFiction = bookService.getCount(2, (long) 39);
                List<Book> author = bookService.authorListOfBookSub((long) 39);
                List<Book> bookList = bookService.bookBySubjectId((long) 39);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Child.jsp";
            }
            if (action.equalsIgnoreCase("artList")) {
                Float countOfFiction = bookService.getCount(2, (long) 40);
                List<Book> author = bookService.authorListOfBookSub((long) 40);
                List<Book> bookList = bookService.bookBySubjectId((long) 40);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Art.jsp";
            }
            if (action.equalsIgnoreCase("religionList")) {
                Float countOfFiction = bookService.getCount(2, (long) 42);
                List<Book> author = bookService.authorListOfBookSub((long) 42);
                List<Book> bookList = bookService.bookBySubjectId((long) 42);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Religion.jsp";
            }
            if (action.equalsIgnoreCase("fantacyList")) {
                Float countOfFiction = bookService.getCount(2, (long) 38);
                List<Book> author = bookService.authorListOfBookSub((long) 38);
                List<Book> bookList = bookService.bookBySubjectId((long) 38);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Fantacy.jsp";
            }
            if (action.equalsIgnoreCase("romanceList")) {
                Float countOfFiction = bookService.getCount(2, (long) 36);
                List<Book> author = bookService.authorListOfBookSub((long) 36);
                List<Book> bookList = bookService.bookBySubjectId((long) 36);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Romance.jsp";
            }
            if (action.equalsIgnoreCase("biologyList")) {
                Float countOfFiction = bookService.getCount(2, (long) 41);
                List<Book> author = bookService.authorListOfBookSub((long) 41);
                List<Book> bookList = bookService.bookBySubjectId((long) 41);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Biology.jsp";
            }
            if (action.equalsIgnoreCase("mysteryList")) {
                Float countOfFiction = bookService.getCount(2, (long) 43);
                List<Book> author = bookService.authorListOfBookSub((long) 43);
                List<Book> bookList = bookService.bookBySubjectId((long) 43);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Mystery.jsp";
            }
            if (action.equalsIgnoreCase("medicineList")) {
                Float countOfFiction = bookService.getCount(2, (long) 43);
                List<Book> author = bookService.authorListOfBookSub((long) 43);
                List<Book> bookList = bookService.bookBySubjectId((long) 43);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Mystery.jsp";
            }
            if (action.equalsIgnoreCase("recipeList")) {
                Float countOfFiction = bookService.getCount(2, (long) 34);
                List<Book> author = bookService.authorListOfBookSub((long) 34);
                List<Book> bookList = bookService.bookBySubjectId((long) 34);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Recipe.jsp";
            }
            if (action.equalsIgnoreCase("medicineList")) {
                Float countOfFiction = bookService.getCount(2, (long) 34);
                List<Book> author = bookService.authorListOfBookSub((long) 34);
                List<Book> bookList = bookService.bookBySubjectId((long) 34);
                int bookCount = 0;
                if (countOfFiction != null) {
                    bookCount = Math.round(countOfFiction);
                }
                request.setAttribute("bookList", bookList);
                request.setAttribute("bookCount", Math.round(bookCount));
                request.setAttribute("author", author);
                address = "user/subjects/Medicine.jsp";
            }
            if (action.equalsIgnoreCase("allBooksList")) {
                List<Subjects> subjectsList = subjectService.getSubjectList();
                List<Book> bookList = bookService.allBookList(1);
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("bookList", bookList);
                request.setAttribute("authorList", authorList);
                address = "user/firstPage/allBookList.jsp";
            }
            if (action.equalsIgnoreCase("bookInfo")) {
                Long id = Long.parseLong(request.getParameter("bookId"));
                Book book = bookService.getBookById(id);
                List<Book> authorOfBook=bookService.getAuthorByBookId(id);
                List<Book>subjectOfBook=bookService.getSubjectByBookId(id);
                request.setAttribute("authorOfBook",authorOfBook);
                request.setAttribute("subjectOfBook",subjectOfBook);
                request.setAttribute("book", book);
                address = "user/firstPage/bookInfo.jsp";
            }
            if (action.equalsIgnoreCase("booksOfSubjectId")) {
                Long subjectId = Long.parseLong(request.getParameter("subjectId"));
                List<Book> bookOfSubject = bookService.bookBySubjectId(subjectId);
                request.setAttribute("bookOfSubject", bookOfSubject);
                address = "user/firstPage/booksOfSubject.jsp";
            }

            if (action.equalsIgnoreCase("advComboFill")) {
                List<Author> authorList = authorService.getAuthorList();
                List<Book> bookList = bookService.allBookList(1);
                List<Language> languageList = languageService.getLanguageList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                request.setAttribute("authorList", authorList);
                request.setAttribute("bookList", bookList);
                request.setAttribute("languageList", languageList);
                request.setAttribute("subjectList", subjectsList);
                address = "user/firstPage/advanceSearch.jsp";
            }
            if (action.equalsIgnoreCase("authorInfo")) {
                Long authorId = Long.parseLong(request.getParameter("authorId"));
                Author author = authorService.getAuthorById(authorId);
                List<Author> subjectOfAuthor = authorService.subjectofAuthor(authorId);
                List<Author> bookOfAuthor = authorService.booksOfAuthor(authorId);
                request.setAttribute("author", author);
                request.setAttribute("subjectOfAuthor", subjectOfAuthor);
                request.setAttribute("bookOfAuthor", bookOfAuthor);
                address = "user/firstPage/authorInfo.jsp";
            }
            if (action.equalsIgnoreCase("normalSearch")) {
                String data = request.getParameter("searchData");
                List<Book> bookList = bookService.searchBook(data);
                request.setAttribute("bookList", bookList);
                int listSize = bookList.size();
                request.setAttribute("searchData",data);// change keyword color in jsp
                request.setAttribute("listSize", listSize);
                address = "user/firstPage/normalSearch.jsp";
            }
            if (action.equalsIgnoreCase("resultOfNormalSearch")) {
                Long bookId = Long.parseLong(request.getParameter("bookId"));
                Book book = bookService.getBookById(bookId);
                List<Book> authorOfBook = bookService.getAuthorByBookId(bookId);
                List<Book> subjectOfBook = bookService.getSubjectByBookId(bookId);
                request.setAttribute("book", book);
                request.setAttribute("authorOfBook", authorOfBook);
                request.setAttribute("subjectOfBook", subjectOfBook);
                address = "user/firstPage/bookInfo.jsp";
            }
            if (action.equalsIgnoreCase("advanceSearch")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String book = request.getParameter("bookId");
                String author = request.getParameter("authorId");
                String subject = request.getParameter("subjectId");
                String language = request.getParameter("languageId");
                String page = request.getParameter("page");
                String publisher = request.getParameter("publisher");
                String startDate = request.getParameter("startDate");
                System.out.println("serv   "+startDate);
                String endDate = request.getParameter("endDate");
                AdvanceSearch advanceSearch = new AdvanceSearch();
                if (subject != null) {
                    Long subjectId = Long.parseLong(subject);
                    advanceSearch.setId3(subjectId);
                }
                if (author != null) {
                    Long authorId = Long.parseLong(author);
                    advanceSearch.setId2(authorId);
                }
                if (language != null) {
                    Long languageId = Long.parseLong(language);
                    advanceSearch.setId4(languageId);
                }
                advanceSearch.setCase1(page);
                advanceSearch.setCase2(publisher);
                advanceSearch.setCase3(startDate);
                advanceSearch.setCase4(endDate);
                advanceSearch.setCase5(book);
                List<Book> resultBook = bookService.advanceSearch(advanceSearch);
                request.setAttribute("resultBook", resultBook);
                 address = "user/firstPage/advSearchResult.jsp";
            }if (action.equalsIgnoreCase("bookById")){
                Long id = Long.parseLong(request.getParameter("bookId"));
                Book book = bookService.getBookById(id);
                List<Book> authorOfBook=bookService.getAuthorByBookId(id);
                List<Book>subjectOfBook=bookService.getSubjectByBookId(id);
                request.setAttribute("authorOfBook",authorOfBook);
                request.setAttribute("subjectOfBook",subjectOfBook);
                request.setAttribute("book", book);
                address = "user/firstPage/bookInfo.jsp";
            }if (action.equalsIgnoreCase("showImage")){
 List<Book> bookList=bookService.allBookList(1);
 String image;

                    image=bookList.get(0).getImage();

 request.setAttribute("image",image);
 address="user/firstPage/centerInner.jsp";
            }


            if (address != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
