package com.vmeetserver.mapper;

import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.entity.vo.PageMessageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Mapper
public interface MessageMapper{

    /**
     * 获取用户之间所有消息
     */
     List<Message> selectMessage(Integer senderId, Integer receiverId);

    /**
     * 倒序分页获取用户之间的消息
     */
    List<Message> pageMessage(@Param("senderId") Integer senderId,@Param("pageMessage") PageMessageVo pageMessageVo);

    /**
     * 获取用户之间的总消息数
     */
    Integer getMessageCount(Integer senderId, Integer receiverId);

    /**
     * 储存用户消息
     */
    @Insert("insert into message(sender_id, receiver_id, content, type)" +
            " values(#{senderId},#{receiverId},#{content},#{type})")
    void insertMessage(AddMessageVo addMessageVo);

    /**
     * 删除聊天消息
     */
    @Select("delete from message where id = #{mid}")
    int delMessage(Integer mid);
}
