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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import net.iharder.Base64;
//import org.ini4j.Profile.Section;

public class demoLicense {
    private SecretKey secret; //secret key with the passPhrase
    private byte [] iv = null; // initialization vector
    private byte [] encryptedMessage = null; //encrypted message
    
public void readConf() throws InvalidParameterSpecException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
    String appData;
    appData = System.getenv("APPDATA")+"\\ContainerCalc\\info";
    File infoFile = new File(appData);
    if(!infoFile.isFile()){
        try {
            String info = "FALSE\n1\n60000\n400";
            infoFile.createNewFile();
            FileOutputStream output = new FileOutputStream(infoFile);
            encryptMessage(info);
            output.write(encryptedMessage);
            File ivFile = new File(System.getenv("APPDATA")+"\\Container\\info1");
            ivFile.createNewFile();
            FileOutputStream ivOutput = new FileOutputStream(ivFile);
            ivOutput.write(iv);
        } catch (IOException ex) {
            Logger.getLogger(demoLicense.class.getName()).log(Level.SEVERE, null, ex);
        }
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
private void encryptMessage(String message) throws InvalidParameterSpecException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
         try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            try {
                cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey("markiza2531"));
                AlgorithmParameters params = cipher.getParameters();
                iv = params.getParameterSpec(IvParameterSpec.class).getIV();
                encryptedMessage = cipher.doFinal(message.getBytes("UTF-8"));
             } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex) {
      ex.printStackTrace();
      }
    }
private String decryptMessage(){
    
}
}
