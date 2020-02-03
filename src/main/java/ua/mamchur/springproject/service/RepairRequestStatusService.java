package ua.mamchur.springproject.service;

import ua.mamchur.springproject.model.RepairRequestStatus;

public interface RepairRequestStatusService {
    static final String CURRENT_REQUEST = "CURRENT_REQUEST";
    static final String ACCEPTED_REQUEST = "ACCEPTED_REQUEST";
    static final String CLOSED_REQUEST = "CLOSED_REQUEST";
    static final String DECLINED_REQUEST = "DECLINED_REQUEST";

    RepairRequestStatus findByStatus(String status);
}
