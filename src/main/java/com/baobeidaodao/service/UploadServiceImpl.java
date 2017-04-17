package com.baobeidaodao.service;

import com.baobeidaodao.component.FileUpload;
import com.baobeidaodao.domain.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by DaoDao on 2017-04-16.
 * 上传文件服务接口实现
 */

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileUpload fileUpload;

    @Override
    public FileInfo uploadFile(MultipartFile multipartFile) throws IOException {

        FileInfo fileInfo = fileUpload.saveFile(multipartFile);

        return fileInfo;
    }

}
