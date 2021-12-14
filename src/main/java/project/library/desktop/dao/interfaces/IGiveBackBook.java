package project.library.desktop.dao.interfaces;

import java.util.List;
import project.library.desktop.model.Book;
import project.library.desktop.model.Reader;

/**
 *
 * @Ulviyye Ibrahimli
 */
public interface IGiveBackBook {

    List<Reader> inLibReader() throws Exception;

    List<Reader> inHomeReader() throws Exception;

    List<Reader> readerGetBook() throws Exception;// sehv

    List<Book> bookInLib(Long readerId) throws Exception;

    List<Book> bookInHome(Long readerId) throws Exception;

    boolean addGiveBackBook(Long employeeId, Long readerId, Long bookId) throws Exception;

    boolean addGiveBackBookFromHome(Long employeeId, Long readerId, Long bookId) throws Exception;
}
