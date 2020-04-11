package com.quartz.quartz.service;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    @Value("${alumniNetwork.FTPdirectory}")
    private String alumniNetworkFTPdirectory;

    @Value("${alumniNetwork.FTPpassword}")
    private String alumniNetworkFTPpassword;

    @Value("${alumniNetwork.FTPport}")
    private Integer alumniNetworkFTPport;

    @Value("${alumniNetwork.FTPserver}")
    private String alumniNetworkFTPserver;

    @Value("${alumniNetwork.FTPusername}")
    private String alumniNetworkFTPusername;


    @Value("${proxyPassword}")
    private String proxyPassword;

    @Value("${proxyPort}")
    private Integer proxyPort;

    @Value("${proxyServer}")
    private String proxyServer;

    @Value("${proxyUser}")
    private String proxyUser;


    public void alumniUpload(Path path){

        File file = path.toFile();

        try(FileInputStream inputStream = new FileInputStream(file)) {

            ChannelSftp channelSftp = setupJsch();
            channelSftp.connect();

            channelSftp.put(inputStream, alumniNetworkFTPdirectory + file.getName());

            channelSftp.exit();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();

        Session jschSession = jsch.getSession(alumniNetworkFTPusername, alumniNetworkFTPserver);
        jschSession.setPassword(alumniNetworkFTPpassword);
        jschSession.setPort(alumniNetworkFTPport);

        Properties jschConfig=new Properties();
        jschConfig.put("StrictHostKeyChecking","no");
        jschConfig.put("compression.s2c", "zlib,none");
        jschConfig.put("compression.c2s", "zlib,none");
        jschSession.setConfig(jschConfig);

        ProxyHTTP proxyHTTP = new ProxyHTTP(proxyServer,proxyPort);
        proxyHTTP.setUserPasswd(proxyUser,proxyPassword);
        jschSession.setProxy(proxyHTTP);

        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }


}

