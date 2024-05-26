package com.example.HotelService.HotelService.Controller;



import com.example.HotelService.HotelService.Service.HotelService;
import com.example.HotelService.HotelService.dto.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelDetailsbyId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    @GetMapping("byUserId/{userId}")
    public ResponseEntity<List<Hotel>> getHotelDetailsbyUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getByUserId(userId));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }


}
