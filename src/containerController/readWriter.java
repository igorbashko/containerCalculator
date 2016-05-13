/**
 * Insure that rangeRows processing method was used before creating 
 * instance of this class
 * firstNumber and secondNumber must be not empty in the controller
 */

package containerController;

import containerMath.Container;
import containerMath.Item;
import containerMath.Stock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
    private Sheet outputSheet;
       
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
              i++;
          }
    }
     /**
     * Reads excel file and matches cells to values
     */
          public Stock readFile(int firstItem, int lastItem)throws IllegalStateException,
                  InvalidFormatException, IOException{
              List <Item> stockList = new ArrayList();
              Workbook book = WorkbookFactory.create(this.sourceFile);
                  /*OPCPackage pkg;
                  pkg = OPCPackage.open(sourceFile);*/
              //    XSSFWorkbook book = new XSSFWorkbook(pkg);
                  Sheet workSheet = book.getSheetAt(sheet);
                  Item emptyItem = new Item("Системе не удалось разпознать элемент",
                          0, 0, 0, 0, 0, 0, 0);
                  for (int n=firstItem-1 ; n<lastItem; n++){
                    try{ 
                     Row row = workSheet.getRow(n);
                     Item item = new Item(row.getCell(cellCodes[0]).toString(),
                     row.getCell(cellCodes[1]).getNumericCellValue(),
                     row.getCell(cellCodes[2]).getNumericCellValue(),
                     row.getCell(cellCodes[3]).getNumericCellValue(),
                     row.getCell( cellCodes[4]).getNumericCellValue(),
                     row.getCell(cellCodes[5]).getNumericCellValue(),
                     row.getCell(cellCodes[6]).getNumericCellValue(),
                     row.getCell(cellCodes[7]).getNumericCellValue());
                     stockList.add(item);
                    }catch(NullPointerException ex){
                        stockList.add(emptyItem);
                }
              }
             // pkg.close();
              Stock stock = new Stock(stockList);
              return stock;
          }
       /**
        * Main method for writing output of calculation
        * @param outputFile path to output file
        * @param finalList list with sorted containers and items
        * @param sheet sheet to create
        * @param lastRow last row for items of each container
        */
          public void writeOutput(String outputFile){
           XSSFWorkbook outputBook = new XSSFWorkbook();
           outputSheet = outputBook.createSheet();
           outputSheet.setColumnWidth(0, 1300);
           Row headingsRow = outputSheet.createRow(0);
           setHeadings(headingsRow);
           setValues();
           writeToFile(outputFile, outputBook);
       }
          /**
           * Sets headings in the output file
           * @param headingsRow headings of output file
           */
       private void setHeadings(Row headingsRow){
              String [] headings = {"Наименование", "Цена", 
                         "Количество", "Количество в упаковке", 
                         "Количество коробок", "Вес нетто(1 коробка)", 
                         "Вес брутто(1 коробка)","Объем(1 коробка)",
                         "Суммарный вес нетто", "Суммарный вес брутто",
                         "Объем"};
           for (int i = 0; i <headings.length; i++){
            Cell cell = headingsRow.createCell(i); cell.setCellValue(headings[i]);
           }
        }
       /**
        * Sets values of items into cells of output file
        */
       private void setValues(){
          int lastRow =0;
          for (Container c:finalContainers ){
          List <Item> writeItems = c.getList();
          for (int i=0; i<writeItems.size(); i++){
           //place in excell file from where to write 
          //items of the next container
          lastRow++;
          Row row = outputSheet.createRow(lastRow);
              Item item = writeItems.get(i);
              Object[] values = new Object[]{ //array to store item's values
              item.getName(),                 // 0
              item.getPrice(),                // 1
              item.getNumOfItems(),           // 2
              item.getItemsInPack(),          // 3
              item.getNumOfPacks(),           // 4
              item.getNetWeightOfPack(),      // 5
              item.getWeightOfPack(),         // 6
              item.getVolumeOfPack(),         // 7
              item.getSumNetWeight(),         // 8
              item.getSumWeight(),            // 9
              item.getSumVolume()};           // 10
              //setting name separately, because of String type value
              Cell name = row.createCell(0); name.setCellValue((String) values[0]);
              for(int j = 1; j< values.length; j++){
                Cell cell = row.createCell(j);
                //checking type of number of packs variable it should be int
                if(j==4) cell.setCellValue((double) values[j]); 
                        else
                cell.setCellValue((double)values[j]);
             }
              //checking if end of items list in the containers is reached
              //and adding +2 empty rows
              if(i==(writeItems.size() -1)){
                  lastRow +=1; //+2 empty spaces for better appearance
                  setReport(c, lastRow);
                  lastRow +=4;
              }
          }
         }
       }
      /**
       * Forms report in excel file. Sum weight. Sum volume etc. 
       * @param sheet Sheet where write the items
       * @param c Container from where take the info
       * @param lastRow last row after all items were written
       * method uses lastRow + 2 to write report in next 2 rows
       * after all items of the container were written
       */
       private void setReport(Container c, int lastRow){
           String [] reportHeadings = {
               "Суммарный вес",      // 6
               "Суммарный объем",    // 7
               "Остаток вес",        // 8
               "Остаток объем"       // 9
               };
           double [] values = {c.getWeight(), c.getVolume(), c.getWeightLimit()-
                   c.getWeight(), c.getVolumeLimit() - c.getVolume()};
           int dataCell = 5;
           int valuesIndex = 0; // index of the array of doubles(weigh, volume, etc)
           lastRow++;
           Row headings = outputSheet.createRow(lastRow); 
           lastRow+=2;
           Row data = outputSheet.createRow(lastRow); 
           for(String report: reportHeadings){
               Cell heading = headings.createCell(dataCell); 
               heading.setCellValue(report);
               Cell dataCellValue = data.createCell(dataCell);
               dataCellValue.setCellValue(values[valuesIndex]);
               dataCell++;
               valuesIndex++;
           }
       }
       /**
        * Sets list of containers with sorted items
        * @param containers 
        */
       public void setContainers(List <Container> containers){
           this.finalContainers = containers;
       }/**
        * Writes output of excel book to excel file
        * @param outputPath path of the output file
        * @param writeBook book with final information
        */
       private void writeToFile(String outputPath, XSSFWorkbook writeBook){
         try{
              FileOutputStream outWriter = new FileOutputStream(outputPath);
             writeBook.write(outWriter);
             writeBook.close();
          }catch(IOException ex){
           ex.printStackTrace();
          }
         
       }
     }
