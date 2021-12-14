package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.AuthorDao;
import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Author;
import project.library.desktop.service.interfaces.IAuthorService;

public class AuthorService implements IAuthorService {

    AuthorDao authorDao;

    public AuthorService() {
    }

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getAuthorList() throws Exception {
        return authorDao.getAuthorList();
    }

    @Override
    public boolean addAuthor(Author author) throws Exception {
        return authorDao.addAuthor(author);
    }

    @Override
    public boolean updateAuthor(Long id, Author author) throws Exception {
        return authorDao.updateAuthor(id, author);
    }

    @Override
    public boolean deletesubjectOfAuthor(Long authorid) throws Exception {
        return authorDao.deletesubjectOfAuthor(authorid);
    }

    @Override
    public Author getAuthorById(Long id) throws Exception {
        return authorDao.getAuthorById(id);
    }

    @Override
    public boolean deleteAuthor(Long id) throws Exception {
        return authorDao.deleteAuthor(id);
    }

    @Override
    public boolean addAuthorBook(Long authorId, Long id) throws Exception {
        return authorDao.addAuthorBook(authorId, id);
    }

    @Override
    public List<Author> getAuthorBySubject(String name) throws Exception {
        return authorDao.getAuthorBySubject(name);
    }

    @Override
    public boolean addAuthorBooks(Long book, Long author) throws Exception {
        return authorDao.addAuthorBooks(book, author);
    }

    @Override
    public Long addbookId(Author author) throws Exception {
        return authorDao.addbookId(author);
    }

    @Override
    public boolean addListSubject(Long authorId, Long subjectId) throws Exception {
        return authorDao.addListSubject(authorId, subjectId);
    }

    @Override
    public List<Author> booksOfAuthor(Long authorId) throws Exception {
        return authorDao.booksOfAuthor(authorId);
    }

    @Override
    public List<Author> bookNoAuthor(Long id) throws Exception {
        return authorDao.bookNoAuthor(id);
    }

    @Override
    public List<Author> subjectofAuthor(Long id) throws Exception {
        return authorDao.subjectofAuthor(id);
    }

    @Override
    public boolean updateBookOfAuthor(Long bookId, Long authorId) throws Exception {
        return authorDao.updateBookOfAuthor(bookId, authorId);
    }

    @Override
    public boolean updateSubjectOfAuthor(Long subjectId, Long authorId) throws Exception {
        return authorDao.updateSubjectOfAuthor(subjectId, authorId);
    }

    @Override
    public List<Long> getBookId(Long authorId) throws Exception {
        return authorDao.getBookId(authorId);
    }

    @Override
    public boolean deleteBookOfAuthor(Long authorId, Long bookId) throws Exception {
        return authorDao.deleteBookOfAuthor(authorId, bookId);
    }

    @Override
    public boolean deleteSubject(Long authorId, Long subjectId) throws Exception {
        return authorDao.deleteSubject(authorId, subjectId);
    }

    @Override
    public List<Author> getSubjectByAuthor(Long authorId) throws Exception {
        return authorDao.getSubjectByAuthor(authorId);
    }

    @Override
    public boolean deleteAuthorForSubject(Long authorId) throws Exception {
        return authorDao.deleteAuthorForSubject(authorId);
    }

    @Override
    public List<Author> booksOfAuthorActiveZero(Long authorId) throws Exception {
        return authorDao.booksOfAuthorActiveZero(authorId);
    }

    @Override
    public int getAuthorSubjectCount(Long authorId) throws Exception {
        return authorDao.getAuthorSubjectCount(authorId);
    }

    @Override
    public List<Author> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        return authorDao.advanceSearch(advanceSearch);
    }

}
