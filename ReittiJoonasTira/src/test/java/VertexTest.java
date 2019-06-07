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
        assertEquals(99,0, testVertex.distance);
    }
    
    @Test
    public void toStringTest()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals("(1, 2, 1, 99.0)", testVertex.toString());
    }
    
    @Test
    public void coordinatesTest()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals("(12)", testVertex.coordinates());
    }
    
    @Test
    public void setters()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        testVertex.setX(0);
        assertEquals(0, testVertex.getX());
        testVertex.setY(1);
        assertEquals(1, testVertex.getY());
        testVertex.setMarker(0);
        assertEquals(0, testVertex.getMarker());
        testVertex.setDistance(12);
        assertEquals(12,0, testVertex.getDistance());
        Vertex parent = new Vertex(6, 6, '@');
        testVertex.setParent(parent);
        assertEquals(parent, testVertex.getParent());
    }
    
    @Test
    public void parseMarkerTest()
    {
        Vertex testVertex = new Vertex(1, 1, '.');
        assertEquals(1, testVertex.getMarker());
        testVertex = new Vertex(1, 1, 'G');
        assertEquals(1, testVertex.getMarker());
        testVertex = new Vertex(1, 1, '@');
        assertEquals(0, testVertex.getMarker());
        testVertex = new Vertex(1, 1, 'O');
        assertEquals(0, testVertex.getMarker());
        testVertex = new Vertex(1, 1, 'ö');
        assertEquals(0, testVertex.getMarker());
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
