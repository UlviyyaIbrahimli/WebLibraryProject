package project.library.desktop.dao.interfaces;

import java.util.List;
import project.library.desktop.model.ReadRoom;

/**
 *
 * @ Ulviyye Ibrahimli
 */
public interface IReadRoom {

    List<ReadRoom> getReadRoomList() throws Exception;

    boolean addReadRoom(ReadRoom readRoom) throws Exception;

    boolean updateReadRoom(ReadRoom readRoom, Long id) throws Exception;

    ReadRoom getReadRoomById(Long id) throws Exception;

    boolean deleteReadRoom(Long id) throws Exception;

    List<ReadRoom> getReadRoomByRoomFloor(int floor) throws Exception;

    ReadRoom getTableByRoomId(Long id) throws Exception;

    List<ReadRoom> getReaderRoomforOrder() throws Exception;

    boolean updateForOrder(Long id) throws Exception;

    boolean updateForGive(Long id) throws Exception;

}
