package com.caoliyuan.travelGuide.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUpAndDownService {
    Map<String, Object> uploadPicture(MultipartFile file) throws Exception;
}
