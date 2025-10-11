package com.algohire.backend.controller;


import com.algohire.backend.exception.UserNotFoundException;
import com.algohire.backend.model.Users;
import com.algohire.backend.repository.UserRepository;
import com.algohire.backend.service.impl.AuthserviceImpal;
import com.algohire.backend.service.impl.UploadFIleServiceImp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UploadFileControler {

    private final UploadFIleServiceImp uploadFIleServiceImp;
    private final UserRepository userRepository;
    private final AuthserviceImpal authserviceImpal;


    @PostMapping("/resume/upload")
    public ResponseEntity<String> uploadToVm( @RequestParam ("file") MultipartFile file){
        try {
            if (!file.getOriginalFilename().endsWith(".pdf")) {
                return ResponseEntity.badRequest().body("Only PDF files allowed.");
            }

            UUID userId=authserviceImpal.getCurrentUserId();

            Users users=userRepository.findById(userId).orElseThrow(
                    ()->new UserNotFoundException("user not found"));

            String oldUrl=users.getResumeUrl();

            String url=uploadFIleServiceImp.uploadToVm(file);

            users.setResumeUrl(url);
            if (oldUrl != null) {
                String oldFileName = Paths.get(oldUrl).getFileName().toString();
                uploadFIleServiceImp.deleteFromVm(oldFileName); // delete old file first
            }
            userRepository.save(users);

            return ResponseEntity.ok("PDF uploaded successfully via SFTP!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }


    @GetMapping("/resume/get/{userId}")
    public ResponseEntity<byte[]> getResume(@PathVariable UUID userId) throws JSchException, SftpException, IOException {
        Users users=userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException("no your found")
        );

        String fileUrl= users.getResumeUrl();
//
        System.out.println(fileUrl);

        if(fileUrl==null){
            return ResponseEntity.notFound().build();
        }

        String fileName= Paths.get(fileUrl).getFileName().toString();

//
        System.out.println(fileName);

        byte[] data=uploadFIleServiceImp.downloadFromVm(fileName);

        HttpHeaders headers=new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(fileName)
                .build());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }
}
