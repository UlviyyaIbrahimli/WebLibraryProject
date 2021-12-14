
package project.library.desktop.dao.interfaces;

import java.util.List;
import project.library.desktop.model.Subjects;

/**
 *
 * @Ulviyye Ibrahimli
 */
public interface ISubject {

    List<Subjects> getSubjectList() throws Exception;

    boolean addSubject(Subjects subjects) throws Exception;

    boolean updateSubject(Subjects subject, Long id) throws Exception;

    boolean deleteSubject(Long id) throws Exception;

    Subjects getSubjectById(Long id) throws Exception;
     List<Subjects> searchSubject(String keyword) throws Exception;
}
