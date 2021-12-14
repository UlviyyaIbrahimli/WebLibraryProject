package project.library.desktop.dao;

import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IReadRoomTable;
import project.library.desktop.model.ReadRoomTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @Ulviyye Ibrahimli
 */
public class ReadRoomTableDao implements IReadRoomTable {
    @Override
    public List<ReadRoomTable> getReadRoomTable() throws Exception {
        List<ReadRoomTable>  readRoomTableList=new ArrayList<>();
        Connection c=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="";
        try {

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Util.closeConnection(c,pr,rs);
        }
        return readRoomTableList;
    }

    @Override
    public boolean update(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
