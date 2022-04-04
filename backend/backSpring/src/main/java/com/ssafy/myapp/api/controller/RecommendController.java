package com.ssafy.myapp.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.service.KakaoAuthService;
import com.ssafy.myapp.api.service.RecommendationService;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.util.JwtTokenUtil;
import com.ssafy.myapp.db.entity.User;

@RestController
@RequestMapping("/api/show/recommend")
public class RecommendController {
	
	private final RecommendationService recommendService;


	@Autowired
	public RecommendController(RecommendationService recommendService) {
		this.recommendService = recommendService;
	}
	
	@ResponseBody
    @PostMapping("/{userId}/probability")
    public ResponseEntity<Map<String, Object>> expectRatingRun(@PathVariable String userId) {
        String msg=recommendService.runExpectRating(userId);
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("message", msg);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        
    }

}
