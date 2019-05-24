/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaykob
 */
public class VertexTest {
    
    @Test
    public void constructorSetCorrectValues()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals(1, testVertex.xCoordinate);
        assertEquals(2, testVertex.yCoordinate);
        assertEquals(1, testVertex.marker);        
    }
    
    @Test
    public void toStringTest()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals("X value: 1 | Y value: 2 | mark: 1", testVertex.toString());
    }
    
    @Test
    public void setters()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        testVertex.setX(0);
        assertEquals(0, testVertex.xCoordinate);
        testVertex.setY(1);
        assertEquals(1, testVertex.yCoordinate);
        testVertex.setMarker(0);
        assertEquals(0, testVertex.marker);
    }
    
//    public VertexTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
}
