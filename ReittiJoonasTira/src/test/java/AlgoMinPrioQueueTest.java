import OwnObjects.Vertex;
import DataStructure.AlgoMinPrioQueue;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for AlgoMinPrioQueue
 * 
 * @author Jaykob
 */
public class AlgoMinPrioQueueTest {
    
    Comparator<Vertex> vertexComparator = (Vertex v1, Vertex v2) -> 
    {
        return (int)((v1.getDistance() - v2.getDistance())*100);
    };
    
    @Test
    public void constructorSetCorrectValues()
    {
        AlgoMinPrioQueue test = new AlgoMinPrioQueue(5, 5, this.vertexComparator);
        int arraySize = (5 * 5) / 2;
        
        assertEquals(0, test.size());
        assertEquals(true, test.isEmpty());
        assertEquals(arraySize, test.lenght());
    }
    
    @Test
    public void resizeMethodWorksCorrectly() 
    {
        AlgoMinPrioQueue test = new AlgoMinPrioQueue(3, 2, this.vertexComparator);
        
        Vertex v3 = new Vertex(3, 3, '.');
        Vertex v4 = new Vertex(4, 4, '.');
        Vertex v8 = new Vertex(8, 8, '.');
        
        test.add(v3);
        test.add(v4);
        assertEquals(3, test.lenght());
        test.add(v8);
        assertEquals(6, test.lenght());
    }
    
    @Test
    public void minimumPriorityQueueTest()
    {
        AlgoMinPrioQueue test = new AlgoMinPrioQueue(4, 4, this.vertexComparator);
        
        Vertex v1 = new Vertex(1, 1, '.');
        v1.setDistance(1);
        Vertex v3 = new Vertex(3, 3, '.');
        v3.setDistance(3);
        Vertex v4 = new Vertex(4, 4, '.');
        v4.setDistance(4);
        Vertex v8 = new Vertex(8, 8, '.');
        v8.setDistance(8);
        Vertex v20 = new Vertex(20, 20, '.');
        v20.setDistance(20);
        
        test.add(v3);
        test.add(v8);
        test.add(v1);
        test.add(v20);
        test.add(v4);
        
        Vertex tmp;
        tmp = test.remove();
        assertEquals(v1, tmp);
        tmp = test.remove();
        assertEquals(v3, tmp);
        tmp = test.remove();
        assertEquals(v4, tmp);
        tmp = test.remove();
        assertEquals(v8, tmp);
        tmp = test.remove();
        assertEquals(v20, tmp);
        assertEquals(true, test.isEmpty());
    }
}
