package com.guesthouse.likelion.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

//  s3Client 주입 aws 에서 제공하는 객체 S3에 파일을 업로드하거나 다운로드할때 사용
    private final S3Client s3Client;

//  버킷이름
    @Value("${cloud.aws.s3.bucket-name}")
    private String bucket;


    public String upload(MultipartFile multipartFile) throws IOException {

        String randomName = UUID.randomUUID().toString(); //랜덤한 파일이름 생성
        String dirName = "images"; //Key - Value 형태로 저장되지만, Key의 슬래시를 이용하여 폴더 개념 같이 사용 가능  /images/cloud.png 와 같은 url 로 구성된다.
        String fileName = dirName + "/" + randomName; //만들어진 dirName 폴더 뒤에 ramdomName 을 붙인다.

        PutObjectRequest putObjectRequest = PutObjectRequest.builder() //PutObjectRequest는 AWS SDK v2에서 제공하는 객체로 S3에 파일을 업로드할 때 필요한 정보를 포함하는 요청(Request) 객체이다.
                .bucket(bucket)
                .key(fileName)
                .contentType(multipartFile.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));// 파일 바이트 배열로 바꾼 후 s3에 업로드

        GetUrlRequest request = GetUrlRequest.builder().bucket(bucket).key(fileName).build(); // s3에서 주소를 받아오기 위해 정보 담은 GetUrlRequest 객체 만들기

        return s3Client.utilities().getUrl(request).toString(); // 업로드된 파일 주소 리턴
    }
}
