package com.example.web.sample.controller;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * ファイルアップロードのフォームクラス
 */
@Data
public class UploadForm {
    private MultipartFile multipartFile;
}