package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ArtCenter {

    // 공연장 정보는 수정하지 않기 때문에 setter 생성x

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String art_center_name;

    private String art_center_address;

    private String art_center_tel;

    private String art_center_web;

}
