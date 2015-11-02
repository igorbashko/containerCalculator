/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerMath;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author igorbashka
 */
public class containerMath {
    /*
    Reading excell file method
    */
    public void readFromExcel(){
        try {
            OPCPackage pkg = OPCPackage.create(new File("my Path to File"));
            XSSFWorkbook book = new XSSFWorkbook(pkg);
            Sheet sheet1 = book.getSheetAt(0);
            Row row = sheet1.getRow(0);
            Cell cell = row.getCell(2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
