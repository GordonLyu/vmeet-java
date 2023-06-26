package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

public interface FileService {

    //上传头像
    Result uploadAvatar(String path,Integer id);

}
