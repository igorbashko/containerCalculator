/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerMath;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import java.io.File;
/**
 *
 * @author igorbashka
 */
public class containerMath {
    /*
    Reading excell file method
    */
    public void readFromExcel(){
        OPCPackage pkg = OPCPackage.create(new File("my Path to File"));
    }
}
