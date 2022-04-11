package com.zapphireye.zapphireye.model.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String Id;
    private String username;
    private String password;
    private String role;

    public User() {
    }
}
