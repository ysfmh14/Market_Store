package com.example.market_store.Controller;

import com.example.market_store.criteria.UserCriteria;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.responseDto.ResponseUsersDto;
import com.example.market_store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {
    private UserService userService;
    @GetMapping
//    @PreAuthorize("hasRole('admin')")
    public Page<ResponseUsersDto> getAllUsers(@RequestParam(defaultValue = "0", name ="page") int page,
                                              @RequestParam(defaultValue = "10" , name = "size") int size){

        return userService.getAllUsers(page,size);
    }
    @GetMapping("/criteria")
//    @PreAuthorize("hasRole('admin')")
    Page<ResponseUsersDto> getUsersByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                              @RequestParam(defaultValue = "10" , name = "size") int size,
                                              @RequestParam( name = "id", required = false) Long id ,
                                            @RequestParam(name = "lastName", required = false) String lastName ,
                                            @RequestParam(name = "firstName", required = false) String firstName,
                                              @RequestParam(name = "userCode", required = false) String userCode){

        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setId(id);
        userCriteria.setLastName(lastName);
        userCriteria.setFirstName(firstName);
        userCriteria.setUserCode(userCode);
        return userService.findUsersByCriteria(userCriteria,page,size);
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('admin')")
    public ResponseUsersDto getUserById(@PathVariable(name ="id") Long id){

        return userService.getUserById(id);
    }
    @PostMapping
//    @PreAuthorize("hasRole('admin') or hasRole('client_user')")
    public ResponseUsersDto save(@RequestBody RequestUsersDto requestUserDto){
        return userService.addUser(requestUserDto);
    }
    @PutMapping
//    @PreAuthorize("hasRole('admin') or hasRole('client_user')")
    public ResponseUsersDto update(@RequestBody RequestUsersDto requestUserDto){
        return userService.UpdateUser(requestUserDto);
    }
    @DeleteMapping
//    @PreAuthorize("hasRole('admin') or hasRole('client_user')")
    public void delete(@RequestParam(name ="code") String code){
         userService.deleteUser(code);
    }
}

