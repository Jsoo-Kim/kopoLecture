package com.kopo.project2.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kopo.project2.Sha256EncryptUtil;
import com.kopo.project2.model.User;

@Repository
public class DB {

    @Autowired
    private UserMapper userMapper;
    
//    @PostConstruct  // 이 어노테이션을 사용하여 클래스 초기화 시점에 로그 출력
//    public void init() {
//        System.out.println("UserMapper is null: " + (userMapper == null));
//    }

    // 아이디 중복 확인
    public boolean isUserIdExists(String id) {
        return userMapper.isUserIdExists(id);
    }

    // 사용자 추가
    public void insertUser(User user) {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.created = sdf.format(nowDate);
        user.updated = sdf.format(nowDate);
        user.user_type = "guest";
        userMapper.insertUser(user);
    }
    
    // 이메일로 사용자 추가
    public void insertUserByEmail(User user) {
        userMapper.insertUserByEmail(user);
    }
    

    // 사용자 로그인 검증
    public User findUserByIdAndPassword(String id, String password) {
        return userMapper.findUserByIdAndPassword(id, Sha256EncryptUtil.shaEncoder(password));
    }

    // 이메일로 사용자 로그인 검증
    public User findUserByEmailAndPassword(String email, String password) {
    	return userMapper.findUserByEmailAndPassword(email, Sha256EncryptUtil.shaEncoder(password));
    }

    // 사용자 정보 조회
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }
    
    // 이메일로 사용자 정보 조회
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    // 모든 사용자 목록 조회
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    
    // 이메일 인증 상태 업데이트
    public void updateVerificationStatus(String email, boolean isVerified) {
        userMapper.updateVerificationStatus(email, isVerified);
    }

    // 비밀번호 수정
    public void updatePassword(String id, String password) {
        userMapper.updatePassword(id, password);
    }

    // 사용자 삭제
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }
}
