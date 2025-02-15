package com.enpal.abs.service;

import com.enpal.abs.dto.AppointmentRequest;
import com.enpal.abs.dto.AvailableSlotResponse;

import java.util.List;

public interface IAppointmentService {
    List<AvailableSlotResponse> getAvailableSlots(AppointmentRequest request);
}
