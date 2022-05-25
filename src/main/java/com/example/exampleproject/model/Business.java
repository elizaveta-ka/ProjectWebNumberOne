package com.example.exampleproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int business_id;
    @Column(name = "busName")
    private String busName;
    @Column(name = "location")
    private String location;
    @Column(name = "bus_img")
    private String bus_img;
}
