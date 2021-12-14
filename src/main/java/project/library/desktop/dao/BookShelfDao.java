package project.library.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IBookShelf;
import project.library.desktop.model.Book;
import project.library.desktop.model.BookRoom;
import project.library.desktop.model.BookShelf;

/**
 *
 * @ Ulviyye Ibrahimli
 */
public class BookShelfDao implements IBookShelf {

    @Override
    public List<BookShelf> getBookShelf() throws Exception {
        List<BookShelf> bookShelfList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM R, BS.ID,\n"
                + "       BS.SHELF_FLOOR,\n"
                + "       BS.SHELF_NUMBER,\n"
                + "       BS.POTENTIAL_CAPACITY,\n"
                + "       BR.ROOM_FLOOR,\n"
                + "       BR.ROOM_NUMBER,\n"
                + "       Bs.EMPiTY_CAPACITY\n"
                + "  FROM BOOK_SHELF BS INNER JOIN BOOK_ROOM BR ON BR.ID = BS.ROOM_ID\n"
                + " WHERE BS.ACTIVE = 1  order by BS.SHELF_NUMBER asc";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookShelf bookShelf = new BookShelf();
                    bookShelf.setId(rs.getLong("id"));
                    bookShelf.setNumber(rs.getLong("r"));
                    bookShelf.setShelfNumber(rs.getString("Shelf_number"));
                    bookShelf.setShelfFloor(rs.getInt("shelf_floor"));
                    bookShelf.setPotensialCapacity(rs.getInt("POTENTIAL_CAPACITY"));
                    bookShelf.setEmptyCapacity(rs.getInt("EMPiTY_CAPACITY"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setFloor(rs.getInt("room_floor"));
                    bookRoom.setNumber(rs.getString("room_number"));
                    bookShelf.setRoom(bookRoom);
                    bookShelfList.add(bookShelf);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookShelfList;
    }

    @Override
    public boolean addBookShelf(BookShelf bookShelf) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO BOOK_SHELF(ID,SHELF_NUMBER,SHELF_FLOOR,potential_capacity,empity_capacity,ROOM_ID) VALUES(BOOK_SHELF_SEQ.NEXTVAL,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookShelf.getShelfNumber());
                ps.setInt(2, bookShelf.getShelfFloor());
                ps.setInt(3, bookShelf.getPotensialCapacity());
                ps.setInt(4, bookShelf.getPotensialCapacity());
                ps.setLong(5, bookShelf.getRoom().getId());
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
    public boolean updateBookShelf(BookShelf bookShelf, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update  BOOK_SHELF  set SHELF_NUMBER=?,SHELF_FLOOR=?,POTENTIAL_CAPACITY=?, ROOM_ID=? where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookShelf.getShelfNumber());
                ps.setInt(2, bookShelf.getShelfFloor());
                ps.setInt(3, bookShelf.getPotensialCapacity());
                ps.setLong(4, bookShelf.getRoom().getId());
                ps.setLong(5, id);
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
    public BookShelf getBookShelfById(Long id) throws Exception {
        BookShelf bookShelf = new BookShelf();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT \n"
                + "       BS.SHELF_FLOOR,\n"
                + "       BS.SHELF_NUMBER,\n"
                + "       BS.POTENTIAL_CAPACITY,\n"
                + "       Br.id,\n"
                + "       BR.ROOM_FLOOR,\n"
                + "       BR.ROOM_NUMBER,\n"
                + "       Bs.empity_capacity\n"
                + "  FROM book_shelf bs INNER JOIN book_room br ON BR.ID = BS.ROOM_ID\n"
                + " WHERE bs.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    bookShelf.setShelfFloor(rs.getInt("shelf_floor"));
                    bookShelf.setShelfNumber(rs.getString("shelf_number"));
                    bookShelf.setPotensialCapacity(rs.getInt("POTENTIAL_CAPACITY"));
                    bookShelf.setEmptyCapacity(rs.getInt("empity_capacity"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setId(rs.getLong("id"));
                    bookRoom.setFloor(rs.getInt("room_floor"));
                    bookRoom.setNumber(rs.getString("room_number"));
                    bookShelf.setRoom(bookRoom);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookShelf;
    }

    @Override
    public boolean deleteBookShelf(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE BOOK_SHELF SET ACTIVE=0 WHERE ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection ffferror!");
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
    public Integer countBookInShelf(Long id, int choose) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql1 = "SELECT COUNT (id) as book_count_in_shelf\n"
                + "    FROM book b\n"
                + "   WHERE b.active = 1\n"
                + "GROUP BY shelf_id\n"
                + "  HAVING shelf_id = ?";
        String sql2 = "SELECT (  BS.POTENTIAL_CAPACITY\n"
                + "        - (  SELECT COUNT (id)\n"
                + "               FROM book b  where b.active=1\n"
                + "           GROUP BY shelf_id\n"
                + "             HAVING shelf_id = ?))\n"
                + "          AS empty_capacity\n"
                + "  FROM book_shelf bs\n"
                + " WHERE active = 1 AND bs.id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (choose == 1) {
                    ps = c.prepareStatement(sql1);
                    ps.setLong(1, id);
                    ps.execute();
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("book_count_in_shelf");
                    }
                } else if (choose == 2) {
                    ps = c.prepareStatement(sql2);
                    ps.setLong(1, id);
                    ps.setLong(2, id);
                    ps.execute();
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        result = rs.getInt("empty_capacity");
                    }
                }
            } else {
                System.out.println("Connection errfffor!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return result;
    }

    @Override
    public List<BookShelf> getBookShelfByRoomId(Long id) throws Exception {
        List<BookShelf> getBookshelfList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT BS.ID, BS.SHELF_NUMBER, BS.SHELF_FLOOR\n"
                + "  FROM BOOK_SHELF BS INNER JOIN BOOK_ROOM BR ON BR.ID = BS.ROOM_ID\n"
                + " WHERE BS.ACTIVE = 1 AND BR.ID = ? ";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookShelf bookShelf = new BookShelf();
                    bookShelf.setId(rs.getLong("id"));
                    bookShelf.setShelfNumber(rs.getString("shelf_number"));
                    bookShelf.setShelfFloor(rs.getInt("SHELF_FLOOR"));
                    getBookshelfList.add(bookShelf);
                }
            } else {
                System.out.println("Connection errorrrrr!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return getBookshelfList;
    }

    @Override
    public List<BookShelf> getBookshelfWhichRoomActiveZero() throws Exception {
        List<BookShelf> bookShelfList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM R, BS.ID,\n"
                + "       BS.SHELF_FLOOR,\n"
                + "       BS.SHELF_NUMBER,\n"
                + "       BS.POTENTIAL_CAPACITY,\n"
                + "       BR.ROOM_FLOOR,\n"
                + "       BR.ROOM_NUMBER\n"
                + "  FROM BOOK_SHELF BS INNER JOIN BOOK_ROOM BR ON BR.ID = BS.ROOM_ID\n"
                + " WHERE BS.ACTIVE = 1 AND br.active = 0 order by bs.shelf_floor asc ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookShelf bookShelf = new BookShelf();
                    bookShelf.setId(rs.getLong("id"));
                    bookShelf.setNumber(rs.getLong("r"));
                    bookShelf.setShelfNumber(rs.getString("Shelf_number"));
                    bookShelf.setShelfFloor(rs.getInt("shelf_floor"));
                    bookShelf.setPotensialCapacity(rs.getInt("POTENTIAL_CAPACITY"));
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setFloor(rs.getInt("room_floor"));
                    bookRoom.setNumber(rs.getString("room_number"));
                    bookShelf.setRoom(bookRoom);
                    bookShelfList.add(bookShelf);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookShelfList;
    }

    @Override
    public Integer getBookShelfFloor(Long id) throws Exception {
        Integer floor = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  BS.SHELF_FLOOR\n"
                + "  FROM BOOK_SHELF BS\n"
                + " WHERE BS.ACTIVE = 1 AND BS.ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    floor = rs.getInt("SHELF_FLOOR");
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return floor;

    }

    @Override
    public List<BookShelf> getBookByShelfId(Long shelfId) throws Exception {
        List<BookShelf> bookShelfList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,\n"
                + "         b.id,\n"
                + "         b.isbn,\n"
                + "         b.title,\n"
                + "         BS.SHELF_NUMBER\n"
                + "    FROM book b INNER JOIN book_shelf bs ON bs.id = B.SHELF_ID\n"
                + "   WHERE b.active = 1 AND bs.id = ? \n"
                + "ORDER BY b.title ASC";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, shelfId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookShelf bookShelf = new BookShelf();
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setNumber(rs.getLong("r"));
                    book.setIsbn(rs.getLong("isbn"));
                    book.setTitle(rs.getString("title"));
                    bookShelf.setId(rs.getLong("id"));
                    bookShelf.setBook(book);
                    bookShelfList.add(bookShelf);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return bookShelfList;
    }

    @Override
    public boolean updateEmpity(Long shelfId, Integer capacity) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update book_shelf set empity_capacity=? where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, capacity);
                ps.setLong(2, shelfId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, rs);
        }
        return result;
    }

    @Override
    public BookShelf getShelfEmptyByBook(Long bookId) throws Exception {
        BookShelf bookShelf = new BookShelf();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  bs. id ,bs.empity_capacity\n"
                + "  FROM book_shelf bs INNER JOIN book b ON B.SHELF_ID = bs.id\n"
                + " WHERE bs.active = 1 AND b.id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    bookShelf.setEmptyCapacity(rs.getInt("empity_capacity"));
                    bookShelf.setId(rs.getLong("id"));
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            Util.closeConnection(c, ps, rs);
        }
        return bookShelf;
    }
}
