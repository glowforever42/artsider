package com.ssafy.myapp.db.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String telNum;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

}
