package project.library.desktop.dao;

import oracle.jdbc.OracleTypes;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IBook;
import project.library.desktop.model.*;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Ulviyye Ibrahimli
 */
public class BookDao implements IBook {

    @Override
    public List<Book> allBookList(int choose) throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs1 = null;
        String sql = "{call LIBRARY_DESKTOP.getList_pack.getBookListForWeb(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    cs = c.prepareCall(sql);
                    cs.registerOutParameter(1, OracleTypes.CURSOR);
                    cs.execute();
                    rs1 = (ResultSet) cs.getObject(1);
                    while (rs1.next()) {
                        Book book = new Book();
                        Author author = new Author();
                        Language language = new Language();
                        Subjects subjects = new Subjects();
                        book.setId(rs1.getLong("ID"));
                        book.setNumber(rs1.getLong("r"));
                        book.setImage(rs1.getString("IMAGE_SOURCE"));
                        book.setIsbn(rs1.getLong("ISBN"));
                        book.setTitle(rs1.getString("TITLE"));
                        book.setPage(rs1.getLong("PAGE"));
                        book.setPublisher(rs1.getString("PUBLISHER"));
                        book.setEdition(rs1.getString("B_EDITION"));
                        book.setStar(rs1.getString("BOOK_STAR"));
                        language.setBookLanguage(rs1.getString("language"));
                        book.setLanguage(language);
                        book.setbAbstract(rs1.getString("ABSTRACT"));
                        book.setReleaseDate(rs1.getDate("RELEASE_DATE"));
                        author.setFirstName(rs1.getString("authors"));
                        book.setAuthor(author);
                        bookList.add(book);
                    }
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, rs1);
        }
        return bookList;
    }

    @Override
    public boolean addBook(Book book) throws Exception {
        boolean result = false;
        Connection c = null;// ayri yazmaq
        PreparedStatement pr = null;

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                c.setAutoCommit(false);
                if (book.getReleaseDate() != null) {
                    String sql1
                            =
                            "Insert into book(id,isbn,title,page,PUBLISHER,language_id,B_EDITION,BOOK_STAR,RELEASE_DATE,LIMIT_DAY,LIMIT_TIME,ABSTRACT,SHELF_ID,room_id,case_id,all_count) VALUES(BOOK_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setDate(8, new java.sql.Date(book.getReleaseDate().getTime()));
                    pr.setInt(9, book.getLimitDay());
                    pr.setInt(10, book.getLimitTime());
                    pr.setString(11, book.getbAbstract());
                    pr.setLong(12, book.getBookShelf().getId());
                    pr.setLong(13, book.getBookRoom().getId());
                    pr.setLong(14, book.getbCase().getId());
                    pr.setInt(15, book.getAllBookCount());
                    pr.execute();
                    result = true;
                } else if (book.getReleaseDate() == null) {
                    String sql1
                            =
                            "Insert into book(id,isbn,title,page,PUBLISHER,language_id,B_EDITION,BOOK_STAR,LIMIT_DAY,LIMIT_TIME,ABSTRACT,SHELF_ID,room_id,case_id,all_count) VALUES(BOOK_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setInt(8, book.getLimitDay());
                    pr.setInt(9, book.getLimitTime());
                    pr.setString(10, book.getbAbstract());
                    pr.setLong(11, book.getBookShelf().getId());
                    pr.setLong(12, book.getBookRoom().getId());
                    pr.setLong(13, book.getbCase().getId());
                    pr.setInt(14, book.getAllBookCount());
                    pr.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!");
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

    public boolean addBookWeb(Book book) throws Exception {
        boolean result = false;
        Connection c = null;// ayri yazmaq
        PreparedStatement pr = null;
        CallableStatement cs = null;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                c.setAutoCommit(false);
                if (book.getReleaseDate() != null) {
                    System.out.println("star  " + book.getStar());
                    String sql = "{ call LIBRARY_DESKTOP.add_pack.add_book1(?,?,?,?,?,?,?,?,?)}";
                    cs = c.prepareCall(sql);
                    cs.setLong(1, book.getIsbn());
                    cs.setString(2, book.getTitle());
                    cs.setString(3, book.getPublisher());
                    cs.setLong(4, book.getPage());
                    cs.setString(5, book.getEdition());
                    cs.setString(6, book.getStar());
                    cs.setString(7, book.getbAbstract());
                    cs.setLong(8, book.getLanguage().getId());
                    cs.setDate(9, new java.sql.Date(book.getReleaseDate().getTime()));
                    cs.execute();
                    result = true;
                } else if (book.getReleaseDate() == null) {
                    String sql = "{ call LIBRARY_DESKTOP.add_pack.add_book2(?,?,?,?,?,?,?,?)}";
                    pr = c.prepareStatement(sql);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setString(8, book.getbAbstract());
                    pr.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!");
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


    // m:m de nece olur?
    @Override
    public Book getBookById(Long bookId) throws Exception {
        Book book = new Book();
        Subjects subjects = new Subjects();
        Author author = new Author();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT B.ID,\n"
                + "                B.ISBN,\n"
                + "                B.TITLE,\n"
                + "                B.B_EDITION,\n"
                + "                B.BOOK_STAR,\n"
                + "                B.PUBLISHER,\n"
                + "                B.PAGE,\n"
                + "                B.abstract,\n"
                + "                BC.B_CASE,\n"
                + "                BC.id AS bcId,\n"
                + "                B.ALL_COUNT,\n"
                + "                B.RELEASE_DATE,\n"
                + "                B.limit_time,\n"
                + "                Bss.empity_capacity,\n"
                + "                B.limit_day,\n"
                + "                Bl.id as l_id,\n"
                + "                bS.subject_id,\n"
                + "                B.SHELF_ID as shelf_id,\n"
                + "                BSS.SHELF_NUMBER,\n"
                + "                B.ROOM_ID as room_id,\n"
                + "                BR.ROOM_NUMBER,\n"
                + "                BR.ROOM_NUMBER,\n"
                + "                A.F_NAME,\n"
                + "                A.id as aId,\n"
                + "                A.L_NAME,\n"
                + "                A.GENDER,\n"
                + "                A.DOB,\n"
                + "                A.DEAD_DATE,\n"
                + "                A.NATIONALITY,\n"
                + "                A.REWARD,\n"
                + "                BL.LANGUAGE\n"
                + "  FROM BOOK_SUBJECT BS\n"
                + "       right JOIN BOOK B\n"
                + "          ON B.ID = BS.BOOK_ID\n"
                + "       left JOIN SUBJECTS S\n"
                + "          ON S.ID = BS.SUBJECT_ID\n"
                + "       left JOIN BOOK_AUTHOR BA\n"
                + "          ON BA.BOOK_ID = B.ID\n"
                + "       left JOIN AUTHOR A\n"
                + "          ON A.ID = BA.AUTHOR_ID\n"
                + "       left JOIN book_language bl\n"
                + "          ON bl.id = B.LANGUAGE_ID\n"
                + "       left JOIN book_case bc\n"
                + "          ON bc.id = B.CASE_ID\n"
                + "       left JOIN book_room br\n"
                + "          ON BR.ID = B.ROOM_ID\n"
                + "       left JOIN book_shelf bss\n"
                + "          ON bss.id = B.SHELF_ID\n"
                + " WHERE  B.ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                pr.setLong(1, bookId);
                rs = pr.executeQuery();
                if (rs.next()) {
                    book.setId(rs.getLong("ID"));
                    book.setIsbn(rs.getLong("ISBN"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setPage(rs.getLong("PAGE"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    Language language = new Language();
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    language.setId(rs.getLong("l_id"));
                    book.setLanguage(language);
                    book.setEdition(rs.getString("B_EDITION"));
                    book.setStar(rs.getString("BOOK_STAR"));
                    book.setAllBookCount(rs.getInt("ALL_COUNT"));
                    book.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    subjects.setId(rs.getLong("subject_id"));
                    book.setSubjects(subjects);
                    author.setId(rs.getLong("aID"));
                    author.setFirstName(rs.getString("F_NAME"));
                    author.setLastName(rs.getString("L_NAME"));
                    author.setGender(rs.getString("GENDER"));
                    author.setDob(rs.getDate("DOB"));
                    author.setDeadDate(rs.getDate("DEAD_DATE"));
                    Nationality nationality = new Nationality();
                    nationality.setId(rs.getLong("nationality"));
                    author.setNationality(nationality);
                    author.setReward(rs.getString("REWARD"));
                    book.setLimitTime(rs.getInt("limit_time"));
                    book.setLimitDay(rs.getInt("limit_day"));
                    book.setbAbstract(rs.getString("abstract"));
                    BookCase bookCase = new BookCase();
                    bookCase.setCaseName(rs.getString("B_CASE"));
                    bookCase.setId(rs.getLong("bcId"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setId(rs.getLong("room_id"));
                    bookRoom.setNumber(rs.getString("ROOM_NUMBER"));
                    book.setBookRoom(bookRoom);
                    BookShelf bookShelf = new BookShelf();
                    bookShelf.setId(rs.getLong("shelf_id"));
                    bookShelf.setShelfNumber(rs.getString("SHELF_NUMBER"));
                    bookShelf.setEmptyCapacity(rs.getInt("empity_capacity"));
                    book.setBookShelf(bookShelf);
                    book.setbCase(bookCase);
                    book.setAuthor(author);
                }

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return book;
    }

    /// kitabin butun melumatlarini deyismek olarami otaqda daxil olmaqla?
    @Override
    public boolean updateBook(Book book, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (book.getReleaseDate() != null) {
                    String sql1
                            =
                            " UPDATE BOOK SET isbn=?,title=?,page=?,PUBLISHER=?,language_id=?,B_EDITION=?,BOOK_STAR=?,RELEASE_DATE=?,LIMIT_DAY=?,LIMIT_TIME=?,ABSTRACT=?, case_id=? ,shelf_id=? , room_id=? WHERE ID=?";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setDate(8, new java.sql.Date(book.getReleaseDate().getTime()));
                    pr.setInt(9, book.getLimitDay());
                    pr.setInt(10, book.getLimitTime());
                    pr.setString(11, book.getbAbstract());
                    pr.setLong(12, book.getbCase().getId());
                    pr.setLong(13, book.getBookShelf().getId());
                    pr.setLong(14, book.getBookRoom().getId());
                    pr.setLong(15, id);
                    pr.execute();
                    result = true;
                } else if (book.getReleaseDate() == null) {
                    String sql1
                            =
                            " UPDATE BOOK SET isbn=?,title=?,page=?,PUBLISHER=?,language_id=?,B_EDITION=?,BOOK_STAR=?,LIMIT_DAY=?,LIMIT_TIME=?,ABSTRACT=?, case_id=?,RELEASE_DATE=null,shelf_id=? , room_id=? WHERE ID=?";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setInt(8, book.getLimitDay());
                    pr.setInt(9, book.getLimitTime());
                    pr.setString(10, book.getbAbstract());
                    pr.setLong(11, book.getbCase().getId());
                    pr.setLong(12, book.getBookShelf().getId());
                    pr.setLong(13, book.getBookRoom().getId());
                    pr.setLong(14, id);
                    pr.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!");
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

    public boolean updateBookWeb(Book book, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (book.getReleaseDate() != null) {
                    String sql1
                            =
                            " UPDATE BOOK SET isbn=?,title=?,page=?,PUBLISHER=?,language_id=?,B_EDITION=?,BOOK_STAR=?,RELEASE_DATE=?,ABSTRACT=? WHERE ID=?";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setDate(8, new java.sql.Date(book.getReleaseDate().getTime()));
                    pr.setString(9, book.getbAbstract());
                    pr.setLong(10, id);
                    pr.execute();
                    result = true;
                } else if (book.getReleaseDate() == null) {
                    String sql1
                            =
                            " UPDATE BOOK SET isbn=?,title=?,page=?,PUBLISHER=?,language_id=?,B_EDITION=?,BOOK_STAR=?,ABSTRACT=? WHERE ID=?";
                    pr = c.prepareStatement(sql1);
                    pr.setLong(1, book.getIsbn());
                    pr.setString(2, book.getTitle());
                    pr.setLong(3, book.getPage());
                    pr.setString(4, book.getPublisher());
                    pr.setLong(5, book.getLanguage().getId());
                    pr.setString(6, book.getEdition());
                    pr.setString(7, book.getStar());
                    pr.setString(8, book.getbAbstract());
                    pr.setLong(9, id);
                    pr.execute();
                    result = true;
                }
            } else {
                System.out.println("Connection error!");
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

    @Override
    public boolean deleteBook(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement pr = null;
        String sql1 = "update book set active=0 where id=?";
        String sql2 = "update book_subject set active=0 where book_id=?";
        String sql3 = "update book_author set active=0 where book_id=?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql1);
                pr.setLong(1, id);
                pr.addBatch();
                pr.executeBatch();
                pr = c.prepareStatement(sql2);
                pr.setLong(1, id);
                pr.addBatch();
                pr.executeBatch();
                pr = c.prepareStatement(sql3);
                pr.setLong(1, id);
                pr.addBatch();
                pr.executeBatch();
                result = true;
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, null);
        }

        return result;
    }

    @Override
    public List<Book> searchBook(String keyword) throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;//  2 listagg problem
        String sql1 = "  SELECT ROW_NUMBER () OVER (ORDER BY b.id) AS r, B.ID,\n"
                + "         B.TITLE,\n"
                + "         B.ISBN,\n"
                + "         B.PAGE,\n"
                + "         B.PUBLISHER,\n"
                + "         B.B_EDITION,\n"
                + "         B.BOOK_STAR,\n"
                + "         B.ABSTRACT,\n"
                + "         B.LIMIT_DAY,\n"
                + "         B.LIMIT_TIME,\n"
                + "         B.ALL_COUNT,\n"
                + "         B.RELEASE_DATE,\n"
                + "         BC.B_CASE,\n"
                + "         LISTAGG (\n"
                + "            TO_CHAR (A.F_NAME || ' ' || A.L_NAME || ' ' || A.FATHER_NAME),\n"
                + "            ',  ')\n"
                + "         WITHIN GROUP (ORDER BY (TO_CHAR (a.id)))\n"
                + "            AS authors_full_name,\n"
                + "         LISTAGG (TO_CHAR (S.SUBJECT_NAME), ',  ')\n"
                + "            WITHIN GROUP (ORDER BY (TO_CHAR (s.id)))\n"
                + "            AS subjectss,\n"
                + "         BL.LANGUAGE,\n"
                + "         S.SUBJECT_INFO,\n"
                + "         BSF.SHELF_NUMBER,\n"
                + "         BSF.id,\n"
                + "         BR.ROOM_NUMBER,\n"
                + "         BR.ROOM_FLOOR\n"
                + "    FROM BOOK_AUTHOR BA\n"
                + "         RIGHT JOIN BOOK B\n"
                + "            ON B.ID = BA.BOOK_ID\n"
                + "         LEFT JOIN AUTHOR A\n"
                + "            ON A.ID = BA.AUTHOR_ID\n"
                + "         LEFT JOIN BOOK_SUBJECT BS\n"
                + "            ON BS.BOOK_ID = B.ID\n"
                + "         LEFT JOIN SUBJECTS S\n"
                + "            ON S.ID = BS.SUBJECT_ID\n"
                + "         LEFT JOIN BOOK_SHELF BSF\n"
                + "            ON BSF.ID = B.SHELF_ID\n"
                + "         LEFT JOIN BOOK_ROOM BR\n"
                + "            ON BR.ID = BSF.ROOM_ID\n"
                + "         LEFT JOIN BOOK_CASE BC\n"
                + "            ON BC.ID = B.CASE_ID\n"
                + "         LEFT JOIN BOOK_LANGUAGE BL\n"
                + "            ON BL.ID = B.LANGUAGE_ID\n"
                + "   WHERE     B.ACTIVE = 1 \n"
                + "         AND (   b.isbn LIKE (?)\n"
                + "              OR LOWER (b.title) LIKE (LOWER (?))\n"
                + "              OR B.PAGE LIKE (?)\n"
                + "              OR LOWER (B.PUBLISHER) LIKE (LOWER (?))\n"
                + "              OR LOWER (B.B_EDITION) LIKE (LOWER (?))\n"
                + "              OR LOWER (B.ABSTRACT) LIKE (LOWER (?))\n"
                + "              OR B.LIMIT_DAY LIKE (?)\n"
                + "              OR B.LIMIT_TIME LIKE (?)\n"
                + "              OR B.ALL_COUNT LIKE (?)\n"
                + "              OR TO_CHAR (B.RELEASE_DATE, 'dd/MM/yyyy') LIKE (?)\n"
                + "              OR LOWER (A.F_NAME) LIKE (LOWER (?))\n"
                + "              OR LOWER (A.L_NAME) LIKE (LOWER (?))\n"
                + "              OR LOWER (A.FATHER_NAME) LIKE (LOWER (?))\n"
                + "              OR LOWER (BL.LANGUAGE) LIKE (LOWER (?))\n"
                + "              OR BR.ROOM_FLOOR LIKE (?)\n"
                + "              OR LOWER (BR.ROOM_NUMBER) LIKE (LOWER (?))\n"
                + "              OR LOWER (BSF.SHELF_FLOOR) LIKE (?)\n"
                + "              OR B.ALL_COUNT LIKE (?)\n"
                + "              OR LOWER (S.SUBJECT_NAME) LIKE (LOWER (?))\n"
                + "              OR LOWER (BC.B_CASE) LIKE (LOWER (?))\n"
                + "              OR B.BOOK_STAR LIKE (?))\n"
                + "GROUP BY B.ID,\n"
                + "         B.TITLE,\n"
                + "         B.ISBN,\n"
                + "         B.PAGE,\n"
                + "         B.PUBLISHER,\n"
                + "         B.B_EDITION,\n"
                + "         B.BOOK_STAR,\n"
                + "         B.ABSTRACT,\n"
                + "         B.LIMIT_DAY,\n"
                + "         B.LIMIT_TIME,\n"
                + "         B.ALL_COUNT,\n"
                + "         B.RELEASE_DATE,\n"
                + "         BC.B_CASE,\n"
                + "         BL.LANGUAGE,\n"
                + "         S.SUBJECT_INFO,\n"
                + "         BSF.SHELF_NUMBER,\n"
                + "         BSF.id,\n"
                + "         BR.ROOM_NUMBER,\n"
                + "         BR.ROOM_FLOOR";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql1);
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
                pr.setString(12, "%" + keyword + "%");
                pr.setString(13, "%" + keyword + "%");
                pr.setString(14, "%" + keyword + "%");
                pr.setString(15, "%" + keyword + "%");
                pr.setString(16, "%" + keyword + "%");
                pr.setString(17, "%" + keyword + "%");
                pr.setString(18, "%" + keyword + "%");
                pr.setString(19, "%" + keyword + "%");
                pr.setString(20, "%" + keyword + "%");
                pr.setString(21, "%" + keyword + "%");
                rs = pr.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    Subjects subjects = new Subjects();
                    Author author = new Author();
                    Language language = new Language();
                    BookShelf bookShelf = new BookShelf();
                    book.setId(rs.getLong("ID"));
                    book.setNumber(rs.getLong("r"));
                    book.setIsbn(rs.getLong("ISBN"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setPage(rs.getLong("PAGE"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    book.setLanguage(language);
                    book.setbAbstract(rs.getString("Abstract"));
                    book.setEdition(rs.getString("B_EDITION"));
                    book.setStar(rs.getString("BOOK_STAR"));
                    book.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    book.setLimitDay(rs.getInt("LIMIT_DAY"));
                    book.setLimitTime(rs.getInt("LIMIT_TIME"));
                    subjects.setSubjectName(rs.getString("SUBJECTss"));
                    author.setFirstName(rs.getString("authors_full_name"));
                    book.setAuthor(author);
                    book.setSubjects(subjects);
                    book.setAllBookCount(rs.getInt("ALL_COUNT"));
                    book.setSubjects(subjects);
                    BookCase bookCase = new BookCase();
                    bookCase.setCaseName(rs.getString("B_CASE"));
                    book.setbCase(bookCase);
                    bookShelf.setId(rs.getLong("id"));
                    bookShelf.setShelfNumber(rs.getString("SHELF_NUMBER"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setFloor(rs.getInt("ROOM_FLOOR"));
                    bookRoom.setNumber(rs.getString("ROOM_NUMBER"));
                    book.setBookRoom(bookRoom);
                    book.setBookShelf(bookShelf);
                    bookList.add(book);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return bookList;

    }

    @Override
    public List<Book> getBookWhichNotAuthor() throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT b.id,\n"
                + "         B.TITLE,\n"
                + "         B.ISBN,\n"
                + "         B.B_EDITION,\n"
                + "         B.PUBLISHER,\n"
                + "         LISTAGG (TO_CHAR (S.SUBJECT_NAME), ',')\n"
                + "            WITHIN GROUP (ORDER BY TO_CHAR (S.SUBJECT_NAME))\n"
                + "            AS SUBJECT_NAME,\n"
                + "         BL.LANGUAGE\n"
                + "    FROM book_author ba\n"
                + "         full JOIN book b\n"
                + "            ON b.id = BA.BOOK_ID\n"
                + "         left JOIN book_subject bs\n"
                + "            ON BS.BOOK_ID = b.id\n"
                + "         LEFT JOIN subjects s\n"
                + "            ON s.id = BS.SUBJECT_ID\n"
                + "         LEFT JOIN book_language bl\n"
                + "            ON bl.id = B.LANGUAGE_ID\n"
                + "   WHERE b.active=1  \n"
                + "GROUP BY b.id,\n"
                + "         B.TITLE,\n"
                + "         B.ISBN,\n"
                + "         B.B_EDITION,\n"
                + "         B.PUBLISHER,\n"
                + "         B.RELEASE_DATE,\n"
                + "         BL.LANGUAGE";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                rs = pr.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    BookSubject bookSubject = new BookSubject();
                    Subjects subjects = new Subjects();
                    Language language = new Language();
                    book.setId(rs.getLong("ID"));
                    book.setIsbn(rs.getLong("ISBN"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    book.setLanguage(language);
                    book.setEdition(rs.getString("B_EDITION"));
                    subjects.setSubjectName(rs.getString("SUBJECT_NAME"));
                    bookSubject.setSubjects(subjects);
                    book.setBookSubject(bookSubject);
                    bookList.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return bookList;
    }

    @Override
    public Float getCount(int choose, Long id) throws Exception {
        Float result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "SELECT COUNT (ID) AS ALL_BOOK\n"
                + "  FROM BOOK B\n"
                + " WHERE ACTIVE = 1";
        String sql2 = "SELECT COUNT (BOOK_ID) AS book_count_By_Id\n" +
                "    FROM BOOK_SUBJECT bs FULL JOIN book b ON b.id = BS.BOOK_ID" +
                "   WHERE b.active = 1 and BS.ACTIVE=1" +
                "GROUP BY SUBJECT_ID" +
                "  HAVING SUBJECT_ID = ? ";
        String sql3 = "SELECT COUNT (ID) AS all_Author_Have_book\n"
                + "  FROM author\n"
                + " WHERE active = 1 ";
        String sql4 = "SELECT COUNT (ID) as book_count_for_language\n"
                + "    FROM BOOK\n"
                + "   WHERE ACTIVE = 1\n"
                + "GROUP BY LANGUAGE_ID\n"
                + "  HAVING LANGUAGE_ID = ?";
        String sql5 = "SELECT COUNT (ID) AS ALL_LANGUAGE_COUNT\n"
                + "  FROM BOOK_LANGUAGE BL\n"
                + " WHERE ACTIVE = 1";
        String sql6 = " SELECT COUNT (CASE_ID) as book_count_for_case\n"
                + "    FROM BOOK B\n"
                + "   WHERE ACTIVE = 1\n"
                + "GROUP BY CASE_ID\n"
                + "  HAVING CASE_ID = ?";
        String sql7 = "  SELECT COUNT (id) as book_count_for_old\n"
                + "    FROM book\n"
                + "   WHERE active = 1\n"
                + "GROUP BY language_id, case_id\n"
                + "  HAVING language_id = ? AND case_id = 1";
        String sql8 = "  SELECT COUNT (id) as book_count_for_new\n"
                + "    FROM book\n"
                + "   WHERE active = 1\n"
                + "GROUP BY language_id, case_id\n"
                + "  HAVING language_id = ? AND case_id = 2";
        String sql9 = "SELECT COUNT (id) as shelf_count\n"
                + "  FROM book_shelf\n"
                + " WHERE active = 1";
        String sql10 = "SELECT COUNT (id) as book_room_count\n"
                + "  FROM book_room\n"
                + " WHERE active = 1";
        String sql11 = "SELECT COUNT (id)as read_room_count\n"
                + "  FROM read_room\n"
                + " WHERE active = 1";
        String sql12 = "SELECT COUNT (id) as table_count\n"
                + "  FROM read_table\n"
                + " WHERE active = 1";
        String sql13 = "SELECT SUM (chair_count) AS sum_chair\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1";
        String sql14 = "SELECT SUM (chair_count) AS sum_chair\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1\n"
                + "GROUP BY id\n"
                + "  HAVING id = ?";
        String sql15 = " SELECT COUNT (id) AS table_sum\n"
                + "    FROM read_table\n"
                + "   WHERE active = 1\n"
                + "GROUP BY room_id\n"
                + "  HAVING room_id = ?";
        String sql16 = "SELECT COUNT (id) AS read_room_count\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1\n"
                + "GROUP BY lib_floor\n"
                + "  HAVING lib_floor = ?";
        String sql17 = " SELECT COUNT (id) AS book_room_count\n"
                + "    FROM book_room\n"
                + "   WHERE active = 1\n"
                + "GROUP BY room_floor\n"
                + "  HAVING room_floor = ?";
        String sql18 = "SELECT COUNT (bs.id) AS shelf_for_floor\n"
                + "    FROM book_shelf bs INNER JOIN book_room br ON BR.ID = BS.ROOM_ID\n"
                + "   WHERE bs.active = 1\n"
                + "GROUP BY BR.ROOM_FLOOR\n"
                + "  HAVING br.room_floor = ?";
        String sql19 = "SELECT COUNT (bs.id) AS shelf_for_room\n"
                + "    FROM book_shelf bs INNER JOIN book_room br ON BR.ID = BS.ROOM_ID\n"
                + "   WHERE bs.active = 1\n"
                + "GROUP BY BR.id\n"
                + "  HAVING br.id = ?";
        String sql20 = "SELECT SUM (chair_count) as chair_for_floor\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1\n"
                + "GROUP BY lib_floor\n"
                + "  HAVING lib_floor = ?";
        String sql21 = "SELECT COUNT (RT.ID) AS READ_ROOM_COUNT\n"
                + "    FROM READ_TABLE RT INNER JOIN READ_ROOM RR ON RR.ID = RT.ROOM_ID\n"
                + "   WHERE RT.ACTIVE = 1 and rr.active=1\n"
                + "GROUP BY RR.LIB_FLOOR\n"
                + "  HAVING RR.LIB_FLOOR = ?";
        String sql22 = " SELECT SUM (COMPUTER_COUNT) AS SUM_COMPUTER\n"
                + "    FROM READ_ROOM\n"
                + "   WHERE active = 1\n"
                + "GROUP BY lib_floor\n"
                + "  HAVING lib_floor = ?";
        String sql23 = " SELECT SUM (computer_count) AS sum_computer\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1\n"
                + "GROUP BY id\n"
                + "  HAVING id = ?";
        String sql24 = "SELECT SUM (computer_count) AS com_count\n"
                + "  FROM read_room\n"
                + " WHERE active = 1";
        String sql25 = " SELECT COUNT (WITH_COMPUTER) AS COUNT_COMP\n"
                + "    FROM READ_TABLE RT\n"
                + "   WHERE ACTIVE = 1\n"
                + "GROUP BY WITH_COMPUTER\n"
                + "  HAVING WITH_COMPUTER = 1";
        String sql26 = " SELECT COUNT (WITH_COMPUTER) AS COUNT_COMP\n"
                + "    FROM READ_TABLE RT\n"
                + "   WHERE ACTIVE = 1\n"
                + "GROUP BY WITH_COMPUTER\n"
                + "  HAVING WITH_COMPUTER = 0";
        String sql27 = " SELECT COUNT (with_computer) AS count_computer_floor\n"
                + "    FROM read_table rt INNER JOIN read_room rr ON rr.id = RT.ROOM_ID\n"
                + "   WHERE rt.active = 1\n"
                + "GROUP BY RR.LIB_FLOOR\n"
                + "  HAVING RR.LIB_FLOOR = ?";
        String sql28 = "SELECT COUNT (with_computer) AS count_computer_floor\n"
                + "    FROM read_table rt INNER JOIN read_room rr ON rr.id = RT.ROOM_ID\n"
                + "   WHERE rt.active = 1\n"
                + "GROUP BY RR.LIB_FLOOR, with_computer\n"
                + "  HAVING RR.LIB_FLOOR = ? AND with_computer = 1";
        String sql29 = "SELECT COUNT (with_computer) AS countt_computer_floor\n"
                + "    FROM read_table rt INNER JOIN read_room rr ON rr.id = RT.ROOM_ID\n"
                + "   WHERE rt.active = 1\n"
                + "GROUP BY RR.LIB_FLOOR, with_computer\n"
                + "  HAVING RR.LIB_FLOOR = ? AND with_computer = 0";
        String sql30 = " SELECT COUNT (with_computer) AS comp_for_room\n"
                + "    FROM read_table rt\n"
                + "   WHERE RT.active = 1\n"
                + "GROUP BY rt.room_id, with_computer\n"
                + "  HAVING rt.room_id = ? AND with_computer = 1";
        String sql31 = " SELECT COUNT (with_computer) AS comp_for_room\n"
                + "    FROM read_table rt\n"
                + "   WHERE RT.active = 1\n"
                + "GROUP BY rt.room_id, with_computer\n"
                + "  HAVING rt.room_id = ? AND with_computer = 0";
        String sql32 = "select sum(B.ALL_COUNT) as allBooks from book b where b.active=1";
        String sql33 = "SELECT COUNT (id) as allC \n"
                + "  FROM read_table rt\n"
                + " WHERE active = 1 AND with_computer = 1";
        String sql34 = "SELECT COUNT (id) as allC\n"
                + "  FROM read_table rt\n"
                + " WHERE active = 1 AND with_computer = 0";
        String sql35 = "SELECT COUNT (bs.id) AS shelf_for_floor\n"
                + "  FROM book_shelf bs INNER JOIN book_room br ON BR.ID = BS.ROOM_ID\n"
                + " WHERE bs.active = 1";
        String sql36 = "SELECT COUNT (id) AS book_room_count\n"
                + "                    FROM book_room\n"
                + "                  WHERE active = 1";
        String sql37 = "SELECT COUNT (id) AS read_room_count\n"
                + "    FROM read_room\n"
                + "   WHERE active = 1\n";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    ps = c.prepareStatement(sql1);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("ALL_BOOK");
                    }
                } else if (choose == 2) {
                    ps = c.prepareStatement(sql2);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("book_count_By_Id");
                    }
                } else if (choose == 3) {
                    ps = c.prepareStatement(sql3);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("all_Author_Have_book");
                    }
                } else if (choose == 4) {
                    ps = c.prepareStatement(sql4);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("book_count_for_language");
                    }
                } else if (choose == 5) {
                    ps = c.prepareStatement(sql5);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("ALL_LANGUAGE_COUNT");
                    }
                } else if (choose == 6) {
                    ps = c.prepareStatement(sql6);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("book_count_for_case");
                    }
                } else if (choose == 7) {
                    ps = c.prepareStatement(sql7);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("book_count_for_old");
                    }
                } else if (choose == 8) {
                    ps = c.prepareStatement(sql8);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("book_count_for_new");
                    }
                } else if (choose == 9) {
                    ps = c.prepareStatement(sql9);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("shelf_count");
                    }
                } else if (choose == 10) {
                    ps = c.prepareStatement(sql10);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("book_room_count");
                    }
                } else if (choose == 11) {
                    ps = c.prepareStatement(sql11);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("read_room_count");
                    }
                } else if (choose == 12) {
                    ps = c.prepareStatement(sql12);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("table_count");
                    }
                } else if (choose == 13) {
                    ps = c.prepareStatement(sql13);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("sum_chair");
                    }
                } else if (choose == 14) {
                    ps = c.prepareStatement(sql14);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getFloat("sum_chair");
                    }
                } else if (choose == 15) {
                    ps = c.prepareStatement(sql15);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("table_sum");
                    }
                } else if (choose == 16) {
                    ps = c.prepareStatement(sql16);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("read_room_count");

                    }
                } else if (choose == 17) {
                    ps = c.prepareStatement(sql17);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("book_room_count");
                    }
                } else if (choose == 18) {
                    ps = c.prepareStatement(sql18);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("shelf_for_floor");
                    }
                } else if (choose == 19) {
                    ps = c.prepareStatement(sql19);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("shelf_for_room");
                    }
                } else if (choose == 20) {
                    ps = c.prepareStatement(sql20);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("chair_for_floor");
                    }
                } else if (choose == 21) {
                    ps = c.prepareStatement(sql21);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("READ_ROOM_COUNT");
                    }
                } else if (choose == 22) {
                    ps = c.prepareStatement(sql22);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("SUM_COMPUTER");
                    }
                } else if (choose == 23) {
                    ps = c.prepareStatement(sql23);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("sum_computer");
                    }
                } else if (choose == 24) {
                    ps = c.prepareStatement(sql24);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("com_count");
                    }
                } else if (choose == 25) {
                    ps = c.prepareStatement(sql25);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("COUNT_COMP");
                    }
                } else if (choose == 26) {
                    ps = c.prepareStatement(sql25);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("COUNT_COMP");
                    }
                } else if (choose == 27) {
                    ps = c.prepareStatement(sql27);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("count_computer_floor");
                    }
                } else if (choose == 28) {
                    ps = c.prepareStatement(sql28);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("count_computer_floor");
                    }
                } else if (choose == 29) {
                    ps = c.prepareStatement(sql29);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("countt_computer_floor");
                    }
                } else if (choose == 30) {
                    ps = c.prepareStatement(sql30);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("comp_for_room");
                    }
                } else if (choose == 31) {
                    ps = c.prepareStatement(sql31);
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("comp_for_room");
                    }
                } else if (choose == 32) {
                    ps = c.prepareStatement(sql32);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allBooks");
                    }
                } else if (choose == 33) {
                    ps = c.prepareStatement(sql33);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allC");
                    }

                } else if (choose == 34) {
                    ps = c.prepareStatement(sql34);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("allC");
                    }

                } else if (choose == 35) {
                    ps = c.prepareStatement(sql35);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("shelf_for_floor");
                    }

                } else if (choose == 36) {
                    ps = c.prepareStatement(sql36);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("book_room_count");
                    }

                } else if (choose == 37) {
                    ps = c.prepareStatement(sql37);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getFloat("read_room_count");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return result;
    }

    @Override
    public List<Book> gtBookForOrder() throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT b.ID,\n"
                + "         b.isbn,\n"
                + "         b.title,\n"
                + "         LISTAGG (\n"
                + "            TO_CHAR (A.F_NAME || ' ' || A.L_NAME),\n"
                + "            ',')\n"
                + "         WITHIN GROUP (ORDER BY (TO_CHAR (a.id)))\n"
                + "            AS authors\n"
                + "    FROM book_author ba\n"
                + "         FULL JOIN book b\n"
                + "            ON BA.BOOK_ID = b.id\n"
                + "         LEFT JOIN author a\n"
                + "            ON BA.AUTHOR_ID = a.id\n"
                + "   WHERE B.ACTIVE = 1\n"
                + "GROUP BY b.ID, b.isbn, b.title";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    Author author = new Author();
                    author.setId(rs.getLong("id"));
                    author.setFirstName(rs.getString("authors"));
                    book.setAuthor(author);
                    bookList.add(book);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookList;
    }

    @Override
    public boolean updateBookForOrder(Long id, Integer bookCount) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE book\n"
                + "   SET all_count = ?\n"
                + " WHERE id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, bookCount);
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
    public boolean updateBookForBackBook(Integer bookCount, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE book\n"
                + "   SET all_count = ?\n"
                + " WHERE id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setFloat(1, bookCount);
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
    public Integer getBookCount(Long id) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  all_count from book where active=1 and id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("all_count");
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
    public Integer getTimeLimit(Long id) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  limit_time from book where active=1 and id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("limit_time");
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
    public Float getTimeLime(Long id) throws Exception {
        Float result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select limit_time from book where isbn=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getFloat("limit_time");
                }
            } else {
                System.out.println("Connection error!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return result;
    }

    @Override
    public List<Book> bookBySubjectId(Long subjectId) throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT ROWNUM R,\n" +
                "       k.ID,\n" +
                "       k.ISBN,\n" +
                "       k.TITLE,\n" +
                "       k.SUBJECT_NAME,\n" +
                "       k.f_name,\n" +
                "       k.father_name,\n" +
                "       k.l_name,\n" +
                "       k.PUBLISHER,\n" +
                "       k.B_EDITION,\n" +
                "       k.BOOK_STAR,\n" +
                "       k.RELEASE_DATE,\n" +
                "       k.LANGUAGE,\n" +
                "       k.page,\n" +
                "       k.aid,\n" +
                "       k.abstract\n" +
                "  FROM (  SELECT DISTINCT B.ID,\n" +
                "                          B.ISBN,\n" +
                "                          B.TITLE,\n" +
                "                          B.PUBLISHER,\n" +
                "                          B.B_EDITION,\n" +
                "                          B.BOOK_STAR,\n" +
                "                          B.RELEASE_DATE,\n" +
                "                          B.PAGE,\n" +
                "                          l.language,\n" +
                "                          S.SUBJECT_NAME,\n" +
                "                          A.id aId,\n" +
                "                          A.F_NAME,\n" +
                "                          A.L_NAME,\n" +
                "                          A.FATHER_NAME,\n" +
                "                          B.ABSTRACT\n" +
                "            FROM book_subject bs\n" +
                "                 INNER JOIN book b\n" +
                "                    ON b.id = BS.BOOK_ID\n" +
                "                 INNER JOIN subjects s\n" +
                "                    ON S.ID = BS.SUBJECT_ID\n" +
                "                 INNER JOIN book_author ba\n" +
                "                    ON BA.BOOK_ID = b.id\n" +
                "                 INNER JOIN author a\n" +
                "                    ON a.id = BA.AUTHOR_ID\n" +
                "                 INNER JOIN book_language l\n" +
                "                    ON l.id = B.LANGUAGE_ID\n" +
                "           WHERE S.ID = ? AND b.active = 1\n" +
                "        ORDER BY b.title DESC) k";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, subjectId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setNumber(rs.getLong("r"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setbAbstract(rs.getString("ABSTRACT"));
                    Author author = new Author();
                    book.setEdition(rs.getString("B_EDITION"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    book.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    Language language = new Language();
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    book.setLanguage(language);
                    book.setPage(rs.getLong("page"));
                    book.setStar(rs.getString("BOOK_STAR"));
                    author.setId(rs.getLong("aId"));
                    author.setFirstName(rs.getString("F_NAME"));
                    author.setLastName(rs.getString("l_name"));
                    author.setFatherName(rs.getString("father_name"));
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(rs.getString("SUBJECT_NAME"));
                    book.setSubjects(subjects);
                    book.setAuthor(author);
                    bookList.add(book);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookList;
    }

    @Override
    public boolean deleteSub(Long bookId) throws Exception {
        boolean flag = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM book_subject bs\n" +
                "      WHERE BS.BOOK_ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.execute();
                flag = true;
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return flag;
    }

    @Override
    public boolean deleteAuthor(Long bookId) throws Exception {
        boolean flag = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM book_author bs\n" +
                "      WHERE BS.BOOK_ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.execute();
                flag = true;
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return flag;
    }

    @Override
    public Long getBookCurrentId() throws Exception {
        Long result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql2 = "Select  max(id) as maxid from   book";

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
    public boolean addSubjects(Long bookId, Long subjectId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into  book_subject(id,book_id,subject_id) values(book_subject_seq.nextval,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.setLong(2, subjectId);
                ps.execute();
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
    public boolean addAuthors(Long bookId, Long authorId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into book_author(id,book_id,author_id) values(book_author_seq.nextval,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.setLong(2, authorId);
                ps.execute();
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
    public List<Book> getSubjectByBookId(Long bookId) throws Exception {
        List<Book> bookSubjectsList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{?= call LIBRARY_DESKTOP.getBookSubject(?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(2, bookId);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Book book = new Book();
                    book.setNumber(rs.getLong("r"));
                    Subjects subjects = new Subjects();
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    subjects.setId(rs.getLong("id"));
                    subjects.setSubjectName(rs.getString("SUBJECT_NAME"));
                    book.setSubjects(subjects);
                    bookSubjectsList.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, rs);
        }
        return bookSubjectsList;
    }

    @Override
    public List<Book> getAuthorByBookId(Long bookId) throws Exception {
        List<Book> bookAuthorList = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT  distinct a.id, A.F_NAME, A.L_NAME, A.FATHER_NAME\n"
                + "  FROM BOOK_AUTHOR BA\n"
                + "       INNER JOIN BOOK B\n"
                + "          ON B.ID = BA.BOOK_ID\n"
                + "       INNER JOIN AUTHOR A\n"
                + "          ON A.ID = BA.AUTHOR_ID\n"
                + " WHERE BA.ACTIVE = 1 AND  b.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                pr.setLong(1, bookId);
                rs = pr.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    BookSubject bookSubject = new BookSubject();
                    Author author = new Author();
                    author.setId(rs.getLong("id"));
                    author.setFirstName(rs.getString("f_name"));
                    author.setLastName(rs.getString("l_name"));
                    author.setFatherName(rs.getString("father_name"));
                    book.setAuthor(author);
                    bookAuthorList.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return bookAuthorList;
    }

    @Override
    public boolean updateSubjectbyBookId(Long bookId, Long subjectId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update book_subject set active=0 where book_id=? and subject_id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.setLong(2, subjectId);
                ps.execute();
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
    public boolean updateAuthorbyBookId(Long bookId, Long authorId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update book_author  set active=0 where book_id=? and author_id=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.setLong(2, authorId);
                ps.execute();
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
    public boolean updateSubjectForBook(Long bookId, Long subjectId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE BOOK_SUBJECT SET SUBJECT_ID=? WHERE  BOOK_ID=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                ps.execute();
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
    public boolean updateAuthorForBook(Long bookId, Long authorId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book getBookForOrder(Long bookId) throws Exception {
        Book book = new Book();
        Subjects subjects = new Subjects();
        Author author = new Author();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT B.ISBN,\n"
                + "       B.TITLE,\n"
                + "       BC.B_CASE,\n"
                + "       BL.LANGUAGE,\n"
                + "       B.ALL_COUNT,\n"
                + "       B.LIMIT_TIME,\n"
                + "       B.LIMIT_DAY,\n"
                + "       BR.ROOM_FLOOR,\n"
                + "       BR.ROOM_NUMBER,\n"
                + "       BS.SHELF_NUMBER\n"
                + "  FROM BOOK B\n"
                + "       INNER JOIN BOOK_ROOM BR\n"
                + "          ON BR.ID = B.ROOM_ID\n"
                + "       INNER JOIN BOOK_SHELF BS\n"
                + "          ON BS.ID = B.SHELF_ID\n"
                + "       INNER JOIN BOOK_LANGUAGE BL\n"
                + "          ON BL.ID = B.LANGUAGE_ID\n"
                + "       INNER JOIN BOOK_CASE BC\n"
                + "          ON BC.ID = B.CASE_ID\n"
                + " WHERE B.ACTIVE = 1 AND B.ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                pr.setLong(1, bookId);
                rs = pr.executeQuery();
                if (rs.next()) {
                    book.setIsbn(rs.getLong("ISBN"));
                    book.setTitle(rs.getString("TITLE"));
                    Language language = new Language();
                    language.setBookLanguage(rs.getString("LANGUAGE"));
                    book.setLanguage(language);
                    book.setAllBookCount(rs.getInt("ALL_COUNT"));
                    book.setSubjects(subjects);
                    book.setLimitTime(rs.getInt("limit_time"));
                    book.setLimitDay(rs.getInt("LIMIT_DAY"));
                    BookCase bookCase = new BookCase();
                    bookCase.setCaseName(rs.getString("B_CASE"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setFloor(rs.getInt("ROOM_FLOOR"));
                    bookRoom.setNumber(rs.getString("ROOM_NUMBER"));
                    book.setBookRoom(bookRoom);
                    BookShelf bookShelf = new BookShelf();
                    bookShelf.setShelfNumber(rs.getString("SHELF_NUMBER"));
                    book.setBookShelf(bookShelf);
                    book.setbCase(bookCase);
                    book.setAuthor(author);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return book;
    }

    @Override
    public List<Book> advanceSearch(AdvanceSearch advanceSearch) throws Exception {
        List<Book> bookList = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "  SELECT ROW_NUMBER () OVER (ORDER BY b.id) AS r,\n" +
                "         B.ID,\n" +
                "         B.ISBN,\n" +
                "         B.TITLE,\n" +
                "         B.B_EDITION,\n" +
                "         B.BOOK_STAR,\n" +
                "         B.ABSTRACT,\n" +
                "         B.PUBLISHER,\n" +
                "         B.PAGE,\n" +
                "         B.RELEASE_DATE,\n" +
                "         LISTAGG (TO_CHAR (A.F_NAME || ' ' || ' ' || A.L_NAME), ', ')\n" +
                "            WITHIN GROUP (ORDER BY (TO_CHAR (A.id)))\n" +
                "            AS Authors,\n" +
                "         BL.LANGUAGE\n" +
                "    FROM BOOK B\n" +
                "         LEFT JOIN BOOK_AUTHOR BA\n" +
                "            ON BA.BOOK_ID = B.ID\n" +
                "         LEFT JOIN AUTHOR A\n" +
                "            ON A.ID = BA.AUTHOR_ID\n" +
                "         LEFT JOIN book_language bl\n" +
                "            ON bl.id = B.LANGUAGE_ID\n" +
                "   WHERE B.ACTIVE = 1  ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (advanceSearch.getCase1() != null && !advanceSearch.getCase1().isEmpty()) {
                    sql += " AND B.PAGE LIKE '%" + advanceSearch.getCase1() + "%'";
                }
                if (advanceSearch.getCase2() != null && !advanceSearch.getCase2().isEmpty()) {
                    sql += " AND LOWER(B.PUBLISHER) LIKE LOWER('%" + advanceSearch.getCase2() + "%')";
                }
                if (advanceSearch.getCase3() != null && !advanceSearch.getCase3().isEmpty()) {
                    sql +=
                            " AND b.release_date >= TO_DATE('" + new java.sql.Date(df.parse(advanceSearch.getCase3()).getTime()) + "','YYYY-MM-DD')";
                }
                if (advanceSearch.getCase4() != null && !advanceSearch.getCase4().isEmpty()) {

                    sql +=
                            " AND B.RELEASE_DATE<= TO_DATE('" + new java.sql.Date(df.parse(advanceSearch.getCase4()).getTime()) + "','YYYY-MM-DD')";
                }
                if (advanceSearch.getCase5() != null && !advanceSearch.getCase5().isEmpty()) {
                    sql += " and LOWER (b.title) LIKE (LOWER ('%" + advanceSearch.getCase5() + "%'))";
                }
//                if (advanceSearch.getId1()!=null){
//                    sql+=" and b.id="+advanceSearch.getId1();
//                }
                if (advanceSearch.getId2() != null) {
                    sql += " and a.id=" + advanceSearch.getId2();

                }
//                if (advanceSearch.getId3() != null) {
//                    sql += " and s.id=" + advanceSearch.getId3();
//
//                }
                if (advanceSearch.getId4() != null) {
                    sql += " and bl.id=" + advanceSearch.getId4();
                }
                sql += "  GROUP BY B.ID,\n" +
                        " B.ISBN,\n" +
                        " B.TITLE,\n" +
                        " B.B_EDITION,\n" +
                        "  B.BOOK_STAR,\n" +
                        "  B.ABSTRACT,\n" +
                        "  B.PUBLISHER,\n" +
                        "  B.PAGE,\n" +
                        "  B.RELEASE_DATE,\n" +
                        "  BL.LANGUAGE";
                pr = c.prepareStatement(sql);
                rs = pr.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    Author author = new Author();
                    Language language = new Language();
                    book.setId(rs.getLong("ID"));
                    book.setNumber(rs.getLong("r"));
                    book.setIsbn(rs.getLong("ISBN"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setPage(rs.getLong("PAGE"));
                    book.setPublisher(rs.getString("PUBLISHER"));
                    book.setEdition(rs.getString("B_EDITION"));
                    book.setStar(rs.getString("BOOK_STAR"));
                    language.setBookLanguage(rs.getString("language"));
                    book.setLanguage(language);
                    book.setbAbstract(rs.getString("ABSTRACT"));
                    book.setReleaseDate(rs.getDate("RELEASE_DATE"));
                    author.setFirstName(rs.getString("authors"));
                    book.setAuthor(author);
                    bookList.add(book);
                }
                System.out.println(sql);

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return bookList;
    }

    @Override
    public List<Book> authorListOfBookSub(Long subjectId) throws Exception {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT DISTINCT  A.id,A.F_NAME, A.L_NAME, A.FATHER_NAME\n" +
                "  FROM book_author ba\n" +
                "       INNER JOIN book b\n" +
                "          ON b.id = BA.BOOK_ID\n" +
                "       INNER JOIN author a\n" +
                "          ON a.id = BA.AUTHOR_ID\n" +
                "       INNER JOIN book_subject bs\n" +
                "          ON BS.BOOK_ID = b.id\n" +
                "       INNER JOIN subjects s\n" +
                "          ON s.id = BS.SUBJECT_ID\n" +
                " WHERE B.ACTIVE = 1 AND s.id = ?";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setId(rs.getLong("id"));
                author.setFirstName(rs.getString("F_name"));
                author.setLastName(rs.getString("l_name"));
                author.setFatherName(rs.getString("FATHER_NAME"));
                book.setAuthor(author);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean addImageForBook(Long bookId, String image) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Insert into book(id,image) VALUES(1,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                FileInputStream fin = new FileInputStream("D:\\Capturegg.png");
                ps.setBinaryStream(1, fin, fin.available());
//                int i = ps.executeUpdate();
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
}
