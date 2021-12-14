package project.library.desktop.service.interfaces;

import project.library.desktop.model.Nationality;

import java.util.List;

public interface INationlityService {
    List<Nationality> getNationalityList() throws Exception;
}
