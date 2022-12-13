package com.cookos.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (mappedBy = "User_userID", fetch = FetchType.EAGER)
    private Set<Order> orders;

    private String login;

    private byte[] password;

    private String userName;

    private String userSurname;

    private String userCity;

    private int isAdmin;

    public User(int Id, String login, byte[] password,
                String userName, String userSurname,
                 String userCity, int isAdmin) {
        this.userID = Id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userCity = userCity;
        this.isAdmin = isAdmin;
    }

    public User() {
        
    }

    public User(String login, byte[] password, String userName,
                String userSurname, String userCity,
                int isAdmin) {
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userCity = userCity;
        this.isAdmin = isAdmin;
    }
}
