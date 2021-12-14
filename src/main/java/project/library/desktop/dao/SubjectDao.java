package project.library.desktop.dao;

import oracle.jdbc.OracleTypes;
import project.library.desktop.Util;
import project.library.desktop.dao.interfaces.ISubject;
import project.library.desktop.model.AbstractClass;
import project.library.desktop.model.Subjects;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Ulviyya Ibrahimli
 */
public class SubjectDao extends AbstractClass implements ISubject {

    @Override
    public List<Subjects> getSubjectList() throws Exception {
        List<Subjects> subjectList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call LIBRARY_DESKTOP.getList_pack.getSubjectListForWeb}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Subjects subject = new Subjects();
                    subject.setNumber(rs.getLong("r"));
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectInfo(rs.getString("subject_info"));
                    subjectList.add(subject);
                }
            } else {
                System.out.println("Connection  Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, rs);
        }
        return subjectList;
    }

    @Override
    public boolean addSubject(Subjects subjects) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call LIBRARY_DESKTOP.add_pack.add_Subject(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, subjects.getSubjectName());
                cs.setString(2, subjects.getSubjectInfo());
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean updateSubject(Subjects subject, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIBRARY_DESKTOP.update_pack.update_subject(?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, subject.getSubjectName());
                cs.setString(2, subject.getSubjectInfo());
                cs.setLong(3, id);
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection2(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean deleteSubject(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update  subjects set active=0 where id=?";
        String sql1 = "update book_subject set active=0 where subject_id=?";
        String sql2 = "update author_subject set active=0 where subject_id=? ";
        Subjects subjects = new Subjects();
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                ps = c.prepareStatement(sql1);
                ps.setLong(1, id);
                ps.execute();
                ps = c.prepareStatement(sql2);
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
    public Subjects getSubjectById(Long id) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select subject_name,subject_info  from subjects where id=?";
        Subjects subjects = new Subjects();
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    subjects.setSubjectName(rs.getString("subject_name"));
                    subjects.setSubjectInfo(rs.getString("subject_info"));
                }
            } else {
                System.out.println("Connection error!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, ps, rs);
        }
        return subjects;
    }

    @Override
    public List<Subjects> searchSubject(String keyword) throws Exception {
        List<Subjects> subjectsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql =
                "select S.SUBJECT_NAME,S.SUBJECT_INFO from subjects s where lower (S.SUBJECT_NAME) like(LOWER(?)) or lower(S.SUBJECT_INFO) like(lower(?)) or S.DATA_DATE like(?) and S.ACTIVE=1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                pr = c.prepareStatement(sql);
                pr.setString(1, "%" + keyword + "%");
                pr.setString(2, "%" + keyword + "%");
                pr.setString(3, "%" + keyword + "%");
                rs = pr.executeQuery();
                while (rs.next()) {
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(rs.getString("subject_name"));
                    subjects.setSubjectInfo(rs.getString("subject_info"));
                    subjectsList.add(subjects);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(c, pr, rs);
        }
        return subjectsList;
    }

}
