package com.baobeidaodao.web;

import com.baobeidaodao.component.AppYml;
import com.baobeidaodao.domain.model.FileInfo;
import com.baobeidaodao.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaoDao on 2017-04-14.
 * 上传文件控制器
 */

@RestController
@RequestMapping("upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private AppYml appYml;

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    private ModelMap uploadFile(@RequestParam("file") MultipartFile file) {

        ModelMap modelMap = new ModelMap();

        FileInfo fileInfo = null;
        try {
            fileInfo = uploadService.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            modelMap.addAttribute("error", "Uploading Failed");
            return modelMap;
        }

        modelMap.addAttribute("file", fileInfo);

        return modelMap;
    }

    @RequestMapping(value = {"/multiple", "/multi"}, method = RequestMethod.POST)
    public ModelMap uploadMultiFile(@RequestParam("file") MultipartFile[] files) {

        ModelMap modelMap = new ModelMap();

        List<FileInfo> fileInfoList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileInfo fileInfo = null;
            try {
                fileInfo = uploadService.uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
                modelMap.addAttribute("error", "Uploading Failed");
                return modelMap;
            }
            fileInfoList.add(fileInfo);
        }

        modelMap.addAttribute("fileList", fileInfoList);

        return modelMap;
    }
}
