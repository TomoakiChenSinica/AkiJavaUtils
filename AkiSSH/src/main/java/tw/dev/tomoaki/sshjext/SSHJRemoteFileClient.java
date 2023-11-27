/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.sshjext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.FileAttributes;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;
import net.schmizz.sshj.xfer.FileTransfer;

/**
 *
 * @author tomoaki
 */
public class SSHJRemoteFileClient {
    
    protected String knownHostsFilePath;
    protected String remoteHostName;
    
    protected AuthType authType;    
    protected String authAccount;
    protected String authPassword; 
    protected KeyProvider[] authKeyProviders;
    protected String[] authKeyPaths;
    
    protected SSHClient sshClient;
    protected SFTPClient sftpClient;
    protected FileTransfer fileTransfer; //FileTransfer 並沒有關閉(Client類才需要)

    protected Boolean printLog = false;
    
    protected SSHJRemoteFileClient() {
    }
    
    public static class Factory {


        /**
         * @param knownHostsFilePath Client OS 上會有「Known Hosts」的檔案。其檔案位置
         * @param remoteHostName 連線的 Host Name
         * @param authAccount 連線身分
         * @param authPassword 連線身分的密碼
         * 
         * @return 
         * 
         */        
        public static SSHJRemoteFileClient createAuthByPassword(String knownHostsFilePath, String remoteHostName, String authAccount, String authPassword) {
            SSHJRemoteFileClient remoteFileClient = new SSHJRemoteFileClient();
            remoteFileClient.knownHostsFilePath = knownHostsFilePath;
            remoteFileClient.remoteHostName = remoteHostName;
            remoteFileClient.authAccount = authAccount;
            remoteFileClient.authPassword = authPassword;
            remoteFileClient.authType = AuthType.PASSWORD;
            return remoteFileClient;
        }

//        public static SSHJRemoteFileClient createAuthByPublicKey(String knownHostsFilePath, String remoteHostName, String authAccount, KeyProvider... keyProviders) {
//            SSHJRemoteFileClient remoteFileClient = new SSHJRemoteFileClient();
//            remoteFileClient.knownHostsFilePath = knownHostsFilePath;
//            remoteFileClient.remoteHostName = remoteHostName;
//            remoteFileClient.authAccount = authAccount;
//            remoteFileClient.authPublicKeyProviders = keyProviders;
//            remoteFileClient.authType = AuthType.PUBLIC_KEY;
//            return remoteFileClient;
//        }
        
        /**
         * @param knownHostsFilePath Client OS 上會有「Known Hosts」的檔案。其檔案位置
         * @param remoteHostName 連線的 Host Name
         * @param authAccount 連線身分
         * @param authKeyPaths 連線身分的 Private Key 位置
         * 
         * @return 
         * 
         */
        public static SSHJRemoteFileClient createAuthByPublicKey(String knownHostsFilePath, String remoteHostName, String authAccount, String... authKeyPaths) {
            SSHJRemoteFileClient remoteFileClient = new SSHJRemoteFileClient();
            remoteFileClient.knownHostsFilePath = knownHostsFilePath;
            remoteFileClient.remoteHostName = remoteHostName;
            remoteFileClient.authAccount = authAccount;
            remoteFileClient.authKeyPaths = authKeyPaths;
            remoteFileClient.authType = AuthType.PUBLIC_KEY;
            return remoteFileClient;
        }
        
    }
    
    protected SSHClient obtainSSHClient() throws IOException {
        if (this.sshClient == null) {
            tryPrintLog("obtainSSHClient(): knownHostsFilePath= %s, remoteHostName= %s, authAccount= %s", knownHostsFilePath, remoteHostName, authAccount);
            sshClient = new SSHClient();
            sshClient.loadKnownHosts(new File(knownHostsFilePath));
            sshClient.connect(remoteHostName);
            sshClient = processClienAuthorization();
        }
//        SSHJHelper.checkSSHConnected(sshClient);
        return sshClient;
    }
    
    protected SSHClient processClienAuthorization() throws UserAuthException, TransportException {
        switch (authType) {
            case PASSWORD: {
                tryPrintLog("processClienAuthorization(): AuthType Is Password, authAccount= %s, authPassword= %s", authAccount, authPassword);
                this.sshClient.authPassword(authAccount, authPassword);
                break;
            }
            case PUBLIC_KEY: {
                tryPrintLog("processClienAuthorization(): AuthType Is Public Key, authAccount= %s, authKeyPaths= %s", authAccount, Arrays.asList(authKeyPaths));
                this.sshClient.authPublickey(authAccount, authKeyPaths);
                break;
                
            }
        }
        return sshClient;
    }
    
