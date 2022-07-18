package com.example.web.sample.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * ファイルアップロードのフォームクラス
 */
@Data
public class MultiUploadForm {
	// ===== 変更2：フォームクラスのフィールド名の型をリスト型に変更 ===== //
	private List<MultipartFile> uploadRef;
	private List<MultipartFile> file;
	private List<String> idList;
	private String anothorInfo;
}