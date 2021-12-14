package project.library.desktop.dao.interfaces;

import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Book;

import java.util.List;

public interface IBook {

    List<Book> allBookList(int choose) throws Exception;

    boolean addBook(Book book) throws Exception;

    boolean addBookWeb(Book book) throws Exception;

    Book getBookById(Long bookId) throws Exception;

    boolean updateBook(Book book, Long id) throws Exception;

    boolean updateBookWeb(Book book, Long id) throws Exception;

    boolean deleteBook(Long id) throws Exception;

    List<Book> searchBook(String keyword) throws Exception;

    List<Book> getBookWhichNotAuthor() throws Exception;

    Float getCount(int choose, Long id) throws Exception;

    List<Book> gtBookForOrder() throws Exception;

    boolean updateBookForOrder(Long id, Integer bookCount) throws Exception;

    boolean updateBookForBackBook(Integer bookCount, Long id) throws Exception;

    Integer getBookCount(Long id) throws Exception;

    Integer getTimeLimit(Long id) throws Exception;

    Float getTimeLime(Long id) throws Exception;

    List<Book> bookBySubjectId(Long subjectId) throws Exception;

    boolean deleteSub(Long bookId) throws Exception;

    boolean deleteAuthor(Long bookId) throws Exception;

    Long getBookCurrentId() throws Exception;

    boolean addSubjects(Long bookId, Long subjectId) throws Exception;

    boolean addAuthors(Long bookId, Long authorId) throws Exception;

    List<Book> getSubjectByBookId(Long bookId) throws Exception;

    List<Book> getAuthorByBookId(Long bookId) throws Exception;

    boolean updateSubjectbyBookId(Long bookId, Long subjectId) throws Exception;

    boolean updateAuthorbyBookId(Long bookId, Long authorId) throws Exception;

    boolean updateSubjectForBook(Long bookId, Long subjectId) throws Exception;

    boolean updateAuthorForBook(Long bookId, Long authorId) throws Exception;

    Book getBookForOrder(Long bookId) throws Exception;

    List<Book> advanceSearch(AdvanceSearch advanceSearch) throws Exception;

    List<Book> authorListOfBookSub(Long subjectId) throws Exception;

    boolean addImageForBook(Long bookId, String image) throws Exception;

}
