package com.algohire.backend.service.impl;

import com.algohire.backend.service.UploadFile;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadFIleServiceImp implements UploadFile {

    @Value("${vm.username}")
    private String userName;

    @Value("${vm.password}")
    private String password;

    @Value("${vm.host}")
    private String host;

    @Value("${vm.port}")
    private int port;


    @Override
    public String uploadToVm(MultipartFile file) throws JSchException, IOException, SftpException {


        JSch jSch=new JSch();

        Session session=jSch.getSession(userName,host,port);

        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        Channel channel=session.openChannel("sftp");
        channel.connect();

        ChannelSftp sftp=(ChannelSftp) channel;

        sftp.put(file.getInputStream(),"/home/mithun/uploads/" + file.getOriginalFilename());

        sftp.exit();

        session.disconnect();

        return "/uploads/"+file.getOriginalFilename();
    }

    @Override
    public byte[] downloadFromVm(String fileName) throws JSchException, SftpException, IOException {
        JSch jSch=new JSch();
        Session session= jSch.getSession(userName,host,port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();


        Channel channel=session.openChannel("sftp");
        channel.connect();

        ChannelSftp sftp=(ChannelSftp) channel;

        try (var inputStream = sftp.get( "/home/mithun/uploads/"+ fileName)) {
            return inputStream.readAllBytes();
        } finally {
            sftp.exit();
            session.disconnect();
        }
    }


    @Override
    public void deleteFromVm(String fileName) throws JSchException, SftpException {
        JSch jSch = new JSch();
        Session session = jSch.getSession(userName, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();

        ChannelSftp sftp = (ChannelSftp) channel;

        try {
            sftp.rm("/home/mithun/uploads/" + fileName);
            System.out.println("Old file deleted: " + fileName);
        } catch (SftpException e) {
            System.out.println("No previous file found for deletion: " + fileName);
        } finally {
            sftp.exit();
            session.disconnect();
        }
    }



}
