package project.library.desktop.service;

import project.library.desktop.dao.StatusDao;
import project.library.desktop.model.Status;
import project.library.desktop.service.interfaces.IStatusService;

import java.util.List;

public class StatusService implements IStatusService {
    StatusDao statusDao;

    public StatusService(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public List<Status> getStatusList() throws Exception {
        return statusDao.getStatusList();
    }
}
