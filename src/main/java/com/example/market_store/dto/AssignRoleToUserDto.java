package com.example.market_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AssignRoleToUserDto {
    private String userName;
    private String roleName;
}
