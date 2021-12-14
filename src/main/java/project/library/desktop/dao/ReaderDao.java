package project.library.desktop.dao;

import oracle.jdbc.OracleTypes;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IReader;
import project.library.desktop.model.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Ulviyye Ibrahimli
 */
public class ReaderDao implements IReader {

    @Override
    public List<Reader> getReaderList() throws Exception {
        List<Reader> readers = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call LIBRARY_DESKTOP.getList_pack.getReaderListForWeb }";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Reader reader = new Reader();
                    Country country = new Country();
                    Nationality nationality = new Nationality();
                    Status status = new Status();
                    status.setId(rs.getLong("sid"));
                    status.setStatus(rs.getString("status"));
                    reader.setId(rs.getLong("ID"));
                    nationality.setId(rs.getLong("nid"));
                    nationality.setNationality(rs.getString("NATIONALITY"));
                    reader.setNationality(nationality);
                    reader.setNumber(rs.getLong("r"));
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("L_name"));
                    reader.setFatherName(rs.getString("Father_name"));
                    reader.setDob(rs.getDate("Dob"));
                    reader.setGender(rs.getString("Gender"));
                    reader.setIdentifyNumber(rs.getString("IDENTIFY_NUMBER"));
                    reader.setCardNumber(rs.getString("CARD_NUMBER"));
                    reader.setStartMemberDate(rs.getDate("START_MEMBER_DATE"));
                    reader.setPullMemberDate(rs.getDate("pull_MEMBER_DATE"));
                    reader.setPenalty(rs.getFloat("PENALTY"));
                    reader.setActivity(rs.getInt("ACTIVITY"));
                    country.setId(rs.getLong("cId"));
                    country.setCountryName(rs.getString("country_name"));
                    reader.setCountry(country);
                    reader.setCity(rs.getString("City"));
                    reader.setStatus(status);
                    reader.setStreet(rs.getString("street"));
                    reader.setHouseNumber(rs.getString("house_number"));
                    reader.setHomeNumber(rs.getString("home_number"));
                    reader.seteMail(rs.getString("Email"));
                    reader.setPhone1(rs.getString("Phone_1"));
                    reader.setPhone2(rs.getString("phone_2"));
                    reader.setHomePhone(rs.getString("home_phone"));
                    reader.setAdditionInfo(rs.getString("ADDITION_INFO"));
                    readers.add(reader);
                }
            } else {
                System.out.println("Connection error!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, rs);
        }
        return readers;
    }

    @Override
    public boolean addReader(Reader reader) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql2
                =
                "Insert into reader_address(id, reader_id,country,city,street,house_number,home_number,email)values(READER_ADDRESS_SEQ.NEXTVAL,READER_SEQ.CURRVAL,?,?,?,?,?,?)";
        String sql3
                =
                "InSERT INTO READER_PHONE(ID,READER_ID,PHONE_1,PHONE_2,HOME_PHONE)VALUES(READER_PHONE_SEQ.NEXTVAL,READER_SEQ.CURRVAL,?,?,?)";
        try {
            c = DbHelper.getConnection();
            c.setAutoCommit(false);
            if (c != null) {
                if (reader.getDob() != null) {
                    String sql
                            =
                            "Insert into reader(id,f_name,l_name,father_name,dob,gender,nationality,identify_number,card_number,START_MEMBER_DATE,PULL_MEMBER_DATE,addition_info,statusid)"
                                    + "VALUES(READER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pr = c.prepareStatement(sql);
                    pr.setString(1, reader.getFirstName());
                    pr.setString(2, reader.getLastName());
                    pr.setString(3, reader.getFatherName());
                    pr.setDate(4, new java.sql.Date(reader.getDob().getTime()));
                    pr.setString(5, reader.getGender());
                    pr.setLong(6, reader.getNationality().getId());
                    pr.setString(7, reader.getIdentifyNumber());
                    pr.setString(8, reader.getCardNumber());
                    pr.setDate(9, new java.sql.Date(reader.getStartMemberDate().getTime()));
                    pr.setDate(10, new java.sql.Date(reader.getPullMemberDate().getTime()));
                    pr.setString(11, reader.getAdditionInfo());
                    pr.setLong(12, reader.getStatus().getId());
                    pr.addBatch();
                    pr.executeBatch();
                    pr = c.prepareStatement(sql2);
                    pr.setLong(1, reader.getCountry().getId());
                    pr.setString(2, reader.getCity());
                    pr.setString(3, reader.getStreet());
                    pr.setString(4, reader.getHouseNumber());
                    pr.setString(5, reader.getHomeNumber());
                    pr.setString(6, reader.geteMail());
                    pr.addBatch();
                    pr.executeBatch();
                    pr = c.prepareStatement(sql3);
                    pr.setString(1, reader.getPhone1());
                    pr.setString(2, reader.getPhone2());
                    pr.setString(3, reader.getHomePhone());
                    pr.addBatch();
                    pr.executeBatch();
                    result = true;
                } else {
                    String sql1
                            =
                            "Insert into reader(id,f_name,l_name,father_name,gender,nationality,identify_number,card_number,START_MEMBER_DATE,PULL_MEMBER_DATE,addition_info,statusId)"
                                    + "VALUES(READER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
                    pr = c.prepareStatement(sql1);
                    pr.setString(1, reader.getFirstName());
                    pr.setString(2, reader.getLastName());
                    pr.setString(3, reader.getFatherName());
//                    pr.setDate(4, new java.sql.Date(reader.getDob().getTime()));
                    pr.setString(4, reader.getGender());
                    pr.setLong(5, reader.getNationality().getId());
                    pr.setString(6, reader.getIdentifyNumber());
                    pr.setString(7, reader.getCardNumber());
                    pr.setDate(8, new java.sql.Date(reader.getStartMemberDate().getTime()));
                    pr.setDate(9, new java.sql.Date(reader.getPullMemberDate().getTime()));
                    pr.setString(10, reader.getAdditionInfo());
                    pr.setLong(11, reader.getStatus().getId());
                    pr.addBatch();
                    pr.executeBatch();
                    pr = c.prepareStatement(sql2);
                    pr.setLong(1, reader.getCountry().getId());
                    pr.setString(2, reader.getCity());
                    pr.setString(3, reader.getStreet());
                    pr.setString(4, reader.getHouseNumber());
                    pr.setString(5, reader.getHomeNumber());
                    pr.setString(6, reader.geteMail());
                    pr.addBatch();
                    pr.executeBatch();
                    pr = c.prepareStatement(sql3);
                    pr.setString(1, reader.getPhone1());
                    pr.setString(2, reader.getPhone2());
                    pr.setString(3, reader.getHomePhone());
                    pr.addBatch();
                    pr.executeBatch();
                    result = true;
                }

            } else {
                System.out.println("Connection Error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, pr, null);
        }
        return result;
    }

    public boolean addReaderWeb(Reader reader) throws Exception {
        boolean result = false;
        Connection c = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (reader.getDob() != null) {
                    System.out.println("add dao  " + reader.getDob());
                    String sql = "{call LIBRARY_DESKTOP.add_pack.add_reader1(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql);
                    cs.setString(1, reader.getFirstName());
                    cs.setString(2, reader.getLastName());
                    cs.setString(3, reader.getFatherName());
                    cs.setString(4, reader.getGender());
                    cs.setDate(5, new java.sql.Date(reader.getDob().getTime()));
                    cs.setString(6, reader.getAdditionInfo());
                    cs.setLong(7, reader.getNationality().getId());
                    cs.setLong(8, reader.getStatus().getId());
                    cs.setString(9, reader.getPassword());
                    cs.setString(10, reader.getPhone1());
                    cs.setLong(11, reader.getCountry().getId());
                    cs.setString(12, reader.getCity());
                    cs.setString(13, reader.getHomeNumber());
                    cs.setString(14, reader.geteMail());
                    cs.execute();
                    result = true;
                } else {
                    String sql = "{call LIBRARY_DESKTOP.add_pack.add_reader2(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql);
                    cs.setString(1, reader.getFirstName());
                    cs.setString(2, reader.getLastName());
                    cs.setString(3, reader.getFatherName());
                    cs.setString(4, reader.getGender());
                    cs.setLong(5, reader.getNationality().getId());
                    cs.setString(6, reader.getAdditionInfo());
                    cs.setLong(7, reader.getStatus().getId());
                    cs.setString(8, reader.getPassword());
                    cs.setLong(9, reader.getCountry().getId());
                    cs.setString(10, reader.getCity());
                    cs.setString(11, reader.geteMail());
                    cs.setString(12, reader.getHomeNumber());
                    cs.setString(13, reader.getPhone1());
                    cs.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection Error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection2(c, cs, null);
//            Util.closeConnection(c, pr, null);
        }
        return result;
    }

    @Override
    public boolean updateReader(Reader reader, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;
        String sql2
                =
                "UPDATE READER_ADDRESS SET country=?,city=?,street=?,house_number=?,home_number=?,email=?  WHERE READER_ID=?";
        String sql3 = "UPDATE READER_PHONE  SET PHONE_1=?,PHONE_2=?,HOME_PHONE=? WHERE READER_ID=?";
        try {
            c = DbHelper.getConnection();
            c.setAutoCommit(false);
            if (c != null) {
                if (reader.getDob() != null) {
                    String sql11
                            =
                            " UPDATE READER SET f_name=?,l_name=?,father_name=?,dob=?,gender=?,nationality=?,identify_number=?,card_number=?,START_MEMBER_DATE=?,PULL_MEMBER_DATE=?,PENALTY=?, ADDITION_INFO=?, ACTIVITY=?,STATUSID=? WHERE ID=?";
                    pr = c.prepareStatement(sql11);
                    pr.setString(1, reader.getFirstName());
                    pr.setString(2, reader.getLastName());
                    pr.setString(3, reader.getFatherName());
                    pr.setDate(4, new java.sql.Date(reader.getDob().getTime()));
                    pr.setString(5, reader.getGender());
                    pr.setLong(6, reader.getNationality().getId());
                    pr.setString(7, reader.getIdentifyNumber());
                    pr.setString(8, reader.getCardNumber());
                    pr.setDate(9, new java.sql.Date(reader.getStartMemberDate().getTime()));
                    pr.setDate(10, new java.sql.Date(reader.getPullMemberDate().getTime()));
                    pr.setFloat(11, reader.getPenalty());
                    pr.setString(12, reader.getAdditionInfo());
                    pr.setInt(13, reader.getActivity());
                    pr.setLong(14, reader.getStatus().getId());
                    pr.setLong(15, id);
                    pr.execute();
                    pr = c.prepareStatement(sql2);
                    pr.setString(1, reader.getCountryOld());
                    pr.setString(2, reader.getCity());
                    pr.setString(3, reader.getStreet());
                    pr.setString(4, reader.getHouseNumber());
                    pr.setString(5, reader.getHomeNumber());
                    pr.setString(6, reader.geteMail());
                    pr.setLong(7, id);
                    pr.execute();
                    pr = c.prepareStatement(sql3);
                    pr.setString(1, reader.getPhone1());
                    pr.setString(2, reader.getPhone2());
                    pr.setString(3, reader.getHomePhone());
                    pr.setLong(4, id);
                    pr.execute();
                    result = true;

                } else if (reader.getDob() == null) {
                    String sql11
                            =
                            " UPDATE READER SET f_name=?,l_name=?,father_name=?,dob=?,gender=?,nationality=?,identify_number=?,card_number=?,START_MEMBER_DATE=?,PULL_MEMBER_DATE=?,PENALTY=?, ADDITION_INFO=?, ACTIVITY=?,STATUSID=? WHERE ID=?";
                    pr = c.prepareStatement(sql11);
                    pr.setString(1, reader.getFirstName());
                    pr.setString(2, reader.getLastName());
                    pr.setString(3, reader.getFatherName());
                    pr.setDate(4, null);
                    pr.setString(5, reader.getGender());
                    pr.setLong(6, reader.getNationality().getId());
                    pr.setString(7, reader.getIdentifyNumber());
                    pr.setString(8, reader.getCardNumber());
                    pr.setDate(9, new java.sql.Date(reader.getStartMemberDate().getTime()));
                    pr.setDate(10, new java.sql.Date(reader.getPullMemberDate().getTime()));
                    pr.setFloat(11, reader.getPenalty());
                    pr.setString(12, reader.getAdditionInfo());
                    pr.setInt(13, reader.getActivity());
                    pr.setLong(14, reader.getStatus().getId());
                    pr.setLong(15, id);
                    pr.execute();
                    pr = c.prepareStatement(sql2);
                    pr.setString(1, reader.getCountryOld());
                    pr.setString(2, reader.getCity());
                    pr.setString(3, reader.getStreet());
                    pr.setString(4, reader.getHouseNumber());
                    pr.setString(5, reader.getHomeNumber());
                    pr.setString(6, reader.geteMail());
                    pr.setLong(7, id);
                    pr.execute();
                    pr = c.prepareStatement(sql3);
                    pr.setString(1, reader.getPhone1());
                    pr.setString(2, reader.getPhone2());
                    pr.setString(3, reader.getHomePhone());
                    pr.setLong(4, id);
                    pr.execute();
                    result = true;

                }
            } else {
                System.out.println("Connection error!!!!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            result = false;
        } finally {
            c.commit();
            Util.closeConnection(c, pr, null);
        }
        return result;
    }

    public boolean updateReaderforWeb(Reader reader, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (reader.getDob() != null) {
                    System.out.println("cdcd   " + reader.getDob());
                    String sql = "{call LIBRARY_DESKTOP.update_pack.update_reader(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql);
                    cs.setString(1, reader.getFirstName());
                    cs.setString(2, reader.getLastName());
                    cs.setString(3, reader.getFatherName());
                    cs.setString(4, reader.getGender());
                    cs.setDate(5, new java.sql.Date(reader.getDob().getTime()));
                    cs.setLong(6, reader.getNationality().getId());
                    cs.setString(7, reader.getAdditionInfo());
                    cs.setLong(8, reader.getStatus().getId());
                    cs.setString(9, reader.getPassword());
                    cs.setString(10, reader.getPhone1());
                    cs.setLong(11, reader.getCountry().getId());
                    cs.setString(12, reader.getCity());
                    cs.setString(13, reader.getHomeNumber());
                    cs.setString(14, reader.geteMail());
                    cs.setLong(15, id);
                    cs.execute();
                    result = true;
                } else if (reader.getDob() == null) {
                    String sql = "{call LIBRARY_DESKTOP.update_pack.update_reader2(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql);
                    cs.setString(1, reader.getFirstName());
                    cs.setString(2, reader.getLastName());
                    cs.setString(3, reader.getFatherName());
                    cs.setString(4, reader.getGender());
                    cs.setLong(5, reader.getNationality().getId());
                    cs.setString(6, reader.getAdditionInfo());
                    cs.setLong(7, reader.getStatus().getId());
                    cs.setString(8, reader.getPassword());
                    cs.setString(9, reader.getPhone1());
                    cs.setLong(10, reader.getCountry().getId());
                    cs.setString(11, reader.getCity());
                    cs.setString(12, reader.getHomeNumber());
                    cs.setString(13, reader.geteMail());
                    cs.setLong(14, id);
                    cs.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!!!!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            result = false;
        } finally {
            c.commit();
            Util.closeConnection2(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean deleteReader(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;
        String sql1 = "update reader set active=0 where id=?";
        String sql2 = "update reader_address set active=0 where id=?";
        String sql3 = "update reader_phone set active=0 where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                c.setAutoCommit(false);
                pr = c.prepareStatement(sql1);
                pr.setLong(1, id);
                pr.execute();
                pr = c.prepareStatement(sql2);
                pr.setLong(1, id);
                pr.execute();
                pr = c.prepareStatement(sql3);
                pr.setLong(1, id);
                pr.execute();
                result = true;
            }
        } catch (Exception e) {
            result = false;
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, pr, null);
        }
        return result;
    }

    @Override
    public Reader getReaderById(Long id) throws Exception {
        Reader reader = new Reader();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT R.F_NAME,\n" +
                "       R.L_NAME,\n" +
                "       R.FATHER_NAME,\n" +
                "       R.DOB,\n" +
                "       R.GENDER,\n" +
                "       R.IDENTIFY_NUMBER,\n" +
                "       R.CARD_NUMBER,\n" +
                "       n.NATIONALITY,\n" +
                "       n.id AS nId,\n" +
                "       R.ADDITION_INFO,\n" +
                "       R.START_MEMBER_DATE,\n" +
                "       R.PULL_MEMBER_DATE,\n" +
                "       R.PENALTY,\n" +
                "       R.ACTIVITY,\n" +
                "       RA.COUNTRY,\n" +
                "       RA.CITY,\n" +
                "       C.COUNTRY_NAME,\n" +
                "       c.id AS cId,\n" +
                "       RA.STREET,\n" +
                "       RA.HOUSE_NUMBER,\n" +
                "       RA.HOME_NUMBER,\n" +
                "       RA.EMAIL,\n" +
                "       RP.PHONE_1,\n" +
                "       RP.PHONE_2,\n" +
                "       S.ID as SID,\n" +
                "       S.STATUS,\n" +
                "       RP.HOME_PHONE,\n" +
                "       R.PASSWORD\n" +
                "  FROM READER R\n" +
                "       LEFT JOIN READER_ADDRESS RA\n" +
                "          ON R.ID = RA.READER_ID\n" +
                "       LEFT JOIN READER_PHONE RP\n" +
                "          ON R.ID = RP.READER_ID\n" +
                "       LEFT JOIN country c\n" +
                "          ON c.id = RA.COUNTRY\n" +
                "       LEFT JOIN nationality n\n" +
                "          ON n.id = R.NATIONALITY\n" +
                "       FULL JOIN status s\n" +
                "          ON S.ID = R.STATUSID\n" +
                " WHERE r.id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Status status = new Status();
                    status.setId(rs.getLong("SID"));
                    status.setStatus(rs.getString("status"));
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("L_name"));
                    reader.setFatherName(rs.getString("Father_name"));
                    reader.setDob(rs.getDate("Dob"));
                    reader.setPassword(rs.getString("PASSWORD"));
                    reader.setGender(rs.getString("Gender"));
                    Nationality nationality = new Nationality();
                    nationality.setId(rs.getLong("nId"));
                    nationality.setNationality(rs.getString("NATIONALITY"));
                    reader.setNationality(nationality);
                    reader.setIdentifyNumber(rs.getString("IDENTIFY_NUMBER"));
                    reader.setCardNumber(rs.getString("CARD_NUMBER"));
                    reader.setStartMemberDate(rs.getDate("START_MEMBER_DATE"));
                    reader.setPullMemberDate(rs.getDate("pull_MEMBER_DATE"));
                    reader.setPenalty(rs.getFloat("PENALTY"));
                    reader.setActivity(rs.getInt("ACTIVITY"));
                    Country country = new Country();
                    country.setId(rs.getLong("cId"));
                    country.setCountryName(rs.getString("COUNTRY_NAME"));
                    reader.setCountry(country);
                    reader.setCountryOld(rs.getString("Country"));
                    reader.setAdditionInfo(rs.getString("ADDITION_INFO"));
                    reader.setCity(rs.getString("City"));
                    reader.setStreet(rs.getString("street"));
                    reader.setHouseNumber(rs.getString("house_number"));
                    reader.setHomeNumber(rs.getString("home_number"));
                    reader.seteMail(rs.getString("Email"));
                    reader.setPhone1(rs.getString("Phone_1"));
                    reader.setPhone2(rs.getString("phone_2"));
                    reader.setHomePhone(rs.getString("home_phone"));
                    reader.setStatus(status);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return reader;
    }

    @Override
    public Integer getCount(int choose, String sqlChoose) throws Exception {
        Integer result = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    String sql1 = "SELECT COUNT (id) AS ALLREADER\n"
                            + "  FROM reader\n"
                            + " WHERE active = 1";
                    ps = c.prepareStatement(sql1);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("ALLREADER");
                    }
                } else if (choose == 2) {
                    String sql2 = " SELECT COUNT (gender) as GENDERREADER\n"
                            + "    FROM reader\n"
                            + "   WHERE active = 1\n"
                            + "GROUP BY gender\n"
                            + "  HAVING gender = ?";
                    ps = c.prepareStatement(sql2);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("GENDERREADER");
                    }
                } else if (choose == 3) {
                    String sql3 = " SELECT COUNT (status) AS COUNT\n"
                            + "    FROM reader\n"
                            + "   WHERE active = 1\n"
                            + "GROUP BY status\n"
                            + "  HAVING status = ?";
                    ps = c.prepareStatement(sql3);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("COUNT");
                    }
                } else if (choose == 4) {
                    String sql4 = "SELECT SUM (COUNT (id)) AS countActivity\n"
                            + "    FROM reader\n"
                            + "   WHERE active = 1\n"
                            + "GROUP BY activity\n"
                            + "  HAVING activity = ?";
                    ps = c.prepareStatement(sql4);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("countActivity");
                    }
                } else if (choose == 5) {
                    String sql5 = "SELECT f_name, l_name AS fullNAme\n"
                            + "  FROM reader\n"
                            + " WHERE active = 1 AND activity = ?";
                    ps = c.prepareStatement(sql5);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                    }
                } else if (choose == 6) {
                    String sql6 = " SELECT SUM (COUNT (id)) as nationalityCount \n"
                            + "    FROM reader\n"
                            + "   WHERE active = 1\n"
                            + "GROUP BY nationality\n"
                            + "  HAVING nationality = ?";
                    ps = c.prepareStatement(sql6);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("nationalityCount");
                    }
                } else if (choose == 7) {
                    String sql7 = "SELECT SUM (COUNT (ID)) AS PENALITY_COUNT\n"
                            + "    FROM READER\n"
                            + "   WHERE ACTIVE = 1\n"
                            + "GROUP BY PENALTY\n"
                            + "  HAVING PENALTY = ?";
                    ps = c.prepareStatement(sql7);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("PENALITY_COUNT");
                    }
                } else if (choose == 8) {
                    String sql8 = "SELECT SUM (COUNT (ID)) AS PENALITY_COUNT\n"
                            + "    FROM READER\n"
                            + "   WHERE ACTIVE = 1\n"
                            + "GROUP BY PENALTY\n"
                            + "  HAVING PENALTY < ?";
                    ps = c.prepareStatement(sql8);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("PENALITY_COUNT");
                    }
                } else if (choose == 9) {
                    String sql9 = "SELECT SUM (COUNT (ID)) AS PENALITY_COUNT\n"
                            + "    FROM READER\n"
                            + "   WHERE ACTIVE = 1\n"
                            + "GROUP BY PENALTY\n"
                            + "  HAVING PENALTY > ?";
                    ps = c.prepareStatement(sql9);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("PENALITY_COUNT");
                    }
                } else if (choose == 10) {
                    String sql10 = "SELECT SUM (COUNT (ID)) AS SUMCOUNT\n"
                            + "                   FROM READER\n"
                            + "                  WHERE ACTIVE = 1\n"
                            + "               GROUP BY start_member_date\n"
                            + "                 HAVING START_MEMBER_DATE >=to_date('" + new java.sql.Date(df.parse(sqlChoose).getTime()) + "', 'yyyy-mm-dd')";
                    ps = c.prepareStatement(sql10);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("SUMCOUNT");
                    }
                }
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return result;
    }

    @Override
    public List<Reader> getReaderForActive(int choose, String sqlChoose) throws Exception {
//        List<Reader> readerList = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql1 = "SELECT RowNum  r, id, f_name, l_name, activity,status \n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND activity =?";
//        String sql2 = "SELECT   RowNum  r, id , f_name, l_name ,status,activity\n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND status=?";
//        String sql3 = "SELECT  rownum r,id, f_name, l_name, nationality\n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND nationality = ?";
//        String sql4 = "SELECT rownum r, id,f_name, l_name, penalty\n"
//                + "  FROM READER\n"
//                + " WHERE ACTIVE = 1 AND penalty = ?";
//        String sql5 = "SELECT  rownum r, id, f_name, l_name, penalty\n"
//                + "  FROM READER\n"
//                + " WHERE ACTIVE = 1 AND penalty >?";
//        String sql6 = "SELECT rownum r,id,f_name, l_name, penalty\n"
//                + "  FROM READER\n"
//                + " WHERE ACTIVE = 1 AND penalty <?";
//        String sql7 = "SELECT Rownum r, id, f_name, l_name, start_member_date\n"
//                + "  FROM READER\n"
//                + " WHERE     ACTIVE = 1\n"
//                + " AND start_member_date = TO_DATE (?, 'MM/DD/YYYY ')";
//        String sql8 = "SELECT RowNum  r, id, f_name, l_name, gender \n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND gender=?";
//        String sql9 = "SELECT RowNum  r, id,f_name, l_name, gender \n"
//                + "  FROM reader\n"
//                + " WHERE active = 1";
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                if (choose == 1) {
//                    ps = c.prepareStatement(sql1);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("L_name"));
//                        reader.setActivity(rs.getInt("activity"));
//                        reader.setStatus(rs.getString("STATUS"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 2) {
//                    ps = c.prepareStatement(sql2);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("L_name"));
//                        reader.setStatus(rs.getString("status"));
//                        reader.setActivity(rs.getInt("activity"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 3) {
//                    ps = c.prepareStatement(sql3);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        Nationality nationality = new Nationality();
//                        nationality.setNationality(rs.getString("nationality"));
//                        reader.setNationality(nationality);
//                        readerList.add(reader);
//                    }
//                } else if (choose == 4) {
//                    ps = c.prepareStatement(sql4);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setPenalty(rs.getFloat("penalty"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 5) {
//                    ps = c.prepareStatement(sql5);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setPenalty(rs.getFloat("penalty"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 6) {
//                    ps = c.prepareStatement(sql6);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setPenalty(rs.getFloat("penalty"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 7) {
//                    ps = c.prepareStatement(sql7);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setStartMemberDate(rs.getDate("start_member_date"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 8) {
//                    ps = c.prepareStatement(sql8);
//                    ps.setString(1, sqlChoose);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setGender(rs.getString("gender"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 9) {
//                    ps = c.prepareStatement(sql9);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setId(rs.getLong("id"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setGender(rs.getString("gender"));
//                        readerList.add(reader);
//                    }
//                }

//            } else {
//                System.out.println("Connection error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;
    }

    @Override
    public List<String> getNationlity() throws Exception {
        List<String> nationalityLis = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT NATIONALITY\n"
                + "  FROM reader\n"
                + " WHERE active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String nat = rs.getString("NATIONALITY");
                    nationalityLis.add(nat);
                }
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {

        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return nationalityLis;
    }

    @Override
    public List<Reader> getActivityAndStatus(String sqlChoose1, String sqlchoose2) throws Exception {
//        List<Reader> readerList = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "SELECT   RowNum  r,f_name, l_name ,status,activity\n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND status=? and activity=? ";
//
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                ps = c.prepareStatement(sql);
//                ps.setString(1, sqlChoose1);
//                ps.setString(2, sqlchoose2);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    Reader reader = new Reader();
//                    reader.setNumber(rs.getLong("r"));
//                    reader.setFirstName(rs.getString("f_name"));
//                    reader.setLastName(rs.getString("l_name"));
//                    reader.setStatus(rs.getString("status"));
//                    reader.setActivity(rs.getInt("activity"));
//                    readerList.add(reader);
//                }
//            } else {
//                System.out.println("Connection error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }

        return null;
    }

    @Override
    public Integer getRelationResult(int choose, String sqlChoose1, String sqChoosel2, String sqlChoose3, String sql5) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "SELECT SUM (COUNT (id)) as sumAll \n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY gender, activity, status\n"
                + "  HAVING gender = ? AND status = ? AND activity = ?";
        String sql2 = "SELECT SUM (COUNT (id)) sumR\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY gender, activity, status\n"
                + "  HAVING gender = ? AND activity = ?";
        String sql3 = "SELECT SUM (COUNT (id)) as sumR \n"
                + "                    FROM reader\n"
                + "                   WHERE active = 1\n"
                + "                GROUP BY gender,  status\n"
                + "                  HAVING gender =? AND status=?";
        String sql4 = "  SELECT SUM (COUNT (id)) AS sumAll\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY activity, status\n"
                + "  HAVING  status = ? AND activity = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    ps = c.prepareStatement(sql1);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqChoosel2);
                    ps.setString(3, sqlChoose3);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("sumAll");
                    }
                } else if (choose == 2) {
                    ps = c.prepareStatement(sql2);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqChoosel2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("sumR");
                    }
                } else if (choose == 3) {
                    ps = c.prepareStatement(sql3);
                    ps.setString(1, sql1);
                    ps.setString(2, sqChoosel2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("sumR");
                    }
                } else if (choose == 4) {
                    ps = c.prepareStatement(sql4);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqChoosel2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("sumAll");
                    }
                }
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return result;

    }

    @Override
    public List<Reader> getReaderRelation(int choose, String sqlChoose1, String sqlChoose2, String sqlChoose3, String sqlChoose4) throws Exception {
//        List<Reader> readerList = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql1 = "SELECT ROWNUM r,\n"
//                + "       f_name,\n"
//                + "       l_name,\n"
//                + "       status,\n"
//                + "       activity\n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND gender = ? AND status = ? AND activity = ?";
//        String sql2 = "SELECT ROWNUM r,\n"
//                + "       f_name,\n"
//                + "       l_name,\n"
//                + "       activity\n"
//                + "  FROM reader\n"
//                + " WHERE active = 1 AND gender = ? AND activity = ?";
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                if (choose == 1) {
//                    ps = c.prepareStatement(sql1);
//                    ps.setString(1, sqlChoose1);
//                    ps.setString(2, sqlChoose2);
//                    ps.setString(3, sqlChoose3);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setStatus(rs.getString("status"));
//                        reader.setActivity(rs.getInt("activity"));
//                        readerList.add(reader);
//                    }
//                } else if (choose == 2) {
//                    ps = c.prepareStatement(sql2);
//                    ps.setString(1, sqlChoose1);
//                    ps.setString(2, sqlChoose2);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reader reader = new Reader();
//                        reader.setNumber(rs.getLong("r"));
//                        reader.setFirstName(rs.getString("f_name"));
//                        reader.setLastName(rs.getString("l_name"));
//                        reader.setActivity(rs.getInt("activity"));
//                        readerList.add(reader);
//                    }
//                }
//            } else {
//                System.out.println("Connection error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;
    }

    @Override
    public Float getPenalty(int choose, String sqlChoose) throws Exception {
        Float result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "SELECT penalty\n"
                + "  FROM reader\n"
                + " WHERE id = ?";
        String sql2 = "SELECT SUM (penalty) allP\n"
                + "  FROM reader\n"
                + " WHERE active = 1";
        String sql3 = "SELECT SUM (penalty) allGender\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY gender\n"
                + "  HAVING gender = ?";
        String sql4 = "SELECT SUM (penalty)  as AllP\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY  status\n"
                + "  HAVING   status = ?";
        String sql5 = "  SELECT SUM (penalty) AS pp\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY activity\n"
                + "  HAVING activity = ?";
        String sql6 = "SELECT PENALTY\n"
                + "  FROM reader\n"
                + " WHERE id = ? AND active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    ps = c.prepareStatement(sql1);
                    ps.setLong(1, Integer.parseInt(sqlChoose));
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("penalty");
                    }
                } else if (choose == 2) {
                    ps = c.prepareStatement(sql2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allP");
                    }
                } else if (choose == 3) {
                    ps = c.prepareStatement(sql3);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allGender");
                    }
                } else if (choose == 4) {
                    ps = c.prepareStatement(sql4);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("AllP");
                    }
                } else if (choose == 5) {
                    ps = c.prepareStatement(sql5);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("pp");
                    }
                } else if (choose == 6) {
                    ps = c.prepareStatement(sql6);
                    ps.setString(1, sqlChoose);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("PENALTY");
                    }
                }
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return result;
    }

    @Override
    public Float getPenaltyTwoParam(int choose, String sqlChoose1, String sqlChoose2) throws Exception {
        Float result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = " SELECT SUM (penalty)  as  allForTow\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY gender, status\n"
                + "  HAVING gender = ? AND status = ?";

        String sql2 = " SELECT SUM (penalty) as  penl\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY  status, activity\n"
                + "  HAVING status = ? AND activity = ?";

        String sql3 = "SELECT SUM (penalty) as  penl\n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY  gender, activity\n"
                + "  HAVING gender = ? AND activity = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    ps = c.prepareStatement(sql1);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqlChoose2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allForTow");
                    }

                } else if (choose == 2) {
                    ps = c.prepareStatement(sql2);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqlChoose2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("penl");
                    }

                } else if (choose == 3) {
                    ps = c.prepareStatement(sql3);
                    ps.setString(1, sqlChoose1);
                    ps.setString(2, sqlChoose2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("penl");
                    }

                }
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return result;
    }

    @Override
    public Float getPenaltyActivityWithStatusWithGender(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception {
        Float result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT SUM (penalty) as penl \n"
                + "    FROM reader\n"
                + "   WHERE active = 1\n"
                + "GROUP BY gender, status, activity\n"
                + "  HAVING status = ? AND activity = ? AND gender = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, sqlChoose1);
                ps.setString(2, sqlChoose2);
                ps.setInt(2, Integer.parseInt(sqlChoose3));
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getFloat("penl");
                }

            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return result;
    }

    @Override
    public List<Integer> getAllActivity() throws Exception {
        List<Integer> getActivityList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select  activity from reader where active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Integer r = rs.getInt("activity");
                    getActivityList.add(r);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return getActivityList;
    }

    @Override
    public Integer addActivity(Long id) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT SUM (COUNT (book_id)) AS activity\n"
                + "                   FROM order_in_lib\n"
                + "                  WHERE MONTHS_BETWEEN (SYSDATE, data_date) / 12 <=1\n"
                + "               GROUP BY reader_id\n"
                + "                 HAVING reader_id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("activity");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }
        return result;

    }

    @Override
    public boolean increaseActivity(Long id, int act) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " update  reader  set activity=?   where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, act);
                ps.setLong(2, id);
                ps.execute();
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return result;

    }

    @Override
    public boolean updatePenalty(Long id, Float penalty) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " update reader set penalty=? where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setFloat(1, penalty);
                ps.setLong(2, id);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return result;
    }

    @Override
    public Float getPenalty(Long readerId) throws Exception {
        Float penalty = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select penalty from reader where id=? and active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, readerId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    penalty = rs.getFloat("penalty");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return penalty;
    }

    @Override
    public Integer getActivity(Long readerId) throws Exception {
        Integer activity = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select activity from reader where id=? and active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, readerId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    activity = rs.getInt("activity");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;

    }

    @Override
    public Reader getReaderForOrder(Long readerId) throws Exception {
//        Reader reader = new Reader();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "SELECT R.F_NAME,\n"
//                + "       R.L_NAME,\n"
//                + "       R.FATHER_NAME,\n"
//                + "       R.CARD_NUMBER,\n"
//                + "       R.IDENTIFY_NUMBER,\n"
//                + "       R.PENALTY,\n"
//                + "       R.ACTIVITY,\n"
//                + "       R.START_MEMBER_DATE,\n"
//                + "       R.PULL_MEMBER_DATE,\n"
//                + "       R.STATUS\n"
//                + "  FROM READER R\n"
//                + " WHERE R.ACTIVE = 1 AND R.ID = ?";
//
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                ps = c.prepareStatement(sql);
//                ps.setLong(1, readerId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    reader.setFirstName(rs.getString("F_NAME"));
//                    reader.setLastName(rs.getString("l_name"));
//                    reader.setFatherName(rs.getString("father_name"));
//                    reader.setCardNumber(rs.getString("card_number"));
//                    reader.setIdentifyNumber(rs.getString("identify_number"));
//                    reader.setPenalty(rs.getFloat("penalty"));
//                    reader.setActivity(rs.getInt("activity"));
//                    reader.setStatus(rs.getString("STATUS"));
//                    reader.setStartMemberDate(rs.getDate("START_MEMBER_DATE"));
//                    reader.setPullMemberDate(rs.getDate("PULL_MEMBER_DATE"));
//                }
//            } else {
//                System.out.println("Connection error!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;

    }

    @Override
    public List<Reader> readerForDate(String time) throws Exception {
        List<Reader> readers = new ArrayList<>();
        Connection c = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.F_NAME) AS rr,\n"
                + "       r.id,\n"
                + "       R.F_NAME,\n"
                + "       R.L_NAME,\n"
                + "       R.START_MEMBER_DATE,\n"
                + "       R.PULL_MEMBER_DATE,\n"
                + "       R.ACTIVITY,\n"
                + "       R.STATUS\n"
                + "  FROM reader r\n"
                + " WHERE     r.active = 1\n"
                + "       AND R.START_MEMBER_DATE >= TO_DATE ('" + new java.sql.Date(df.parse(time).getTime()) + "', 'yyyy-MM-dd')";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reader reader = new Reader();
                    reader.setNumber(rs.getLong("rr"));
                    reader.setId(rs.getLong("id"));
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("l_name"));
                    reader.setStartMemberDate(rs.getDate("start_member_date"));
                    reader.setPullMemberDate(rs.getDate("pull_member_date"));
                    reader.setActivity(rs.getInt("activity"));
