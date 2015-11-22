package cz.kbc.silverpop.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class FTPClient {

    private String userName;
    private String pass;
    private String url;
    private FTPSClient ftpsClient;

    public FTPClient(String userName, String pass, String url) {
        this.userName = userName;
        this.pass = pass;
        this.url = url;
        ftpsClient = new FTPSClient();
    }

    public boolean connect() throws SocketException, IOException {
        ftpsClient.connect(url);
        //login to server
        if (!ftpsClient.login(this.userName, this.pass)) {
            ftpsClient.logout();
            return false;
        }
        int reply = ftpsClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpsClient.disconnect();
            return false;
        }
        //passive mode
        ftpsClient.enterLocalPassiveMode();
        return true;
    }

    public void disconnect() throws IOException {
        ftpsClient.disconnect();
    }

    public void downloadFile(String remoteFolder, String fileName, String localFilePath) throws IOException, FtpException {
        ftpsClient.changeWorkingDirectory(remoteFolder);
        int returnCode = ftpsClient.getReplyCode();
        if (returnCode == 550) {
            throw new FtpException("Remote folder does not exist!");
        }
        FileOutputStream fos = new FileOutputStream(localFilePath);
        this.ftpsClient.retrieveFile(fileName, fos);

    }
}
