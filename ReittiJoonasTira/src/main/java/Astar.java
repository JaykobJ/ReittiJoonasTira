import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is used to find the shortest path using A* algorithm.
 * 
 * @author Jaykob
 */
public class Astar
{
    Comparator<Vertex> vertexComparator = (Vertex v1, Vertex v2) -> 
    {
        return (int)((v1.getFCost() - v2.getFCost())*100);
    };
    PriorityQueue<Vertex> mpq; //Minimum-priority Queue
    Set<Vertex> s; //Set for finished shortest path objects
    Vertex[][] graph; //2D array to be searched
    boolean endFound;
    final double hvWeight = 1; //Weight for horizontal and Vertical movement
    final double diagonalWeight = Math.sqrt(2); //Weight for diagonal movement
    long timer; //Time for the algorithm to run from start to finish
    final double maxValue = 99999;
    
    /**
     * Constructor for the A* class. Calling this method creates a new
     * empty HashSet and empty PrioityQueue. The PrirorityQueue compares 
     * the objects (Vertex) fCost value. fCost = distance from starting Vertex
     * + estimated distance from the end Vertex (Heuristic value).
     */
    public Astar()
    {
        this.s = new HashSet();
        this.mpq = new PriorityQueue(vertexComparator);
    }
    
    /**
     * Method to search the shortest path from start Vertex to the end Vertex.
     * Updates the class attributes information after it is done.
     * 
     * @param map 2d Array for Vertex objects.
     * @param start starting Vertex.
     * @param end Vertex to be searched for.
     */
    public void doAStar(Vertex[][] map, Vertex start, Vertex end)
    {
        long startTime = System.currentTimeMillis();
        endFound = false;
        this.graph = map;
        
        start.setDistance(0); //set start Vertex dinstance to 0
        start.setFCost(0); //set fCost to 0
        mpq.add(start);
        
        //Setting distance and fCost to 99 999 to all objects
        //except start object
        for(Vertex[] row : graph)
        {
            for(int i = 0; i < row.length; i++)
            {
                if(row[i] != null)
                {
                    if(!row[i].equals(start))
                    {
                        row[i].setFCost(maxValue);
                        row[i].setDistance(maxValue);
                    }
                }
            }
        }
        
        //remove vertex with minimum priority untill destination Vertex is found
        while(!mpq.isEmpty() && endFound == false)
        {
            Vertex current = mpq.remove();
            if(s.contains(current))
            {
                continue;
            }
            s.add(current);
            relaxEdges(current, end);
        }
        long endTime = System.currentTimeMillis();
        this.timer = endTime - startTime;
    }
    
    /**
     * Checks the edges between current Vertex and all it's 8 neighbour
     * Vertex (Skips Vertex that all ready have shortest path and are in 
     * set S and skips Vertices that are out of bounds).
     * If there is edge between the 2 Vertices, compares if the new fCost 
     * is smaller than the current fCost if so then update the neighbours fCost,
     * distance and add it to priority queue.
     * If the neighbour Vertex is the destination then
     * change endFound to true and jump out of the loop.
     * If the neighbour Vertex is all ready in minimum-priority queue and it's 
     * fCost is updated then it is added to the priority queue again. This
     * is done because updating specific object in the priority queue takes O(n)
     * time and changing the attribute of an object doesn't change the order
     * inside the priority queue.
     * 
     * @param current Current Vertex witch neighbours are to be checked 
     * @param end Destination Vertex
     */
    private void relaxEdges(Vertex current, Vertex end)
    {
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
            
            //if neighbor vertex is not out of graph bounds realx the edges
            if((nX >= 0 && nY >= 0) && (nX < graph.length && nY < graph[cX].length) && graph[nX][nY] != null)
            {
                neighbor = graph[nX][nY];
                if(!s.contains(neighbor))
                {
                    //update heuristic value
                    neighbor.setHeuristic(end, diagonalWeight, hvWeight);
                    neighbor.tie(); //used to narrow down the searched area
                    //weigh is 1 if neighbor vertex is above, below,
                    //left or right to the current vertex
                    if(cX == nX || cY == nY)
                    {
                        if(neighbor.equals(end))
                        {
                            neighbor.setDistance(current.getDistance() + hvWeight);
                            neighbor.setParent(current);
                            printRoute(neighbor); //print route
                            endFound = true;
                            break;
                        }
                        else if((current.getDistance() + neighbor.getHeurictic()) < neighbor.getFCost())
                        {
                            neighbor.setDistance(current.distance + hvWeight);
                            neighbor.setParent(current);
                            neighbor.setFCost(current.distance);
                            mpq.add(neighbor);
                            neighbor.setMarker(2); //Marked as visited Vertex
                        }
                    }
                    //weight is sqrt(2) if neighbor vertex is located
                    //diagonally to current vertex
                    else
                    {
                        if(neighbor.equals(end))
                        {
                            neighbor.setDistance(current.getDistance() + diagonalWeight);
                            neighbor.setParent(current);
                            printRoute(neighbor); //print route
                            endFound = true;
                            break;
                        }
                        else if((current.getDistance() + neighbor.getHeurictic()) < neighbor.getFCost())
                        {
                            neighbor.setDistance(current.distance + diagonalWeight);
                            neighbor.setParent(current);
                            neighbor.setFCost(current.distance);
                            mpq.add(neighbor);
                            neighbor.setMarker(2); //Marked as visited Vertex
                        }
                    }
                }
            }
        }
    } 
    
    /**
     * Prints the route from end Vertex to start Vertex by recursively
     * printing the parent Vertex coordinates to command line.
     * 
     * @param v Vertex witch parent is to be printed
     */
    private void printRoute(Vertex v)
    {
        System.out.print(v.coordinates());
        if(v.hasParent())
        {
            printRoute(v.getParent());
        }
    }
    
    /**
     * Method to get specific Vertex from this class.
     * This is mainly used because the Vertex attributes differ from the 
     * other search algorithm classes
     * 
     * @param x The Vertex X coordinate 
     * @param y The Vertex Y coordinate
     * @return Vertex
     */
    public Vertex getVertex(int x, int y)
    {
        return this.graph[x][y];
    } 
    
    /**
     * Returns boolean value if the end Vertex was found or not
     * 
     * @return boolean
     */
    public boolean endFound()
    {
        return this.endFound;
    }
}
