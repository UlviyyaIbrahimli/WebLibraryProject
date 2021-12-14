package project.library.desktop.dao;

import oracle.jdbc.OracleTypes;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IAuthor;
import project.library.desktop.model.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Ulviyya Ibrahimli
 */
public class AuthorDao implements IAuthor {

    @Override
    public List<Author> getAuthorList() throws Exception {
        List<Author> authorList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call LIBRARY_DESKTOP.getList_pack.getAuthorListForWeb(?)}";
        ResultSet rs1 = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs1 = (ResultSet) cs.getObject(1);
                while (rs1.next() ) {
                    Author author = new Author();
                    author.setId(rs1.getLong("Id"));
                    author.setNumber(rs1.getLong("r"));
                    author.setFirstName(rs1.getString("F_NAME"));
                    author.setLastName(rs1.getString("L_NAME"));
                    author.setFatherName(rs1.getString("FATHER_NAME"));
                    Nationality nationality = new Nationality();
                    nationality.setNationality(rs1.getString("NATIONALITY"));
                    author.setNationality(nationality);
                    author.setDob(rs1.getDate("Dob"));
                    author.setDeadDate(rs1.getDate("DEAD_DATE"));
                    author.setGender(rs1.getString("Gender"));
                    author.setReward(rs1.getString("Reward"));
                    author.setAuthorInfo(rs1.getString("AUTHOR_INFO"));
                    Book book = new Book();
                    book.setTitle(rs1.getString("book"));
                    author.setBook(book);
                    authorList.add(author);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, rs1);
        }

        return authorList;
    }

    @Override
    public boolean addAuthor(Author author) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (author.getNationality() != null) {
                    if (author.getDob() != null && author.getDeadDate() != null) {
                        String sql1 =
                                "{call LIBRARY_DESKTOP.add_pack.add_author1 (?,?,?,?,?,?,?,?,?)}";
                        cs = c.prepareCall(sql1);
                        cs.setString(1, author.getFirstName());
                        cs.setString(2, author.getLastName());
                        cs.setString(3, author.getFatherName());
                        cs.setString(4, author.getGender());
                        cs.setDate(5, new java.sql.Date(author.getDob().getTime()));
                        cs.setDate(6, new java.sql.Date(author.getDeadDate().getTime()));
                        cs.setLong(7, author.getNationality().getId());
                        cs.setString(8, author.getReward());
                        cs.setString(9, author.getAuthorInfo());
                        cs.execute();
                        result = true;
                    } else if (author.getDob() != null && author.getDeadDate() == null) {
                        String sql1 = "{call LIBRARY_DESKTOP.add_pack.add_author2 (?,?,?,?,?,?,?,?)}";
                        ps = c.prepareCall(sql1);
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setDate(5, new java.sql.Date(author.getDob().getTime()));
                        ps.setLong(6, author.getNationality().getId());
                        ps.setString(7, author.getReward());
                        ps.setString(8, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    } else if (author.getDob() == null && author.getDeadDate() != null) {
                        String sql1 =
                                "{call LIBRARY_DESKTOP.add_pack.add_author3 (?,?,?,?,?,?,?,?)}";
                        ps = c.prepareCall(sql1);
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setDate(5, new java.sql.Date(author.getDeadDate().getTime()));
                        ps.setLong(6, author.getNationality().getId());
                        ps.setString(7, author.getReward());
                        ps.setString(8, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    } else if (author.getDob() == null && author.getDeadDate() == null) {
                        String sql1 =
                                "{call LIBRARY_DESKTOP.add_pack.add_author4 (?,?,?,?,?,?,?)}";
                        ps = c.prepareCall(sql1);// burada nece nationalityni daxil etnmemek olar
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setLong(5, author.getNationality().getId());
                        ps.setString(6, author.getReward());
                        ps.setString(7, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    }
                } else {
                    if (author.getDob() != null && author.getDeadDate() != null) {
                        String sql1 =
                                "INSERT INTO AUTHOR (ID,F_NAME,l_NAME,FATHER_NAME,GENDER,DOB,DEAD_DATE,REWARD,AUTHOR_INFO)"
                                        + "values(author_seq.nextval,?,?,?,?,?,?,?,?)";
                        ps = c.prepareCall(sql1);
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setDate(5, new java.sql.Date(author.getDob().getTime()));
                        ps.setDate(6, new java.sql.Date(author.getDeadDate().getTime()));
                        ps.setString(7, author.getReward());
                        ps.setString(8, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    } else if (author.getDob() != null && author.getDeadDate() == null) {
                        String sql1 =
                                "INSERT INTO AUTHOR (ID,F_NAME,l_NAME,FATHER_NAME,GENDER,DOB,REWARD,AUTHOR_INFO)"
                                        + "values(author_seq.nextval,?,?,?,?,?,?,?)";
                        ps = c.prepareCall(sql1);
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setDate(5, new java.sql.Date(author.getDob().getTime()));
                        ps.setString(6, author.getReward());
                        ps.setString(7, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    } else if (author.getDob() == null && author.getDeadDate() != null) {
                        String sql1 =
                                "INSERT INTO AUTHOR (ID,F_NAME,l_NAME,FATHER_NAME,GENDER,DEAD_DATE,REWARD,AUTHOR_INFO)"
                                        + "values(author_seq.nextval,?,?,?,?,?,?,?)";
                        ps = c.prepareCall(sql1);
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setDate(5, new java.sql.Date(author.getDeadDate().getTime()));
                        ps.setString(6, author.getReward());
                        ps.setString(7, author.getAuthorInfo());
                        ps.execute();
                        result = true;
                    } else if (author.getDob() == null && author.getDeadDate() == null) {
                        String sql1 =
                                "INSERT INTO AUTHOR (ID,F_NAME,l_NAME,FATHER_NAME,GENDER,REWARD,AUTHOR_INFO)"
                                        + " values(author_seq.nextval,?,?,?,?,?,?)";
                        ps = c.prepareCall(sql1);// burada nece nationalityni daxil etnmemek olar
                        ps.setString(1, author.getFirstName());
                        ps.setString(2, author.getLastName());
                        ps.setString(3, author.getFatherName());
                        ps.setString(4, author.getGender());
                        ps.setString(5, author.getReward());
                        ps.setString(6, author.getAuthorInfo());
                        ps.execute();
                    }
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean updateAuthor(Long id, Author author) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (author.getDob() != null && author.getDeadDate() != null) {
                    String sql11 =
                            "{call LIBRARY_DESKTOP.update_pack.update_author1(?,?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql11);
                    cs.setString(1, author.getFirstName());
                    cs.setString(2, author.getLastName());
                    cs.setString(3, author.getFatherName());
                    cs.setString(4, author.getGender());
                    cs.setDate(5, new java.sql.Date(author.getDob().getTime()));
                    cs.setDate(6, new java.sql.Date(author.getDeadDate().getTime()));
                    cs.setLong(7, author.getNationality().getId());
                    cs.setString(8, author.getReward());
                    cs.setString(9, author.getAuthorInfo());
                     cs.setLong(10,id);
                    cs.execute();
                    result = true;
                } else if (author.getDob() != null && author.getDeadDate() == null) {
                    String sql1 =
                            "{call LIBRARY_DESKTOP.update_pack.update_author2(?,?,?,?,?,?,?,?,?)}";
                    ps = c.prepareCall(sql1);
                    ps.setString(1, author.getFirstName());
                    ps.setString(2, author.getLastName());
                    ps.setString(3, author.getFatherName());
                    ps.setString(4, author.getGender());
                    ps.setDate(5, new java.sql.Date(author.getDob().getTime()));
                    ps.setLong(6, author.getNationality().getId());
                    ps.setString(7, author.getReward());
                    ps.setString(8, author.getAuthorInfo());
                    ps.setLong(9, id);
                    ps.execute();
                    result = true;
                } else if (author.getDob() == null && author.getDeadDate() != null) {
                    String sql1 =
                            "{call  LIBRARY_DESKTOP.update_pack.update_author3(?,?,?,?,?,?,?,?,?)}";
                    ps = c.prepareCall(sql1);
                    ps.setString(1, author.getFirstName());
                    ps.setString(2, author.getLastName());
                    ps.setString(3, author.getFatherName());
                    ps.setString(4, author.getGender());
                    ps.setDate(5, new java.sql.Date(author.getDeadDate().getTime()));
                    ps.setLong(6, author.getNationality().getId());
                    ps.setString(7, author.getReward());
                    ps.setString(8, author.getAuthorInfo());
                    ps.setLong(9, id);
                    ps.execute();
                    result = true;
                } else if (author.getDob() == null && author.getDeadDate() == null) {
                    String sql1 =
                            "{call LIBRARY_DESKTOP.update_pack.update_author4(?,?,?,?,?,?,?,?)}";
                    ps = c.prepareCall(sql1);
                    ps.setString(1, author.getFirstName());
                    ps.setString(2, author.getLastName());
                    ps.setString(3, author.getFatherName());
                    ps.setString(4, author.getGender());
                    ps.setLong(5, author.getNationality().getId());
                    ps.setString(6, author.getReward());
                    ps.setString(7, author.getAuthorInfo());
                    ps.setLong(8, id);
                    ps.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("dd  " + result);
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public Author getAuthorById(Long id) throws Exception {
        Author author = new Author();
        Connection c = null;
        CallableStatement cs = null;
//        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "{?=call LIBRARY_DESKTOP.getAuthorByIdForWeb (?)}";
//        String sql = "SELECT A.ID,\n" +
//                "       A.F_NAME,\n" +
//                "       A.L_NAME,\n" +
//                "       A.FATHER_NAME,\n" +
//                "       A.DOB,\n" +
//                "       A.DEAD_DATE,\n" +
//                "       A.GENDER,\n" +
//                "       n.NATIONALITY,\n" +
//                "       n.id AS nat_Id,\n" +
//                "       A.AUTHOR_INFO,\n" +
//                "       A.REWARD,\n" +
//                "       S.id,\n" +
//                "       S.SUBJECT_NAME,\n" +
//                "       B.TITLE,\n" +
//                "       B.id\n" +
//                "  FROM book_author ba\n" +
//                "       FULL JOIN author a\n" +
//                "          ON A.ID = BA.AUTHOR_ID\n" +
//                "       LEFT JOIN book b\n" +
//                "          ON B.ID = BA.BOOK_ID\n" +
//                "       FULL JOIN author_subject ass\n" +
//                "          ON ass.author_id = a.id\n" +
//                "       LEFT JOIN subjects s\n" +
//                "          ON S.ID = ass.subject_id\n" +
//                "       LEFT JOIN nationality n\n" +
//                "          ON n.id = A.NATIONALITY\n" +
//                " WHERE a.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
//                ps = c.prepareStatement(sql);
//                ps.setLong(1, id);
//                ps.execute();
                cs.setLong(2, id);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                if (rs.next()) {
                    author.setFirstName(rs.getString("F_NAME"));
                    author.setLastName(rs.getString("L_name"));
                    author.setFatherName(rs.getString("FATHER_NAME"));
                    Nationality nationality = new Nationality();
                    nationality.setId(rs.getLong("nat_id"));
                    nationality.setNationality(rs.getString("NATIONALITY"));
                    author.setNationality(nationality);
                    author.setDob(rs.getDate("Dob"));
                    author.setDeadDate(rs.getDate("DEAD_DATE"));
                    author.setGender(rs.getString("Gender"));
                    author.setReward(rs.getString("Reward"));
                    author.setAuthorInfo(rs.getString("AUTHOR_INFO"));
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(rs.getString("Subject_name"));
                    subjects.setId(rs.getLong("id"));
                    author.setSubjects(subjects);
                    Book book = new Book();
                    book.setTitle(rs.getString("Title"));
                    book.setId(rs.getLong("id"));
                    author.setBook(book);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            Util.closeConnection(c, ps, rs);
            rs.close();
            cs.close();
            c.close();
        }
        return author;
    }

    @Override
    public boolean deletesubjectOfAuthor(Long authorid) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call LIBRARY_DESKTOP.delete_subject_of_author(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, authorid);
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteAuthor(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs=null;
        String sql="{call LIBRARY_DESKTOP.delete_author(?) }";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs=c.prepareCall(sql);
                cs.setLong(1,id);
                cs.execute();

                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean addAuthorBook(Long authorId, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;

        String sql = "insert into book_author(id,book_id,author_id)values(book_author_seq.nextval,?,?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {

                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.setLong(2, authorId);
                ps.addBatch();
                ps.executeBatch();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Author> getAuthorBySubject(String name) throws Exception {
        List<Author> getAuthotList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
//        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "{?=call LIBRARY_DESKTOP.getAuthorBySubjectForWeb(?)}";
//        String sql = "SELECT A.ID,\n"
//                + "       A.F_NAME,\n"
//                + "       A.L_NAME,\n"
//                + "       A.FATHER_NAME\n"
//                + "  FROM AUTHOR_SUBJECT ASS\n"
//                + "       INNER JOIN SUBJECTS S\n"
//                + "          ON S.ID = ASS.SUBJECT_ID\n"
//                + "       INNER JOIN AUTHOR A\n"
//                + "          ON A.ID = ASS.AUTHOR_ID\n"
//                + " WHERE A.ACTIVE = 1 AND S.subject_name = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
//                ps = c.prepareStatement(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
//                ps.setString(1, name);
                cs.setString(2, name);
                cs.execute();
//                rs = ps.executeQuery();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getLong("id"));
                    author.setFirstName(rs.getString("F_name"));
                    author.setLastName(rs.getString("L_name"));
                    author.setFatherName(rs.getString("Father_name"));
                    getAuthotList.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, rs);
        }

        return getAuthotList;
    }

    @Override
    public boolean addAuthorBooks(Long book, Long author) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql2 = "insert into book_author(id,book_id,author_id)values(book_author_seq.nextval,?,?)";
        try {

            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql2);
                ps.setLong(1, book);
                ps.setLong(2, author);
                ps.addBatch();
                ps.executeBatch();

                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public Long addbookId(Author author) throws Exception {
        Long result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql2 = "Select  max(id) as maxid from   author";// bunun islemesi ucun cevdelde en azi 1 row olmalidi!!

        try {

            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getLong("maxid");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean addListSubject(Long authorId, Long subjectId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIBRARY_DESKTOP.add_author_subject_for_web(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, authorId);
                cs.setLong(2, subjectId);
                cs.addBatch();
                cs.executeBatch();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Author> booksOfAuthor(Long authorId) throws Exception {
        List<Author> getBooksOfAuthor = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{? = call  LIBRARY_DESKTOP.booksOfAuthorForWeb(?)}";
        ResultSet rs = null;
//        String sql = "SELECT b.id,\n" +
//                "       b.isbn,\n" +
//                "       B.TITLE,\n" +
//                "       B.PUBLISHER,\n" +
//                "       B.BOOK_STAR,\n" +
//                "       B.PAGE,\n" +
//                "       B.B_EDITION,\n" +
//                "       BL.LANGUAGE,\n" +
//                "       A.F_NAME,\n" +
//                "       A.L_NAME,\n" +
//                "       b.RELEASE_DATE,\n" +
//                "       b.ABSTRACT,\n" +
//                "       A.FATHER_NAME\n" +
//                "  FROM book_author ba\n" +
//                "       INNER JOIN book b\n" +
//                "          ON b.id = BA.BOOK_ID\n" +
//                "       INNER JOIN author a\n" +
//                "          ON a.id = BA.AUTHOR_ID\n" +
//                "       FULL JOIN book_language bl\n" +
//                "          ON bl.id = B.LANGUAGE_ID\n" +
//                " WHERE a.id = ? AND ba.active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(2, authorId);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Author author = new Author();
                    author.setFirstName(rs.getString("F_NAME"));
                    author.setLastName(rs.getString("L_NAME"));
                    author.setFatherName(rs.getString("FATHER_NAME"));
                    Book book = new Book();
                    Language language = new Language();
                    book.setbAbstract(rs.getString("ABSTRACT"));
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    book.setLanguage(language);
                    book.setPage(rs.getLong("page"));
                    book.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setEdition(rs.getString("B_EDITION"));
                    book.setId(rs.getLong("id"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    author.setBook(book);
                    getBooksOfAuthor.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            rs.close();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return getBooksOfAuthor;
    }

    @Override
    public List<Author> bookNoAuthor(Long id) throws Exception {
        List<Author> getBooksOfAuthor = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT distinct b.id, B.TITLE,b.isbn from\n"
                + "    book_author ba     \n"
                + "       INNER JOIN book b\n"
                + "          ON b.id = BA.BOOK_ID\n"
                + "       INNER JOIN author a\n"
                + "          ON a.id = BA.AUTHOR_ID\n"
                + " WHERE a.id != ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    author.setBook(book);
                    getBooksOfAuthor.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return getBooksOfAuthor;
    }

    @Override
    public List<Author> subjectofAuthor(Long id) throws Exception {
        List<Author> getBooksOfAuthor = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{?=call  LIBRARY_DESKTOP.getSubjectOfAuthor(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs=c.prepareCall(sql);
                cs.registerOutParameter(1,OracleTypes.CURSOR);
                cs.setLong(2,id);
                cs.execute();
                rs= (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Author author = new Author();
                    author.setNumber(rs.getLong("r"));
                    Subjects subjects = new Subjects();
                    subjects.setId(rs.getLong("id"));
                   author.setFirstName(rs.getString("f_name"));
                   author.setLastName(rs.getString("l_name"));
                   author.setFatherName(rs.getString("father_name"));
                    subjects.setSubjectName(rs.getString("SUBJECT_NAME"));
                    author.setSubjects(subjects);
                    getBooksOfAuthor.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
                       cs.close();
            rs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return getBooksOfAuthor;
    }

    @Override
    public boolean updateBookOfAuthor(Long bookId, Long authorId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql2 = "update  book_author set book_id=? where author_id=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {

                ps = c.prepareStatement(sql2);
                ps.setLong(1, bookId);
                ps.setLong(2, authorId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;

    }

    @Override
    public boolean updateSubjectOfAuthor(Long subjectId, Long authorId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs=null;
        String sql= "{call  LIBRARY_DESKTOP.update_Subject_Of_Author(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs=c.prepareCall(sql);
                cs.setLong(1, subjectId);
                cs.setLong(2, authorId);
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Long> getBookId(Long authorId) throws Exception {
        List<Long> results = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql2 = "{?=call LIBRARY_DESKTOP.getBookIdByAuthorId(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql2);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                cs.setLong(1, authorId);
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Long result = rs.getLong("BOOK_ID");
                    results.add(result);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            rs.close();
            cs.close();
            c.close();
//            Util.closeConnection(c, ps, null);
        }
        return results;
    }

    @Override
    public boolean deleteBookOfAuthor(Long authorId, Long bookId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql2 = " update book_author ba set active=0 where BA.AUTHOR_ID=? and BA.BOOK_ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql2);
                ps.setLong(1, authorId);
                ps.setLong(2, bookId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteSubject(Long authorId, Long subjectId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql2 = " update  author_subject ass set active=0 where ASS.AUTHOR_ID=? and ASS.SUBJECT_ID=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql2);
                ps.setLong(1, authorId);
                ps.setLong(2, subjectId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Author> getSubjectByAuthor(Long subjectId) throws Exception {
        List<Author> getAuthotList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,\n"
                + "       A.ID,\n"
                + "       A.F_NAME,\n"
                + "       A.L_NAME,\n"
                + "       A.Father_NAME,\n"
                + "       a.SUBJECT_NAME\n"
                + "  FROM (  SELECT DISTINCT A.ID,\n"
                + "                          A.F_NAME,\n"
                + "                          A.L_NAME,\n"
                + "                          A.Father_NAME,\n"
                + "                          S.SUBJECT_NAME\n"
                + "            FROM author_subject ass\n"
                + "                 INNER JOIN subjects s\n"
                + "                    ON s.id = ASS.SUBJECT_ID\n"
                + "                 INNER JOIN author a\n"
                + "                    ON a.id = ASS.AUTHOR_ID\n"
                + "           WHERE ASS.SUBJECT_ID = ? AND a.active = 1\n"
                + "        ORDER BY s.SUBJECT_NAME desc) a";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getLong("id"));
                    author.setNumber(rs.getLong("r"));
                    author.setFirstName(rs.getString("F_name"));
                    author.setLastName(rs.getString("L_name"));
                    author.setFatherName(rs.getString("Father_name"));
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(rs.getString("SUBJECT_NAME"));
                    author.setSubjects(subjects);
                    getAuthotList.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return getAuthotList;
    }

    @Override
    public boolean deleteAuthorForSubject(Long authorId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update  author_subject  set active=0 where subject_id=?  ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareCall(sql);
                ps.setLong(1, authorId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Author> booksOfAuthorActiveZero(Long authorId) throws Exception {
        List<Author> getBooksOfAuthor = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,\n"
                + "       d.id,\n"
                + "       d.isbn,\n"
                + "       d.TITLE,\n"
                + "       d.f_name,\n"
                + "       d.l_name\n"
                + "  FROM (SELECT DISTINCT b.id,\n"
                + "                        b.isbn,\n"
                + "                        B.TITLE,\n"
                + "                        a.f_name,\n"
                + "                        a.l_name\n"
                + "          FROM book_author ba\n"
                + "               INNER JOIN book b\n"
                + "                  ON b.id = BA.BOOK_ID\n"
                + "               INNER JOIN author a\n"
                + "                  ON a.id = BA.AUTHOR_ID\n"
                + "         WHERE a.id = ? AND ba.active = 0) d";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, authorId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setNumber(rs.getLong("r"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    author.setFirstName(rs.getString("f_name"));
                    author.setLastName(rs.getString("l_name"));
                    author.setBook(book);
                    getBooksOfAuthor.add(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, null);
        }
        return getBooksOfAuthor;
    }

    @Override
    public int getAuthorSubjectCount(Long authorId) throws Exception {
        int count = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(ass.id) as sub_count from author_subject ass where ass.active=1 and ASS.AUTHOR_ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, authorId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("sub_count");
                }
            } else {
                System.out.println("Connection Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return count;
    }

    @Override
    public List<Author> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        List<Author> authorList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT ROW_NUMBER () OVER (ORDER BY A.f_NAME ASC) AS r,\n" +
                "         A.ID,\n" +
                "         A.F_NAME,\n" +
                "         A.L_NAME,\n" +
                "         A.FATHER_NAME,\n" +
                "         A.DOB,\n" +
                "         A.DEAD_DATE,\n" +
                "         A.GENDER,\n" +
                "         n.NATIONALITY,\n" +
                "         A.AUTHOR_INFO,\n" +
                "         A.REWARD,\n" +
                "         LISTAGG (TO_CHAR (S.SUBJECT_NAME), ', ')\n" +
                "            WITHIN GROUP (ORDER BY TO_CHAR (S.SUBJECT_NAME))\n" +
                "            AS SUBJECT_NAME,\n" +
                "         LISTAGG (TO_CHAR (B.TITLE), ', ')\n" +
                "            WITHIN GROUP (ORDER BY TO_CHAR (b.title))\n" +
                "            AS book\n" +
                "    FROM book_author ba\n" +
                "         RIGHT JOIN author a\n" +
                "            ON A.ID = BA.AUTHOR_ID\n" +
                "         LEFT JOIN book b\n" +
                "            ON B.ID = BA.BOOK_ID\n" +
                "         LEFT JOIN author_subject ass\n" +
                "            ON a.ID = ass.author_id\n" +
                "         LEFT JOIN subjects s\n" +
                "            ON s.id = ass.subject_id\n" +
                "         LEFT JOIN nationality n\n" +
                "            ON n.id = A.NATIONALITY\n" +
                "   WHERE A.ACTIVE = 1 AND ass.ACTIVE = 1 ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (advanceSearch.getId1() != null) {
                    sql += "  and  s.id=  " + advanceSearch.getId1();
                }
                if (advanceSearch.getId2() != null) {
                    sql += " and n.id= " + advanceSearch.getId2();
                }
                if (advanceSearch.getCase1() != null && !advanceSearch.getCase1().isEmpty()) {
                    sql += "  and LOWER (a.f_name) LIKE (LOWER ('%" + advanceSearch.getCase1() + "%'))";
                }
                if (advanceSearch.getCase2() != null && !advanceSearch.getCase2().isEmpty()) {
                    sql += "  and LOWER (a.l_name) LIKE (LOWER ('%" + advanceSearch.getCase2() + "%'))";
                }
                sql += "  GROUP BY A.ID,\n" +
                        "         A.F_NAME,\n" +
                        "         A.L_NAME,\n" +
                        "         A.FATHER_NAME,\n" +
                        "         A.DOB,\n" +
                        "         N.NATIONALITY,\n" +
                        "         A.DEAD_DATE,\n" +
                        "         A.GENDER,\n" +
                        "         A.NATIONALITY,\n" +
                        "         A.AUTHOR_INFO,\n" +
                        "         A.REWARD";
                ps = c.prepareStatement(sql);
                System.out.println("sql   " + sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getLong("Id"));
                    author.setNumber(rs.getLong("r"));
                    author.setFirstName(rs.getString("F_NAME"));
                    author.setLastName(rs.getString("l_name"));
                    author.setFatherName(rs.getString("FATHER_NAME"));
                    Nationality nationality = new Nationality();
                    nationality.setNationality(rs.getString("NATIONALITY"));
                    author.setNationality(nationality);
                    author.setDob(rs.getDate("Dob"));
                    author.setDeadDate(rs.getDate("DEAD_DATE"));
                    author.setGender(rs.getString("Gender"));
                    author.setReward(rs.getString("Reward"));
                    author.setAuthorInfo(rs.getString("AUTHOR_INFO"));
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(rs.getString("Subject_name"));
                    author.setSubjects(subjects);
                    Book book = new Book();
                    book.setTitle(rs.getString("book"));
                    author.setBook(book);
                    authorList.add(author);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return authorList;
    }

}
