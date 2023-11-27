/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.sshjext;

import java.nio.file.Path;
import java.security.KeyPair;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.Transport;
import tw.dev.tomoaki.nioext.PureTextPathHelper;

/**
 *
 * @author tomoaki
 */
public class SSHJHelper {

//    public static String createStrRemoteServerFilePath(Transport transport, String remoteDirPath, String... pathNodes) {
//        String remoteServerIdentity = transport.getServerVersion();
//        Path remotePath = Path.of(remoteDirPath, pathNodes);
//        return SCPHelper.createStrRemoteServerFilePath(remotePath, remoteServerIdentity);
//    }
//    
//    public static String createStrRemoteServerFilePath(Path remotePath, Transport transport) {
//        String remoteServerIdentity = transport.getServerVersion();
//        return SCPHelper.createStrRemoteServerFilePath(remotePath, remoteServerIdentity);
//    }
//    
//    public static String createStrRemoteServerFilePath(Path remotePath, String remoteServerIdentity) {
//        if(remoteServerIdentity.contains("FreeBSD")) {
//            return remotePath.toString().replaceAll("\\\\", "/");
//        } else {
//            return remotePath.toString();
//        }        
//    }
    public static String createStrRemoteServerFilePath(SSHClient sshClient, String remoteDirPath, String... pathNodes) {
        Transport transport = sshClient.getTransport();
        return SSHJHelper.createStrRemoteServerFilePath(transport, remoteDirPath, pathNodes);
    }

    public static String createStrRemoteServerFilePath(Transport transport, String remoteDirPath, String... pathNodes) {
        String remoteServerIdentity = transport.getServerVersion();
        return SSHJHelper.createStrRemoteServerFilePath(remoteServerIdentity, remoteDirPath, pathNodes);
    }

    public static String createStrRemoteServerFilePath(String remoteServerIdentity, String remoteDirPath, String... pathNodes) {
        if (remoteServerIdentity.contains("Windows")) {
            return PureTextPathHelper.obtainPureTextPath(PureTextPathHelper.SEPARATOR_WINDOWS, remoteDirPath, pathNodes);
        } else {
            return PureTextPathHelper.obtainPureTextPath(PureTextPathHelper.SEPARATOR_UNIX_LIKE, remoteDirPath, pathNodes);
        }
    }

    public static String createStrRemoteServerFilePath(SSHClient sshClient, Path remoteFilePath) {
        Transport transport = sshClient.getTransport();
        return SSHJHelper.createStrRemoteServerFilePath(transport, remoteFilePath);
    }

    public static String createStrRemoteServerFilePath(Transport transport, Path remoteFilePath) {
        String remoteServerIdentity = transport.getServerVersion();
        return SSHJHelper.createStrRemoteServerFilePath(remoteServerIdentity, remoteFilePath);
    }

    public static String createStrRemoteServerFilePath(String remoteServerIdentity, Path remoteFilePath) {
        if (remoteServerIdentity.contains("Windows")) {
            return PureTextPathHelper.obtainPureTextPath(PureTextPathHelper.SEPARATOR_WINDOWS, remoteFilePath);
        } else {
            return PureTextPathHelper.obtainPureTextPath(PureTextPathHelper.SEPARATOR_UNIX_LIKE, remoteFilePath);
        }
    }
    
    public static void checkSSHConnected(SSHClient sshClient) {
        System.out.println("sshClient.getConnection()= " + sshClient.getConnection());
//        sshClient.getConnection().
//        KeyPair keyPair = new KeyPair();
                
                
    }

}
