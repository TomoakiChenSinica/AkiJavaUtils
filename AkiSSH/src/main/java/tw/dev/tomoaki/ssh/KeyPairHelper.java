/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.ssh;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomoaki
 */
public class KeyPairHelper {

    public final static String ALGORITHM_X25519 = "X25519";

    public final static String PROVIDER_BOUNCY_CASTLE = "BC";

    public static Boolean isAlgorithmProvided(String algorithmName, String providerAbbrev) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithmName, providerAbbrev);
            return true;
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            System.out.println("Algorithm(algorithmName= %s) or Provider(providerAbbrev= %s) Not Found");
            ex.printStackTrace();
            return false;
        }

    }

    public static Boolean isAlgorithmProvided(String algorithmName, Provider provider) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithmName, provider);
            return true;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(String.format("Algorithm(algorithmName= %s) Not Found From provider= %s", algorithmName, provider));
            ex.printStackTrace();
            return false;
        }

    }

    /*
    // Not AutoCloseable
    public static Boolean isAlgorithmProvided(String algorithmName, String providerAbbrev) {
        try (KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("X25519", "BC")) {

            return true;
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            System.out.println("Algorithm(algorithmName= %s) or Provider(providerAbbrev= %s) Not Found");
            ex.printStackTrace();
            return false;
        }

    }
     */
    public static void tryAddProvided(Provider provider) {
        // 加載 BouncyCastle 提供程序
        Security.addProvider(provider);

        /*// 創建 KeyPairGenerator
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("X25519", "BC");
        
        // 初始化密鑰生成器
        keyPairGen.initialize(255);
        
        // 生成密鑰對
        KeyPair keyPair = keyPairGen.generateKeyPair(); */
    }
}
