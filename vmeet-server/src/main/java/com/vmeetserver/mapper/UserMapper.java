package com.vmeetserver.mapper;

import com.vmeetserver.entity.User;
import com.vmeetserver.entity.dto.ContactDto;
import com.vmeetserver.entity.dto.UserDto;
import com.vmeetserver.entity.vo.SignUpVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Mapper
public interface UserMapper {


    User SelectUser(String username);

    User SelectUserById(Integer id);

    @Select("select * from user where email = #{email}")
    User selectUserByEmail(String email);

    int insertUser(SignUpVo user);
    int changePassword(String newPassword, Integer id);

    int changeNickName(@Param("newNickName") String newPassword,@Param("id")Integer id);
    int upload(@Param("path")String path,@Param("id")Integer id);

    List<ContactDto> selectContacts(List<Integer> contactUserIds);

    ContactDto searchUser(@Param("username") String username);

    // 是否存在该邮箱
    @Select("select exists(select id from user where email = #{email} )")
    boolean isExistEmail(String email);

    // 修改邮箱
    @Update("update user set email = #{email} where id = #{id}")
    int changeEmail(Integer id, String email);
}
