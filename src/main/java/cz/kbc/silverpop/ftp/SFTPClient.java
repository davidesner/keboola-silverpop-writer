package cz.kbc.silverpop.ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
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

    public boolean connect() {
        try {

//        String knownHostsFilename = "/home/username/.ssh/known_hosts";
//        jsch.setKnownHosts(knownHostsFilename);
            this.session = sftpClient.getSession(userName, url);
            session.setPassword(pass);

            session.connect();

            return true;
        } catch (JSchException ex) {
            System.out.println("Unable to connect to sftp repository. " + ex.getMessage());
            return false;
        }
    }

    public void disconnect() throws IOException {
        session.disconnect();
    }

    public void uploadFile(String remoteFolder, String fileName, String localFilePath) throws FtpException {
        File localFile = new File(localFilePath);
//        if (!localFile.exists()) {
//            throw new FtpException("File: " + localFile + " does not exist.");
//        }
//
//        Channel channel = session.openChannel("sftp");
//        channel.connect();
//
//        ChannelSftp sftpChannel = (ChannelSftp) channel;
//        InputStream in = new FileInputStream(localFile);
//        BufferedInputStream bin = new BufferedInputStream(in, 0);
//
//        sftpChannel.put(bin, pass);
//
//        sftpChannel.exit();

    }
}
