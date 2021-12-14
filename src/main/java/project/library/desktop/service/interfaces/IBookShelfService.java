package project.library.desktop.service.interfaces;

import java.util.List;
import project.library.desktop.model.BookShelf;

public interface IBookShelfService {

    List<BookShelf> getBookShelf() throws Exception;

    boolean addBookShelf(BookShelf bookShelf) throws Exception;

    boolean updateBookShelf(BookShelf bookShelf, Long id) throws Exception;

    BookShelf getBookShelfById(Long id) throws Exception;

    boolean deleteBookShelf(Long id) throws Exception;

    Integer countBookInShelf(Long id, int choose) throws Exception;

    List<BookShelf> getBookShelfByRoomId(Long id) throws Exception;

    List<BookShelf> getBookshelfWhichRoomActiveZero() throws Exception;

    Integer getBookShelfFloor(Long id) throws Exception;

    List<BookShelf> getBookByShelfId(Long shelfId) throws Exception;

    boolean updateEmpity(Long shelfId, Integer capacity) throws Exception;

    BookShelf getShelfEmptyByBook(Long bookId) throws Exception;

}
