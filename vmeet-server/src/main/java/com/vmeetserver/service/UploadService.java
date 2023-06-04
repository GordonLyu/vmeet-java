package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

public interface UploadService {
    Result upload(String path,Integer id);
}
