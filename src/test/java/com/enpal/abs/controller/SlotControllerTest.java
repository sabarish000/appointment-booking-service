package com.enpal.abs.controller;

import com.enpal.abs.dto.AppointmentRequest;
import com.enpal.abs.service.IAppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SlotController.class)
public class SlotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IAppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testQueryAppointment_Success() throws Exception {
        AppointmentRequest request = new AppointmentRequest();
        request.setDate("2024-02-28");
        request.setProducts(List.of("SolarPanels", "Heatpumps"));
        request.setLanguage("English");
        request.setRating("Gold");

        when(appointmentService.getAvailableSlots(any(AppointmentRequest.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryAppointment_InvalidDate() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-30\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_EmptyDate() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"5\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_InvalidProductType() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"InvalidProduct\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_NullDate() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_EmptyProducts() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_NullProducts() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_InvalidLanguage() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\"],\"language\":\"InvalidLanguage\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_EmptyLanguage() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_NullLanguage() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_InvalidRating() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\"],\"language\":\"English\",\"rating\":\"InvalidRating\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_EmptyRating() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_NullRating() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_MissingRequestBody() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_InvalidJsonFormat() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\",\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQueryAppointment_UnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/calendar/query")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testQueryAppointment_InvalidUrl() throws Exception {
        mockMvc.perform(post("/calendar/invalid-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-02-28\",\"products\":[\"SolarPanels\",\"Heatpumps\"],\"language\":\"English\",\"rating\":\"Gold\"}"))
                .andExpect(status().isNotFound());
    }
}
