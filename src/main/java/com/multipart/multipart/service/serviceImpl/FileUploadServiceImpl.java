package com.multipart.multipart.service.serviceImpl;

import com.multipart.multipart.model.UploadFile;
import com.multipart.multipart.repository.FileUploadRepository;
import com.multipart.multipart.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String uploadFolderPath ="/Users/psm/Documents/upload_";
    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public void uploadToLocal(MultipartFile file) {
        try {
            byte[] data = file.getBytes();

            Path path = Paths.get(uploadFolderPath+file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public UploadFile uploadToDB(MultipartFile file) {

        UploadFile uploadFile = new UploadFile();
        try {
            uploadFile.setFileData(file.getBytes());
            uploadFile.setFileTypes(file.getContentType());
            uploadFile.setFileName(file.getOriginalFilename());
            UploadFile uploadFileToRet = fileUploadRepository.save(uploadFile);
            return uploadFileToRet;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<UploadFile> downloadFile(String fileId) {
        Optional<UploadFile> uploadFileToRet = fileUploadRepository.findById(fileId);
        return uploadFileToRet;
    }
}
