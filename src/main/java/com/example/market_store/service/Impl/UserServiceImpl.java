package com.example.market_store.service.Impl;

import com.example.market_store.dto.RequestUsersDto;
import com.example.market_store.dto.ResponseUsersDto;
import com.example.market_store.entitie.Users;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.UsersMapper;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UsersRepo usersRepo;
    private UsersMapper usersMapper;
    @Override
    public Page<ResponseUsersDto> getAllUsers(int page , int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Users> usersPage = usersRepo.findAll(pageable);
        if (usersPage.isEmpty()){
            throw new EntityNotFoundException("users Not Found");
        }
        return usersMapper.modelToDtos(usersPage);
    }

    @Override
    public ResponseUsersDto getUserById(long id) {
        Optional<Users> user =  usersRepo.findById(id);
        if (!user.isPresent()){
            throw new EntityNotFoundException("user not found");
        }
        return usersMapper.modelToDto(user.get());
    }

    @Override
    public ResponseUsersDto addUser(RequestUsersDto requestUserDto) {
        Optional<Users> existingUser = usersRepo.findById(requestUserDto.getId());
        if (existingUser.isPresent()) {
            throw new EntityAlreadyExisteException("User already exists with id: " + requestUserDto.getId());
        }
        String generatedCodeUser = "USR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Users userToSave = usersMapper.dtoToModel(requestUserDto);
        userToSave.setUserCode(generatedCodeUser);
        Users savedUser = usersRepo.save(userToSave);
        return usersMapper.modelToDto(savedUser);
    }

    @Override
    public ResponseUsersDto UpdateUser(RequestUsersDto requestUserDto) {
        System.out.println(requestUserDto.getUserCode());
        Optional<Users> existingUser = usersRepo.findById(requestUserDto.getId());
//        if (!existingUser.isPresent()){
//            throw new EntityNotFoundException("User Not Found   ");
//        }
        Users userToUpdate = usersMapper.dtoToModel(requestUserDto);
        Users updatedUser = usersRepo.save(userToUpdate);
        return usersMapper.modelToDto(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        Optional<Users> user = usersRepo.findById(id);
        if (!user.isPresent()){
            throw new EntityNotFoundException("User Not Found ID :  "+id);
        }
        usersRepo.deleteById(id);
    }
}
