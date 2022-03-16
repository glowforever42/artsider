package com.ssafy.myapp.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 60000)
@RestController
public class TestController {

    @GetMapping("hello")
    public ResponseEntity<Map<String, Object>> testController() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "success");
        HttpStatus status = HttpStatus.ACCEPTED;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
