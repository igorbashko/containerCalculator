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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
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
import org.apache.poi.util.IOUtils;
import com.jacob.com.*;
import com.jacob.activeX.*;
import java.util.Enumeration;
//import net.iharder.Base64;
//import org.ini4j.Profile.Section;

public class crypto {
    private SecretKey secret; //secret key with the passPhrase
    private byte [] iv = null; // initialization vector
    private byte [] encryptedMessage = null; //encrypted message
    private String passphrase;
    /**
     * Setting the passphrase for encryption
     * @param passphrase
     * @return 
     */
public String setPassphrase(String passphrase){
    this.passphrase = passphrase;
    return this.passphrase;
}  

public void generateConf(File infoFile, File ivFile, String data){
            try {
            FileOutputStream output = new FileOutputStream(infoFile);
            encryptMessage(data);
            output.write(encryptedMessage);
            output.close(); //probably may cause a problem
            FileOutputStream ivOutput = new FileOutputStream(ivFile);
            ivOutput.write(iv);
            ivOutput.close(); //probably may cause a problem
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   }
/*Method for generation secret key according to passPhrase*/
private SecretKey generateSecretKey(String passPhrase){
    try {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] salt = new byte[8];
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
public void encryptMessage(String message){
         try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            try {
                cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(passphrase));
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
      }catch(GeneralSecurityException ex){
          ex.printStackTrace();
      }catch(UnsupportedEncodingException ex){
          ex.printStackTrace();
      }
    }
/* Method for writing encrypted message from a file and decrypt it*/

public String decryptMessage(String pathToFile, String pathToIV ){
    
     String decryptedMessage = new String("Something went wrong");
    try{
    InputStream fileReader = new FileInputStream(new File(pathToFile));
    InputStream ivReader = new FileInputStream(new File(pathToIV));
    byte [] message = IOUtils.toByteArray(fileReader);
    byte [] iv = IOUtils.toByteArray(ivReader);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(passphrase), 
            new IvParameterSpec(iv) );//chage iv to initialization vector, taken from readed file
    decryptedMessage = new String(cipher.doFinal(message), "UTF-8");
    return decryptedMessage;
    } catch(GeneralSecurityException | IOException ex){
            ex.printStackTrace();
            }
return decryptedMessage;
  }
/**
 * Get unique identifier of the current machine In our case this is the usb 
 * controller number
 */
public String getUniqueId(){
 ComThread.InitMTA();
 String uniqueId = new String();
        try {
            ActiveXComponent wmi = new ActiveXComponent("winmgmts:\\\\.");
            Variant instances = wmi.invoke("InstancesOf", "Win32_OperatingSystem");
            Enumeration<Variant> en = new EnumVariant(instances.getDispatch());
            while (en.hasMoreElements())
            {
                ActiveXComponent bb = new ActiveXComponent(en.nextElement().getDispatch());
                uniqueId = bb.getPropertyAsString("SerialNumber")+"\n"+bb.getPropertyAsString("InstallDate");
                System.out.println(uniqueId);
                //break;
            }
        } finally {
            ComThread.Release();
        }
return uniqueId;
  }
public byte [] getEncrypted(){
    return encryptedMessage;
}
public byte [] getIv(){
   return iv; 
   }
}
