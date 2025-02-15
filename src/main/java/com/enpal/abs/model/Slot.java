package com.enpal.abs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private boolean booked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_manager_id")
    private SalesManager salesManager;
}
