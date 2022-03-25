package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.db.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findReview(Long showId);
    Long addReview(Long showId, ReviewRegisterReq reviewInfo);
    boolean modifyReview(Long reviewId, ReviewRegisterReq reviewInfo);
    boolean removeReview(Long reviewId);
}
