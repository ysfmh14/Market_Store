package com.example.market_store.Controller;

import com.example.market_store.criteria.UserCriteria;
import com.example.market_store.dto.RequestUsersDto;
import com.example.market_store.dto.ResponseUsersDto;
import com.example.market_store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    @GetMapping
    public Page<ResponseUsersDto> getAllUsers(@RequestParam(defaultValue = "0", name ="page") int page,
                                              @RequestParam(defaultValue = "10" , name = "size") int size){

        return userService.getAllUsers(page,size);
    }
    @GetMapping("/criteria")
    Page<ResponseUsersDto> getUsersByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                              @RequestParam(defaultValue = "10" , name = "size") int size,
                                              @RequestParam( name = "id", required = false) Long id ,
                                            @RequestParam(name = "lastName", required = false) String lastName ,
                                            @RequestParam(name = "firstName", required = false) String firstName){

        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setId(id);
        userCriteria.setLastName(lastName);
        userCriteria.setFirstName(firstName);
        return userService.findUsersByCriteria(userCriteria,page,size);
    }
    @GetMapping("/{id}")
    public ResponseUsersDto getAllUsers(@PathVariable(name ="id") Long id){

        return userService.getUserById(id);
    }
    @PostMapping
    public ResponseUsersDto save(@RequestBody RequestUsersDto requestUserDto){
        return userService.addUser(requestUserDto);
    }
    @PutMapping
    public ResponseUsersDto update(@RequestBody RequestUsersDto requestUserDto){
        return userService.UpdateUser(requestUserDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
         userService.deleteUser(id);
    }
}

