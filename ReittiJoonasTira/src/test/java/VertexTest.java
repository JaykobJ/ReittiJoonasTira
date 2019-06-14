/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import OwnObjects.Vertex;
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
        assertEquals(1, testVertex.getX());
        assertEquals(2, testVertex.getY());
        assertEquals(1, testVertex.getMarker());
        assertEquals(null, testVertex.getParent());
        
        testVertex = new Vertex();
        assertEquals(0, testVertex.getX());
        assertEquals(0, testVertex.getY());
        assertEquals(0, testVertex.getMarker());
        assertEquals(null, testVertex.getParent());
    }
    
    @Test
    public void toStringTest()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals("(1, 2, 0.0)", testVertex.toString());
    }
    
    @Test
    public void coordinatesTest()
    {
        Vertex testVertex = new Vertex(1, 2, '.');
        assertEquals("(1, 2)", testVertex.coordinates());
    }
    
    @Test
    public void heuristicsTest()
    {
        Vertex test = new Vertex(1, 2, '.');
        Vertex end = new Vertex(6, 7, '.');
        double diagonalW = Math.sqrt(2);
        double hvW = 1;
        test.setHeuristic(end, diagonalW, hvW);
        
        int dx = Math.abs(test.getX() - end.getX()); //distance from end x-coordinate
        int dy = Math.abs(test.getY() - end.getY()); //distance from end y-coordinate  
        double heuristic = hvW * (dx + dy) + (diagonalW - 2 * hvW) * Math.min(dx, dy);
        double twoDecimalHeuristic = Math.round(100 * heuristic) / 100;
        double testHeuristics = test.getHeurictic();
        double twoDecimalVertexHeuristic = Math.round(100 * testHeuristics) / 100;
        
        assertEquals(twoDecimalHeuristic, twoDecimalVertexHeuristic, 0.00);
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
    
    @Test
    public void setCoordinates()
    {
        Vertex test = new Vertex();
        test.setX(5);
        test.setY(6);
        assertEquals(5, test.getX());
        assertEquals(6, test.getY());
    }
    
    @Test
    public void setMarkerTest()
    {
        Vertex test = new Vertex(1, 1, '.');
        test.setMarker(2);
        assertEquals(2, test.getMarker());
    }
    
    @Test
    public void setDistanceTest()
    {
        Vertex test = new Vertex(1, 1, '.');
        test.setDistance(8);
        assertEquals(8.0, test.getDistance(), 0.0);
    }
    
    @Test
    public void setFCostTest()
    {
        Vertex test = new Vertex(1, 1, '.');
        test.setFCost(1.2);
        assertEquals(1.2, test.getFCost(), 0.0);
    }
    
    @Test
    public void setParentTest()
    {
        Vertex test = new Vertex(1, 1, '.');
        Vertex parent = new Vertex(2, 2, '.');
        test.setParent(parent);
        assertEquals(true, test.hasParent());
        assertEquals(parent, test.getParent());
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
