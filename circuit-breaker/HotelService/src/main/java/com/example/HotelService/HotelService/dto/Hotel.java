package com.example.HotelService.HotelService.dto;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    private  String id;
    private  String name;
    private  String location;
    private  String about;
    private String userId;

}
