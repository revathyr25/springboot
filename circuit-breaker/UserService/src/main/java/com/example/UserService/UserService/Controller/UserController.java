package com.example.UserService.UserService.Controller;


import com.example.UserService.UserService.Service.UserService;
import com.example.UserService.UserService.dto.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get


    int retryCount=1;
    @GetMapping("/{userId}")
    @CircuitBreaker(name="hotelBreaker" ,fallbackMethod = "hotelFallback")
    //@Retry(name="hotelService",fallbackMethod = "hotelFallback" )
//    @RateLimiter(name="userRateLimiter",fallbackMethod = "hotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry count:{}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> hotelFallback(String userId, Exception e){
        logger.info("Fallback is executed bcos service is down",e.getMessage());
        User user=User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created bcos service is down")
                .userId("141234")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //creating fall back  method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
