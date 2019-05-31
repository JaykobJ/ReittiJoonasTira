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
    Vertex startVertex;
    Vertex endVertex;
        
    public Edge(Vertex v, Vertex w){
        this.startVertex = v;
        this.endVertex = w;
    }

}
