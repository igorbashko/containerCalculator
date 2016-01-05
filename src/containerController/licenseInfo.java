/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author igor
 */
public class licenseInfo {
    private String [] variables;
    private boolean activated;
    private int runTimes;
    private String licenseKey;
    private String uniqueId;
    
    /*Process data in input file for license checking purposes*/
    public licenseInfo(String data){
        variables = new String[4];
      InputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
      BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
      int index = 0;
      String s = new String();
        try {
            while ((s = rd.readLine()) != null){
                variables[index] = s;
                index++;
            } 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /*Get boolean value of was the software activated or not*/
    public boolean getActivated(){
        this.activated = Boolean.valueOf(variables[0].trim());
        return activated;
    }
    /*Get to know how many times file was run under the demo license*/
    public int getRunTimes(){
        this.runTimes = Integer.parseInt(variables[1].trim());
        return runTimes;
    }
    /*Get the license key*/
    public String getKey(){
        this.licenseKey = variables[2].trim();
        return licenseKey;
    }
    public String getId(){
        this.uniqueId = variables[3];
        return uniqueId;
    }
 }
