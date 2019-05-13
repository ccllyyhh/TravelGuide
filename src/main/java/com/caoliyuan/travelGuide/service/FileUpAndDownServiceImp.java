package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.util.MessageProperties;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUpAndDownServiceImp implements FileUpAndDownService {

    @Autowired
    private MessageProperties config; //用来获取file-message.properties配置文件中的信息

    @Override
    public Map<String, Object> uploadPicture(MultipartFile file) throws Exception {
        try {
            Map<String, Object> resMap = new HashMap<>();
            String[] IMAGE_TYPE = config.getImageType().split(",");
            String path = null;
            boolean flag = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                resMap.put("result", "success");
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获得文件类型
                String fileType = file.getContentType();
                // 获得文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                // 原名称
                String oldFileName = file.getOriginalFilename();
                // 新名称
                String newFileName = uuid + "." + imageName;
                // 年月日文件夹
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                // 进行压缩(大于4M)
                if (file.getSize() > config.getFileSize()) {
                    // 重新生成
                    String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                    newFileName = newUUID + "." + imageName;
                    path = config.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                    // 如果目录不存在则创建目录
                    File oldFile = new File(path);
                    if (!oldFile.exists()) {
                        oldFile.mkdirs();
                    }
                    file.transferTo(oldFile);
                    // 压缩图片
                    Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + newUUID + "." + imageName);
                } else {
                    path = config.getUpPath() + "/" + basedir + "/" + uuid + "." + imageName;
                    // 如果目录不存在则创建目录
                    File uploadFile = new File(path);
                    if (!uploadFile.exists()) {
                        uploadFile.mkdirs();
                    }
                    file.transferTo(uploadFile);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + uuid + "." + imageName);
                }
                resMap.put("oldFileName", oldFileName);
                resMap.put("newFileName", newFileName);
                resMap.put("fileSize", file.getSize());
            } else {
                resMap.put("result", "图片格式不正确,支持png|jpg|jpeg");
            }
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
