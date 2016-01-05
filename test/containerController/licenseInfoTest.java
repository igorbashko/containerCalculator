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

/**
 *
 * @author igor
 */
public class licenseInfoTest {
    private String testMessage = "FALSE\n1\nABC";
    
    public licenseInfoTest() {
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
     * Test of getActivated method, of class licenseInfo.
     */
    @Test
    public void testGetActivated() {
        System.out.println("getActivated");
        licenseInfo instance = new licenseInfo(testMessage);
        boolean expResult = false;
        boolean result = instance.getActivated();
        assertEquals(expResult, result);
       
    }
    /**
     * Test of getRunTimes method, of class licenseInfo.
     */
    @Test
    public void testGetRunTimes() {
        System.out.println("getRunTimes");
        licenseInfo instance = new licenseInfo(testMessage);
        int expResult = 1;
        int result = instance.getRunTimes();
        assertEquals(expResult, result);
   }
    /**
     * Test of getKey method, of class licenseInfo.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        licenseInfo instance = new licenseInfo(testMessage);
        String expResult = "ABC";
        String result = instance.getKey();
        assertEquals(expResult, result);
       
    }
}
