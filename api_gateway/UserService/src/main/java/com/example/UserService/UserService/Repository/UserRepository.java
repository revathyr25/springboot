package com.example.UserService.UserService.Repository;
import com.example.UserService.UserService.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
}
