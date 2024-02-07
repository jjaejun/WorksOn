package com.sh.workson.attachment.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sh.app.attachment.dto.AttachmentCreateDto;
import com.sh.app.attachment.dto.AttachmentDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
public class S3FileService {
    @Autowired
    private AmazonS3Client amazonS3Client;
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public AttachmentCreateDto upload(MultipartFile upFile) throws IOException {
        String key = UUID.randomUUID().toString();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(upFile.getContentType());
        objectMetadata.setContentLength(upFile.getSize());

        // 업로드
        amazonS3Client.putObject(bucket, key, upFile.getInputStream(), objectMetadata);
        // url 조회
        String url = amazonS3Client.getUrl(bucket, key).toString();

        return new AttachmentCreateDto(null, upFile.getOriginalFilename(), key, url);
    }

    /**
     * Resource
     * - (5가지 특징 : IoC/DI, POJO, PSA, AOP)
     * - PSA에 해당하는 추상화 레이어이다.
     * - Resource를 통해 처리할 수 있는 자원타입
     *
     *  1. UrlResource
     *  2. ClassPathResource
     *  3. FileSystemResource
     *  4. ServletContextResource (web root)
     *  5. ...
     *  
     *  - ResourceLoader빈을 통해 url을 제공하면 적절한 구현체를 통해 자원을 획득
     *
     * @param attachmentDetailDto
     * @return
     */
    public ResponseEntity<?> download(AttachmentDetailDto attachmentDetailDto) throws UnsupportedEncodingException {
        // http:// https:// 은 UrlResource구현 객체를 통해 자원을 획득
        Resource resource = resourceLoader.getResource(attachmentDetailDto.getUrl());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(attachmentDetailDto.getOriginalFilename(), "UTF-8"))
                .body(resource);
    }
}
