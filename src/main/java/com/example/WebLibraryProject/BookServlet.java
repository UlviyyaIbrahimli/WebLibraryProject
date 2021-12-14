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
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@WebServlet(name = "BookServlet", value = "/bs")

public class BookServlet extends HttpServlet {
    public void init() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pr = response.getWriter();
        String action = null;
        String address = null;
        BookDao bookDao = new BookDao();
        BookService bookService = new BookService(bookDao);
        AuthorDao authorDao = new AuthorDao();
        AuthorService authorService = new AuthorService(authorDao);
        SubjectDao subjectDao = new SubjectDao();
        SubjectService subjectService = new SubjectService(subjectDao);
        LanguageDao languageDao = new LanguageDao();
        LanguageService languageService = new LanguageService(languageDao);

        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("bookData")) {
                List<Book> bookList = bookService.allBookList(1);
                request.setAttribute("bookList", bookList);
                address = "admin/getDataTable/bookData.jsp";
            }
            if (action.equalsIgnoreCase("bookCmb")) {
                List<Author> authorList = authorService.getAuthorList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                List<Language> languageList = languageService.getLanguageList();
                request.setAttribute("authorList", authorList);
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("languageList", languageList);
                address = "admin/views/addBook.jsp";
            }
            if (action.equalsIgnoreCase("addBook")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Language languageObj = new Language();
                String isbn = request.getParameter("isbn");
                String title = request.getParameter("title");
                String page = request.getParameter("page");
                String edition = request.getParameter("edition");
                String publisher = request.getParameter("publisher");
                String abst = request.getParameter("abst");
                String release = request.getParameter("release");
                String author = request.getParameter("author");
                String subject = request.getParameter("subject");
                String language = request.getParameter("language");
                String star = request.getParameter("star");
                System.out.println(star+"  star ser");
                Book book = new Book();
                List<Book> bookList = bookService.allBookList(1);
                boolean isExits = false;
                for (Book b : bookList) {
                    if (isbn.equals(b.getIsbn().toString())) {
                        isExits = true;
                    }
                }
                if (isExits) {
                    response.setContentType("text/html");
                    pr.write("warningForBookRepeated");
                } else {
                    if (isbn.contains(".")) {
                        response.setContentType("text/html");
                        pr.write("wrongIsbn");
                    } else {
                        book.setIsbn(Long.parseLong(isbn));
                        book.setTitle(title);
                        book.setPublisher(publisher);
                            book.setStar(star);

                        book.setbAbstract(abst);
                        languageObj.setId(Long.parseLong(language));
                        book.setEdition(edition);
                        if (page != null && !page.isEmpty()) {
                            book.setPage(Long.parseLong(page));
                        }
                        if (release != null && !release.isEmpty()) {
                            book.setReleaseDate(df.parse(release));
                        }
                        book.setLanguage(languageObj);
                        boolean addBookAuthor = false;
                        boolean addBookSubject = false;
                        boolean addBook = bookService.addBookWeb(book);
                        if (addBook) {
                            Long bookId = bookService.getBookCurrentId();
                            if (subject.equals("0")) {
                                response.setContentType("text/html");
                                pr.write("warningSubject");
                            } else {
                                String subjectList[] = subject.split(",");
                                for (String sub : subjectList) {
                                    addBookSubject = bookService.addSubjects(bookId, Long.parseLong(sub));
                                }
                                if (author.equals("0")) {
                                    response.setContentType("text/html");
                                    pr.write("warningAuthor");
                                } else {
                                    String authors[] = author.split(",");
                                    for (String aut : authors) {
                                        addBookAuthor = bookService.addAuthors(bookId, Long.parseLong(aut));
                                    }
                                }
                                if (addBookAuthor == true && addBookSubject == true) {
                                    response.setContentType("text/html");
                                    pr.write("success");
                                }
                            }

                        } else {
                            System.out.println("Adding book is fail");
                        }
                    }
                }
            }
            if (action.equalsIgnoreCase("editBook")) {
                Long bookId = Long.parseLong(request.getParameter("bookId"));
                List<Language> languageList = languageService.getLanguageList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                List<Author> authorList = authorService.getAuthorList();
                Book book = bookService.getBookById(bookId);
                request.setAttribute("languageList", languageList);
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("authorList", authorList);
                List<Book> subjectOfBook = bookService.getSubjectByBookId(bookId);
                List<Book> authorOfBook = bookService.getAuthorByBookId(bookId);
                request.setAttribute("subjectOfBook", subjectOfBook);
                request.setAttribute("authorOfBook", authorOfBook);
                request.setAttribute("book", book);
                address = "admin/editData/editBook.jsp";
            }
            if (action.equalsIgnoreCase("updateBook")) {
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd", Locale.US);
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String isbn = request.getParameter("isbn");
                String title = request.getParameter("title");
                String page = request.getParameter("page");
                String edition = request.getParameter("edition");
                String publisher = request.getParameter("publisher");
                String subject = request.getParameter("subject");
                String author = request.getParameter("author");
                String language = request.getParameter("language");
                String star = request.getParameter("star");
                String releaseDate = request.getParameter("releaseDate");
                Book book = new Book();

                book.setIsbn(Long.parseLong(isbn));
                book.setTitle(title);
                book.setPage(Long.parseLong(page));
                book.setPublisher(publisher);
                System.out.println("edirt  "+star);
                book.setStar(star);
                if (releaseDate != null && !releaseDate.isEmpty()) {
                    book.setReleaseDate(format.parse(releaseDate));
                }
                book.setEdition(edition);
                Language languageObj = new Language();
                languageObj.setId(Long.parseLong(language));
                book.setLanguage(languageObj);
                Long bookId = Long.parseLong(request.getParameter("bookId"));
                Long currentIsbn = bookService.getBookById(bookId).getIsbn();
                List<Book> bookList = bookService.allBookList(1);
                boolean isExit = false;
                if (!currentIsbn.toString().equals(isbn)) {
                    for (Book b : bookList) {
                        if (isbn.equals(b.getIsbn().toString())) {
                            isExit = true;
                        }
                    }
                }
                if (isExit) {
                    response.setContentType("text/html");
                    pr.write("warningForBookUIsbn");
                } else {
                    if (isbn.contains(".")) {
                        response.setContentType("text/html");
                        pr.write("wrongIsbn");
                    }
                    boolean updateBook = bookService.updateBookWeb(book, bookId);
                    boolean updateSubjectOfBook = false;
                    boolean updateAuthorOfBook = false;
                    if (updateBook) {
                            String subjects[] = subject.split(",");
                            boolean deleteSubOfBook = bookService.deleteSub(bookId);
                            for (String sub : subjects) {
                             updateSubjectOfBook = bookService.addSubjects(bookId, Long.parseLong(sub));
                            }
                            if (author.equals("0")) {
                                response.setContentType("text/html");
                                pr.write("warningUAuthor");
                            } else {
                                String authors[] = author.split(",");
                                boolean deleteAut = bookService.deleteAuthor(bookId);
                                for (String aut : authors) {
                                    System.out.println("aut  " + aut);
                                    updateAuthorOfBook = bookService.addAuthors(bookId, Long.parseLong(aut));
                                }
                                System.out.println("Book update");
                            }
                        if (updateSubjectOfBook == true && updateAuthorOfBook == true) {
                            response.setContentType("text/html");
                            pr.write("success");
                        }
                    } else {
                        System.out.println("Book does not update");
                    }
                }
            }
            if (action.equalsIgnoreCase("deleteBook")) {
                Long bookId = Long.parseLong(request.getParameter("bookId"));
                boolean deleteBook = bookService.deleteBook(bookId);
                if (deleteBook) {
                    System.out.println("book delete successfully");
                    response.setContentType("text/html");
                    pr.write("success");
                } else {
                    System.out.println("book delete fail");
                }
            }
            if (action.equalsIgnoreCase("fillSearchCmb")) {
                List<Language> languageList = languageService.getLanguageList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                List<Author> authorList = authorService.getAuthorList();
                List<Book> bookList= bookService.allBookList(1);
                request.setAttribute("bookList",bookList);
                request.setAttribute("languageList", languageList);
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("authorList", authorList);
                address = "admin/advance/bookAdvanceSearch.jsp";
            }
            if (action.equalsIgnoreCase("advSearchBook")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String book = request.getParameter("book");
                String author = request.getParameter("authorId");
//                String subject = request.getParameter("subjectId");
                String language = request.getParameter("languageId");
                String page = request.getParameter("page");
                String publisher = request.getParameter("publisher");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                AdvanceSearch advanceSearch = new AdvanceSearch();
//                if (subject != null) {
//                    Long subjectId = Long.parseLong(subject);
//                    advanceSearch.setId3(subjectId);
//                }
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
                address = "admin/advance/resultOfSearch/AdvBook.jsp";
            }if (action.equalsIgnoreCase("showSubject")){
                Long bookId=Long.parseLong(request.getParameter("bookId"));
                List<Book> subjectOfBook=bookDao.getSubjectByBookId(bookId);
                Book book= bookService.getBookById(bookId);
                request.setAttribute("book",book);
                request.setAttribute("subjectOfBook",subjectOfBook);
                address = "admin/getDataTable/showBookSubject.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (address != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}
