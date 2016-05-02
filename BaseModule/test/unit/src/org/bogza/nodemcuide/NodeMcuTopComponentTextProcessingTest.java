/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bogza.nodemcuide;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Constantin
 */
public class NodeMcuTopComponentTextProcessingTest {
    
    public NodeMcuTopComponentTextProcessingTest() {
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
     * Test of SendText method, of class NodeMcuTopComponentTextProcessing.
     */
    @Test
    public void testSendText() {
        System.out.println("SendText");
        String textToBePrinted = "";
        NodeMcuTopComponentTextProcessing instance = null;
        instance.SendText(textToBePrinted);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SendDebugText method, of class NodeMcuTopComponentTextProcessing.
     */
    @Test
    public void testSendDebugText() {
        System.out.println("SendDebugText");
        String textToBePrinted = "";
        NodeMcuTopComponentTextProcessing instance = null;
        instance.SendDebugText(textToBePrinted);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ColorString method, of class NodeMcuTopComponentTextProcessing.
     */
    @Test
    public void testColorString() {
        System.out.println("ColorString");
        String textToBeColored = "";
        NodeMcuTopComponentTextProcessing instance = null;
        String expResult = "";
        String result = instance.ColorString(textToBeColored);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
