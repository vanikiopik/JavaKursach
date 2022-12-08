package com.cookos.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Builder
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;

    private String login;

    private byte[] password;

    private String userName;

    private String userSurname;

    private String userCity;

    private int isAdmin;

    public User(int Id, String login, byte[] password,
                String userName, String userSurname,
                 String userCity, int isAdmin) {
        this.UserID = Id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userCity = userCity;
        this.isAdmin = isAdmin;
    }


    public User() {

    }
}
