package com.learning.app1.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.app1.model.FileUpload;
import com.learning.util.App1Constants;

@RestController
@RequestMapping("/api/app/file")
public class App1FilepUploadController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/upload")
	public ResponseEntity<ByteArrayResource> fileUpload(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setFileId(multipartFile.getOriginalFilename() + UUID.randomUUID().toString().substring(0, 10));
		fileUpload.setFileName(multipartFile.getOriginalFilename());
		fileUpload.setFileData(multipartFile.getBytes());
		fileUpload.setFileType(multipartFile.getContentType());
		logger.info("File Upload Suucess");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUpload.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
				.body(new ByteArrayResource(fileUpload.getFileData()));
	}

	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> fileDownload() throws IOException {
		File file = new File(App1Constants.FILE_LOCATION);
		FileUpload fileUpload = null;
		try (InputStream inputStream = new FileInputStream(file)) {
			byte[] fileContent = new byte[(int) file.length()];
			inputStream.read(fileContent);
			fileUpload = new FileUpload();
			fileUpload.setFileId(file.getName() + UUID.randomUUID().toString().substring(0, 10));
			fileUpload.setFileName(file.getName());
			fileUpload.setFileData(fileContent);
			fileUpload.setFileType("application/jpeg");
		} catch (Exception e) {
			logger.warn("Error occured while loading File");
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUpload.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
				.body(new ByteArrayResource(fileUpload.getFileData()));
	}

	@GetMapping("/fileRestCall")
	public ResponseEntity<FileUpload> fileRestCall() throws IOException {
		FileUpload fileUpload = null;
		File file = new File(App1Constants.FILE_LOCATION);
		try (InputStream inputStream = new FileInputStream(file)) {
			byte[] fileContent = new byte[(int) file.length()];
			inputStream.read(fileContent);
			fileUpload = new FileUpload();
			fileUpload.setFileId(file.getName() + UUID.randomUUID().toString().substring(0, 10));
			fileUpload.setFileName(file.getName());
			fileUpload.setFileData(fileContent);
			fileUpload.setFileType("application/jpg");
		} catch (Exception e) {
			logger.warn("Error occured while loading Data", e);
		}
		return new ResponseEntity<>(fileUpload, HttpStatus.OK);

	}

	@PostMapping("/uploadFile")
	public ResponseEntity<FileUpload> fileRestCallUpload(@RequestBody FileUpload fileupload) {
		fileupload.setFileId("VenkateshwarRao");
		return new ResponseEntity<>(fileupload, HttpStatus.CREATED);

	}

}
