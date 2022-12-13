package com.cookos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private int messageID;

    private String Question;

    private String Answer;

    @ManyToOne
    @JoinColumn(name = "User_userID", referencedColumnName = "userID")
    private User user;


}
