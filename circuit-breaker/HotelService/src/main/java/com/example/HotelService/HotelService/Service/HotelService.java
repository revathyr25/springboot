package com.example.HotelService.HotelService.Service;

import com.example.HotelService.HotelService.Exceptions.ResourceNotFoundException;
import com.example.HotelService.HotelService.Repository.HotelRepository;
import com.example.HotelService.HotelService.dto.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
    }
    public List<Hotel> getByUserId(String userId) {
        return hotelRepository.findByUserId(userId);
    }
}
