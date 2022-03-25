package com.ssafy.myapp.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@ToString
@Table(name = "Performance")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_Id")
    private Long id;

    private String showName;

    @OneToMany(mappedBy = "show")
    private List<Review> reviews = new ArrayList<>();
}
