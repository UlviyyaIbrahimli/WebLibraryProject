package project.library.desktop.dao;

import project.library.desktop.dao.interfaces.INationality;
import project.library.desktop.model.Nationality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NationalityDao implements INationality {
    @Override
    public List<Nationality> getNationalityList() throws Exception {
        List<Nationality> nationalityList = new ArrayList<>();
        String sql = "select n.id, n.nationality from nationality n where n.active=1";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Nationality nationality = new Nationality();
                nationality.setId(rs.getLong("id"));
                nationality.setNationality(rs.getString("nationality"));
                nationalityList.add(nationality);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nationalityList;
    }
}
