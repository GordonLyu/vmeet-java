package com.vmeetserver.service.impl;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result uploadAvatar(String path,Integer id) {
        log.info("路径： " + path);
//        用户头像上传
        userMapper.upload(path,id);
        return Result.success(200, "头像上传成功" ,path);
    }
}
