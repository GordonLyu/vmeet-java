package com.vmeetserver.service.impl;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result upload(String path,Integer id) {
        log.info("路径： " + path);
//        用户头像上传
        int i = userMapper.upload(path,id);
        if (i <= 0) {
            log.error("上传成功，但数据更新失败");
            return Result.fail(500, "上传成功，但数据更新失败");
        }
        log.info(i + "");
        log.info("上传成功，数据更新成功");
        return Result.success(path);
    }
}
