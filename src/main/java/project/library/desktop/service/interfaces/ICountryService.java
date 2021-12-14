package project.library.desktop.service.interfaces;

import project.library.desktop.model.Country;

import java.util.List;

public interface ICountryService {
    List<Country> getCountryList() throws Exception;

}
