package com.albinus.netflix.lib.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String fullName;
    private String email;
    private String password;


}