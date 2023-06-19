package com.vmeetserver.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.VideoCallRecord;
import com.vmeetserver.entity.vo.RecordVo;
import com.vmeetserver.service.VideoCallRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/videoCallRecord")
public class VideoCallRecordController {
    @Autowired
    VideoCallRecordService videoCallRecordService;

    // 插入视频通话记录
    @SaCheckLogin
    @PostMapping("/insert-record")
    public Result addRecord(@RequestBody RecordVo recordVo) {
        return videoCallRecordService.addRecord(recordVo);
    }

    //获取视频通话记录
    @SaCheckLogin
    @GetMapping("")
    public Result getRecord(@RequestBody RecordVo recordVo) {
        return videoCallRecordService.getRecord(recordVo);
    }

    //删除视频通话记录
    @SaCheckLogin
    @DeleteMapping("/del")
    public Result delRecord(@RequestBody RecordVo recordVo){
        return videoCallRecordService.delRecord(recordVo);
    }

}

