package com.vlad.backend.dto;

public class UserDTO {
    private Long id_usr;
    private String email;
    private String password;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id_usr, String email, String password, String name ) {
        this.id_usr = id_usr;
        this.name = email;
        this.password = password;
        this.name = name;
    }

    public Long getId_usr() { return id_usr; }

    public void setId_usr(Long id_usr) { this.id_usr = id_usr; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
