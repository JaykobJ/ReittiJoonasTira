package DataStructure;

import OwnObjects.Vertex;
import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class AlgoMinPrioQueue implements Serializable
{
    private int objectCount;
    private Vertex[] priorityQue;
    private Comparator<Vertex> vertexComparator;  // optional comparator
    
    public AlgoMinPrioQueue(int graphHeight, int graphWidth, Comparator c)
    {
        this.vertexComparator = c;
        int arraySize = (graphHeight + graphWidth)*2;
        this.priorityQue = new Vertex[arraySize];
        this.objectCount = 0;
    }
    
    public void add(Vertex v)
    {
        if (objectCount == priorityQue.length - 1)
        {
            resize(2 * priorityQue.length);
        }
        priorityQue[++objectCount] = v;
        moveUp(objectCount);
    }
    
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
    
    private void moveUp(int k) 
    {
        while (k > 1 && greater(k/2, k)) 
        {
            exchange(k, k/2);
            k = k/2;
        }
    }
    
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
    
    private void resize(int capacity) 
    {
        Vertex[] temp = new Vertex[capacity];
        for (int i = 1; i <= objectCount; i++) 
        {
            temp[i] = priorityQue[i];
        }
        priorityQue = temp;
    }
    
    private void exchange(int i, int j) 
    {
        Vertex swap = priorityQue[i];
        priorityQue[i] = priorityQue[j];
        priorityQue[j] = swap;
    }
    
    private boolean greater(int i, int j) 
    {
        return vertexComparator.compare(priorityQue[i], priorityQue[j]) > 0;
    }
    
    public boolean isEmpty() 
    {
        return objectCount == 0;
    }
}
