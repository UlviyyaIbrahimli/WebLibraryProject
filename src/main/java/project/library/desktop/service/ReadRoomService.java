package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.ReadRoomDao;
import project.library.desktop.model.ReadRoom;
import project.library.desktop.service.interfaces.IReadRoomService;

public class ReadRoomService implements IReadRoomService {

    ReadRoomDao readRoomDao;

    public ReadRoomService() {
    }

    public ReadRoomService(ReadRoomDao readRoomDao) {
        this.readRoomDao = readRoomDao;
    }

    @Override
    public List<ReadRoom> getReadRoomList() throws Exception {
        return readRoomDao.getReadRoomList();
    }

    @Override
    public boolean addReadRoom(ReadRoom readRoom) throws Exception {
        return readRoomDao.addReadRoom(readRoom);
    }

    @Override
    public boolean updateReadRoom(ReadRoom readRoom, Long id) throws Exception {
        return readRoomDao.updateReadRoom(readRoom, id);
    }

    @Override
    public ReadRoom getReadRoomById(Long id) throws Exception {
        return readRoomDao.getReadRoomById(id);
    }

    @Override
    public boolean deleteReadRoom(Long id) throws Exception {
        return readRoomDao.deleteReadRoom(id);
    }

    @Override
    public List<ReadRoom> getReadRoomByRoomFloor(int floor) throws Exception {
        return readRoomDao.getReadRoomByRoomFloor(floor);
    }

    @Override
    public ReadRoom getTableByRoomId(Long id) throws Exception {
        return readRoomDao.getTableByRoomId(id);
    }

    @Override
    public List<ReadRoom> getReaderRoomforOrder() throws Exception {
     return   readRoomDao.getReaderRoomforOrder();
    }

    @Override
    public boolean updateForOrder(Long id) throws Exception {
return readRoomDao.updateForOrder(id);    }

    @Override
    public boolean updateForGive(Long id) throws Exception {
return readRoomDao.updateForGive(id);    }
}
