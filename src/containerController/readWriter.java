/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author igor
 */
public class readWriter {
    private File sourceFile;
    private int[] cellCodes;
    int sheet;
       
    public readWriter(File file, int sheet){
       this.cellCodes = new int[10]; 
       this.sourceFile = file; 
       this.sheet = sheet;
    }
    /**
     * Matching cells with apache poi indexes
     * @param cells Array with cell names in excel file
     * The order of columns is following:
     * ----------------------------------
     * Item name
     * Number of items
     * Number of items in pack
     * Number of packs
     * Net weight of 1 pack
     * Gross weight of 1 pack
     * Volume of 1 pack
     * Sum net weight(will be calculated further)
     * Sum gross weight(will be calculated further)
     * Sum volume(will be calculated further)
     */
        private void setCells(String [] cells){
          int i = 0;
          for (String cell : cells){
              cellCodes[i]= CellReference.convertColStringToIndex(cell);
          }
    }
          private void readFile(){
              try{
                  OPCPackage pkg;
                  pkg = OPCPackage.open(sourceFile);
                  XSSFWorkbook book = new XSSFWorkbook(pkg);
                  Sheet workSheet = book.getSheetAt(sheet);
              }catch(IOException | InvalidFormatException ex){
                  ex.printStackTrace();
              }
          }
}
