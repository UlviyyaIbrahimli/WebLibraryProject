package project.library.desktop.dao.interfaces;

import project.library.desktop.model.Status;

import java.util.List;

public interface IStatus {
    List<Status> getStatusList() throws  Exception;
}
