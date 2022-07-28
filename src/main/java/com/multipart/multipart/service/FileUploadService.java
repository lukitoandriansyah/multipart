package com.multipart.multipart.service;

import com.multipart.multipart.model.UploadeFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileUploadService {
    public void uplodToLocal(MultipartFile file);
    public UploadeFile uploadToDB(MultipartFile file);
    public Optional<UploadeFile> downloadFile(String fileId);
}
