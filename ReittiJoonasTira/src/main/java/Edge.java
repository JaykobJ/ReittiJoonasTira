/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class Edge {
    //class properties
    Vertex v, w;
    int weight;
    
    //constructor for the class (without weight)
    public Edge(Vertex v, Vertex w)
    {
        this.v = v;
        this.w = w;
    }
    
//    public Edge(Vertex v, Vertex w, int weight)
//    {
//        this.v = v;
//        this.w = w;
//        this.weight = weight;
//    }
    
    //prints the vertex of the edge
    public void edgeVertex()
    {
        System.out.println("Vertex in edge: ==> " + this.v.vertexIdentifier + 
                " & " + this.w.vertexIdentifier);
    }
    
}
