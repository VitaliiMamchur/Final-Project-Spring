package ua.mamchur.springproject.service;

import ua.mamchur.springproject.model.RepairRequestStatus;

public interface RepairRequestStatusService {
    String CURRENT_REQUEST = "CURRENT_REQUEST";
    String ACCEPTED_REQUEST = "ACCEPTED_REQUEST";
    String CLOSED_REQUEST = "CLOSED_REQUEST";
    String DECLINED_REQUEST = "DECLINED_REQUEST";

    RepairRequestStatus findByStatus(String status);
}
