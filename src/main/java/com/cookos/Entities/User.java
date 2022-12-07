package com.cookos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private int UserID;


    private String login;

    private String password;

    private String userName;

    private String userSurname;

    private String userPhoneNum;

    private String userCity;

    private int isAdmin;

    public User(int userID, String login, String password,
                String userName, String userSurname,
                String userPhoneNum, String userCity, int isAdmin) {
        this.UserID = userID;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPhoneNum = userPhoneNum;
        this.userCity = userCity;
        this.isAdmin = isAdmin;
    }


    public User() {

    }
}