    protected FileTransfer obtainFileTransfer(SSHClient localSSHClient) {
        if (this.fileTransfer == null) {
            this.fileTransfer = localSSHClient.newSCPFileTransfer();
        }
        return fileTransfer;
    }
    
    protected FileTransfer obtainFileTransfer(SFTPClient localSFTPClient) {
        if (this.fileTransfer == null) {
            this.fileTransfer = localSFTPClient.getFileTransfer();
        }
        return fileTransfer;
    }
    
    protected SFTPClient obtainSFTPClient() throws IOException {
        if (this.sftpClient == null) {
            SSHClient localSSHClient = this.obtainSSHClient();
            this.sftpClient = localSSHClient.newSFTPClient();
        }
        return this.sftpClient;
    }
    
    public void copyFromRemote(Path remoteUserFilePath, Path localFilePath) throws IOException {
        SSHClient localSSHClient = this.obtainSSHClient();
        String strRemoteFilePath = SSHJHelper.createStrRemoteServerFilePath(localSSHClient, remoteUserFilePath);
        String strLocalFilePath = localFilePath.toString();
        
        FileTransfer localFileTransfer = this.obtainFileTransfer(localSSHClient);
        localFileTransfer.download(strRemoteFilePath, strLocalFilePath);
    }
    
    public void copyToRemote(Path localFilePath, Path remoteUserFilePath, Integer remoteFileUId) throws IOException {
        tryPrintLog("copyToRemote(): localFilePath= %s, remoteUserFilePath= %s, remoteFileUId= %s", localFilePath, remoteUserFilePath, remoteFileUId);
        SSHClient localSSHClient = this.obtainSSHClient();
        String strLocalFilePath = localFilePath.toString();
        
        Path remoteUserDirPath = remoteUserFilePath.getParent();
        String strRemoteUserDirPath = SSHJHelper.createStrRemoteServerFilePath(localSSHClient, remoteUserDirPath);
        String strRemoteUserFilePath = SSHJHelper.createStrRemoteServerFilePath(localSSHClient, remoteUserFilePath);
        
        SFTPClient localSFTPClient = this.obtainSFTPClient();
        
        FileAttributes fileAttributes = localSFTPClient.statExistence(strRemoteUserDirPath);
        if (fileAttributes == null) {
            localSFTPClient.mkdir(strRemoteUserDirPath);
        }
        tryPrintLog("copyToRemote(): Before obtainTransfer(), localSFTPClient= %s", localSFTPClient);
        FileTransfer localFileTransfer = this.obtainFileTransfer(localSFTPClient);
        tryPrintLog("copyToRemote(): After obtainTransfer() And Before Upload File, localSFTPClient= %s", localSFTPClient);
        localFileTransfer.upload(strLocalFilePath, strRemoteUserFilePath);
        tryPrintLog("copyToRemote(): After Upload File, localSFTPClient= %s", localSFTPClient);
        
        localSFTPClient.chown(strRemoteUserDirPath, remoteFileUId);
        localSFTPClient.chown(strRemoteUserFilePath, remoteFileUId);
    }
    
    public void closeAll() throws IOException {
        
    }
    
    public void terminateSSHClient() throws IOException {
        if (this.sshClient != null) {
            this.sshClient.disconnect();
            this.sshClient.close();
        }
    }
    
    public void terminateSFTPClient() throws IOException {
        if (this.sftpClient != null) {
            this.sftpClient.close();
        }
    }
    
    public void turnOnPrintLog() {
        this.printLog = true;
    }
    
    public void turnOffPrintLog() {
        this.printLog = false;
    }
    
    public SSHClient getSshClient() {
        return sshClient;
    }
    
    public SFTPClient getSftpClient() {
        return sftpClient;
    }
    
    public FileTransfer getFileTransfer() {
        return fileTransfer;
    }
    
    protected void tryPrintLog(String msgFmt, Object... args) {
        if (printLog) {
            String msgFmtWithHeader = String.format("[SSHJRemoteFileClient] %s", msgFmt);
            System.out.println(String.format(msgFmtWithHeader, args));
        }
    }
    
    public enum AuthType {
        PASSWORD,
        PUBLIC_KEY
    }
    
}
