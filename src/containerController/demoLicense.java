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
import org.ini4j.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.ini4j.Profile.Section;

public class demoLicense {
public void readConf(){
    try{
        String appData;
        appData = System.getenv("APPDATA"+"\\ContainerCalc");
        File infoFile = new File(appData);
        if(infoFile.isFile()){
        Ini firstConf = new Ini(infoFile);
        Section section1 = firstConf.add("section1"); //number of times application was run 
        section1.put("runned", "0");
        
        }
    }catch(IOException ex){
        ex.printStackTrace();
    }
   }

}
