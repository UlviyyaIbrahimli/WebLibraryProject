package project.library.desktop.dao.interfaces;

import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Author;

import java.util.List;

public interface IAuthor {

    List<Author> getAuthorList() throws Exception;

    boolean addAuthor(Author author) throws Exception;

    boolean updateAuthor(Long id, Author author) throws Exception;

    Author getAuthorById(Long id) throws Exception;

    boolean deletesubjectOfAuthor(Long authorid) throws Exception;

    boolean deleteAuthor(Long id) throws Exception;

    boolean addAuthorBook(Long authorid, Long id) throws Exception;

    List<Author> getAuthorBySubject(String name) throws Exception;

    Long addbookId(Author author) throws Exception;

    boolean addAuthorBooks(Long book, Long author) throws Exception;

    boolean addListSubject(Long authorId, Long subjectId) throws Exception;

    List<Author> booksOfAuthor(Long authorId) throws Exception;

    List<Author> bookNoAuthor(Long id) throws Exception;

    List<Author> subjectofAuthor(Long id) throws Exception;

    boolean updateBookOfAuthor(Long bookId, Long authorId) throws Exception;

    boolean updateSubjectOfAuthor(Long subjectId, Long authorId) throws Exception;

    List<Long> getBookId(Long authorId) throws Exception;

    boolean deleteBookOfAuthor(Long authorId, Long bookId) throws Exception;

    boolean deleteSubject(Long authorId, Long subjectId) throws Exception;

    List<Author> getSubjectByAuthor(Long subjectId) throws Exception;

    boolean deleteAuthorForSubject(Long authorId) throws Exception;

    List<Author> booksOfAuthorActiveZero(Long authorId) throws Exception;

    int getAuthorSubjectCount(Long authorId) throws Exception;
    List<Author> advanceSearch(AdvanceSearch advanceSearch) throws Exception;

}
