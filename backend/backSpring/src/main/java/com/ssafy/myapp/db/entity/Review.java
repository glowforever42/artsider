package com.ssafy.myapp.db.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.myapp.api.request.ReviewRegisterReq;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Review_ID")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String contents;

    private int rating;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    @JsonIgnore // 순환참조 방지
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Show_Id")
    @JsonIgnore // 순환참조 방지
    private Show show;

    // === 연관관계 메소드 ===
    public void setUser(User user) {
        if (this.user != null) { // 기존에 이미 유저가 존재한다면
            this.user.getReviews().remove(this); // 관계를 끊는다.
        }
        this.user = user;
        user.getReviews().add(this);
    }

    public void setShow(Show show) {
        if (this.show != null) { // 기존에 이미 공연이 존재한다면
            this.show.getReviews().remove(this); // 관계를 끊는다.
        }
        this.show = show;
        show.getReviews().add(this);
    }

    // ===생성 메서드===
    public static Review createReview(User user, Show show, ReviewRegisterReq reviewInfo) {
        Review review = new Review();
        review.setUser(user);
        review.setShow(show);
        review.setTitle(reviewInfo.getTitle());
        review.setContents(reviewInfo.getContents());
        review.setRating(reviewInfo.getRating());
        review.setCreateDate(LocalDateTime.now());
        return review;
    }

}
