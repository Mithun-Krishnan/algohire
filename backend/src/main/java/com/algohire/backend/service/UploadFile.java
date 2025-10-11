package com.algohire.backend.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFile {
    String uploadToVm(MultipartFile file) throws JSchException, IOException, SftpException;
    byte[] downloadFromVm(String fileName) throws JSchException, SftpException, IOException;
    void deleteFromVm(String fileName) throws JSchException, SftpException;
}
