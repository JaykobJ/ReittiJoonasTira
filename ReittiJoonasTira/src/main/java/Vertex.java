
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class Vertex {
    //class properties
    int vertexIdentifier; 
    int vertexMarker;
    Set<Vertex> neighborSet = new HashSet<Vertex>();
    
    //class constructor
    public Vertex(int vertexId)
    {
        this.vertexIdentifier = vertexId;
    }
    
    //sets vertex marker number as integer
    public void setMarker(int marker)
    {
        this.vertexMarker = marker;
    }
    
    //return the vertex id as integer
    public int getId()
    {
        return this.vertexIdentifier;
    }
    
    //return the vertex marker as integer
    public int getMarker()
    {
        return this.vertexMarker;
    }
}
