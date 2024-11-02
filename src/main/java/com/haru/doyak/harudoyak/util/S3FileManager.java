package com.haru.doyak.harudoyak.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.haru.doyak.harudoyak.dto.file.FileDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3FileManager extends AbstractView {
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    // File 저장시 중복되지 않는 fileName 생성
    // S3에 저장
    public FileDTO saveImageFile(MultipartFile image) throws Exception {

        // 파일 DTO 생성
        FileDTO fileDTO = new FileDTO();

        // 중복되지 않는 파일명 생성 (UUID, Date)
        String fileName = UUID.randomUUID().toString();

        // 확장자 이어붙이기
        StringBuffer buffer = new StringBuffer();
        buffer.append(fileName);

        // 실제 업로드된 파일명에서 확장자 분리
        String ex = image.getOriginalFilename(); // 파일명.jpg
        fileDTO.setOriginalName(ex); // 실제 업로드된 파일명 DTO에 담음
        ex = ex.substring(ex.indexOf("."));     // .을 기준으로 자름

        // fileName에 분리한 확장자 append
        buffer.append(ex);

        // 파일명.확장자를 fileName에 대입
        fileName = buffer.toString();

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());

        // S3에 파일 업로드 요청 생성
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, image.getInputStream(), metadata);

        // S3에 파일 업로드
        amazonS3.putObject(putObjectRequest);

        log.info("==================================");
        log.info("fileName {}", fileName);

        fileDTO.setFilePathName(getPublicUrl(fileName));

        return fileDTO;
    }

    // 데이터베이스에 저장할 url+fileName
    private String getPublicUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, amazonS3.getRegionName(), fileName);
    }

}
