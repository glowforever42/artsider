package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.db.entity.Expectation;

import java.util.List;

public interface ExpectService {
    List<Expectation> findExpectation(Long id);
    Long addExpectation(Long id, ReviewRegisterReq expectInfo);
    boolean modifyExpectation(Long expectationId, ReviewRegisterReq expectInfo);
    boolean removeExpectation(Long expectationId);
}
