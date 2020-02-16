package ua.mamchur.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mamchur.springproject.model.RepairRequest;
import ua.mamchur.springproject.model.RepairRequestStatus;

import java.util.List;

@Repository
public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {
    List<RepairRequest> findAllByRepairRequestCreatorId(Long id);

    List<RepairRequest> findAllByStatus(RepairRequestStatus repairRequestStatus);
}
