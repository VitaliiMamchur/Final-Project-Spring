package ua.mamchur.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mamchur.springproject.model.RepairRequestStatus;

@Repository
public interface RepairRequestStatusRepository extends JpaRepository<RepairRequestStatus,Long> {
    RepairRequestStatus findByStatus(String status);
}
