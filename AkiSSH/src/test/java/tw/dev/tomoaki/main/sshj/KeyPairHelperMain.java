/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main.sshj;

import java.security.Provider;
import tw.dev.tomoaki.ssh.KeyPairHelper;

/**
 *
 * @author tomoaki
 */
public class KeyPairHelperMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Provider bcProvider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        KeyPairHelper.tryAddProvided(bcProvider);
        System.out.println(KeyPairHelper.isAlgorithmProvided(KeyPairHelper.ALGORITHM_X25519, KeyPairHelper.PROVIDER_BOUNCY_CASTLE));
        System.out.println(KeyPairHelper.isAlgorithmProvided(KeyPairHelper.ALGORITHM_X25519, bcProvider));
    }

}
