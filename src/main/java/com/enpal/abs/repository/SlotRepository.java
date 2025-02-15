package com.enpal.abs.repository;

import com.enpal.abs.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    @Query(value = """
            SELECT s.*
            FROM slots s
            JOIN sales_managers sm ON s.sales_manager_id = sm.id
                    WHERE s.start_date >= :startDate
                      AND s.start_date < :endDate
                      AND s.booked = FALSE
                      AND :language = ANY(sm.languages)
                      AND :rating = ANY(sm.customer_ratings)
                      AND sm.products @> CAST(:products AS VARCHAR[])
                      AND NOT EXISTS (
                          SELECT 1
                          FROM slots s2
                          WHERE s2.sales_manager_id = s.sales_manager_id
                            AND s2.booked = TRUE
                            AND (
                                (s2.start_date < s.start_date AND s2.end_date > s.start_date) OR
                                (s2.start_date < s.end_date AND s2.end_date > s.end_date) OR
                                (s2.start_date >= s.start_date AND s2.end_date <= s.end_date)
                            )
                      )
            """, nativeQuery = true)
    List<Slot> findAvailableSlots(ZonedDateTime startDate, ZonedDateTime endDate, String language, String rating, String products);
}
