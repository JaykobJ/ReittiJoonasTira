package DataStructure;

import OwnObjects.Vertex;
import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Minimum-priority queue implementation for the project
 * 
 * @author Jaykob
 */
public class AlgoMinPrioQueue implements Serializable
{
    private int objectCount;
    private Vertex[] priorityQue;
    private Comparator<Vertex> vertexComparator;  // optional comparator
    
    /**
     * Constructor for the minimum-priority queue
     * 
     * @param graphHeight graphs height
     * @param graphWidth graphs width
     * @param c Comparator
     */
    public AlgoMinPrioQueue(int graphHeight, int graphWidth, Comparator c)
    {
        this.vertexComparator = c;
        int arraySize = (graphHeight * graphWidth)/2;
        this.priorityQue = new Vertex[arraySize];
        this.objectCount = 0;
    }
    
    /**
     * Adds a Vertex to the priority queue and set it to it's correct location
     * by the comparator value
     * 
     * @param v Vertex to be added
     */
    public void add(Vertex v)
    {
        if (objectCount == priorityQue.length - 1)
        {
            growSize(2 * priorityQue.length);
        }
        priorityQue[++objectCount] = v;
        moveUp(objectCount);
    }
    
    /**
     * Removes and returns the minimum-priority Vertex from the start
     * of the array.
     * 
     * @return Vertex that was removed
     */
    public Vertex remove()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Vertex min = priorityQue[1];
        exchange(1, objectCount--);
        moveDown(1);
        priorityQue[objectCount+1] = null;
        return min;
    }
    
    /**
     * If possible changes the Vertex location in the queue by it's index
     * in the array. This method is used to rearrange the priority queue.
     * Time consume O(log n), where n = size of the array
     * 
     * @param k Index in the array
     */
    private void moveUp(int k) 
    {
        while (k > 1 && greater(k/2, k)) 
        {
            exchange(k, k/2);
            k = k/2;
        }
    }
    
    /**
     * If possible changes the Vertex location in the queue by it's index
     * in the array. This method is used to rearrange the priority queue.
     * Time consume O(log n), where n = size of the array
     * 
     * @param k index in the array
     */
    private void moveDown(int k) 
    {
        while (2*k <= objectCount) 
        {
            int j = 2*k;
            if (j < objectCount && greater(j, j+1))
            {
                j++;
            }
            if (!greater(k, j))
            {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }
    
    /**
     * Method used to grow the size of the array used by the priority queue.
     * Time consume O(n), where n = size of the previous array.
     * 
     * @param capacity new array size
     */
    private void growSize(int capacity) 
    {
        Vertex[] temp = new Vertex[capacity];
        for (int i = 1; i <= objectCount; i++) 
        {
            temp[i] = priorityQue[i];
        }
        priorityQue = temp;
    }
    
    /**
     * Changes the position of the 2 Vertex by their index value in the array
     * 
     * @param i Index in array
     * @param j Index in array
     */
    private void exchange(int i, int j) 
    {
        Vertex swap = priorityQue[i];
        priorityQue[i] = priorityQue[j];
        priorityQue[j] = swap;
    }
    
    /**
     * Method to compare 2 Vertex by their index in the array. Comparison is
     * declared by the Comparator class. Returns true if the first one
     * is greater than the second one.
     * 
     * @param i Index in array
     * @param j Index in array
     * @return boolean value
     */
    private boolean greater(int i, int j) 
    {
        return vertexComparator.compare(priorityQue[i], priorityQue[j]) > 0;
    }
    
    /**
     * Method to check if the array used by the priority queue is empty or not
     * 
     * @return boolean value
     */
    public boolean isEmpty() 
    {
        return objectCount == 0;
    }
    
    /**
     * Returns the amount of Objects the priority queue holds
     * 
     * @return Object count in integer value
     */
    public int size()
    {
        return this.objectCount;
    }
    
    /**
     * Returns the size of the array that the priority queue uses
     * 
     * @return Array size in integer value
     */
    public int lenght()
    {
        return this.priorityQue.length;
    }
}
