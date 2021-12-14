/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.SubjectDao;
import project.library.desktop.model.BookSubject;
import project.library.desktop.model.Subjects;
import project.library.desktop.service.interfaces.ISubjectService;

/**
 *
 * @Ulviyye Ibrahimli
 */
public class SubjectService  implements ISubjectService{
    SubjectDao subjectDao;

    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subjects> getSubjectList() throws Exception {
 return subjectDao.getSubjectList();    }

    @Override
    public boolean addSubject(Subjects subjects) throws Exception {
        return subjectDao.addSubject(subjects);
    }

    @Override
    public boolean updateSubject(Subjects subject,Long id) throws Exception {
return subjectDao.updateSubject(subject,id);    }

    @Override
    public boolean deleteSubject(Long id) throws Exception {
return subjectDao.deleteSubject(id) ;   }

    @Override
    public Subjects getSubjectById(Long id) throws Exception {
return subjectDao.getSubjectById(id);    }

    @Override
    public List<Subjects> searchSubject(String keyword) throws Exception {
        return subjectDao.searchSubject(keyword);
    }


}
