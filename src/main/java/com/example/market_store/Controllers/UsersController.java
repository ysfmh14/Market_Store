package com.example.market_store.Controllers;

import com.example.market_store.dtos.RequestUsersDto;
import com.example.market_store.dtos.ResponseUsersDto;
import com.example.market_store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {
    private UserService userService;
    @GetMapping("/users")
    public Page<ResponseUsersDto> getAllUsers(@RequestParam(defaultValue = "0", name ="page") int page,
                                              @RequestParam(defaultValue = "10" , name = "size") int size){

        return userService.getAllUsers(page,size);
    }
    @GetMapping("/users/{id}")
    public ResponseUsersDto getAllUsers(@PathVariable(name ="id") Long id){

        return userService.getUserById(id);
    }
    @PostMapping("/users")
    public ResponseUsersDto save(@RequestBody RequestUsersDto requestUserDto){
        return userService.addUser(requestUserDto);
    }
    @PutMapping ("/users")
    public ResponseUsersDto update(@RequestBody RequestUsersDto requestUserDto){
        return userService.UpdateUser(requestUserDto);
    }
    @DeleteMapping("/users")
    public void delete(@RequestParam(name ="id") Long id){
         userService.deleteUser(id);
    }
}

