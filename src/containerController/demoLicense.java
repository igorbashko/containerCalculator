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

public class demoLicense {
public void readConf(){
    try{
       String appData;
        appData = System.getenv("APPDATA"+"\\ContainerCalc");
        Ini firstConf = new Ini(new File(appData));
    }catch(IOException ex){
        ex.printStackTrace();
    }
}    
}
