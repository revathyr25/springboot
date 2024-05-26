package com.example.HotelService.HotelService.Repository;

import com.example.HotelService.HotelService.dto.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, String> {
    List<Hotel> findByUserId(String userId);
}
