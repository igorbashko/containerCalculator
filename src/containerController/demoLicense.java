/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

/**
 *
 * @author igor
 */
//import org.ini4j.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
//import org.ini4j.Profile.Section;

public class demoLicense {
    private SecretKey secret; //secret key with the passPhrase
public void readConf(){
    String appData;
    appData = System.getenv("APPDATA"+"\\ContainerCalc\\info.txt");
    File infoFile = new File(appData);
    if(infoFile.isFile()){
        String info = "FALSE\n1\n60000\n400";
        //infoFile.
    }
   }
/*Method for generation secret key according to passPhrase*/
private SecretKey generateSecretKey(String passPhrase){
    try {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] salt = new byte[8];
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(salt);
        KeySpec specification= new PBEKeySpec(passPhrase.toCharArray(), salt, 65536, 128);
        try {
            SecretKey tmp = factory.generateSecret(specification);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            this.secret = secret;
        } catch (InvalidKeySpecException ex) {
           ex.printStackTrace();
        }
     } catch (NoSuchAlgorithmException ex) {
        ex.printStackTrace();
    }
   return secret;
 }

/*Method for encrypting the message*/
private String encryptMessage(String message){
    
}
}
