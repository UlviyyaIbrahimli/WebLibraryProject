package project.library.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IReadTable;
import project.library.desktop.model.ReadRoom;
import project.library.desktop.model.ReadTable;
/**
 * 
 * @ Ulviyye Ibrahimli
 */
public class ReadTableDao implements IReadTable {

    @Override
    public List<ReadTable> getReadTable() throws Exception {
        List<ReadTable> getReadTableList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  rownum r, rt.id, RT.READ_TABLE_NUMBER,\n"
                + "       RT.WITH_COMPUTER,\n"
                + "       RR.LIB_FLOOR,\n"
                + "       RR.READ_ROOM_NUMBER\n"
                + "  FROM read_table rt INNER JOIN read_room rr ON rr.id = rt.room_id\n"
                + " WHERE rt.active = 1  order by rt.read_table_number asc";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadTable readTable = new ReadTable();
                    readTable.setId(rs.getLong("ID"));
                    readTable.setNumber(rs.getLong("r"));
                    readTable.setTableNumber(rs.getString("READ_TABLE_NUMBER"));
                    readTable.setWithComputer(rs.getInt("WITH_COMPUTER"));
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setReadRoomNumber(rs.getString("READ_ROOM_NUMBER"));
                    readRoom.setLibFloor(rs.getInt("LIB_FLOOR"));
                    readTable.setReadRoom(readRoom);
                    getReadTableList.add(readTable);
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return getReadTableList;
    }

    @Override
    public boolean addReadTable(ReadTable readTable) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql1 = "Insert into read_table (ID,READ_TABLE_NUMBER, WITH_COMPUTER,room_id) values(READ_TABLE_SEQ.NEXTVAL,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql1);
                ps.setString(1, readTable.getTableNumber());
                ps.setInt(2, readTable.getWithComputer());
                ps.setLong(3, readTable.getReadRoom().getId());
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
    public boolean updateReadTable(ReadTable readTable, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql1 = "UPDATE READ_TABLE SET READ_TABLE_NUMBER=?, WITH_COMPUTER=?,  room_id=? where id=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql1);
                ps.setString(1, readTable.getTableNumber());
                ps.setInt(2, readTable.getWithComputer());
                ps.setLong(3, readTable.getReadRoom().getId());
                ps.setLong(4, id);
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
    public boolean deleteReadTable(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql1 = "UPDATE READ_TABLE SET ACTIVE=0 WHERE ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql1);
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
    public ReadTable getReadTableById(Long id) throws Exception {
        ReadTable readTable = new ReadTable();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT RT.READ_TABLE_NUMBER,\n"
                + "       RT.WITH_COMPUTER,\n"
                + "       RR.LIB_FLOOR,\n"
                + "       RR.READ_ROOM_NUMBER\n"
                + "  FROM read_table rt INNER JOIN read_room rr ON rr.id = rt.room_id\n"
                + " WHERE   rt.id=?";
        try {
            c = DbHelper.getConnection();
            ps = c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            rs = ps.executeQuery();
            while (rs.next()) {
                readTable.setTableNumber(rs.getString("READ_TABLE_NUMBER"));
                readTable.setWithComputer(rs.getInt("WITH_COMPUTER"));
                ReadRoom readRoom = new ReadRoom();
                readRoom.setReadRoomNumber(rs.getString("READ_ROOM_NUMBER"));
                readRoom.setLibFloor(rs.getInt("Lib_floor"));
                readTable.setReadRoom(readRoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }

        return readTable;
    }

    @Override
    public List<ReadTable> getTable(int choose, Long sqlchoose) throws Exception {
        List<ReadTable> tables = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rt.id, RT.READ_TABLE_NUMBER, RR.READ_ROOM_NUMBER\n"
                + "  FROM read_table rt INNER JOIN read_room rr ON rr.id = rt.room_id\n"
                + " WHERE rt.active = 1 AND with_computer = ? AND rr.id = ?  and  rt.status=0 ORDER BY RT.READ_TABLE_NUMBER ASC";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, choose);
                ps.setLong(2, sqlchoose);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadTable readTable = new ReadTable();
                    readTable.setId(rs.getLong("id"));
                    readTable.setTableNumber(rs.getString("READ_TABLE_NUMBER"));
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setReadRoomNumber(rs.getString("READ_ROOM_NUMBER"));
                    readTable.setReadRoom(readRoom);
                    tables.add(readTable);
                }

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return tables;
    }

    @Override
    public boolean update(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update read_table rt set status=1 where id=? and active=1";
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
            Util.closeConnection(c, ps, rs);
        }

        return result;
    }

    @Override
    public Integer tableWhichSatatus1(Long id) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  count(rt.id) as full from  read_table rt  where RT.STATUS=1 and room_id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("full");
                }
                ps.execute();
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
    public Integer allTable(Long id) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  count(id) as fullA from read_table rt where RT.ROOM_ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("fullA");
                }
                ps.execute();
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
    public boolean updateStatus(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update read_table rt set status=0 where  id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setFloat(1, id);
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
    public Integer getOrderedTableCount(Long roomId) throws Exception {
        Integer result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT SUM (COUNT (OIL.READ_ROOM_TABLE_ID)) as Odertable\n"
                + "    FROM order_in_lib oil\n"
                + "GROUP BY OIL.READ_ROOM_ID\n"
                + "  HAVING OIL.READ_ROOM_ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("Odertable");
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
    public Long tableId(Long readerId, Long bookId) throws Exception {
        Long result = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select read_room_table_id from order_in_lib oil where reader_id=? and book_id =? and active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, readerId);
                ps.setLong(2, bookId);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getLong("read_room_table_id");
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
    public List<ReadTable> getTableByRoomId(Long roomId) throws Exception {

        List<ReadTable> tables = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT ROWNUM r,\n"
                + "         RT.ID,\n"
                + "         RT.READ_TABLE_NUMBER,\n"
                + "         RT.WITH_COMPUTER,\n"
                + "         RR.READ_ROOM_NUMBER,\n"
                + "         RR.LIB_FLOOR\n"
                + "    FROM read_table rt INNER JOIN read_room rr ON rr.id = RT.ROOM_ID\n"
                + "   WHERE RT.ROOM_ID = ? AND rt.active = 1\n"
                + "ORDER BY rt.read_table_number DESC";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ReadTable readTable = new ReadTable();
                    readTable.setId(rs.getLong("id"));
                    readTable.setNumber(rs.getLong("r"));
                    readTable.setTableNumber(rs.getString("READ_TABLE_NUMBER"));
                    readTable.setWithComputer(rs.getInt("WITH_COMPUTER"));
                    ReadRoom readRoom = new ReadRoom();
                    readRoom.setReadRoomNumber(rs.getString("READ_ROOM_NUMBER"));
                    readRoom.setLibFloor(rs.getInt("LIB_FLOOR"));
                    readTable.setReadRoom(readRoom);
                    tables.add(readTable);
                }

            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return tables;
    }

}
