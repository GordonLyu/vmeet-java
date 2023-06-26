package com.fileserver.service.impl;


import cn.hutool.crypto.digest.MD5;
import com.fileserver.config.bean.FileProperties;
import com.fileserver.service.FileService;
import com.fileserver.utils.FileUtil;
import com.vmeetcommon.utils.FileUploadUtil;
import com.vmeetcommon.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author LvXinming
 * @since 2023/6/26
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileProperties fileProperties;

    @Resource
    private FileUtil fileUtil;

    @Override
    public Result uploadAvatar(MultipartFile file, Integer id) {
        try {
            Pair<Boolean, String> pair = fileUtil.checkFile(file);
            if (!pair.getLeft()) {
                return Result.fail(400, pair.getRight());
            }
            String fileName = "avatar-" + id;
//            String fileSuffix = Objects.requireNonNull(multiFile.getOriginalFilename())
//                    .substring(multiFile.getOriginalFilename().lastIndexOf('.'));
            fileName = MD5.create().digestHex16(fileName) + ".jpg";
            boolean b = FileUploadUtil.uploadToServer(file, fileProperties.getPath().getAvatar(), fileName);
            if (!b) {
                return Result.fail(500, "上传失败");
            }
            return Result.success(200, "上传成功", "/img/avatar/" + fileName);
        } catch (Exception e) {
            log.error("系统异常e:", e);
            return Result.fail(500, "上传失败");
        }
    }
}
