package ua.mamchur.springproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.model.RepairRequest;

import java.util.List;
import java.util.Optional;

public interface RepairRequestService {
    List<RepairRequest> getAllByUser(UserDetails userDetails);

    Optional<RepairRequest> findOne(Long id);

    void create(UserDetails currentUser, RepairRequest repairRequest);

    void edit(Long id, RepairRequest repairRequest);

    RepairRequest addFeedBack( Long id, String feedBack);

    void changeRepairRequestStatus(Long id, String repairRequestStatus);

    List<RepairRequest> getMasterRepairRequestList();

    List<RepairRequest> getManagerRepairRequestList();


}