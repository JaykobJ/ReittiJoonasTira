package DataStructure;

import OwnObjects.Vertex;
import java.io.Serializable;

/**
 * Hash Set implementation for the project
 *
 * @author Jaykob
 */
public class AlgoSet implements Serializable
{
    private int objectCount;
    private Vertex[] array;
    private double alfa = 0.75;
    
    /**
     * Constructor for the hash set
     * 
     * @param graphHeight graphs height
     * @param graphWidth  graphs width
     */
    public AlgoSet(int graphHeight, int graphWidth)
    {
        int arraySize = (graphHeight * graphWidth)/4;
        this.array = new Vertex[arraySize];
        this.objectCount = 0;
    }
    
    /**
     * Adds a Vertex to the hash set by it's hash value.
     * Returns a boolean value for result if the Vertex was added or not.
     * Time consume O(1)
     * 
     * @param v Vertex to be added
     * @return boolean value
     */
    public boolean add(Vertex v) 
    {
        if(grow())
        {
            growSize();
        }
        int i = 0;

        while(i <= this.array.length)
        {
            int index = Math.abs((v.hashCode() + i) % this.array.length);          

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
      return false;
    }
    
    /**
     * Check if the Set contains the Vertex.
     * Returns a boolean value for result if the Vertex was found or not.
     * Time consume O(1)
     * 
     * @param v Vertex that is searched
     * @return boolean value
     */
    public boolean contains(Vertex v)
    {
        int i = 0;
        
        while(i <= this.array.length)
        {
            int index = Math.abs((v.hashCode() + i) % this.array.length);
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
    
    /**
     * Method that grows the size of the array used by the set if it's Object
     * count is about to exceed the alfa value. Re hashes the previous
     * Objects to the new array.
     * Time consume O(n), where n = size of the previous array.
     * 
     */
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
    
    /**
     * Method that checks if the size of the array that the set uses has 
     * to be grown.
     * 
     * @return boolean value
     */
    private boolean grow(){
        int newObjCount = this.objectCount + 1;
        double c = (double)newObjCount/(double)this.array.length;
        if(c < this.alfa && c >= 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Returns the amount of Objects the Set holds
     * 
     * @return Object count as integer value
     */
    public int getSize()
    {
        return this.objectCount;
    }
    
    /**
     * Returns the size of the array that the Set uses
     * 
     * @return Array size as integer value
     */
    public int lenght()
    {
        return this.array.length;
    }
}
