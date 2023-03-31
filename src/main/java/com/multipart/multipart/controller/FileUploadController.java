package com.multipart.multipart.controller;

import com.multipart.multipart.model.UploadFile;
import com.multipart.multipart.response.FileUploadResponse;
import com.multipart.multipart.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    //Upload file to local
    @PostMapping("upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile){
        //
        fileUploadService.uploadToLocal(multipartFile);
    }

    //UploadFileto Db
    @PostMapping("upload/db")
    public FileUploadResponse uploadDB(@RequestParam("file")MultipartFile multipartFile){
        //
        FileUploadResponse response = new FileUploadResponse();
        UploadFile uploadFile =fileUploadService.uploadToDB(multipartFile);
        if (uploadFile !=null){
           String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                   .path("api/v1/download/")
                   .path(uploadFile.getFileId())
                   .toUriString();
           response.setDownloadUrl(downloadUrl);
           response.setFileId(uploadFile.getFileId());
           response.setFileType(uploadFile.getFileTypes());
           response.setFileName(uploadFile.getFileName());
           response.setUploadStatus(true);
           response.setMessage("Upload Successfully");
           return response;
        }
        response.setMessage("Oops 1 something went wrong. Please re-upload");
        return  response;
    }


    //DownloadFile which our upload to DB
    @GetMapping("/download/{fileId}")
    /*public Optional<UploadeFile> downloadFile(@PathVariable String fileId){
        return fileUploadService.downloadFile(fileId);

    }*/
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId){
        Optional<UploadFile> uploadFileToRet = fileUploadService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadFileToRet.get().getFileTypes()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadFileToRet.get().getFileName())
                .body(new ByteArrayResource(uploadFileToRet.get().getFileData()));
    }
}
