
package project.library.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.ILanguage;
import project.library.desktop.model.Language;

/**
 *
 * @Ulviyye Ibrahimli
 */
public class LanguageDao implements ILanguage {

    LanguageDao languageDao;

    public LanguageDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    public LanguageDao() {
    }

    @Override
    public List<Language> getLanguageList() throws Exception {
        List<Language> getLanguageList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select  id,language from book_language where active=1 order by language desc";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Language language = new Language();
                    language.setId(rs.getLong("Id"));
                    language.setBookLanguage(rs.getString("language"));
                    getLanguageList.add(language);
                }
            } else {
                System.out.println("connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return getLanguageList;
    }

}
