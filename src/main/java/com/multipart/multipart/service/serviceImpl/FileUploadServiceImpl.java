package com.multipart.multipart.service.serviceImpl;

import com.multipart.multipart.model.UploadeFile;
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
    public void uplodToLocal(MultipartFile file) {
        try {
            byte[] data = file.getBytes();

            Path path = Paths.get(uploadFolderPath+file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public UploadeFile uploadToDB(MultipartFile file) {

        UploadeFile uploadeFile = new UploadeFile();
        try {
            uploadeFile.setFileData(file.getBytes());
            uploadeFile.setFileTypes(file.getContentType());
            uploadeFile.setFileName(file.getOriginalFilename());
            UploadeFile uploadFileToRet = fileUploadRepository.save(uploadeFile);
            return uploadFileToRet;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<UploadeFile> downloadFile(String fileId) {
        Optional<UploadeFile> uploadeFileToRet = fileUploadRepository.findById(fileId);
        return uploadeFileToRet;
    }
}
