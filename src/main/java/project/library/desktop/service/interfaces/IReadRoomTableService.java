package project.library.desktop.service.interfaces;

import project.library.desktop.model.ReadRoomTable;

import java.util.List;

public interface IReadRoomTableService {
    List<ReadRoomTable> getReadRoomTable() throws  Exception;
}
