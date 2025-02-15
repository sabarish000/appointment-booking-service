package com.enpal.abs.dto;


import com.enpal.abs.validation.ValidDate;
import com.enpal.abs.validation.ValidLanguage;
import com.enpal.abs.validation.ValidProducts;
import com.enpal.abs.validation.ValidRating;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppointmentRequest {
    @Schema(description = "The date of the appointment", example = "2024-02-28", required = true)
    @ValidDate
    private String date;

    @Schema(description = "List of products for the appointment. Supported products: SolarPanels, Heatpumps", example = "[\"SolarPanels\", \"Heatpumps\"]", required = true)
    @ValidProducts(message = "Products cannot be empty and accepts only list of allowed values: [SolarPanels, Heatpumps]")
    private List<String> products;

    @Schema(description = "Preferred language for the appointment. Supported languages: German, English", example = "English", required = true)
    @ValidLanguage(message = "Language cannot be empty and allowed values are: German, English")
    private String language;

    @Schema(description = "Customer rating for the appointment. Supported ratings: Gold, Silver, Bronze", example = "Gold", required = true)
    @ValidRating(message = "Rating cannot be empty and allowed values are: Gold, Silver, Bronze")
    private String rating;
}
