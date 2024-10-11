package com.kopo.project2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.kopo.project2.model.User;

@Mapper
public interface UserMapper {
    // 아이디 중복 확인
    @Select("SELECT COUNT(*) FROM users WHERE id = #{id}")
    boolean isUserIdExists(String id);

    // id로 사용자 추가
    @Insert("INSERT INTO users (name, id, password, sex, address, phone, user_type, updated, created) " +
            "VALUES (#{name}, #{id}, #{password}, #{sex}, #{address}, #{phone}, #{user_type}, #{updated}, #{created})")
    void insertUser(User user);
    
    // email로 사용자 추가
    @Insert("INSERT INTO users (name, email, password, is_verified, sex, address, phone, user_type, updated, created) VALUES (#{email}, #{password}, #{isVerified}, #{sex}, #{address}, #{phone}, #{user_type}, #{updated}, #{created})")
    void insertUserByEmail(User user);

    // 사용자 로그인 검증
    @Select("SELECT id, user_type FROM users WHERE id = #{id} AND password = #{password}")
    User findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);
    
    // 이메일로 사용자 로그인 검증
    @Select("SELECT email, user_type FROM users WHERE email = #{email} AND password = #{password}")
    User findUserByEmailAndPassword(@Param("email") String id, @Param("password") String password);
    
    // email 인증 상태 업데이트
    @Update("UPDATE users SET is_verified = #{isVerified} WHERE email = #{email}")
    void updateVerificationStatus(@Param("email") String email, @Param("isVerified") boolean isVerified);

    // id로 사용자 정보 조회
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findUserById(String id);
    
    // email로 사용자 정보 조회
    @Select("SELECT * FROM users WHERE email = #{email}")
    User findUserByEmail(String email);

    // 모든 사용자 조회
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    // 사용자 정보 수정
    @Update("UPDATE users SET name = #{name}, sex = #{sex}, address = #{address}, phone = #{phone} WHERE id = #{id}")
    void updateUser(User user);

    // 비밀번호 수정
    @Update("UPDATE users SET password = #{password} WHERE id = #{id}")
    void updatePassword(@Param("id") String id, @Param("password") String password);

    // 사용자 삭제
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(String id);
}
