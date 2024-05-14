package com.example.market_store.service.Impl;

import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.ConfirmationMailDto;
import com.example.market_store.dto.LogoutUserDto;
import com.example.market_store.dto.ResetPasswordDto;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.dto.requestDto.RequestValidationCodeDto;
import com.example.market_store.dto.responseDto.ResponseValidationCodeDto;
import com.example.market_store.service.KeycloakService;
import com.example.market_store.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private Keycloak keycloak;
    private MailSenderService mailSenderService;
    private final Map<String, String> codeMap = new HashMap<>();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Override
    public void createUser(RequestUsersDto requestUsersDto) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(requestUsersDto.getEmail());
        user.setEmail(requestUsersDto.getEmail());
        user.setFirstName(requestUsersDto.getFirstName());
        user.setLastName(requestUsersDto.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setUserLabel("My password");
        credential.setTemporary(false);
        credential.setValue(requestUsersDto.getPassword());
        user.setCredentials(Arrays.asList(credential));
        keycloak.realm("master").users().create(user);
        assignRoleToUser(AssignRoleToUserDto.builder()
                .userName(requestUsersDto.getEmail())
                .roleName("user")
                .build());
    }
    @Override
    public void updateUser(RequestUsersDto requestUsersDto) {
        System.out.println(requestUsersDto.getFirstName());
        List<UserRepresentation> users = keycloak.realm("master")
                .users()
                .search(requestUsersDto.getEmail());
        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            System.out.println(user);
            user.setEnabled(true);
            user.setFirstName(requestUsersDto.getFirstName());
            user.setLastName(requestUsersDto.getLastName());
            UserResource userResource = keycloak.realm("master")
                    .users()
                    .get(user.getId());
            userResource.update(user);
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            System.out.println("Utilisateur non trouvé.");
        }
    }
    @Override
    public void createSeller(RequestSellerDto requestSellerDto) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(requestSellerDto.getEmail());
        user.setEmail(requestSellerDto.getEmail());
        user.setFirstName(requestSellerDto.getFirstName());
        user.setLastName(requestSellerDto.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setUserLabel("My password");
        credential.setTemporary(false);
        credential.setValue(requestSellerDto.getPassword());
        user.setCredentials(Arrays.asList(credential));
        keycloak.realm("master").users().create(user);
        assignRoleToUser(AssignRoleToUserDto.builder()
                .userName(requestSellerDto.getEmail())
                .roleName("seller")
                .build());
    }

    @Override
    public void updateSeller(RequestSellerDto requestSellerDto) {
        List<UserRepresentation> users = keycloak.realm("master")
                .users()
                .search(requestSellerDto.getEmail());
        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            user.setEnabled(true);
            user.setFirstName(requestSellerDto.getFirstName());
            user.setLastName(requestSellerDto.getLastName());
            UserResource userResource = keycloak.realm("master")
                    .users()
                    .get(user.getId());
            userResource.update(user);
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            System.out.println("seller non trouvé.");
        }
    }

    @Override
    public void createDeliveryMan(RequestDeliverymanDto requestDeliverymanDto) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(requestDeliverymanDto.getEmail());
        user.setEmail(requestDeliverymanDto.getEmail());
        user.setFirstName(requestDeliverymanDto.getFirstName());
        user.setLastName(requestDeliverymanDto.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setUserLabel("My password");
        credential.setTemporary(false);
        credential.setValue(requestDeliverymanDto.getPassword());
        user.setCredentials(Arrays.asList(credential));
        keycloak.realm("master").users().create(user);
        assignRoleToUser(AssignRoleToUserDto.builder()
                .userName(requestDeliverymanDto.getEmail())
                .roleName("deliveryman")
                .build());
    }


    @Override
    public void deleteUser(String userName) {

        List<UserRepresentation> users = keycloak.realm("master").users().search(userName);
        System.out.println(users.get(0).getId());
        keycloak.realm("master").users().delete(users.get(0).getId());
    }

    @Override
    public void deleteSeller(String userName) {

    }

    @Override
    public void assignRoleToUser(AssignRoleToUserDto assignRoleToUserDto) {
        UsersResource usersResource = keycloak.realm("master").users();

        List<UserRepresentation> users = usersResource.search(assignRoleToUserDto.getUserName());

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String userId = user.getId();

            List<RoleRepresentation> availableRoles = keycloak.realm("master").roles().list();
            System.out.println(assignRoleToUserDto.getRoleName());
            RoleRepresentation role = availableRoles.stream()
                    .filter(r -> r.getName().equals(assignRoleToUserDto.getRoleName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Role not found: " + assignRoleToUserDto.getRoleName()));
            UserResource userResource = usersResource.get(userId);
            userResource.roles().realmLevel().add(List.of(role));
        } else {
            throw new RuntimeException("User not found: " + assignRoleToUserDto.getUserName());
        }
    }

    @Override
    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        UsersResource usersResource = keycloak.realm("master").users();

        List<UserRepresentation> users = usersResource.search(resetPasswordDto.getUserName());

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);

            CredentialRepresentation newCredential = new CredentialRepresentation();
            newCredential.setType(CredentialRepresentation.PASSWORD);
            newCredential.setValue(resetPasswordDto.getNewPassword());

            UserResource userResource = usersResource.get(user.getId());
            userResource.resetPassword(newCredential);
        } else {
            throw new RuntimeException("User not found: " + resetPasswordDto.getUserName());
        }
    }

    @Override
    public void logoutUser(LogoutUserDto logoutUserDto) {
        UsersResource usersResource = keycloak.realm("master").users();
        System.out.println(logoutUserDto.getUserName());
        List<UserRepresentation> users = usersResource.search(logoutUserDto.getUserName());
        System.out.println(users.get(0).getId());
        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String userId = user.getId();
            System.out.println(userId);
            // Utilisez la méthode logout() sur l'objet UserResource pour déconnecter l'utilisateur
            usersResource.get(userId).logout();

        } else {
            throw new RuntimeException("User not found: " + logoutUserDto.getUserName());
        }

    }

    @Override
    public String generateConfirmationMail(ConfirmationMailDto confirmationMailDto) {
        String code = generateRandomCode();
        mailSenderService.sendNewMail(confirmationMailDto.getEmail(),"Reset password code","Hello ;\nYour confirmation code\n "+code);
        codeMap.put(confirmationMailDto.getEmail(),code);
        scheduleCodeRemoval(confirmationMailDto.getEmail());
        return code;
    }
    private void scheduleCodeRemoval(String email) {
        executorService.schedule(() -> codeMap.remove(email), 1, TimeUnit.MINUTES);
    }
    @Override
    public ResponseValidationCodeDto codeValidation(RequestValidationCodeDto requestValidationCodeDto) {
          String code = codeMap.get(requestValidationCodeDto.getEmail());
          ResponseValidationCodeDto responseValidationCodeDto = new ResponseValidationCodeDto();
          if (code != null && code.equals(requestValidationCodeDto.getCode())){
              responseValidationCodeDto.setValid(true);
          }else {
              responseValidationCodeDto.setValid(false);
          }
      return responseValidationCodeDto;
    }


    public String generateRandomCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return code.toString();
    }


}
