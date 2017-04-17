package com.baobeidaodao.service;

import com.baobeidaodao.domain.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by DaoDao on 2017-04-16.
 * 上传文件服务接口
 */
public interface UploadService {

    FileInfo uploadFile(MultipartFile multipartFile) throws IOException;

}
