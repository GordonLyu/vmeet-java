package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.VideoCallRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vmeetserver.entity.vo.RecordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
public interface VideoCallRecordService{

    Result addRecord(RecordVo recordVo);

    Result getRecord(RecordVo recordVo);

    Result delRecord(RecordVo recordVo);
}
