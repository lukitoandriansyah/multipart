package com.multipart.multipart.repository;

import com.multipart.multipart.model.UploadeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadeFile, String> {

}
