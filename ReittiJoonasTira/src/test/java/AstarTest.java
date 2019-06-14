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
import Algoritm.Astar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaykob
 */
public class AstarTest {
    
    Vertex[][] testMap = {
                            {new Vertex(0, 0, '.'), new Vertex(0, 1, '.'), new Vertex(0, 2, '.'), new Vertex(0, 3, '.')},
                            {new Vertex(1, 0, '.'), new Vertex(1, 1, '.'), new Vertex(1, 2, '.'), new Vertex(1, 3, '.')},
                            {new Vertex(2, 0, '.'), new Vertex(2, 1, '.'), new Vertex(2, 2, '.'), new Vertex(2, 3, '.')},
                            {new Vertex(3, 0, '.'), new Vertex(3, 1, '.'), new Vertex(3, 2, '.'), new Vertex(3, 3, '.')}
                        };
    
    Vertex[][] testFailMap = {
                            {new Vertex(0, 0, '.'), new Vertex(0, 1, '.'), new Vertex(0, 2, '.'), new Vertex(0, 3, '.')},
                            {new Vertex(1, 0, '.'), new Vertex(1, 1, '.'), null                 , null}                 ,
                            {new Vertex(2, 0, '.'), new Vertex(2, 1, '.'), null                 , null}                 ,
                            {new Vertex(3, 0, '.'), new Vertex(3, 1, '.'), null                 , new Vertex(3, 3, '.')}
                        };
    
    @Test
    public void findPathTrue()
    {
        Astar astar = new Astar();
        Vertex start = testMap[0][0];
        Vertex end = testMap[2][2];
        astar.doAStar(testMap, start, end);
        
        assertEquals(true, astar.endFound());
    }
    
    @Test
    public void findPathFalse()
    {
        Astar astar = new Astar();
        Vertex start = testFailMap[1][1];
        Vertex end = testFailMap[3][3];
        astar.doAStar(testFailMap, start, end);
        
        assertEquals(false, astar.endFound());
    }
    
//    public AstarTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
