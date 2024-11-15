package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_location;

    private String province;
    private String city;
    private float lon;
    private float lat;

}
