/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main.sshj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.FileAttributes;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.Transport;
import net.schmizz.sshj.xfer.FileTransfer;
import net.schmizz.sshj.xfer.scp.SCPFileTransfer;
import tw.dev.tomoaki.sshjext.SSHJHelper;

/**
 *
 * @author tomoaki
 */
public class SCPTestMain {

    private static String remoteHostName = "192.168.111.102";
    private static String remoteDirPath = "/www/htdocs/papers";
    private static String remoteAuthAccount = "root";
    private static String remoteAuthPassword = "ccs@IIS1301";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String localFilePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing\\TEST3.pdf";
        testCopyToRemote(localFilePath, "tomoaki", "123.pdf");
//        testCopyFromRemote(localFilePath, "liao", "25702-F.pdf");
    }

    protected static void testCopyToRemote(String localFilePath, String userAccount, String remoteFileName) throws IOException {
        // TODO code application logic here
        //https://stackoverflow.com/questions/33950209/file-copy-from-local-to-remote-system-using-java-io
        //https://juejin.cn/post/7097970514848645134
        String knowHostsFilePath = "C:\\Users\\tomokai\\.ssh\\known_hosts";

        //https://www.jscape.com/blog/secure-file-transfer-with-java-scp-library                
        SSHClient sshClient = new SSHClient();
        sshClient.loadKnownHosts(new File(knowHostsFilePath));
        sshClient.connect(remoteHostName);
        sshClient.authPassword(remoteAuthAccount, remoteAuthPassword);
        
        Transport transport = sshClient.getTransport();
        String remoteUserDirPath = SSHJHelper.createStrRemoteServerFilePath(transport, remoteDirPath, userAccount);
        String remoteUserFilePath = SSHJHelper.createStrRemoteServerFilePath(transport, remoteUserDirPath, remoteFileName);
        System.out.println(String.format("remoteUserDirPath= %s, remoteFilePath= %s", remoteUserDirPath, remoteUserFilePath));

        try {
            SFTPClient sftpClient = sshClient.newSFTPClient();

            FileAttributes fileAttributes = sftpClient.statExistence(remoteUserDirPath);
            if (fileAttributes == null) {
                sftpClient.mkdir(remoteUserDirPath);
            }
            FileTransfer fileTransfer = sftpClient.getFileTransfer();
            fileTransfer.upload(localFilePath, remoteUserFilePath);
            sftpClient.chown(remoteUserDirPath, 80);
            sftpClient.chown(remoteUserFilePath, 80);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sshClient.disconnect();
        sshClient.close();
    }

    protected static void testCopyFromRemote(String localFilePath, String userAccount, String remoteFileName) throws IOException {
        //https://stackoverflow.com/questions/33950209/file-copy-from-local-to-remote-system-using-java-io
        //https://juejin.cn/post/7097970514848645134
        String filePath = "C:\\Users\\tomokai\\.ssh\\known_hosts";

        //https://www.jscape.com/blog/secure-file-transfer-with-java-scp-library                
        SSHClient sshClient = new SSHClient();
        sshClient.loadKnownHosts(new File(filePath));
        sshClient.connect(remoteHostName);
        sshClient.authPassword(remoteAuthAccount, remoteAuthPassword);

        // System.out.println(String.format("Server identity= %s", sshClient.getTransport().getServerVersion()));
        Transport transport = sshClient.getTransport();
        String remoteFilePath = SSHJHelper.createStrRemoteServerFilePath(transport, remoteDirPath, userAccount, remoteFileName);
        System.out.println("remoteFilePath= " + remoteFilePath);

        try {
            SCPFileTransfer scp = sshClient.newSCPFileTransfer();
            scp.download(remoteFilePath, localFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sshClient.disconnect();
        sshClient.close();
    }

//    protected static String createRemoteServerFilePath(Transport transport, String userAccount, String remoteFileName) {
//        String serverIdentity = transport.getServerVersion();
//        Path remotePath = Path.of(remoteDirPath, userAccount, remoteFileName);
//        if(serverIdentity.contains("FreeBSD")) {
//            return remotePath.toString().replaceAll("\\\\", "/");
//        } else {
//            return remotePath.toString();
//        }
//    }
    
}
