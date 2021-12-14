package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.ReadTableDao;
import project.library.desktop.model.ReadTable;
import project.library.desktop.service.interfaces.IReadTableService;

public class ReadTableService implements IReadTableService {

    private ReadTableDao readTableDao;

    public ReadTableService() {
    }

    public ReadTableService(ReadTableDao readTableDao) {
        this.readTableDao = readTableDao;
    }

    @Override
    public List<ReadTable> getReadTable() throws Exception {
        return readTableDao.getReadTable();
    }

    @Override
    public boolean addReadTable(ReadTable readTable) throws Exception {
        return readTableDao.addReadTable(readTable);
    }

    @Override
    public boolean updateReadTable(ReadTable readTable, Long id) throws Exception {
        return readTableDao.updateReadTable(readTable, id);
    }

    @Override
    public boolean deleteReadTable(Long id) throws Exception {
        return readTableDao.deleteReadTable(id);
    }

    @Override
    public ReadTable getReadTableById(Long id) throws Exception {
        return readTableDao.getReadTableById(id);
    }

    @Override
    public List<ReadTable> getTable(int sqlchoose1, Long sqlchoose2) throws Exception {
        return readTableDao.getTable(sqlchoose1, sqlchoose2);
    }

    @Override
    public boolean update(Long id) throws Exception {
        return readTableDao.update(id);
    }

    @Override
    public Integer tableWhichSatatus1(Long id) throws Exception {
        return readTableDao.tableWhichSatatus1(id);
    }

    @Override
    public Integer allTable(Long id) throws Exception {
        return readTableDao.allTable(id);
    }

    @Override
    public boolean updateStatus(Long id) throws Exception {
        return readTableDao.updateStatus(id);
    }

    @Override
    public Integer getOrderedTableCount(Long roomId) throws Exception {
        return readTableDao.getOrderedTableCount(roomId);
    }

    @Override
    public Long tableId(Long readerId,Long bookId) throws Exception {
        return readTableDao.tableId(readerId,bookId);
    }

    @Override
    public List<ReadTable> getTableByRoomId(Long roomId) throws Exception {
return readTableDao.getTableByRoomId(roomId);    }

}
