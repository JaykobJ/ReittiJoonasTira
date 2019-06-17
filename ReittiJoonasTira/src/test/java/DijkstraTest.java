import OwnObjects.Vertex;
import Algoritm.Dijkstra;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for dijkstra algorithm
 *
 * @author Jaykob
 */
public class DijkstraTest {
    
    Vertex[][] testMap = {
                            {new Vertex(0, 0, '.'), new Vertex(0, 1, '.'), new Vertex(0, 2, '.'), new Vertex(0, 3, '.')},
                            {new Vertex(1, 0, '.'), new Vertex(1, 1, '.'), new Vertex(1, 2, '.'), new Vertex(1, 3, '.')},
                            {new Vertex(2, 0, '.'), new Vertex(2, 1, '.'), new Vertex(2, 2, '.'), new Vertex(2, 3, '.')},
                            {new Vertex(3, 0, '.'), new Vertex(3, 1, '.'), new Vertex(3, 2, '.'), new Vertex(3, 3, '.')}
                        };
    
    Vertex[][] testFailMap = {
                            {new Vertex(0, 0, '.'), new Vertex(0, 1, '.'), new Vertex(0, 2, '.'), new Vertex(0, 3, '.')},
                            {new Vertex(1, 0, '.'), new Vertex(1, 1, '.'), null                 , null},
                            {new Vertex(2, 0, '.'), new Vertex(2, 1, '.'), null                 , null},
                            {new Vertex(3, 0, '.'), new Vertex(3, 1, '.'), null                 , new Vertex(3, 3, '.')}
                        };
    
    @Test
    public void findPathTrue()
    {
        Dijkstra dij = new Dijkstra();
        Vertex start = testMap[0][0];
        Vertex end = testMap[2][2];
        dij.doDijkstra(testMap, start, end);
        
        assertEquals(true, dij.endFound());
    }
    
    @Test
    public void findPathFalse()
    {
        Dijkstra dij = new Dijkstra();
        Vertex start = testFailMap[1][1];
        Vertex end = testFailMap[3][3];
        dij.doDijkstra(testFailMap, start, end);
        
        assertEquals(false, dij.endFound());
    }
}