//                    reader.setStatus(rs.getString("status"));
                    readers.add(reader);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return readers;

    }

    @Override
    public List<Reader> getPActSthGrForTable(String sqlChoose1, String sqlChoose2, String sqlChoose3) throws Exception {
//        List<Reader> readers = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "SELECT ROW_NUMBER () OVER (ORDER BY r.f_name) AS rr,"
//                + "r.id,\n"
//                + "       R.F_NAME,\n"
//                + "       R.L_NAME,\n"
//                + "       R.CARD_NUMBER,\n"
//                + "       R.GENDER,\n"
//                + "       R.STATUS,\n"
//                + "       R.ACTIVITY,\n"
//                + "       R.penalty,\n"
//                + "       R.IDENTIFY_NUMBER\n"
//                + "  FROM reader r\n"
//                + " WHERE active = 1 AND status = ? AND activity = ? AND gender = ? ";
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                ps = c.prepareStatement(sql);
//                ps.setString(1, sqlChoose1);
//                ps.setString(2, sqlChoose2);
//                ps.setString(3, sqlChoose3);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    Reader reader = new Reader();
//                    reader.setId(rs.getLong("id"));
//                    reader.setNumber(rs.getLong("rr"));
//                    reader.setFirstName(rs.getString("f_name"));
//                    reader.setLastName(rs.getString("l_name"));
//                    reader.setGender(rs.getString("gender"));
//                    reader.setActivity(rs.getInt("activity"));
//                    reader.setPenalty(rs.getFloat("penalty"));
//                    reader.setStatus(rs.getString("status"));
//                    reader.setIdentifyNumber(rs.getString("IDENTIFY_NUMBER"));
//                    reader.setCardNumber(rs.getString("CARD_NUMBER"));
//                    readers.add(reader);
//                }
//            } else {
//                System.out.println("Connection error!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;

    }

    @Override
    public Integer getBookCountForHome(Long readerId) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT SUM (COUNT (book_id)) AS activity\n"
                + "                   FROM order_in_home\n"
                + "                  WHERE MONTHS_BETWEEN (SYSDATE, data_date) / 12 <=1\n"
                + "               GROUP BY reader_id\n"
                + "                 HAVING reader_id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, readerId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("activity");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<String> cardNumbers() throws Exception {
        List<String> cards = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  R.CARD_NUMBER\n"
                + "  FROM reader r\n"
                + " WHERE active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String card = rs.getString("CARD_NUMBER");
                    cards.add(card);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return cards;

    }

    @Override
    public Reader getFullName(String cardNumber) throws Exception {
        Reader reader = new Reader();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT R.F_NAME, R.L_NAME\n"
                + "  FROM reader r\n"
                + " WHERE active = 1 AND R.CARD_NUMBER = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, cardNumber);
                rs = ps.executeQuery();
                while (rs.next()) {
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("l_name"));
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return reader;
    }

    @Override
    public List<String> getCardNumbers() throws Exception {
        List<String> cards = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT R.CARD_NUMBER\n"
                + "  FROM reader R WHERE R.ACTIVE=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String card = rs.getString("CARD_NUMBER");
                    cards.add(card);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return cards;
    }

    @Override
    public List<String> getIdentity() throws Exception {
        List<String> cards = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT R.IDENTIFY_NUMBER\n"
                + "  FROM reader r WHERE R. ACTIVE=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String card = rs.getString("IDENTIFY_NUMBER");
                    cards.add(card);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return cards;
    }

    @Override
    public List<Reader> getrReaderInfoByDate(String time1, String time2) throws Exception {
//        List<Reader> readers = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String sql = "";
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                if (time1 != null && time2 != null) {
//                    sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.START_MEMBER_DATE ASC) AS rr,\n"
//                            + "       r.id,\n"
//                            + "       R.F_NAME,\n"
//                            + "       R.L_NAME,\n"
//                            + "       R.ACTIVITY,\n"
//                            + "       R.STATUS,\n"
//                            + "       R.CARD_NUMBER,\n"
//                            + "       R.START_MEMBER_DATE,\n"
//                            + "       R.PULL_MEMBER_DATE,\n"
//                            + "       R.GENDER\n"
//                            + "  FROM reader r\n"
//                            + " WHERE     active = 1\n"
//                            + "       AND R.START_MEMBER_DATE >= TO_DATE ('" + new java.sql.Date(df.parse(time1).getTime()) + "', 'yyyy-MM-dd')\n"
//                            + "       AND R.START_MEMBER_DATE < TO_DATE ('" + new java.sql.Date(df.parse(time2).getTime()) + "', 'yyyy-MM-dd')";
//                } else if (time1 != null && time2 == null) {
//                    sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.START_MEMBER_DATE ASC) AS rr,\n"
//                            + "       r.id,\n"
//                            + "       R.F_NAME,\n"
//                            + "       R.L_NAME,\n"
//                            + "       R.ACTIVITY,\n"
//                            + "       R.STATUS,\n"
//                            + "       R.CARD_NUMBER,\n"
//                            + "       R.START_MEMBER_DATE,\n"
//                            + "       R.PULL_MEMBER_DATE,\n"
//                            + "       R.GENDER\n"
//                            + "  FROM reader r\n"
//                            + " WHERE     active = 1\n"
//                            + "       AND R.START_MEMBER_DATE >= TO_DATE ('" + new java.sql.Date(df.parse(time1).getTime()) + "', 'yyyy-MM-dd')\n";
//                } else if (time1 == null && time2 != null) {
//                    sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.START_MEMBER_DATE ASC) AS rr,\n"
//                            + "       r.id,\n"
//                            + "       R.F_NAME,\n"
//                            + "       R.L_NAME,\n"
//                            + "       R.ACTIVITY,\n"
//                            + "       R.STATUS,\n"
//                            + "       R.CARD_NUMBER,\n"
//                            + "       R.START_MEMBER_DATE,\n"
//                            + "       R.PULL_MEMBER_DATE,\n"
//                            + "       R.GENDER\n"
//                            + "  FROM reader r\n"
//                            + " WHERE     active = 1\n"
//                            + "       AND R.START_MEMBER_DATE < TO_DATE ('" + new java.sql.Date(df.parse(time2).getTime()) + "', 'yyyy-MM-dd')";
//                }
//                ps = c.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    Reader reader = new Reader();
//                    reader.setId(rs.getLong("id"));
//                    reader.setNumber(rs.getLong("rr"));
//                    reader.setFirstName(rs.getString("f_name"));
//                    reader.setLastName(rs.getString("l_name"));
//                    reader.setCardNumber(rs.getString("card_number"));
//                    reader.setStartMemberDate(rs.getDate("start_member_date"));
//                    reader.setPullMemberDate(rs.getDate("pull_member_date"));
//                    reader.setGender(rs.getString("gender"));
//                    reader.setStatus(rs.getString("status"));
//                    reader.setActivity(rs.getInt("activity"));
//                    readers.add(reader);
//                }
//            } else {
//                System.out.println("Connection errror!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;
    }

    @Override
    public List<Reader> getReaderByNationality(String nationality) throws Exception {
//        List<Reader> readers = new ArrayList<>();
//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "SELECT row_number() over(order by r.f_name asc) as rr, r.id,\n"
//                + "       R.F_NAME,\n"
//                + "       R.L_NAME,\n"
//                + "       R.ACTIVITY,\n"
//                + "       R.NATIONALITY,\n"
//                + "       R.CARD_NUMBER,\n"
//                + "       R.STATUS,\n"
//                + "       R.gender\n"
//                + "  FROM reader r\n"
//                + " WHERE active = 1 AND R.NATIONALITY = ?";
//        try {
//            c = DbHelper.getConnection();
//            if (c != null) {
//                ps = c.prepareStatement(sql);
//                ps.setString(1, nationality);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    Reader reader = new Reader();
//                    reader.setId(rs.getLong("id"));
//                    reader.setNumber(rs.getLong("rr"));
//                    reader.setFirstName(rs.getString("f_name"));
//                    reader.setLastName(rs.getString("l_name"));
//                    reader.setCardNumber(rs.getString("card_number"));
//                    reader.setStatus(rs.getString("status"));
//                    Nationality nationality1 = new Nationality();
//                    nationality1.setNationality(rs.getString(rs.getString("nationality")));
//                    reader.setNationality(nationality1);
//                    reader.setActivity(rs.getInt("activity"));
//                    readers.add(reader);
//                }
//            } else {
//                System.out.println("Connection error!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeConnection(c, ps, rs);
//        }
        return null;
    }

    @Override
    public List<Reader> readerBooksById(Long id) throws Exception {
        List<Reader> readers = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROW_number () over(order by title asc) as ddd, isbn, title,id \n"
                + "                  FROM (SELECT DISTINCT b.isbn, b.title,b.id\n"
                + "                          FROM order_in_lib oil\n"
                + "                               full JOIN reader r\n"
                + "                                  ON r.id = OIL.READER_ID\n"
                + "                               full JOIN order_in_home oih\n"
                + "                                  ON OIH.READER_ID = r.id\n"
                + "                               full JOIN book b\n"
                + "                                  ON b.id = OIL.BOOK_ID OR OIh.BOOK_ID = B.ID\n"
                + "                         WHERE r.active = 1 AND r.id = ?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reader reader = new Reader();
                    reader.setNumber(rs.getLong("ddd"));
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    reader.setBook(book);
                    readers.add(reader);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return readers;
    }

    @Override
    public List<Reader> searchReader(String keyword) throws Exception {
        List<Reader> readers = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.START_MEMBER_DATE DESC) AS R,\n"
                + "       R.ID,\n"
                + "       R.F_NAME,\n"
                + "       R.L_NAME,\n"
                + "       R.FATHER_NAME,\n"
                + "       R.DOB,\n"
                + "       R.GENDER,\n"
                + "       R.ADDITION_INFO,\n"
                + "       R.IDENTIFY_NUMBER,\n"
                + "       R.CARD_NUMBER,\n"
                + "       R.NATIONALITY,\n"
                + "       R.STATUS,\n"
                + "       R.START_MEMBER_DATE,\n"
                + "       R.PULL_MEMBER_DATE,\n"
                + "       R.PENALTY,\n"
                + "       R.ACTIVITY,\n"
                + "       RA.COUNTRY,\n"
                + "       RA.CITY,\n"
                + "       RA.STREET,\n"
                + "       RA.HOUSE_NUMBER,\n"
                + "       RA.HOME_NUMBER,\n"
                + "       RA.EMAIL,\n"
                + "       RP.PHONE_1,\n"
                + "       RP.PHONE_2,\n"
                + "       RP.HOME_PHONE\n"
                + "  FROM READER R\n"
                + "       LEFT JOIN READER_ADDRESS RA\n"
                + "          ON R.ID = RA.READER_ID\n"
                + "       LEFT JOIN READER_PHONE RP\n"
                + "          ON R.ID = RP.READER_ID\n"
                + " WHERE     R.ACTIVE = 1\n"
                + "       AND (   LOWER (f_name) LIKE (LOWER (?))\n"
                + "            OR LOWER (l_name) LIKE (LOWER (?))\n"
                + "            OR LOWER (father_name) LIKE (LOWER (?))\n"
                + "            OR card_number LIKE (?)\n"
                + "            OR LOWER (gender) LIKE (LOWER (?))\n"
                + "            OR LOWER (status) LIKE (LOWER (?))\n"
                + "            OR LOWER (nationality) LIKE (LOWER (?))\n"
                + "            OR LOWER (IDENTIFY_NUMBER) LIKE (LOWER (?))\n"
                + "            OR LOWER (EMAIL) LIKE (LOWER (?))\n"
                + "            OR START_MEMBER_DATE LIKE (?)\n"
                + "            OR PULL_MEMBER_DATE LIKE (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                pr.setString(1, "%" + keyword + "%");
                pr.setString(2, "%" + keyword + "%");
                pr.setString(3, "%" + keyword + "%");
                pr.setString(4, "%" + keyword + "%");
                pr.setString(5, "%" + keyword + "%");
                pr.setString(6, "%" + keyword + "%");
                pr.setString(7, "%" + keyword + "%");
                pr.setString(8, "%" + keyword + "%");
                pr.setString(9, "%" + keyword + "%");
                pr.setString(10, "%" + keyword + "%");
                pr.setString(11, "%" + keyword + "%");
                rs = pr.executeQuery();
                while (rs.next()) {
                    Reader reader = new Reader();
                    Nationality nationality = new Nationality();
                    reader.setId(rs.getLong("ID"));
                    reader.setNumber(rs.getLong("r"));
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("L_name"));
                    reader.setFatherName(rs.getString("Father_name"));
                    reader.setDob(rs.getDate("Dob"));
                    reader.setGender(rs.getString("Gender"));
                    nationality.setNationality(rs.getString("nationality"));
                    reader.setNationality(nationality);
                    reader.setIdentifyNumber(rs.getString("IDENTIFY_NUMBER"));
                    reader.setCardNumber(rs.getString("CARD_NUMBER"));
                    reader.setStartMemberDate(rs.getDate("START_MEMBER_DATE"));
                    reader.setPullMemberDate(rs.getDate("pull_MEMBER_DATE"));
                    reader.setPenalty(rs.getFloat("PENALTY"));
                    reader.setActivity(rs.getInt("ACTIVITY"));
                    reader.setCountryOld(rs.getString("Country"));
                    reader.setCity(rs.getString("City"));
//                    reader.setStatus(rs.getString("status"));
                    reader.setStreet(rs.getString("street"));
                    reader.setHouseNumber(rs.getString("house_number"));
                    reader.setHomeNumber(rs.getString("home_number"));
                    reader.seteMail(rs.getString("Email"));
                    reader.setPhone1(rs.getString("Phone_1"));
                    reader.setPhone2(rs.getString("phone_2"));
                    reader.setHomePhone(rs.getString("home_phone"));
                    reader.setAdditionInfo(rs.getString("ADDITION_INFO"));
                    readers.add(reader);
                }
            } else {
                System.out.println("Connection error!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return readers;
    }

    @Override
    public List<Reader> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        List<Reader> readers = new ArrayList<>();
        Connection c = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT ROW_NUMBER () OVER (ORDER BY R.START_MEMBER_DATE DESC) AS R,\n" +
                "       R.ID,\n" +
                "       R.F_NAME,\n" +
                "       R.L_NAME,\n" +
                "       R.FATHER_NAME,\n" +
                "       R.DOB,\n" +
                "       R.GENDER,\n" +
                "       R.ADDITION_INFO,\n" +
                "       R.IDENTIFY_NUMBER,\n" +
                "       R.CARD_NUMBER,\n" +
                "       s.STATUS,\n" +
                "       R.START_MEMBER_DATE,\n" +
                "       R.PULL_MEMBER_DATE,\n" +
                "       R.PENALTY,\n" +
                "       R.ACTIVITY,\n" +
                "       C.id AS cId,\n" +
                "       C.COUNTRY_NAME,\n" +
                "       RA.CITY,\n" +
                "       RA.STREET,\n" +
                "       n.id AS nId,\n" +
                "       N.NATIONALITY,\n" +
                "       RA.HOUSE_NUMBER,\n" +
                "       RA.HOME_NUMBER,\n" +
                "       RA.EMAIL,\n" +
                "       RP.PHONE_1,\n" +
                "       RP.HOME_PHONE,\n" +
                "       s.status\n" +
                "  FROM READER R\n" +
                "       LEFT JOIN READER_ADDRESS RA\n" +
                "          ON R.ID = RA.READER_ID\n" +
                "       LEFT JOIN READER_PHONE RP\n" +
                "          ON R.ID = RP.READER_ID\n" +
                "       LEFT JOIN country c\n" +
                "          ON c.id = RA.COUNTRY\n" +
                "       LEFT JOIN nationality n\n" +
                "          ON n.id = R.NATIONALITY\n" +
                "       LEFT JOIN status s\n" +
                "          ON s.id = R.STATUSID\n" +
                " WHERE R.ACTIVE = 1   ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (advanceSearch.getCase1() != null && !advanceSearch.getCase1().isEmpty()) {
                    sql += "  and LOWER (r.f_name) LIKE (LOWER ('%" + advanceSearch.getCase1() + "%'))";
                }
                if (advanceSearch.getCase2() != null && !advanceSearch.getCase2().isEmpty()) {
                    sql += " AND LOWER (R.l_NAME) LIKE (LOWER ('%" + advanceSearch.getCase2() + "%'))";
                }
                if (!advanceSearch.getCase3().equals("0")) {
                    sql += " AND  R.STATUSId=" + advanceSearch.getCase3();
                }
                if (!advanceSearch.getCase4().equals("0")) {
                    sql += " AND  lower( R.gender) like (lower('%" + advanceSearch.getCase4() + "%'))";
                }
                if (advanceSearch.getCase5() != null && !advanceSearch.getCase5().isEmpty()) {
                    sql += " AND  R.ACTIVITY=" + advanceSearch.getCase5();
                }
                if (advanceSearch.getCase6() != null && !advanceSearch.getCase6().isEmpty()) {
                    sql +=" AND  R.START_MEMBER_DATE>=TO_DATE('" + new java.sql.Date(df.parse(advanceSearch.getCase6()).getTime()) + "','YYYY-MM-DD')";
                }
                if (advanceSearch.getCase7() != null && !advanceSearch.getCase7().isEmpty()) {
                    sql +=" AND R.START_MEMBER_DATE<=TO_DATE('" + new java.sql.Date(df.parse(advanceSearch.getCase7()).getTime()) + "','YYYY-MM-DD')";
                } if (advanceSearch.getDate() != null && !advanceSearch.getDate().isEmpty()) {
                    sql +=" AND R.dob<=TO_DATE('" + new java.sql.Date(df.parse(advanceSearch.getDate()).getTime()) + "','YYYY-MM-DD')";
                }
                if (advanceSearch.getId1() != null) {
                    sql +=" AND  r.NATIONALITY=" + advanceSearch.getId1();
                }
                if (advanceSearch.getId2() != null) {
                    sql += " AND  ra.country=" + advanceSearch.getId2();
                }
                pr = c.prepareStatement(sql);
                System.out.println(sql);
                rs = pr.executeQuery();
                while (rs.next()) {
                    Reader reader = new Reader();
                    Country country = new Country();
                    Nationality nationality = new Nationality();
                    Status status = new Status();
                    reader.setId(rs.getLong("ID"));
                    nationality.setNationality(rs.getString("NATIONALITY"));
                    reader.setNationality(nationality);
                    reader.setNumber(rs.getLong("r"));
                    reader.setFirstName(rs.getString("f_name"));
                    reader.setLastName(rs.getString("L_name"));
                    reader.setFatherName(rs.getString("Father_name"));
                    reader.setDob(rs.getDate("Dob"));
                    reader.setGender(rs.getString("Gender"));
                    reader.setStartMemberDate(rs.getDate("START_MEMBER_DATE"));
                    reader.setActivity(rs.getInt("ACTIVITY"));
                    country.setCountryName(rs.getString("country_name"));
                    reader.setCountry(country);
                    reader.setCity(rs.getString("City"));
                    status.setStatus(rs.getString("status"));
                    reader.setStatus(status);
                    reader.seteMail(rs.getString("Email"));
                    reader.setPhone1(rs.getString("Phone_1"));
                    reader.setHomePhone(rs.getString("home_phone"));
                    reader.setAdditionInfo(rs.getString("ADDITION_INFO"));
                    readers.add(reader);
                }
            } else {
                System.out.println("Connection error!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return readers;
    }

}
