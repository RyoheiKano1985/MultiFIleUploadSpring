package com.example.web.sample.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * ファイルアップロードのコントローラークラス
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FileUploadController {

	@PostMapping("/upload")
	String upload(UploadForm uploadForm) {
		// フォームで渡されてきたアップロードファイルを取得
		MultipartFile multipartFile = uploadForm.getMultipartFile();

		// アップロード実行処理メソッド呼び出し
		uploadAction(multipartFile);

		// リダイレクト
		return getJSONFromObj(uploadForm);
	}

	/**
	 * ファイルアップロード処理
	 * 
	 * @param uploadForm
	 * @return
	 */
	@PostMapping("/multiUpload")
	String multiUpload(MultiUploadForm uploadForm) {
		// フォームで渡されてきたアップロードファイルを取得
		List<MultipartFile> multipartFile = uploadForm.getFile();
				// ===== 変更1：コントローラークラスに複数ファイルを1つずつにする処理に変更 ===== //
		multipartFile.forEach(e -> {
			// アップロード実行処理メソッド呼び出し
			uploadAction(e);
		});

		// リダイレクト
		return "OK";
	}

	/**
	 * アップロード実行処理
	 * 
	 * @param multipartFile
	 */
	private void uploadAction(MultipartFile multipartFile) {
		// ファイル名取得
		String fileName = multipartFile.getOriginalFilename();

		// 格納先のフルパス ※事前に格納先フォルダ「UploadTest」をCドライブ直下に作成しておく
		Path filePath = Paths.get("./temp/" + fileName);

		try {
			// アップロードファイルをバイト値に変換
			byte[] bytes = multipartFile.getBytes();

			// バイト値を書き込む為のファイルを作成して指定したパスに格納
			OutputStream stream = Files.newOutputStream(filePath);

			// ファイルに書き込み
			stream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// JavaオブジェクトをJSONに変換
	private String getJSONFromObj(Object obj) {

		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

}