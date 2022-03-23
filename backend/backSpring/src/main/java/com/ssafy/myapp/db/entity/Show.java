package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter    // getter 자동 추가
@Entity    // entity
@NoArgsConstructor    // 기본생성자 자동 추가
@Table(name = "show")
public class Show {

    // 공연정보는 수정하지 않기 때문에 setter 생성x

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 기본키 생성을 DB에 위임한다.MySQL의 경우 AUTO_INCREMENT를 사용하여 기본키를 생성한다.
    private Long id;    // PK(auto increment) 공연 id

    @Column(nullable = false)    // null 허용 불가능
    private String show_name;    // 공연이름

    @Column(nullable = false)
    private String show_id;    // 인터파크 기준 공연 id => 리뷰, 출연진 join 하기 위함.

    @Column(nullable = false)
    private String start_date;    // 공연시작일

    private String end_date;    // 공연 종료일

    private String openrun;    // 오픈런 여부

    @Column(nullable = false)
    private String producer;    // 제작사

    @Column(nullable = false)
    private String age;    // 관람등급

    @Column(nullable = false)
    private String runtime;    // 공연시간

    @Column(length = 500, nullable = false)
    private String price;    // 가격정보

    @Column(length = 500, nullable = false)
    private String poster_path;    // 포스터 경로

    private String show_day;    // 공연상세일시

    @Column(nullable = false)
    private String category;    // 공연 카테고리

    @Column(nullable = false)
    private String art_center_name;    // 공연장

    @Column(nullable = false)
    private String men_rate;    // 남성이용자 예매율

    @Column(nullable = false)
    private String women_rate;    // 여성 이용자 예매율

}