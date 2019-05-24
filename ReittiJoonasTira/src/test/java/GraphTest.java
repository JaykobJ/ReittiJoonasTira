/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaykob
 */
public class GraphTest {
    
    @Test
    public void constructorTest()
    {
        Graph testGraph = new Graph();
        assertNotNull(testGraph.graph);
    }
    
    @Test
    public void addAndRemoveVertex(){
        Graph testGraph = new Graph();
        Vertex testVertex = new Vertex(3, 2, '.');
        testGraph.addVertex(testVertex);
        assertEquals(true, testGraph.graph.containsKey(testVertex));
        testGraph.removeVertex(testVertex);
        assertEquals(true, testGraph.graph.isEmpty());
    }
    
    @Test
    public void neighborTest()
    {
        Graph testGraph = new Graph();
        Vertex mainVertex = new Vertex(3, 2, '.');
        Vertex neighborVertex = new Vertex(1, 2, '.');
        testGraph.addVertex(mainVertex);
        testGraph.addNeighbor(mainVertex, neighborVertex);
        
        assertEquals(true, testGraph.graph.get(mainVertex).contains(neighborVertex));
    }
    
    
    
//    public GraphTest() {
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
