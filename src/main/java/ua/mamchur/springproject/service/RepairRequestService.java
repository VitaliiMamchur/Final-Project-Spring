package ua.mamchur.springproject.service;

import ua.mamchur.springproject.model.RepairRequest;
import ua.mamchur.springproject.model.User;

import java.util.List;

public interface RepairRequestService {
    List<RepairRequest> getAllByUser(User user);

    void create(User currentUser, RepairRequest repairRequest);

    void edit(Long id, RepairRequest repairRequest);

    RepairRequest addFeedBack(Long id, String feedBack);

    void changeRepairRequestStatus(Long id, String repairRequestStatus);

    List<RepairRequest> getMasterRepairRequestList();

    List<RepairRequest> getManagerRepairRequestList();


}