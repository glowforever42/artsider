package com.ssafy.myapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.request.UserpassPatchReq;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.auth.SsafyUserDetails;
import com.ssafy.myapp.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private JavaMailSender sender;
    
    @Autowired
    public UserController(UserService userService,PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 401, message = "회원가입 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userAdd(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

        String userEmail = registerInfo.getEmail();
        Map<String, Object> resultMap = new HashMap<>();
        
        if(userService.chkDplByEmail(userEmail)) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        User user = userService.createUser(registerInfo);
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    
    @GetMapping("/{userEmail}")
    @ApiOperation(value = "이메일 중복검사 ", notes = "<strong>이메일</strong>중복여부를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "중복 없음"),
            @ApiResponse(code = 401, message = "중복"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> emailChk(
            @PathVariable @ApiParam(value = "회원가입 정보", required = true) String userEmail) {
        Map<String, Object> resultMap = new HashMap<>();
        
        if(userService.chkDplByEmail(userEmail)) {
        	resultMap.put("message", "fail");
        	resultMap.put("emailCheck",false);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        resultMap.put("message", "success");
        resultMap.put("emailCheck",true);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/{userEmail}/password")
    @ApiOperation(value = "비밀번호 찾기", notes = "로그인한 회원의 비밀번호를 찾습니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공"),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> findPassword(
            @PathVariable @ApiParam(value = "비밀번호 찾기 정보", required = true) String userEmail
    ) {
    	Map<String, Object> resultMap = new HashMap<>();
        User user = userService.getUserByEmail(userEmail);

        if(user == null){
        	resultMap.put("message", "invailed user");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        String uuid = UUID.randomUUID().toString();
        String setfrom = "artsider_ssafy@naver.com";
        String tomail = user.getEmail();// 받는사람
        String title = "[Artsider] 임시 비밀번호 이메일 입니다";
        String content =
                System.getProperty("line.separator") + "안녕하세요 회원님"
                        + System.getProperty("line.separator") + "임시 비밀번호는 " + uuid + " 입니다."
                        + System.getProperty("line.separator") + "로그인을 하시고 꼭 비밀번호를 바꿔주세요 :)";

        try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom(setfrom); 
            simpleMessage.setTo(tomail);
            simpleMessage.setSubject(title);
            simpleMessage.setText(content);
            sender.send(simpleMessage);

            userService.updatePassword(userEmail, uuid);
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        resultMap.put("message", "Failed to Find the Password");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }
    
    
    
    @PostMapping("/{userEmail}")
    @ApiOperation(value = "이메일 본인 인증", notes = "이메일이 본인 이메일인지 인증합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증번호 생성및 메일전송 성공"),
            @ApiResponse(code = 401, message = "인증번호 생성 및 메일전송 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> emailAuth(
            @PathVariable @ApiParam(value = "인증번호 발송 이메일", required = true) String userEmail
    ) {
    	Map<String, Object> resultMap = new HashMap<>();
       
        String emailNumber = userService.createAuthNum();
        String setfrom = "artsider_ssafy@naver.com";
        String tomail = userEmail;// 받는사람
        String title = "[Artsider] 인증번호가 발송되었습니다.";
        String content =
                System.getProperty("line.separator") + "안녕하세요 "
                        + System.getProperty("line.separator") + "인증 번호는 " + emailNumber + " 입니다."
                        + System.getProperty("line.separator") + "아트사이더에서 인증번호를 입력해주세요.";

        try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom(setfrom); 
            simpleMessage.setTo(tomail);
            simpleMessage.setSubject(title);
            simpleMessage.setText(content);
            sender.send(simpleMessage);

            resultMap.put("message", "success");
            resultMap.put("emailNumber", emailNumber);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        resultMap.put("message", "Failed to send email");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{userEmail}")
    @ApiOperation(value = "유저 정보 삭제", notes = "유저 정보를 삭제 후 응답한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 삭제(탈퇴) 성공"),
            @ApiResponse(code = 401, message = "유저 정보 삭제(탈퇴) 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> deleteUser(
            @PathVariable("userEmail") String userEmail
    ) {
    	Map<String, Object> resultMap = new HashMap<>();

        userService.deleteUser(userEmail);
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @PostMapping("/password")
    @ApiOperation(value = "회원 비밀번호 변경", notes = "회원의 비밀번호를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경 "),
            @ApiResponse(code = 401, message = "비밀번호 변경 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> updatePassword(Authentication authentication,
            @RequestBody @ApiParam(value = "비밀번호 정보", required = true) UserpassPatchReq userpassPatchReq) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();

        // 비밀번호 변경 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if(passwordEncoder.matches(userpassPatchReq.getCurrentPass(), user.getPassword())) {
            // 유효한 패스워드가 맞는 경우
            userService.updatePassword(user.getEmail(), userpassPatchReq.getNewPass());
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);

    }
    
    
    
    
}
