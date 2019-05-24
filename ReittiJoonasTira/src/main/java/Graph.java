
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
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
public class Graph {
    
    HashMap<Vertex, HashSet<Vertex>> graph;
    
    public Graph(){
        this.graph = new HashMap();
    }
    
    //get a neighbor vertex of vector v.
    public Vertex getNeighbor(Vertex v)
    {
        HashSet<Vertex> neighborSet = this.graph.get(v);
        Iterator<Vertex> it = neighborSet.iterator();
        
        Vertex returnableVertex = new Vertex();
        
        try
        {
            returnableVertex = it.next();
        } catch(NoSuchElementException e) {
            System.out.println(e);
        }
        
        return it.next();
    }
    
    //returns boolean value that represents if v has neighbor/s
    public boolean hasNeighbor(Vertex v)
    {
        HashSet<Vertex> neighborSet = this.graph.get(v);
        
        return !neighborSet.isEmpty();
    }
    
    public void addNeighbor(Vertex v, Vertex n)
    {
        HashSet<Vertex> neighborOfV = this.graph.get(v);
        
        if(!neighborOfV.contains(n))
        {
            this.graph.get(v).add(n);
        }
    }
    
    public void addVertex(Vertex v)
    {
        if(this.graph.isEmpty() || !this.graph.containsKey(v))
        {
            HashSet<Vertex> neighbors = new HashSet<>();
            this.graph.put(v, neighbors);
        }
    }
    
    public void removeVertex(Vertex v)
    {
        if(this.graph.containsKey(v))
        {
            HashSet<Vertex> neighborOfV = this.graph.get(v);
            neighborOfV.forEach((e) -> {
                this.graph.get(e).remove(v);
            });
            this.graph.remove(v);
        }
    }
    
//    public void showGraphInfo(){
//        graph.forEach(v, s)
//    }
}
