package com.learning.app1.model;

public class FileUpload {

	private String fileId;

	private String fileName;

	private String fileType;
	
	private byte[] fileData;

	public FileUpload(String fileId, String fileName, byte[] fileData) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileData = fileData;
	}

	
	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public FileUpload() {
		super();
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

}