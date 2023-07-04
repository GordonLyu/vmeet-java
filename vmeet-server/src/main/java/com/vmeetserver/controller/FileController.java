package com.vmeetserver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.client.FileClient;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.service.FileService;
import feign.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

/**
 * @author 像风如你
 * @since 2023/5/23
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Resource
    FileClient fileClient;

    @SaCheckLogin
    @PostMapping(value = "/update/avatar")
    public Result uploadAvatar(@RequestParam(value = "file", required = false) MultipartFile file) {
        int loginId = StpUtil.getLoginIdAsInt();
        Result result = fileClient.updateAvatar(file, loginId);
        if (result.getCode() == 200){
            return fileService.uploadAvatar(result.getData().toString(), loginId);
        }
        return result;
    }

    @SaCheckLogin
    @PostMapping("/send-to/{uid}")
    public Result sendFile(@RequestParam("file") MultipartFile file, @PathVariable Integer uid, String type){
        int loginId = StpUtil.getLoginIdAsInt();
        AddMessageVo addMessageVo = new AddMessageVo();
        addMessageVo.setSenderId(loginId);
        addMessageVo.setReceiverId(uid);
        addMessageVo.setType(type);
        Result result = fileClient.sendFileMessage(file, loginId, uid);
        if (result.getCode() == 200){
            Map<?,?> data = (Map<?,?>) result.getData();
            addMessageVo.setContent(data.get("url").toString());
            String originalFilename = data.get("originalFilename").toString();
            return fileService.addFileMessage(addMessageVo, originalFilename);
        }
        return result;
    }

//    @SaCheckLogin
    @GetMapping("/download/{messageId}")
    public void downloadMessageFile(@PathVariable Integer messageId,@Autowired HttpServletResponse response) throws IOException {
//        Message message = fileService.getFileMessage(messageId);
//        if(message == null){
////            response.getWriter().print();
//            return Result.fail(404,"非文件消息类型，无法下载");
//        }
//        Response feignResponse = fileClient.downloadMessageFile(message.getContent(), message.getOriginalFilename());
//        convert2ServletResp(feignResponse, response);
//        return Result.success();
        Result result;
        Message message = fileService.getFileMessage(messageId);
        if(message == null){
            response.getWriter().print(Result.fail(404,"非文件消息类型，无法下载"));
        }
        String url = message.getContent().substring(1);
        String filename = message.getOriginalFilename();
        String path = "C:/static/" + url;
        File file = new File(path);
        System.out.println(path);
        if(!file.exists()){
           response.getWriter().print(Result.fail(404,"找不到资源",null));
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
            response.getWriter().print(Result.fail(403,"用户已终止下载",null));
        }catch (IOException e) {
            e.printStackTrace();
            result =  Result.fail(500,"下载出现错误",null);
        }
//        response.getWriter().print(Result.success());
    }

    @PostMapping("/test")
    Result test(MultipartFile file){
        System.out.println(file);
        return fileClient.updateAvatar(file, 1);
    }
    @SneakyThrows
    public static void convert2ServletResp(Response feignResponse, HttpServletResponse response) {
        Response.Body body = feignResponse.body();
        try (InputStream inputStream = body.asInputStream(); OutputStream outputStream = response.getOutputStream()) {
            feignResponse.headers().get(HttpHeaders.CONTENT_DISPOSITION).stream().findFirst()
                    .ifPresent(disposition -> response.setHeader(HttpHeaders.CONTENT_DISPOSITION, disposition));
            feignResponse.headers().get(HttpHeaders.CONTENT_TYPE).stream().findFirst()
                    .ifPresent(response::setContentType);
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
