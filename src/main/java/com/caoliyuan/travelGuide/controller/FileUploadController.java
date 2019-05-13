package com.caoliyuan.travelGuide.controller;

import com.caoliyuan.travelGuide.service.FileUpAndDownService;
import com.caoliyuan.travelGuide.util.IStatusMessage;
import com.caoliyuan.travelGuide.util.ResponseResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Controller
@RequestMapping("/upload")
public class FileUploadController {



    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @RequestMapping(value = "/setFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setFileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
        ResponseResult result = new ResponseResult();
        try {
            Map<String, Object> resultMap = upload(file);
            if (!IStatusMessage.SystemStatus.SUCCESS.getMessage().equals(resultMap.get("result"))) {
                result.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
                result.setMessage((String) resultMap.get("msg"));
                result.setObj(resultMap.get("path"));
                return result;
            }
            result.setData(resultMap);
        } catch (ServiceException e) {
            e.printStackTrace();
            result.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
            result.setMessage(IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Map<String, Object> upload(MultipartFile file) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            if (!file.isEmpty()) {
                Map<String, Object> picMap = fileUpAndDownService.uploadPicture(file);
                if (IStatusMessage.SystemStatus.SUCCESS.getMessage().equals(picMap.get("result"))) {
                    return picMap;
                } else {
                    returnMap.put("result", IStatusMessage.SystemStatus.ERROR.getMessage());
                    returnMap.put("msg", picMap.get("result"));
                    returnMap.put("path", picMap.get("path"));
                }
            } else {
                returnMap.put("result", IStatusMessage.SystemStatus.ERROR.getMessage());
                returnMap.put("msg", IStatusMessage.SystemStatus.FILE_UPLOAD_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getCode());
        }
        return returnMap;
    }





    public class ServiceException extends Exception {

        public ServiceException() {
            super();
        }
        public ServiceException(String message){
            super(message);
        }
        public ServiceException(Throwable throwable){
            super(throwable);
        }
        public ServiceException(String message ,Throwable throwable){
            super(message, throwable);
        }
    }



}