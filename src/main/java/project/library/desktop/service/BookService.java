package project.library.desktop.service;

import project.library.desktop.dao.BookDao;
import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Book;
import project.library.desktop.service.interfaces.IBookService;
import java.util.List;

/**
 * 
 * @ Ulviyye Ibrahimli
 */
public class BookService implements IBookService {

    BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookService() {
    }

    @Override
    public List<Book> allBookList(int choose) throws Exception {
        return bookDao.allBookList(choose);
    }

    @Override
    public boolean addBook(Book book) throws Exception {
        return bookDao.addBook(book);
    }
    public boolean addBookWeb(Book book) throws Exception {
        return bookDao.addBookWeb(book);
    }

    @Override
    public boolean updateBookWeb(Book book, Long id) throws Exception {
        return bookDao.updateBookWeb(book,id);
    }

    @Override
    public boolean updateBook(Book book, Long id) throws Exception {
        return bookDao.updateBook(book, id);
    }

    @Override
    public boolean deleteBook(Long id) throws Exception {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean deleteSub(Long bookId) throws Exception {
        return bookDao.deleteSub(bookId);
    }

    @Override
    public boolean deleteAuthor(Long bookId) throws Exception {
        return bookDao.deleteAuthor(bookId);
    }

    @Override
    public Book getBookById(Long id) throws Exception {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> searchBook(String keyword) throws Exception {
        return bookDao.searchBook(keyword);
    }

    @Override
    public List<Book> getBookWhichNotAuthor() throws Exception {
        return bookDao.getBookWhichNotAuthor();
    }

    @Override
    public Float getCount(int choose, Long id) throws Exception {
        return bookDao.getCount(choose, id);
    }

    @Override
    public List<Book> gtBookForOrder() throws Exception {
        return bookDao.gtBookForOrder();
    }

    @Override
    public boolean updateBookForOrder(Long id, Integer bookCount) throws Exception {
        return bookDao.updateBookForOrder(id, bookCount);
    }

    @Override
    public boolean updateBookForBackBook(Integer bookCount, Long id) throws Exception {
        return bookDao.updateBookForBackBook(bookCount, id);
    }

    @Override
    public Integer getBookCount(Long id) throws Exception {
        return bookDao.getBookCount(id);
    }

    @Override
    public Integer getTimeLimit(Long id) throws Exception {
        return bookDao.getTimeLimit(id);
    }

    @Override
    public Float getTimeLime(Long id) throws Exception {
        return bookDao.getTimeLime(id);
    }

    @Override
    public List<Book> bookBySubjectId(Long subjectId) throws Exception {
        return bookDao.bookBySubjectId(subjectId);
    }

    @Override
    public Long getBookCurrentId() throws Exception {
        return bookDao.getBookCurrentId();
    }

    @Override
    public boolean addSubjects(Long bookId, Long subjectId) throws Exception {
        return bookDao.addSubjects(bookId, subjectId);
    }

    @Override
    public boolean addAuthors(Long bookId, Long authorId) throws Exception {
        return bookDao.addAuthors(bookId, authorId);
    }

    @Override
    public List<Book> getSubjectByBookId(Long bookId) throws Exception {
        return bookDao.getSubjectByBookId(bookId);
    }

    @Override
    public List<Book> getAuthorByBookId(Long bookId) throws Exception {
        return bookDao.getAuthorByBookId(bookId);
    }

    @Override
    public boolean updateSubjectbyBookId(Long bookId,Long subjectId) throws Exception {
        return bookDao.updateSubjectbyBookId(bookId,subjectId);
    }

    @Override
    public boolean updateAuthorbyBookId(Long bookId,Long authorId) throws Exception {
        return bookDao.updateAuthorbyBookId(bookId,authorId);
    }

    @Override
    public boolean updateSubjectForBook(Long bookId, Long subjectId) throws Exception {
return bookDao.updateSubjectForBook(bookId, subjectId);    }

    @Override
    public boolean updateAuthorForBook(Long bookId, Long authorId) throws Exception {
return bookDao.updateAuthorForBook(bookId, authorId) ;   }

    @Override
    public Book getBookForOrder(Long bookId) throws Exception {
return bookDao.getBookForOrder(bookId);    }

    @Override
    public List<Book> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        return bookDao.advanceSearch(advanceSearch);
    }

    @Override
    public List<Book> authorListOfBookSub(Long subjectId) throws Exception {
        return bookDao.authorListOfBookSub(subjectId);
    }

    @Override
    public boolean addImageForBook(Long bookId,String image) throws Exception {
        return bookDao.addImageForBook(bookId,image);
    }

}
