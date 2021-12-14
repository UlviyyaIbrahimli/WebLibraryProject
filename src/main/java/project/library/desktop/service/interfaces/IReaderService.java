package project.library.desktop.service.interfaces;

import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Reader;

import java.util.List;

public interface IReaderService {

    List<Reader> getReaderList() throws Exception;

    boolean addReader(Reader reader) throws Exception;

    boolean addReaderWeb(Reader reader) throws Exception;

    boolean updateReader(Reader reader, Long id) throws Exception;

    boolean updateReaderForWeb(Reader reader, Long id) throws Exception;

    boolean deleteReader(Long id) throws Exception;

    Reader getReaderById(Long id) throws Exception;

    Integer getCount(int choose, String sqlChoose) throws Exception;

    List<Reader> getReaderForActive(int choose, String sqlChoose) throws Exception;

    List<String> getNationlity() throws Exception;

    List<Reader> getActivityAndStatus(String sqlChoose1, String sqlchoose2) throws Exception;

    Integer getRelationResult(int choose, String sql1, String sql2, String sql3, String sql4) throws Exception;

    List<Reader> getReaderRelation(int choose, String sql1, String sql2, String sql3, String sql4) throws Exception;

    Float getPenalty(int choose, String sqlChoose) throws Exception;

    Float getPenaltyTwoParam(int choose, String sqlChoose1, String sqlChoose2) throws Exception;

    Float getPenaltyActivityWithStatusWithGender(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception;

    List<Integer> getAllActivity() throws Exception;

    Integer addActivity(Long id) throws Exception;

    boolean increaseActivity(Long id, int act) throws Exception;

    boolean updatePenalty(Long id, Float penalty) throws Exception;

    Float getPenalty(Long readerId) throws Exception;

    Integer getActivity(Long readerId) throws Exception;

    Reader getReaderForOrder(Long readerId) throws Exception;

    List<Reader> readerForDate(String time) throws Exception;

    List<Reader> getPActSthGrForTable(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception;

    Integer getBookCountForHome(Long readerId) throws Exception;

    List<String> cardNumbers() throws Exception;

    Reader getFullName(String cardNumber) throws Exception;

    List<String> getCardNumbers() throws Exception;

    List<String> getIdentity() throws Exception;

    List<Reader> getrReaderInfoByDate(String time1, String time2) throws Exception;

    List<Reader> getReaderByNationality(String nationality) throws Exception;

    List<Reader> readerBooksById(Long id) throws Exception;

    List<Reader> searchReader(String keyword) throws Exception;

    List<Reader> advanceSearch(AdvanceSearch advanceSearch) throws Exception;

}
