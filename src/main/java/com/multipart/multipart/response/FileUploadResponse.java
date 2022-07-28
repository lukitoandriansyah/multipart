package com.multipart.multipart.response;

public class FileUploadResponse {
    private String fileId;
    private String fileName;
    private String message;
    private String fileType;
    private boolean uploadStatus;
    private String downloadUrl;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(boolean uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
