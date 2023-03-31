package com.multipart.multipart.service;

import com.multipart.multipart.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileUploadService {
    public void uploadToLocal(MultipartFile file);
    public UploadFile uploadToDB(MultipartFile file);
    public Optional<UploadFile> downloadFile(String fileId);
}
