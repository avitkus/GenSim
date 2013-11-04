/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gensim;

import gensim.util.VersionComparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class VersionComparatorTest {
    
    public VersionComparatorTest() {
    }

    /**
     * Test of compare method, of class VersionComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        String v1 = "1.4";
        String v2 = "1.4";
        VersionComparator instance = new VersionComparator();
        int expResult = 0;
        int result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "1.5";
        v2 = "1.4";
        expResult = 1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "1.3";
        v2 = "1.4";
        expResult = -1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "2.5";
        v2 = "1.5";
        expResult = 1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "2.5";
        v2 = "3.4";
        expResult = -1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "1.5b";
        v2 = "1.5";
        expResult = -1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "1.5b";
        v2 = "1.5a";
        expResult = 1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
        
        v1 = "1.5b3";
        v2 = "1.5b2";
        expResult = 1;
        result = instance.compare(v1, v2);
        assertEquals(expResult, result);
    }
}
