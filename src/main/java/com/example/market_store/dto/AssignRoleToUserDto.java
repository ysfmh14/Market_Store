package com.example.market_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AssignRoleToUserDto {
    private String userName;
    private String roleName;
}
