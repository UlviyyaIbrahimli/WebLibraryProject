package project.library.desktop.service;

import project.library.desktop.dao.NationalityDao;
import project.library.desktop.model.Nationality;
import project.library.desktop.service.interfaces.INationlityService;

import java.util.List;

public class NationalityService implements INationlityService {
    NationalityDao nationalityDao;

    public NationalityService(NationalityDao nationalityDao) {
        this.nationalityDao = nationalityDao;
    }


    @Override
    public List<Nationality> getNationalityList() throws Exception {
        return nationalityDao.getNationalityList();
    }
}
