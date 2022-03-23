package com.ssafy.myapp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDetailsGetRes {
    private Long id;    // id
    private String show_name;    // 공연이름
    private String show_id;    // 인터파크 기준 공연 id => 리뷰, 출연진 join 하기 위함.
    private String start_date;    // 공연시작일
    private String end_date;    // 공연 종료일
    private String openrun;    // 오픈런 여부
    private String producer;    // 제작사
    private String age;    // 관람등급
    private String runtime;    // 공연시간
    private String price;    // 가격정보
    private String poster_path;    // 포스터 경로
    private String show_day;    // 공연상세일시
    private String category;    // 공연 카테고리
    private String art_center_name;    // 공연장
    private String men_rate;    // 남성이용자 예매율
    private String women_rate;    // 여성 이용자 예매율

}



