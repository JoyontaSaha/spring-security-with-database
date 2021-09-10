package com.joyonta.springsecuritywithdatabase.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiUrlRole {
    @Id
    private Long id;
    private String apiUrl;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ApiUrlRole{" +
                "id=" + id +
                ", apiUrlRole='" + apiUrl + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
