/**
 * Insure that rangeRows processing method was used before creating 
 * instance of this class
 * firstNumber and secondNumber must be not empty in the controller
 */

package containerController;

import containerMath.Container;
import containerMath.Item;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
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
    private List <Container> finalContainers;
       
    public readWriter(String filePath, int sheet, String [] cells){
       this.cellCodes = new int[8]; 
       this.sourceFile = new File(filePath); 
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
     /**
     * Reads excel file and matches cells to values
     */
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
                     (int)row.getCell( cellCodes[4]).getNumericCellValue(),
                     row.getCell(cellCodes[5]).getNumericCellValue(),
                     row.getCell(cellCodes[6]).getNumericCellValue(),
                     row.getCell(cellCodes[7]).getNumericCellValue());
                     cont.setStock(item);
                 }
              }catch(IOException | InvalidFormatException ex){
                  ex.printStackTrace();
              }
          }
       /**
        * Main method for writing output of calculation
        * @param outputFile path to output file
        * @param finalList list with sorted containers and items
        * @param sheet sheet to create
        * @param lastRow last row for items of each container
        */
          public void writeOutput(String outputFile, List <Container> finalList, 
           Sheet sheet, int lastRow){
           sheet.setColumnWidth(0, 1300);
           Row headingsRow = sheet.createRow(0);
           setHeadings(headingsRow);
         for(Container c : finalList){
          double values [] = {};
          
         }  
       }
          /**
           * Sets headings in the output file
           * @param headingsRow headings of output file
           */
       private void setHeadings(Row headingsRow){
              String [] headings = {"Наименование", "Количество", 
                         "Количество в упаковке", "Количество упаковок", 
                          "Вес коробки", "Суммарный вес", "ОБъем коробки", 
                          "Суммарный объем"};
           for (int i = 0; i <headings.length; i++){
            Cell cell = headingsRow.createCell(i); cell.setCellValue(headings[i]);
           }
        }
       /**
        * Sets values of items into cells of output file
        * @param row in which create data
        */
       private void setValues(Row row){
       for (Container c:finalContainers ){
          List <Item> writeItems = c.getList();
          for (int i=0; i<writeItems.size(); i++){
              Item item = writeItems.get(i);
              Object[] values = new Object[]{item.getName(),
              item.getNumOfItems(), item.getItemsInPack(), item.getNumOfPacks(),
              item.getWeightOfPack(), item.getSumWeight(), item.getVolumeOfPack(),
              item.getSumVolume()};
              for(int j = 0; j< values.length; j++){
                Cell cell = row.createCell(i); cell.setCellValue(values[i]);
              }
          }
         }
       }
       /**
        * Sets list of containers with sorted items
        * @param containers 
        */
       private void setContainers(List <Container> containers){
           this.finalContainers = containers;
       }
}
