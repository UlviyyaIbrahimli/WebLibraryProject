package project.library.desktop.dao.interfaces;

import project.library.desktop.model.Country;

import java.util.List;

public interface ICountry {
    List<Country> getCountryList() throws Exception;
}
