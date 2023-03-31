package com.multipart.multipart.repository;

import com.multipart.multipart.model.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadFile, String> {

}
