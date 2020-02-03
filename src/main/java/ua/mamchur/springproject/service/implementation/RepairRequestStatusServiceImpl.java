package ua.mamchur.springproject.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mamchur.springproject.model.RepairRequestStatus;
import ua.mamchur.springproject.repository.RepairRequestStatusRepository;
import ua.mamchur.springproject.service.RepairRequestStatusService;

@Service
public class RepairRequestStatusServiceImpl implements RepairRequestStatusService {

    @Autowired
    RepairRequestStatusRepository repairRequestStatusRepository;

    @Override
    public RepairRequestStatus findByStatus(String status) {
        return repairRequestStatusRepository.findByStatus(status);
    }
}
