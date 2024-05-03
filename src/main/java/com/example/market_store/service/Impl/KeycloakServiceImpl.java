package com.example.market_store.service.Impl;

import com.example.market_store.dto.AssignRoleToUserDto;
import com.example.market_store.dto.LogoutUserDto;
import com.example.market_store.dto.ResetPasswordDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.service.KeycloakService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private Keycloak keycloak;
    @Override
    public void createUser(RequestUsersDto requestUsersDto) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(requestUsersDto.getFirstName());
        user.setEmail(requestUsersDto.getEmail());
        user.setFirstName(requestUsersDto.getFirstName());
        user.setLastName(requestUsersDto.getLastName());
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("12345");
        user.setCredentials(Arrays.asList(credential));
        keycloak.realm("master").users().create(user);
    }

    @Override
    public void deleteUser(String userName) {

        List<UserRepresentation> users = keycloak.realm("master").users().search(userName);

        keycloak.realm("MarktStoreKeyCloak").users().delete(users.get(0).getId());
    }

    @Override
    public void assignRoleToUser(AssignRoleToUserDto assignRoleToUserDto) {
        UsersResource usersResource = keycloak.realm("master").users();

        List<UserRepresentation> users = usersResource.search(assignRoleToUserDto.getUserName());

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String userId = user.getId();

            List<RoleRepresentation> availableRoles = keycloak.realm("master").roles().list();

            RoleRepresentation role = availableRoles.stream()
                    .filter(r -> r.getName().equals(assignRoleToUserDto.getRoleName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Role not found: " + assignRoleToUserDto.getUserName()));
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

}
