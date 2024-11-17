package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocation;

    private String province;
    private String city;
    private Float lon;
    private Float lat;

}
