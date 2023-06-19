package com.vmeetserver.mapper;

import com.vmeetserver.entity.VideoCallRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vmeetserver.entity.vo.RecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface VideoCallRecordMapper{

    int insertRecord(@Param("item") RecordVo recordVo);

    List<VideoCallRecord> getRecord(Integer callerId, Integer receiverId);

    int delRecord(@Param("item") RecordVo recordVo);
}
