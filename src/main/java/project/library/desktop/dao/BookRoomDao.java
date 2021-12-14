package project.library.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IBookRoom;
import project.library.desktop.model.BookRoom;

/**
 *
 * @Ulviyye Ibrahimli
 */
public class BookRoomDao implements IBookRoom {

    @Override
    public List<BookRoom> getBookRoomList() throws Exception {
        List<BookRoom> bookRoomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;// eger oracle de unique verilibse bu activenin sifir olmasina baxmayaraq davam edir-- methodla uniqui yoxlayan zaman
        ResultSet rs = null;
        String sql = "SELECT  rownum r,id, room_number, room_floor FROM book_room where active=1 ORDER BY room_number ASC ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setId(rs.getLong("id"));
                    bookRoom.setNumberOrder(rs.getLong("r"));
                    bookRoom.setNumber(rs.getString("room_number"));
                    bookRoom.setFloor(rs.getInt("room_floor"));
                    bookRoomList.add(bookRoom);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return bookRoomList;

    }

    @Override
    public boolean addBookRoom(BookRoom bookRoom) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " insert into  book_room( id, room_number, room_floor) values(book_room_seq.nextval,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookRoom.getNumber());
                ps.setInt(2, bookRoom.getFloor());
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
    public boolean updateBookRook(BookRoom bookRoom, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " update  book_room set room_number=?  where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookRoom.getNumber());
                ps.setLong(2, id);
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
    public BookRoom getBookRoomByid(Long id) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookRoom bookRoom = new BookRoom();
        String sql = " select room_number,  room_floor from  book_room  where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {

                    bookRoom.setNumber(rs.getString("room_number"));
                    bookRoom.setFloor(rs.getInt("room_floor"));
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
        return bookRoom;
    }

    @Override
    public boolean deleteBookRoom(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " update  book_room set active=0  where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
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
    public List<BookRoom> getRoomNumburForFloor(Integer floor) throws Exception {
        List<BookRoom> getbookRoomNumberList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  br.id ,BR.ROOM_NUMBER\n"
                + "  FROM book_room br\n"
                + " WHERE BR.ROOM_FLOOR = ? and br.active=1 ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, floor);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookRoom bookRoom = new BookRoom();
                    bookRoom.setId(rs.getLong("id"));
                    bookRoom.setNumber(rs.getString("room_number"));
                    getbookRoomNumberList.add(bookRoom);

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {

        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return getbookRoomNumberList;
    }

    @Override
    public List<String> getBookRoomNumber(String floor) throws Exception {
        List< String> numbers = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String number = null;
        String sql = "SELECT BR.ROOM_NUMBER\n"
                + "  FROM book_room br\n"
                + " WHERE BR.ROOM_FLOOR = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, floor);
                rs = ps.executeQuery();
                while (rs.next()) {
                    number = rs.getString("ROOM_NUMBER");
                    numbers.add(number);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return numbers;
    }
}
