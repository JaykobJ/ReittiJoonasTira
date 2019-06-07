
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
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
public class Dijkstra {
    Comparator<Vertex> vertexComparator = (Vertex v1, Vertex v2) -> 
    {
        if (v1.getDistance() < v2.getDistance()) return -1;
        if (v1.getDistance() > v2.getDistance()) return 1;
        return 0;
    };
    PriorityQueue<Vertex> mpq;
    Set<Vertex> s;
    Vertex[][] graph;
    boolean endFound;
    double distance;  
    
    public Dijkstra(){
        this.s = new HashSet();
        this.mpq = new PriorityQueue(vertexComparator);
    }
    
    public void doDijkstra(Vertex[][] map, Vertex start, Vertex end){
        endFound = false;
        this.graph = map;
        
        start.setDistance(0); //set start Vertex dinstance to 0
        
        //addint all Vertex to minimun-priority queue
        for(Vertex[] row : graph)
        {
            for(int i = 0; i < row.length; i++)
            {
                if(row[i] != null)
                {
                    mpq.add(row[i]);
                }
            }
        }
        
        //remove vertex with minimum priority untill destination Vertex is found
        while(!mpq.isEmpty() && endFound == false){
            Vertex current = mpq.remove();
            if(s.contains(current))
            {
                continue;
            }
            s.add(current);
            relaxEdges(current, end);
        }      
    }
    
    private void relaxEdges(Vertex current, Vertex end){
        //neighbor positions in all directions of graph
        //checking clockwise starting from above
        int[] xPos = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] yPos = {0, 1, 1, 1, 0, -1, -1, -1};
        int cX = current.xCoordinate;
        int cY = current.yCoordinate;
        Vertex neighbor;

        for(int i = 0; i < xPos.length; i++)
        {
            int nX = current.xCoordinate + xPos[i]; //neighbor x coordinate
            int nY = current.yCoordinate + yPos[i]; //neighbor y coordinate
            double weight;
            
            //if neighbor vertex is not out of graph bounds realx the edges
            if((nX >= 0 && nY >= 0) && (nX < graph[cX].length && nY < graph[cX].length) && graph[nX][nY] != null)
            {
                neighbor = graph[nX][nY];
                if(!s.contains(neighbor))
                {
                    //weigh is set to 1 if neighbor vertex is above, below,
                    //left or right to the current vertex
                    if(cX == nX || cY == nY)
                    {
                        weight = 1;
                        if(neighbor.equals(end))
                        {
                            neighbor.setDistance(current.distance + weight);
                            neighbor.setParent(current);
                            printRoute(neighbor);
                            System.out.println("");
                            //System.out.println("!!!!!!!!!!!!ENDFOUND");
                            endFound = true;
                            break;
                        }
                        else if((current.distance + weight) < neighbor.distance)
                        {
                            neighbor.setDistance(current.distance + weight);
                            neighbor.setParent(current);
                            mpq.add(neighbor);
                            //System.out.println("added: " + neighbor.coordinates());
                        }
                    }
                    //weight is set to sqrt(2) if neighbor vertex is located
                    //diagonally to current vertex
                    else
                    {
                        weight = Math.sqrt(2);
                        if(neighbor.equals(end))
                        {
                            neighbor.setDistance(current.distance + weight);
                            neighbor.setParent(current);
                            printRoute(neighbor);
                            System.out.println("");
                            //System.out.println("!!!!!!!!!!!!ENDFOUND");
                            endFound = true;
                            break;
                        }
                        else if((current.distance + weight) < neighbor.distance)
                        {
                            neighbor.setDistance(current.distance + weight);
                            neighbor.setParent(current);
                            mpq.add(neighbor);
                            //System.out.println("added: " + neighbor.coordinates());
                        }
                    }
                }
            }
        }
    } 
    
    private void printRoute(Vertex v){
        System.out.print(v.toString());
        if(v.distance > 0){
            printRoute(v.parent);
        }
        this.distance += v.getDistance();
    }  
}
