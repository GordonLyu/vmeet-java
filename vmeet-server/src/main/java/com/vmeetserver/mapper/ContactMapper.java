package com.vmeetserver.mapper;

import com.vmeetserver.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 联系人表	 Mapper 接口
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Mapper
public interface ContactMapper {

    List<Integer> getContact(@Param("userId") Integer userId);

    int addContact(Integer userId, Integer contactUserId);

    int deleteContact(@Param("userId")Integer userId, @Param("id") Integer id);
}
