/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.sshj;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import tw.dev.tomoaki.sshjext.SSHJRemoteFileClient;

/**
 *
 * @author tomoaki
 */
public class FileTransferTestMain {

    private static String knownHostsFilePath = "C:\\Users\\tomokai\\.ssh\\known_hosts";
    private static String remoteHostName = "192.168.111.102";
    private static String remoteDirPath = "/www/htdocs/papers";
    private static String remoteAuthAccount = "root";
    private static String remoteAuthPassword = "ccs@IIS1301";
    private static String[] keyLocations = {"C:/Users/tomokai/.ssh/key-pair/aki_id_freepass"};

    public static void main(String[] args) throws IOException {
        test4();
    }

    protected static void test1() throws IOException {
        SSHJRemoteFileClient remoteFileClient = SSHJRemoteFileClient.Factory.createAuthByPassword(knownHostsFilePath, remoteHostName, remoteAuthAccount, remoteAuthPassword);
        Path remoteFilePath = Paths.get(remoteDirPath, "tomoaki", "123.pdf");
        Path localFilePath = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing", "Download-2.pdf");
        remoteFileClient.copyFromRemote(remoteFilePath, localFilePath);
    }

    protected static void test2() throws IOException {
        SSHJRemoteFileClient remoteFileClient = SSHJRemoteFileClient.Factory.createAuthByPassword(knownHostsFilePath, remoteHostName, remoteAuthAccount, remoteAuthPassword);
        Path remoteFilePath = Paths.get(remoteDirPath, "tomoaki", "123.pdf");
        Path localFilePath1 = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing", "Download-3.pdf");
        Path localFilePath2 = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing", "Download-4.pdf");
        remoteFileClient.copyFromRemote(remoteFilePath, localFilePath1);
        remoteFileClient.copyFromRemote(remoteFilePath, localFilePath2);
    }

    protected static void test3() throws IOException {
        SSHJRemoteFileClient remoteFileClient = SSHJRemoteFileClient.Factory.createAuthByPassword(knownHostsFilePath, remoteHostName, remoteAuthAccount, remoteAuthPassword);
        Path localFilePath1 = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing", "TEST3.pdf");
        Path remoteFilePath = Paths.get(remoteDirPath, "tomoaki", "Upload-11241446.pdf");
        remoteFileClient.copyToRemote(localFilePath1, remoteFilePath, 80);
    }    
    
    protected static void test4() throws IOException {
        SSHJRemoteFileClient remoteFileClient = SSHJRemoteFileClient.Factory.createAuthByPublicKey(knownHostsFilePath, remoteHostName, remoteAuthAccount, keyLocations);
        Path localFilePath1 = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\Testing", "TEST3.pdf");
        Path remoteFilePath = Paths.get(remoteDirPath, "tomoaki", "Upload-11271001.pdf");
        remoteFileClient.copyToRemote(localFilePath1, remoteFilePath, 80);
    }        
}
