package project.library.desktop.service;

import project.library.desktop.dao.CountryDao;
import project.library.desktop.model.Country;
import project.library.desktop.service.interfaces.ICountryService;

import java.util.List;

public class CountryService implements ICountryService {
    CountryDao countryDao;

    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getCountryList() throws Exception {
        return countryDao.getCountryList();
    }
}
