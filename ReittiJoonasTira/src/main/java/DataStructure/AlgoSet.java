package DataStructure;


import OwnObjects.Vertex;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class AlgoSet implements Serializable
{
    private int objectCount;
    private Vertex[] array;
    private double alfa = 0.75;
    
    public AlgoSet(int graphHeight, int graphWidth)
    {
        int arraySize = (graphHeight + graphWidth)/2;
        this.array = new Vertex[arraySize];
        this.objectCount = 0;
    }
    
    public boolean add(Vertex v) 
    {
        try
        {
            if(grow())
            {
                growSize();
            }
            int i = 0;

            while(i <= this.array.length)
            {
                int index = (v.hashCode() + i) % this.array.length;          
                
                if(this.array[index] == null)
                {
                    this.array[index] = v;
                    objectCount++;
                    return true;
                }
                else if(this.array[index].equals(v))
                {
                    return false;
                }
                else
                {
                    i++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
      return false;
    }
    
    public boolean contains(Vertex v)
    {
        int i = 0;
        
        while(i <= this.array.length)
        {
            int index = (v.hashCode() + i) % this.array.length;
            if(this.array[index] == null){
                return false;
            }
            else if(this.array[index].equals(v))
            {
                return true;
            }
            else
            {
                i++;
            }
        }
        return false;
    }
    
    private void growSize()
    {
        Vertex[] temp = this.array.clone();
        int newSize = this.array.length * 2;
        this.array = new Vertex[newSize];
        this.objectCount = 0;
        
        for(Vertex v : temp)
        {
            if(v != null)
            {
                add(v);
            }
        }
    }
    
    private boolean grow(){
        int newObjCount = this.objectCount + 1;
        double c = (double)newObjCount/(double)this.array.length;
        if(c < this.alfa && c >= 0)
        {
            return false;
        }
        return true;
    }
    
    public int getSize()
    {
        return this.objectCount;
    }
    
    public int lenght()
    {
        return this.array.length;
    }
}
