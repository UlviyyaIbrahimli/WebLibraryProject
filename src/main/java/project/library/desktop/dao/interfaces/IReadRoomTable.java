package project.library.desktop.dao.interfaces;

import project.library.desktop.model.ReadRoomTable;

import java.util.List;

public interface IReadRoomTable {
    List<ReadRoomTable> getReadRoomTable() throws Exception;
    boolean update(Long id) throws Exception;
}
