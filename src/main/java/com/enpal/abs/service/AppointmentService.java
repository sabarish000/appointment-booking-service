package com.enpal.abs.service;

import com.enpal.abs.dto.AppointmentRequest;
import com.enpal.abs.dto.AvailableSlotResponse;
import com.enpal.abs.model.SalesManager;
import com.enpal.abs.model.Slot;
import com.enpal.abs.repository.SalesManagerRepository;
import com.enpal.abs.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {
    public static final String YYYY_MM_DD_T_HH_MM_SS_SSSX = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private SalesManagerRepository salesManagerRepository;

    public List<AvailableSlotResponse> getAvailableSlots(AppointmentRequest request) {
        LocalDate date = LocalDate.parse(request.getDate());
        ZonedDateTime startOfDay = date.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endOfDay = startOfDay.plusDays(1);
        // Convert products list to array literal (e.g., "{SolarPanels,WindTurbines}")
        String productsArrayLiteral = String.format("{%s}", String.join(",", request.getProducts()));

        List<Slot> slots = slotRepository.findAvailableSlots(startOfDay, endOfDay, request.getLanguage(), request.getRating(), productsArrayLiteral);
        return slots.stream()
                .collect(Collectors.groupingBy(Slot::getStartDate))
                .entrySet()
                .stream()
                .sorted((a,b) -> a.getKey().compareTo(b.getKey()))
                .map(entry -> new AvailableSlotResponse(entry.getValue().size(),
                        DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_SSSX).format(entry.getKey())))
                .collect(Collectors.toList());
    }
}
