package com.vmeetserver.mapper;

import com.vmeetserver.entity.Contact;
import com.vmeetserver.entity.dto.ContactDto;
import com.vmeetserver.entity.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    List<Integer> getContact(Integer userId);

    int addContact(Integer userId, Integer contactUserId);

    int deleteContact(@Param("userId")Integer userId, @Param("id") Integer id);

    //是否添加过联系人
    @Select("select exists(select id from contact where user_id = #{loginId} and contact_user_id = #{contactUserId})")
    boolean isAddedContact(Integer loginId, Integer contactUserId);

    /**
     * 查询待接受添加联系人列表
     * @param userId 用户名ID
     */
    List<ContactDto> selectWaitAddContactList(Integer userId);

    /**
     * 获取申请待被同意联系人列表
     * @param userId 用户名ID
     */
    List<ContactDto> selectAppliedAddContactList(Integer userId);
}
