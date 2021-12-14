package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.BookShelfDao;
import project.library.desktop.model.BookShelf;
import project.library.desktop.service.interfaces.IBookShelfService;

/**
 *
 * @Ulviyye Ibrahimli
 */
public class BookShelfService implements IBookShelfService {

    BookShelfDao bookShelfDao;

    public BookShelfService() {
    }

    public BookShelfService(BookShelfDao bookShelfDao) {
        this.bookShelfDao = bookShelfDao;
    }

    @Override
    public List<BookShelf> getBookShelf() throws Exception {
        return bookShelfDao.getBookShelf();
    }

    @Override
    public boolean addBookShelf(BookShelf bookShelf) throws Exception {
        return bookShelfDao.addBookShelf(bookShelf);
    }

    @Override
    public boolean updateBookShelf(BookShelf bookShelf, Long id) throws Exception {
        return bookShelfDao.updateBookShelf(bookShelf, id);
    }

    @Override
    public BookShelf getBookShelfById(Long id) throws Exception {
        return bookShelfDao.getBookShelfById(id);
    }

    @Override
    public boolean deleteBookShelf(Long id) throws Exception {
        return bookShelfDao.deleteBookShelf(id);
    }

    @Override
    public Integer countBookInShelf(Long id, int choose) throws Exception {
        return bookShelfDao.countBookInShelf(id, choose);
    }

    @Override
    public List<BookShelf> getBookShelfByRoomId(Long id) throws Exception {
        return bookShelfDao.getBookShelfByRoomId(id);
    }

    @Override
    public List<BookShelf> getBookshelfWhichRoomActiveZero() throws Exception {
        return bookShelfDao.getBookshelfWhichRoomActiveZero();
    }

    @Override
    public Integer getBookShelfFloor(Long id) throws Exception {
        return bookShelfDao.getBookShelfFloor(id);
    }

    @Override
    public List<BookShelf> getBookByShelfId(Long shelfId) throws Exception {
        return bookShelfDao.getBookByShelfId(shelfId);
    }

    @Override
    public boolean updateEmpity(Long shelfId, Integer capacity) throws Exception {
        return bookShelfDao.updateEmpity(shelfId, capacity);
    }

    @Override
    public BookShelf getShelfEmptyByBook(Long bookId) throws Exception {
        return bookShelfDao.getShelfEmptyByBook(bookId);
    }
}
