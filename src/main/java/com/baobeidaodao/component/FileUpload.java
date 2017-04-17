package com.baobeidaodao.component;

import com.baobeidaodao.domain.model.FileInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by DaoDao on 2017-04-16.
 * 文件上传组件
 */
@Component
public class FileUpload {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUpload.class);

    @Autowired
    private AppYml appYml;

    @Autowired
    private ObjectMapper objectMapper;

    public FileInfo saveFile(MultipartFile multipartFile) throws IOException {

        // 获取配置文件中的全局常量
        String fileDirectory = appYml.getFileDirectory();
        String filePathFormat = appYml.getFilePathFormat();
        String fileNameFormat = appYml.getFileNameFormat();
        String fileUriPrefix = appYml.getFileUriPrefix();

        // 根据日期时间生成图片路径和图片名称
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter filePathDateTimeFormatter = DateTimeFormatter.ofPattern(filePathFormat);
        DateTimeFormatter fileNameDateTimeFormatter = DateTimeFormatter.ofPattern(fileNameFormat);
        String filePath = localDateTime.format(filePathDateTimeFormatter);
        String fileName = localDateTime.format(fileNameDateTimeFormatter);

        // 获取图片后缀名，此处直接使用源文件后缀名，而没有根据mimeType判断
        String extension = getFileExtension(multipartFile.getOriginalFilename());

        // 绝对路径
        String absolutePath = fileDirectory + filePath + fileName + extension;
        // 相对路径
        String relativePath = filePath + fileName + extension;
        // 存放位置
        String directory = fileDirectory + filePath;

        // 如果不存在目录，创建
        File file = new File(directory);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        file = new File(absolutePath);

        // 写文件
        multipartFile.transferTo(file);

        // 文件信息
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName + extension);
        fileInfo.setOriginalName(multipartFile.getOriginalFilename());
        fileInfo.setExtension(extension);
        fileInfo.setContentType(new MimetypesFileTypeMap().getContentType(file));
        fileInfo.setSize(file.length());
        fileInfo.setAbsolutePath(absolutePath);
        fileInfo.setRelativePath(relativePath);
        fileInfo.setUri(fileUriPrefix + relativePath);
        fileInfo.setDateTime(localDateTime);

        // 记录日志
        logFileInfo(fileInfo);

        return fileInfo;
    }

    private String getFileExtension(String fileName) {
        int beginIndex = fileName.lastIndexOf(".");
        int endIndex = fileName.length();
        String extension = fileName.substring(beginIndex, endIndex);
        return extension;
    }

    private void logFileInfo(FileInfo fileInfo) throws JsonProcessingException {
        String fileInfoJson = objectMapper.writeValueAsString(fileInfo);
        LOGGER.info(fileInfoJson);
    }

}
