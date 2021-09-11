package com.joyonta.springsecuritywithdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRole {
    @Id
    private Long id;
    private String apiurl;
    private String methodname;
    private String role;

}
