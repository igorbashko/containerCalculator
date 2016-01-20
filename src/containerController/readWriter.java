/**
 * Insure that rangeRows processing method was used before creating 
 * instance of this class
 * firstNumber and secondNumber must be not empty in the controller
 */

package containerController;

import containerMath.Item;
import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
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
    private int sheet;
    private String [] cells;   //array of cells which are letters of columns in the excell file
    private controller cont = controller.getController();
       
    public readWriter(File file, int sheet, String [] cells){
       this.cellCodes = new int[8]; 
       this.sourceFile = file; 
       this.sheet = sheet;
       this.cells = cells;
       setCells();
    }
    /**
     * Matching cells with apache poi indexes
     * @param cells Array with cell names in excel file
     * The order of columns is following:
     * ----------------------------------
     * Item name                                      0
     * Item price                                     1 
     * Number of items                                2
     * Number of items in pack                        3
     * Number of packs                                4
     * Net weight of 1 pack                           5  
     * Gross weight of 1 pack                         6
     * Volume of 1 pack                               7
     * Sum net weight(will be calculated further)     4*5
     * Sum gross weight(will be calculated further)   4*6
     * Sum volume(will be calculated further)         4*7
     */
        private void setCells(){
          int i = 0;
          for (String cell : cells){
              cellCodes[i]= CellReference.convertColStringToIndex(cell);
          }
    }
          public void readFile(){
              try{
                  OPCPackage pkg;
                  pkg = OPCPackage.open(sourceFile);
                  XSSFWorkbook book = new XSSFWorkbook(pkg);
                  Sheet workSheet = book.getSheetAt(sheet);
                 for (int n=cont.getFirstNumber(); n<cont.getSecondNumber(); n++){
                     Row row = workSheet.getRow(n);
                     Item item = new Item(row.getCell(cellCodes[0]).toString(),
                     row.getCell(cellCodes[1]).getNumericCellValue(),
                     row.getCell(cellCodes[2]).getNumericCellValue(),
                     row.getCell(cellCodes[3]).getNumericCellValue(),
                     row.getCell(cellCodes[4]).getNumericCellValue(),
                     row.getCell(cellCodes[5]).getNumericCellValue(),
                     row.getCell(cellCodes[6]).getNumericCellValue(),
                     row.getCell(cellCodes[7]).getNumericCellValue());
                     cont.setStock(item);
                 }
              }catch(IOException | InvalidFormatException ex){
                  ex.printStackTrace();
              }
          }
}
