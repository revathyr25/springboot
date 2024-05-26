package com.example.UserService.UserService.Service;

import com.example.UserService.UserService.Exceptions.ResourceNotFoundException;
import com.example.UserService.UserService.Repository.UserRepository;
import com.example.UserService.UserService.dto.Hotel;
import com.example.UserService.UserService.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    //get single user
    
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        Hotel[] hotelsOfUser = restTemplate.getForObject("http://localhost:8083/hotels/byUserId/" + user.getUserId(), Hotel[].class);
//        Hotel[] hotelsOfUser = restTemplate.getForObject("http://localhost:8082/hotels/byUserId/" + user.getUserId(), Hotel[].class);
        List<Hotel> hotels = Arrays.stream(hotelsOfUser).toList();

        user.setHotels(hotels);

        return user;
    }
}
