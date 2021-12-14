
package project.library.desktop.service.interfaces;

import java.util.List;
import project.library.desktop.model.BookRoom;

/**
 *
 * @Ulviyye Ibrahimli
 */
public interface IBookRoomService {

    List<BookRoom> getBookRoomList() throws Exception;

    boolean addBookRoom(BookRoom bookRoom) throws Exception;

    boolean updateBookRook(BookRoom bookRoom, Long id) throws Exception;

    BookRoom getBookRoomByid(Long id) throws Exception;

    boolean deleteBookRoom(Long id) throws Exception;

    List<BookRoom> getRoomNumburForFloor(Integer floor) throws Exception;

    List<String> getBookRoomNumber(String floor) throws Exception;

}
