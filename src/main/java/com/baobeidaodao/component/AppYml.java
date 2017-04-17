package com.baobeidaodao.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by DaoDao on 2017-04-16.
 * yml 配置文件中的全局常量
 */
@Component
@ConfigurationProperties(value = "app")
public class AppYml {

    private String fileDirectory;
    private String filePathFormat;
    private String fileNameFormat;
    private String fileUriPrefix;

    public AppYml() {
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public String getFilePathFormat() {
        return filePathFormat;
    }

    public void setFilePathFormat(String filePathFormat) {
        this.filePathFormat = filePathFormat;
    }

    public String getFileNameFormat() {
        return fileNameFormat;
    }

    public void setFileNameFormat(String fileNameFormat) {
        this.fileNameFormat = fileNameFormat;
    }

    public String getFileUriPrefix() {
        return fileUriPrefix;
    }

    public void setFileUriPrefix(String fileUriPrefix) {
        this.fileUriPrefix = fileUriPrefix;
    }
}
