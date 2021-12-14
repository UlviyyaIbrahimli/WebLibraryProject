package project.library.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IReadRoom;
import project.library.desktop.model.ReadRoom;

/**
 *
 * @ Ulviyye Ibrahimli
 */
public class ReadRoomDao implements IReadRoom {

    @Override
    public List<ReadRoom> getReadRoomList() throws Exception {
        List<ReadRoom> readRoomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM R,  RR.ID,\n"
                + "       RR.READ_ROOM_NUMBER,\n"
                + "       RR.COMPUTER_COUNT,\n"
                + "       RR.LIB_FLOOR,\n"
                + "       RR.CHAIR_COUNT\n"
                + "  FROM READ_ROOM RR\n"
                + " WHERE RR.ACTIVE = 1 order by rr.read_room_number asc";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setId(rs.getLong("Id"));
                    readRoom.setNumber(rs.getLong("r"));
                    readRoom.setLibFloor(rs.getInt("LIB_FLOOR"));
                    readRoom.setComputerCount(rs.getInt("computer_count"));
                    readRoom.setChairCount(rs.getInt("Chair_count"));
                    readRoom.setReadRoomNumber(rs.getString("read_room_number"));
                    readRoomList.add(readRoom);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return readRoomList;

    }

    @Override
    public boolean addReadRoom(ReadRoom readRoom) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO READ_ROOM (ID,LIB_FLOOR,COMPUTER_COUNT,CHAIR_COUNT,READ_ROOM_NUMBER) VALUES(READ_ROOM_SEQ.NEXTVAL,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, readRoom.getLibFloor());
                ps.setInt(2, readRoom.getComputerCount());
                ps.setInt(3, readRoom.getChairCount());
                ps.setString(4, readRoom.getReadRoomNumber());
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
    public boolean updateReadRoom(ReadRoom readRoom, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE  READ_ROOM  SET READ_ROOM_NUMBER=?, COMPUTER_COUNT=?,CHAIR_COUNT=? where id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, readRoom.getReadRoomNumber());
                ps.setInt(2, readRoom.getComputerCount());
                ps.setInt(3, readRoom.getChairCount());
                ps.setLong(4, id);
                ps.execute();
                result = true;

            } else {
                System.out.println("connection error");
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
    public ReadRoom getReadRoomById(Long id) throws Exception {
        ReadRoom readRoom = new ReadRoom();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT  RR.READ_ROOM_NUMBER,\n"
                + "       RR.LIB_FLOOR,\n"
                + "       RR.CHAIR_COUNT,\n"
                + "       RR.COMPUTER_COUNT\n"
                + "  FROM READ_ROOM RR\n"
                + " WHERE  rr.id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    readRoom.setReadRoomNumber(rs.getString("read_room_number"));
                    readRoom.setLibFloor(rs.getInt("lib_floor"));
                    readRoom.setComputerCount(rs.getInt("computer_count"));
                    readRoom.setChairCount(rs.getInt("chair_count"));

                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return readRoom;
    }

    @Override
    public boolean deleteReadRoom(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update read_room set active=0  where id=?";
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
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }
        return result;
    }

    @Override
    public List<ReadRoom> getReadRoomByRoomFloor(int floor) throws Exception {
        List<ReadRoom> getReadRoomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT RR.ID, RR.READ_ROOM_NUMBER\n"
                + "  FROM READ_ROOM RR\n"
                + " WHERE LIB_FLOOR =  ? AND \n"
                + " ACTIVE=1";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, floor);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setId(rs.getLong("ID"));
                    readRoom.setReadRoomNumber(rs.getString("READ_ROOM_NUMBER"));
                    getReadRoomList.add(readRoom);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return getReadRoomList;
    }

    @Override
    public ReadRoom getTableByRoomId(Long id) throws Exception {
        ReadRoom readRoom = new ReadRoom();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT (ID) as table_count\n"
                + "    FROM READ_TABLE\n"
                + "   WHERE ACTIVE = 1\n"
                + "GROUP BY ROOM_ID\n"
                + "  HAVING ROOM_ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    readRoom.setTableCount(rs.getInt("table_count"));
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return readRoom;

    }

    @Override
    public List<ReadRoom> getReaderRoomforOrder() throws Exception {
        List<ReadRoom> readRoomList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT RR.ID,\n"
                + "       RR.READ_ROOM_NUMBER,\n"
                + "       RR.COMPUTER_COUNT,\n"
                + "       RR.LIB_FLOOR,\n"
                + "       RR.CHAIR_COUNT\n"
                + "  FROM READ_ROOM RR\n"
                + " WHERE RR.ACTIVE = 1 and status=0 ORDER BY RR.LIB_FLOOR ASC ";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setId(rs.getLong("Id"));
                    readRoom.setLibFloor(rs.getInt("LIB_FLOOR"));
                    readRoom.setComputerCount(rs.getInt("computer_count"));
                    readRoom.setChairCount(rs.getInt("Chair_count"));
                    readRoom.setReadRoomNumber(rs.getString("read_room_number"));
                    readRoomList.add(readRoom);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return readRoomList;
    }

    @Override
    public boolean updateForOrder(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update read_room rr set status=1 where rr.id=? and active=1";
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
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return result;

    }

    @Override
    public boolean updateForGive(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update read_room rr set status=0 where rr.id=? and active=1";
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
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, null);
        }

        return result;
    }
}
