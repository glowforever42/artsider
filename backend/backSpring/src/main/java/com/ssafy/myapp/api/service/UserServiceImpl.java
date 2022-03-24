package com.ssafy.myapp.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

/**
 *	
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

//	@Override
//	public User getUserById(Long id) {
//		User user = userRepository.findUserById(id).get();
//		return user;
//	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email).get();
		return user;
	}

	public boolean chkDplByEmail(String email) {
		if(userRepository.findUserByEmail(email).isPresent()) 
			return true;
		else return false;
	}

	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();

		user.setEmail(userRegisterInfo.getEmail());
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		user.setTelNum(userRegisterInfo.getTelNum());
		user.setCreateDate(Calendar.getInstance().getTime());

		return userRepository.save(user);
	}

	@Override
	public void updatePassword(String email, String password) {
		User updateUser = userRepository.findUserByEmail(email).get();
		updateUser.setPassword(passwordEncoder.encode(password));
		userRepository.save(updateUser);
		
	}

	@Override
	public String createAuthNum() {
		Random rand = new Random();

        String numStr = ""; //난수가 저장될 변수
        for(int i=0;i<6;i++) {
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            if(!numStr.contains(ran)) {
                //중복된 값이 없으면 numStr에 append
                numStr += ran;
            }else {
                //생성된 난수가 중복되면 루틴을 다시 실행한다
                i-=1;
            }
        }
        return numStr;
	}
	
	@Override
	public void deleteUser(String email) {
		User deleteUser = userRepository.findUserByEmail(email).get();
		userRepository.deleteById(deleteUser.getId());
	}


}
