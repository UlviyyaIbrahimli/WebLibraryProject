package project.library.desktop.service.interfaces;

import project.library.desktop.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> getStatusList() throws  Exception;

}
