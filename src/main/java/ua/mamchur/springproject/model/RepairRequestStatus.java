package ua.mamchur.springproject.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "statuses")
public class RepairRequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<RepairRequest> repairRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public void setRepairRequests(Set<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }
}
