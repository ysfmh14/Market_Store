package com.example.market_store.service.Impl;

import com.example.market_store.criteria.UserCriteria;
import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.responseDto.ResponseUsersDto;
import com.example.market_store.entitie.Users;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.UsersMapper;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.KeycloakService;
import com.example.market_store.service.UserService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.market_store.dto.AssignRoleToUserDto.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UsersRepo usersRepo;
    private UsersMapper usersMapper;
    private KeycloakService keycloakService;


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
    public Page<ResponseUsersDto> findUsersByCriteria(UserCriteria userCriteria,int page , int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Users> usersPage = usersRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (userCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),userCriteria.getId()));
            }
            if (userCriteria.getFirstName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("firstName"),userCriteria.getFirstName()));
            }
            if (userCriteria.getLastName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("lastName"),userCriteria.getLastName()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
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

        String generatedCodeUser = "USR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Users userToSave = usersMapper.dtoToModel(requestUserDto);
        userToSave.setUserCode(generatedCodeUser);
        Optional<Users> existingUser = usersRepo.findByUserCode(requestUserDto.getUserCode());
        if (existingUser.isPresent()) {
            throw new EntityAlreadyExisteException("User already exists with id: " + requestUserDto.getUserCode());
        }

        keycloakService.createUser(requestUserDto);
        Users savedUser = usersRepo.save(userToSave);
        return usersMapper.modelToDto(savedUser);
    }

    @Override
    public ResponseUsersDto UpdateUser(RequestUsersDto requestUserDto) {
        System.out.println(requestUserDto.getUserCode());
        Optional<Users> existingUser = usersRepo.findByUserCode(requestUserDto.getUserCode());
        if (existingUser.isEmpty()){
            throw new EntityNotFoundException("User Not Found ");
        }
        Users userToUpdate = usersMapper.dtoToModel(requestUserDto);
        userToUpdate.setUserCode(existingUser.get().getUserCode());
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
        keycloakService.deleteUser(user.get().getFirstName());
    }
}
