package com.fileserver.service.impl;


import cn.hutool.crypto.digest.MD5;
import com.fileserver.config.bean.FileProperties;
import com.fileserver.service.FileService;
import com.fileserver.utils.FileUtil;
import com.vmeetcommon.utils.FileUploadUtil;
import com.vmeetcommon.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public Result sendFile(MultipartFile file, Integer senderId, Integer receiverId) {
        try {
            Pair<Boolean, String> pair = fileUtil.checkFile(file);
            if (!pair.getLeft()) {
                return Result.fail(400, pair.getRight());
            }
            String originalFilename = file.getOriginalFilename();
            String fileName = senderId + "-send-file-to-" + receiverId;
            String fileSuffix = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename().lastIndexOf('.'));
            String fileDate = new SimpleDateFormat("ddHHmmss").format(new Date());
            String dirDate = new SimpleDateFormat("yyyyMM").format(new Date());
            fileName = MD5.create().digestHex16(fileName)+ "_" + fileDate + fileSuffix;
            String filePath = "img/msg_file/" + dirDate + "/";
            boolean b = FileUploadUtil.uploadToServer(file, fileProperties.getPath().getRoot() + filePath, fileName);
            if (!b) {
                return Result.fail(500, "上传失败");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("originalFilename", originalFilename);
            map.put("url", "/" + filePath + fileName);
            return Result.success(200, "文件发送成功", map);
        } catch (Exception e) {
            log.error("系统异常e:", e);
            return Result.fail(500, "上传失败");
        }
    }

    @Override
    public Result downloadMessageFile(String url, String filename, HttpServletResponse response) throws UnsupportedEncodingException {
        Result result;
        url = url.substring(1);
        String path = fileProperties.getPath().getRoot() + url;
        File file = new File(path);
        System.out.println(path);
        if(!file.exists()){
            return Result.fail(404,"找不到资源",null);
        }

        System.out.println("下载下载下载下载下载下载下载");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(filename, String.valueOf(StandardCharsets.UTF_8)));

        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
            result = Result.success("开始下载",null);
        } catch (ClientAbortException e){
            System.out.println("用户已终止下载");
            return Result.fail(403,"用户已终止下载",null);
        }catch (IOException e) {
            e.printStackTrace();
            result =  Result.fail(500,"下载出现错误",null);
        }
        return result;
    }
}
