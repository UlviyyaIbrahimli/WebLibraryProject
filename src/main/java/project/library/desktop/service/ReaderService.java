package project.library.desktop.service;

import project.library.desktop.dao.ReaderDao;
import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Reader;
import project.library.desktop.service.interfaces.IReaderService;

import java.util.List;

/**
 *
 * @Ulviyye Ibrahimli
 */
public class ReaderService implements IReaderService {

    ReaderDao readerDao;

    public ReaderService(ReaderDao readerDao) {
        this.readerDao = readerDao;

    }

    @Override
    public List<Reader> getReaderList() throws Exception {
        return readerDao.getReaderList();
    }

    @Override
    public boolean addReader(Reader reader) throws Exception {
        return readerDao.addReader(reader);
    }
    @Override
    public boolean addReaderWeb(Reader reader) throws Exception {
        return readerDao.addReaderWeb(reader);
    }

    @Override
    public boolean updateReader(Reader reader, Long id) throws Exception {
        return readerDao.updateReader(reader, id);
    }

    @Override
    public boolean updateReaderForWeb(Reader reader, Long id) throws Exception {
        return readerDao.updateReaderforWeb(reader,id);
    }

    @Override
    public boolean deleteReader(Long id) throws Exception {
        return readerDao.deleteReader(id);
    }

    @Override
    public Reader getReaderById(Long id) throws Exception {
        return readerDao.getReaderById(id);
    }

    @Override
    public Integer getCount(int choose, String sqlChoose) throws Exception {
        return readerDao.getCount(choose, sqlChoose);
    }

    @Override
    public List<Reader> getReaderForActive(int choose, String sqlChoose) throws Exception {
        return readerDao.getReaderForActive(choose, sqlChoose);
    }

    @Override
    public List<String> getNationlity() throws Exception {
        return readerDao.getNationlity();
    }

    @Override
    public List<Reader> getActivityAndStatus(String sqlChoose1, String sqlchoose2) throws Exception {
        return readerDao.getActivityAndStatus(sqlChoose1, sqlchoose2);
    }

    @Override
    public Integer getRelationResult(int choose, String sql1, String sql2, String sql3, String sql4) throws Exception {
        return readerDao.getRelationResult(choose, sql1, sql2, sql3, sql4);
    }

    @Override
    public List<Reader> getReaderRelation(int choose, String sql1, String sql2, String sql3, String sql4) throws Exception {
        return readerDao.getReaderRelation(choose, sql1, sql2, sql3, sql4);
    }

    @Override
    public Float getPenalty(int choose, String sqlChoose) throws Exception {
        return readerDao.getPenalty(choose, sqlChoose);
    }

    @Override
    public Float getPenaltyTwoParam(int choose, String sqlChoose1, String sqlChoose2) throws Exception {
        return readerDao.getPenaltyTwoParam(choose, sqlChoose1, sqlChoose2);
    }

    @Override
    public Float getPenaltyActivityWithStatusWithGender(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception {
        return readerDao.getPenaltyActivityWithStatusWithGender(sqlChoose1, sqlChoose2, sqlChoose3);
    }

    @Override
    public List<Integer> getAllActivity() throws Exception {
        return readerDao.getAllActivity();
    }

    @Override
    public Integer addActivity(Long id) throws Exception {
        return readerDao.addActivity(id);
    }

    @Override
    public boolean increaseActivity(Long id, int act) throws Exception {
        return readerDao.increaseActivity(id, act);
    }

    @Override
    public boolean updatePenalty(Long id, Float penalty) throws Exception {
        return readerDao.updatePenalty(id, penalty);
    }

    @Override
    public Float getPenalty(Long readerId) throws Exception {
        return readerDao.getPenalty(readerId);
    }

    @Override
    public Integer getActivity(Long readerId) throws Exception {
        return readerDao.getActivity(readerId);
    }

    @Override
    public Reader getReaderForOrder(Long readerId) throws Exception {
        return readerDao.getReaderForOrder(readerId);
    }

    @Override
    public List<Reader> readerForDate(String time) throws Exception {
        return readerDao.readerForDate(time);
    }

    @Override
    public List<Reader> getPActSthGrForTable(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception {
        return readerDao.getPActSthGrForTable(sqlChoose1, sqlChoose2, sqlChoose3);
    }

    @Override
    public Integer getBookCountForHome(Long readerId) throws Exception {
        return readerDao.getBookCountForHome(readerId);
    }

    @Override
    public List<String> cardNumbers() throws Exception {
        return readerDao.cardNumbers();
    }

    @Override
    public Reader getFullName(String cardNumber) throws Exception {
        return readerDao.getFullName(cardNumber);
    }

    @Override
    public List<String> getCardNumbers() throws Exception {
        return readerDao.getCardNumbers();
    }

    @Override
    public List<String> getIdentity() throws Exception {
        return readerDao.getIdentity();
    }

    @Override
    public List<Reader> getrReaderInfoByDate(String time1, String time2) throws Exception {
        return readerDao.getrReaderInfoByDate(time1, time2);
    }

    @Override
    public List<Reader> getReaderByNationality(String nationality) throws Exception {
        return readerDao.getReaderByNationality(nationality);
    }

    @Override
    public List<Reader> readerBooksById(Long id) throws Exception {
        return readerDao.readerBooksById(id);
    }

    @Override
    public List<Reader> searchReader(String keyword) throws Exception {
        return readerDao.searchReader(keyword);
    }

    @Override
    public List<Reader> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        return readerDao.advanceSearch(advanceSearch);
    }
}
