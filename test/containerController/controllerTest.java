/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author igor
 */
public class controllerTest {
    private crypto demo;
    public controllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getController method, of class controller.
     */
    @Ignore
    public void testGetController() {
        System.out.println("getController");
        controller expResult = null;
        controller result = controller.getController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatedValue method, of class controller.
     */
    @Ignore
    public void testFormatedValue() {
        System.out.println("formatedValue");
        String input = "";
        controller instance = null;
        String expResult = "";
        String result = instance.formatedValue(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRowsField method, of class controller.
     */
    @Ignore
    public void testCheckRowsField() {
        System.out.println("checkRowsField");
        String input = "";
        controller instance = null;
        boolean expResult = false;
        boolean result = instance.checkRowsField(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formMessage method, of class controller.
     */
    @Ignore
    public void testFormMessage() {
        System.out.println("formMessage");
        String flag = "";
        controller instance = null;
        String expResult = "";
        String result = instance.formMessage(flag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rowsRangeProcessing method, of class controller.
     */
    @Ignore
    public void testRowsRangeProcessing() {
        System.out.println("rowsRangeProcessing");
        String message = "123-456";
        controller instance = controller.getController();
        instance.rowsRangeProcessing(message);
        assertEquals(455,instance.getSecondNumber());
        
    }
    @Ignore
    public void testDecryptMessage(){
        demo = new crypto();
       // demo.generateConf();
        String filePath = new String(System.getenv("APPDATA")+"\\containerCalculator\\info.txt");
        String ivPath = new String(System.getenv("APPDATA")+"\\containerCalculator\\info1.txt");
        assertEquals("FALSE\n1\n60000\n400",demo.decryptMessage(filePath, ivPath));
    }
    @Ignore
    public void architecture(){
       // System.out.println(System.getProperty("java.library.path"));
        demo = new crypto();
        demo.getUniqueId();
    }
    @Ignore
    public void testCore(){
        controller cont = controller.getController();
      //  cont.Run();
    }
    @Ignore
    public void restRegx(){
      controller cont = controller.getController();
     // cont.setOutput("/jfldsk/jfld/jlfk/ghj.xlsx");
      //assertEquals(cont.regex("/fhsdk/fhk/hjk.xls"), "xls");
    }
 }
