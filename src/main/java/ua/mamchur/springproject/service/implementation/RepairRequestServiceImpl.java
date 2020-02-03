package ua.mamchur.springproject.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.mamchur.springproject.model.RepairRequest;
import ua.mamchur.springproject.repository.RepairRequestRepository;
import ua.mamchur.springproject.repository.RepairRequestStatusRepository;
import ua.mamchur.springproject.service.RepairRequestService;
import ua.mamchur.springproject.service.RepairRequestStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    @Autowired
    RepairRequestRepository repairRequestRepository;
    @Autowired
    RepairRequestStatusRepository repairRequestStatusRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RepairRequestStatusService repairRequestStatusService;

    @Override
    public void create(UserDetails currentUser, RepairRequest repairRequest) {
        repairRequest.setRepairRequestCreator(userService.findByUserDetails(currentUser));
        repairRequest.setStatus(repairRequestStatusRepository.findByStatus(RepairRequestStatusService.CURRENT_REQUEST));
        repairRequest.setActive(true);

        repairRequestRepository.save(repairRequest);
    }

    @Override
    public void edit(Long id, RepairRequest repairRequest) {
        repairRequestRepository.findById(id)
                .ifPresent(old -> {
                            old.setPrice(repairRequest.getPrice());
                            repairRequestRepository.save(old);

                        }
                );

    }

    @Override
    public List<RepairRequest> getAllByUser(UserDetails userDetails) {

        return repairRequestRepository.findAllByRepairRequestCreatorId(userService.findByUserDetails(userDetails).getId());
    }

    @Override
    public Optional<RepairRequest> findOne(Long id) {
        return repairRequestRepository.findById(id);
    }

    @Override
    public RepairRequest addFeedBack(Long id, String feedback) {
        RepairRequest repairRequest = repairRequestRepository.findById(id).get();
        if (repairRequest.getStatus() != repairRequestStatusService.findByStatus(repairRequestStatusService.CLOSED_REQUEST)) {
            return null;
        }
        repairRequest.setFeedback(feedback);
        repairRequestRepository.save(repairRequest);
        return repairRequest;

    }


    @Override
    public List<RepairRequest> getMasterRepairRequestList() {
        return repairRequestRepository.findAllByStatus(repairRequestStatusRepository.findByStatus(repairRequestStatusService.ACCEPTED_REQUEST));
    }

    @Override
    public List<RepairRequest> getManagerRepairRequestList() {
        return repairRequestRepository.findAllByStatus(repairRequestStatusRepository.findByStatus(repairRequestStatusService.CURRENT_REQUEST));
    }

    @Override
    public void changeRepairRequestStatus(Long id, String repairRequestStatus) {
        repairRequestRepository.findById(id).ifPresent(repairRequest ->
        {
            repairRequest.setStatus(repairRequestStatusService.findByStatus(repairRequestStatus));
            repairRequestRepository.save(repairRequest);
        });
    }
}
