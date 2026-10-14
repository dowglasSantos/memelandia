package br.com.ebac.memelandia.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime dateTime;

    public UserDTO() {}

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public UserDTO(Long id, String name, String email, LocalDateTime dataTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateTime = dataTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
