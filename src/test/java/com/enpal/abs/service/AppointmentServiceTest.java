package com.enpal.abs.service;

import com.enpal.abs.dto.AppointmentRequest;
import com.enpal.abs.dto.AvailableSlotResponse;
import com.enpal.abs.model.SalesManager;
import com.enpal.abs.model.Slot;
import com.enpal.abs.repository.SalesManagerRepository;
import com.enpal.abs.repository.SlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentServiceTest {
    @Mock
    private SlotRepository slotRepository;

    @Mock
    private SalesManagerRepository salesManagerRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableSlots_NoMatchingSlots() {
        // Arrange
        AppointmentRequest request = new AppointmentRequest();
        request.setDate("2024-05-03");
        request.setProducts(Arrays.asList("SolarPanels", "Heatpumps"));
        request.setLanguage("German");
        request.setRating("Gold");

        when(slotRepository.findAvailableSlots(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());

        // Act
        List<AvailableSlotResponse> result = appointmentService.getAvailableSlots(request);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void testGetAvailableSlots_MatchingSlots() {
        // Arrange
        AppointmentRequest request = new AppointmentRequest();
        request.setDate("2024-05-03");
        request.setProducts(Arrays.asList("SolarPanels", "Heatpumps"));
        request.setLanguage("German");
        request.setRating("Gold");

        SalesManager salesManager = new SalesManager();
        salesManager.setLanguages(List.of("German", "English"));
        salesManager.setProducts(List.of("SolarPanels", "Heatpumps"));
        salesManager.setCustomerRatings(List.of("Gold", "Silver"));

        Slot slot1 = new Slot();
        slot1.setStartDate(ZonedDateTime.parse("2024-05-03T10:30:00Z"));
        slot1.setEndDate(ZonedDateTime.parse("2024-05-03T11:30:00Z"));
        slot1.setBooked(false);
        slot1.setSalesManager(salesManager);

        Slot slot2 = new Slot();
        slot2.setStartDate(ZonedDateTime.parse("2024-05-03T12:00:00Z"));
        slot2.setEndDate(ZonedDateTime.parse("2024-05-03T13:00:00Z"));
        slot2.setBooked(false);
        slot2.setSalesManager(salesManager);

        when(slotRepository.findAvailableSlots(any(), any(), any(), any(), any()))
                .thenReturn(Arrays.asList(slot1, slot2));

        // Act
        List<AvailableSlotResponse> result = appointmentService.getAvailableSlots(request);

        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getAvailableCount());
        assertEquals("2024-05-03T10:30:00.000Z", result.get(0).getStartDate());
        assertEquals(1, result.get(1).getAvailableCount());
        assertEquals("2024-05-03T12:00:00.000Z", result.get(1).getStartDate());
    }

    @Test
    void testGetAvailableSlots_OverlappingSlots() {
        // Arrange
        AppointmentRequest request = new AppointmentRequest();
        request.setDate("2024-05-03");
        request.setProducts(List.of("SolarPanels", "Heatpumps"));
        request.setLanguage("German");
        request.setRating("Gold");

        SalesManager salesManager1 = new SalesManager();
        salesManager1.setLanguages(List.of("German", "English"));
        salesManager1.setProducts(List.of("SolarPanels", "Heatpumps"));
        salesManager1.setCustomerRatings(List.of("Gold", "Silver"));

        SalesManager salesManager2 = new SalesManager();
        salesManager2.setLanguages(List.of("German", "French"));
        salesManager2.setProducts(List.of("SolarPanels", "Heatpumps"));
        salesManager2.setCustomerRatings(List.of("Gold", "Bronze"));

        Slot slot1 = new Slot();
        slot1.setStartDate(ZonedDateTime.parse("2024-05-03T10:30:00Z"));
        slot1.setEndDate(ZonedDateTime.parse("2024-05-03T11:30:00Z"));
        slot1.setBooked(false);
        slot1.setSalesManager(salesManager1);

        Slot slot2 = new Slot();
        slot2.setStartDate(ZonedDateTime.parse("2024-05-03T11:00:00Z"));
        slot2.setEndDate(ZonedDateTime.parse("2024-05-03T12:00:00Z"));
        slot2.setBooked(false);
        slot2.setSalesManager(salesManager2);

        when(slotRepository.findAvailableSlots(any(), any(), any(), any(), any()))
                .thenReturn(Arrays.asList(slot1, slot2));

        // Act
        List<AvailableSlotResponse> result = appointmentService.getAvailableSlots(request);
        System.out.println(result);
        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getAvailableCount());
        assertEquals("2024-05-03T10:30:00.000Z", result.get(0).getStartDate());
        assertEquals(1, result.get(1).getAvailableCount());
        assertEquals("2024-05-03T11:00:00.000Z", result.get(1).getStartDate());
    }

    @Test
    void testGetAvailableSlots_NoMatchingCriteria() {
        // Arrange
        AppointmentRequest request = new AppointmentRequest();
        request.setDate("2024-05-03");
        request.setProducts(Arrays.asList("SolarPanels", "Heatpumps"));
        request.setLanguage("French"); // No matching language
        request.setRating("Gold");


        when(slotRepository.findAvailableSlots(any(), any(), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        // Act
        List<AvailableSlotResponse> result = appointmentService.getAvailableSlots(request);

        // Assert
        assertEquals(0, result.size());
    }
}
