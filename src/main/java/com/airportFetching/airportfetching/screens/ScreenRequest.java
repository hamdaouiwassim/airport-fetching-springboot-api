package com.airportFetching.airportfetching.screens;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenRequest {
    private String country;
    private String type;
    private String name;
    private String case_id;
    private String gender;
    private Date birth_date;
    private String location;
    private String nationality;
}

