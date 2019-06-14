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
import DataStructure.AlgoSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaykob
 */
public class AlgoSetTest {
    
    @Test
    public void constructorSetCorrectValues()
    {
        AlgoSet test = new AlgoSet(5, 5);
        int arraySize = (5 + 5) / 2;
        
        assertEquals(0, test.getSize());
        assertEquals(arraySize, test.lenght());
    }
    
    @Test
    public void addingToSetAvoidsDublicates() {
        AlgoSet test = new AlgoSet(20, 20);
        
        Vertex v3 = new Vertex(3, 3, '.');
        Vertex v4 = new Vertex(4, 4, '.');
        Vertex v8 = new Vertex(8, 8, '.');
        Vertex v30 = new Vertex(30, 20, '.');
        test.add(v3);
        test.add(v4);
        test.add(v8);
        test.add(v4);
        test.add(v8);
        test.add(v30);

        assertEquals(4, test.getSize());
    }
    
    @Test
    public void containsMethodTest() {
        AlgoSet test = new AlgoSet(20, 20);
        
        Vertex v3 = new Vertex(3, 3, '.');
        Vertex v4 = new Vertex(4, 4, '.');
        Vertex v8 = new Vertex(8, 8, '.');
        test.add(v3);
        test.add(v4);
        
        assertEquals(true, test.contains(v3));
        assertEquals(false, test.contains(v8));
    }
    
    @Test
    public void growsSizeWithCorrectAlfaValue() {
        AlgoSet test = new AlgoSet(4, 4);
        double alfa = 0.75; //alfa = object count / array lenght
        
        Vertex v3 = new Vertex(3, 3, '.');
        Vertex v4 = new Vertex(4, 4, '.');
        Vertex v8 = new Vertex(8, 8, '.');
        test.add(v3); //alfa 0.25
        test.add(v4); //alfa 0.5
        assertEquals(4, test.lenght());
        
        test.add(v8);//alfa 0,75 -> grow size * 2
        assertEquals(8, test.lenght());
    }
    
//    public AlgoSetTest() {
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
