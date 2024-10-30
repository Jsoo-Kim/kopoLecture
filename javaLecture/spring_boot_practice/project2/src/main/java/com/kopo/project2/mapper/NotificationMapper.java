package com.kopo.project2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kopo.project2.model.Notification;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface NotificationMapper {

    @Select("SELECT * FROM notifications ORDER BY created_at DESC")
    List<Notification> findAll();

    @Select("SELECT * FROM notifications WHERE id = #{id}")
    Notification findById(@Param("id") int id);

    @Insert("INSERT INTO notifications (title, content, created_at) VALUES (#{title}, #{content}, #{createdDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertNotification(Notification notification);

    @Update("UPDATE notifications SET title = #{title}, content = #{content} WHERE id = #{id}")
    void updateNotification(Notification notification);

    @Delete("DELETE FROM notifications WHERE id = #{id}")
    void deleteNotification(@Param("id") int id);
	
    @Insert("INSERT INTO uploaded_files (file_type, file_path) VALUES (#{fileType}, #{filePath})")
    void insertFileRecord(@Param("fileType") String fileType, @Param("filePath") String filePath);
}
