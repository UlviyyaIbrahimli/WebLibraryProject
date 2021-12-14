package project.library.desktop.dao.interfaces;

import java.util.List;
import project.library.desktop.model.ReadTable;

/**
 *
 * @Ulviyye Ibrahimli
 */
public interface IReadTable {

    List<ReadTable> getReadTable() throws Exception;

    boolean addReadTable(ReadTable readTable) throws Exception;

    boolean updateReadTable(ReadTable readTable, Long id) throws Exception;

    boolean deleteReadTable(Long id) throws Exception;

    ReadTable getReadTableById(Long id) throws Exception;

    List<ReadTable> getTable(int Sqlchoose1, Long sqlchoose) throws Exception;

    boolean update(Long id) throws Exception;

    Integer tableWhichSatatus1(Long id) throws Exception;

    Integer allTable(Long id) throws Exception;

    boolean updateStatus(Long id) throws Exception;

    Integer getOrderedTableCount(Long roomId) throws Exception;

    Long tableId(Long readerId, Long bookId) throws Exception;

    List<ReadTable> getTableByRoomId(Long roomId) throws Exception;
}
