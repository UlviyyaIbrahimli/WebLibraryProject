package project.library.desktop.dao;

import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.IStatus;
import project.library.desktop.model.Country;
import project.library.desktop.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusDao implements IStatus {
    @Override
    public List<Status> getStatusList() throws Exception {
        List<Status> statusList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id, status from status where active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Status status = new Status();
                    status.setId(rs.getLong("id"));
                    status.setStatus(rs.getString("status"));
                    statusList.add(status);
                }
            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return statusList;
    }
}
