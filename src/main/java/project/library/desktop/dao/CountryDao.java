package project.library.desktop.dao;

import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.ICountry;
import project.library.desktop.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDao implements ICountry {
    @Override
    public List<Country> getCountryList() throws Exception {
        List<Country> countryList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id, country_Name from country where active=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getLong("id"));
                    country.setCountryName(rs.getString("country_name"));
                    countryList.add(country);
                }
            } else {
                System.out.println("Conection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return countryList;
    }
}
