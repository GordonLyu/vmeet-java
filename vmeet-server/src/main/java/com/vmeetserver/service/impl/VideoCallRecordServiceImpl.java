package com.vmeetserver.service.impl;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.VideoCallRecord;
import com.vmeetserver.entity.vo.RecordVo;
import com.vmeetserver.mapper.VideoCallRecordMapper;
import com.vmeetserver.service.VideoCallRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Service
@Slf4j
public class VideoCallRecordServiceImpl implements VideoCallRecordService {

    @Autowired
    VideoCallRecordMapper videoCallRecordMapper;

    @Override
    public Result addRecord(RecordVo recordVo) {
        int i = videoCallRecordMapper.insertRecord(recordVo);
        if (i <= 0) {
            return Result.fail(500, "插入失败");
        }
        return Result.success();
    }

    @Override
    public Result getRecord(RecordVo recordVo) {
        List<VideoCallRecord> recordList = videoCallRecordMapper.getRecord(recordVo.getCallerId(),recordVo.getReceiverId());
        log.info(recordList.toString());
        return Result.success(recordList);
    }

    @Override
    public Result delRecord(RecordVo recordVo) {
        int i = videoCallRecordMapper.delRecord(recordVo);
        if (i <= 0) {
            return Result.fail(400, "操作失败");
        }
        return Result.success("删除成功");
    }
}
