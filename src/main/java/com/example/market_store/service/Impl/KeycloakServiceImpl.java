package com.example.market_store.service.Impl;

import com.example.market_store.dto.requestDto.RequestUsersDto;
import com.example.market_store.service.KeycloakService;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
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
        keycloak.realm("MarktStoreKeyCloak").users().create(user);
    }

    @Override
    public void deleteUser(String userName) {

        List<UserRepresentation> users = keycloak.realm("MarktStoreKeyCloak").users().search(userName);

        keycloak.realm("MarktStoreKeyCloak").users().delete(users.get(0).getId());
    }

    @Override
    public void assignRoleToUser(String userName, String roleName) {
        UsersResource usersResource = keycloak.realm("MarktStoreKeyCloak").users();

        List<UserRepresentation> users = usersResource.search(userName);

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String userId = user.getId();

            List<RoleRepresentation> availableRoles = keycloak.realm("MarktStoreKeyCloak").roles().list();

            RoleRepresentation role = availableRoles.stream()
                    .filter(r -> r.getName().equals(roleName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            UserResource userResource = usersResource.get(userId);
            userResource.roles().realmLevel().add(List.of(role));
        } else {
            throw new RuntimeException("User not found: " + userName);
        }
    }

}
