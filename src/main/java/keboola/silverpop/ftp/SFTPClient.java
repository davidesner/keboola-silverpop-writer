package keboola.silverpop.ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class SFTPClient {

    private String userName;
    private String pass;
    private String url;
    private Session session;
    private JSch sftpClient;

    public SFTPClient(String userName, String pass, String url) {
        this.userName = userName;
        this.pass = pass;
        this.url = url;
        sftpClient = new JSch();;
    }

    public void connect() throws FtpException {
        try {

//        String knownHostsFilename = "/home/username/.ssh/known_hosts";
//        jsch.setKnownHosts(knownHostsFilename);
            this.session = sftpClient.getSession(userName, url);
            /*Ignore unknown host keys*/
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.setPassword(pass);
            session.connect();

        } catch (JSchException ex) {
            throw new FtpException("Unable to connect to sftp repository. " + ex.getMessage());

        }
    }

    public void disconnect() throws IOException {
        session.disconnect();
    }

    public void uploadFile(String remoteFolder, String fileName, String localFilePath) throws FtpException {
        try {
            File localFile = new File(localFilePath);
            if (!localFile.exists()) {
                throw new FtpException("File: " + localFile + " does not exist.");
            }

            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftpChannel = (ChannelSftp) channel;
            InputStream in = new FileInputStream(localFile);
            BufferedInputStream bin = new BufferedInputStream(in, 512);

            sftpChannel.cd(remoteFolder);
            sftpChannel.put(bin, fileName);
            sftpChannel.exit();
        } catch (JSchException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        } catch (SftpException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            throw new FtpException("File does not exist. " + ex.getMessage());
        }

    }

    public void uploadFile(String remoteFolder, String fileName, InputStream in) throws FtpException {
        try {

            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftpChannel = (ChannelSftp) channel;

            BufferedInputStream bin = new BufferedInputStream(in, 512);

            sftpChannel.cd(remoteFolder);
            sftpChannel.put(bin, fileName);
            sftpChannel.exit();
        } catch (JSchException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        } catch (SftpException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        }

    }

    public String downloadFileToString(String remoteFolder, String fileName) throws FtpException {
        try {

            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftpChannel = (ChannelSftp) channel;
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            sftpChannel.cd(remoteFolder);
            sftpChannel.get(fileName, os);
            sftpChannel.exit();
            String result = new String(os.toByteArray());
            return result;
        } catch (JSchException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        } catch (SftpException ex) {
            throw new FtpException("Could not upload the file to FTP server. " + ex.getMessage());
        }

    }
}
