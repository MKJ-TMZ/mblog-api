package com.mtcode.mblogapi.service.impl;

import com.mtcode.mblogapi.exception.ServiceException;
import com.mtcode.mblogapi.service.UploadService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TangMingZhang
 * @date 2022/4/27
 */
@Service
public class UploadServiceImpl implements UploadService {

    private final String GITHUB_TOKEN = "token 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";

    @Override
    public String uploadToGitHub(String base64, String name) {
        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://api.github.com/repos/MKJ-TMZ/imageStore/contents/image/";
        String cdnUrl = "https://cdn.jsdelivr.net/gh/MKJ-TMZ/imageStore/image/";
        Date date = new Date();
        String fileName = date.getTime() + "_" + name;
        // headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("X-GitHub-Media-Type", "github.v3");
        requestHeaders.add("Authorization", GITHUB_TOKEN);
        // body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("message", date + " commit");
        requestBody.put("content", base64.substring(base64.indexOf(",") + 1));
        // HttpEntity
        HttpEntity<Map<String, String>> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);

        try {
            restTemplate.exchange(baseUrl + fileName, HttpMethod.PUT, requestEntity, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("上传失败");
        }
        return cdnUrl + fileName;
    }
}
