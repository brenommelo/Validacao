/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufmg.hc.telessaude.component.ecg.plot;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo.gomes
 */
public class ECG12PlotTest {
    
    public ECG12PlotTest() {
    }

    /**
     * Test of record method, of class ECG12Plot.
     */
//    @Test
    public void testRecord() {
        System.out.println("record");
        ECG12Plot instance = new ECG12Plot();
        int[][] expResult = null;
        int[][] result = instance.registrar();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class ECG12Plot.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int X = 0;
        ECG12Plot instance = new ECG12Plot();
        instance.setX(X);
        assertNotNull(instance.getX());
    }

    /**
     * Test of setY method, of class ECG12Plot.
     */
//    @Test
    public void testSetY() {
        System.out.println("setY");
        int Y = 0;
        ECG12Plot instance = new ECG12Plot();
        instance.setY(Y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showTracing method, of class ECG12Plot.
     */
//    @Test
    public void testShowTracing() {
        System.out.println("showTracing");
        int[][] derivations = null;
        ECG12Plot instance = new ECG12Plot();
        instance.mostrarTracado(derivations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSamples method, of class ECG12Plot.
     */
//    @Test
    public void testSetSamples() {
        System.out.println("setSamples");
        int[] derivations = null;
        ECG12Plot instance = new ECG12Plot();
        instance.setAmostras1(derivations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetView method, of class ECG12Plot.
     */
//    @Test
    public void testResetView() {
        System.out.println("resetView");
        ECG12Plot instance = new ECG12Plot();
        instance.resetView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScroll method, of class ECG12Plot.
     */
//    @Test
    public void testGetScroll() {
        System.out.println("getScroll");
        ECG12Plot instance = new ECG12Plot();
        JScrollPane expResult = null;
        JScrollPane result = instance.getScroll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyTyped method, of class ECG12Plot.
     */
//    @Test
    public void testKeyTyped() {
        System.out.println("keyTyped");
        KeyEvent e = null;
        ECG12Plot instance = new ECG12Plot();
        instance.keyTyped(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class ECG12Plot.
     */
//    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent e = null;
        ECG12Plot instance = new ECG12Plot();
        instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class ECG12Plot.
     */
//    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent e = null;
        ECG12Plot instance = new ECG12Plot();
        instance.keyReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of verificaMaior method, of class ECG12Plot.
     */
//    @Test
    public void testVerificaMaior() {
        System.out.println("verificaMaior");
        int x = 0;
        int y = 0;
        int z = 0;
        ECG12Plot instance = new ECG12Plot();
        int expResult = 0;
        int result = instance.verificaMaior(x, y, z);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtroTracado method, of class ECG12Plot.
     */
//    @Test
    public void testFiltroTracado() {
        System.out.println("filtroTracado");
        int[] x = null;
        int[] y = null;
        int[] z = null;
        ECG12Plot instance = new ECG12Plot();
        int expResult = 0;
        int result = instance.filtroTracado(x, y, z);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAbrirJanela(){
        ECG12Plot ecg = new ECG12Plot();
        ecg.setVisible(true);
        JFrame frame = new JFrame();
        frame.add(ecg);
        frame.setPreferredSize(new Dimension(600, 800));
        ecg.setVisible(true);
        frame.setVisible(true);
        assertNotNull(ecg);
    }
}
