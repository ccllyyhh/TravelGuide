package com.caoliyuan.travelGuide.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "message")
@PropertySource(value = "classpath:file-message.properties")
public class MessageProperties {

    private long fileSize;  //压缩大小

    private double scaleRatio; //压缩比例

    private String upPath; //保存路径

    private String imageType; //图片类型

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public double getScaleRatio() {
        return scaleRatio;
    }

    public void setScaleRatio(double scaleRatio) {
        this.scaleRatio = scaleRatio;
    }

    public String getUpPath() {
        return upPath;
    }

    public void setUpPath(String upPath) {
        this.upPath = upPath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

}