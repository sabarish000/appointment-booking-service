package com.enpal.abs.controller;

import com.enpal.abs.dto.AppointmentRequest;
import com.enpal.abs.dto.AvailableSlotResponse;
import com.enpal.abs.service.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
// TODO: Use '/v1/calendar' to have versioning in the API
public class SlotController {
    @Autowired
    private IAppointmentService appointmentService;

    @Operation(summary = "Query available slots", description = "Returns a list of available slots based on the appointment request criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved available slots"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/query")
    public List<AvailableSlotResponse> queryAvailableSlots(@Valid @RequestBody AppointmentRequest request) {
        return appointmentService.getAvailableSlots(request);
    }
}
