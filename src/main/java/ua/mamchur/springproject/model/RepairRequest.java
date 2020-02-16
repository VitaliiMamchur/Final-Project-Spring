package ua.mamchur.springproject.model;

import javax.persistence.*;

@Entity
@Table(name = "repairRequest")
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String theme;
    private String description;
    private Integer price;
    private boolean active;
    private String feedback;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User repairRequestCreator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "repairRequestStatuses", joinColumns = @JoinColumn(name = "repairRequest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "status_id", referencedColumnName = "id"))
    private RepairRequestStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getRepairRequestCreator() {
        return repairRequestCreator;
    }

    public void setRepairRequestCreator(User repairRequestCreator) {
        this.repairRequestCreator = repairRequestCreator;
    }

    public RepairRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RepairRequestStatus status) {
        this.status = status;
    }

    public String statusToString() {
        String stringStatus;
        switch (this.status.getId().intValue()) {
            case 0:
                stringStatus = "Please wait till manager accept your repair request.";
                return stringStatus;
            case 1:
                stringStatus = "Your repair request is accepted. Please wait.";
                return stringStatus;
            case 2:
                stringStatus = "Your request has been done. Thank you!";
                return stringStatus;
            case 3:
                stringStatus = "Your repair request is declined.";
                return stringStatus;
        }
        return "Error";
    }
}