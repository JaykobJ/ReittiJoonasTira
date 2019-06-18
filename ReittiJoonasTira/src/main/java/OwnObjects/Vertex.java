package OwnObjects;

/**
 * Vertex class to hold the information for nodes in the graph that are
 * walkable areas.
 * 
 * @author Jaykob
 */
public class Vertex{
    //class properties
    int xCoordinate;
    int yCoordinate;
    int marker; 
    double distance; //distance form the start vertex
    double heuristic; //distance from the end vertex
    double fCost; //function cost of distance + heuristic
    Vertex parent;
    
    /**
     * Constructor for the class
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param mark character from the map
     */
    public Vertex(int x, int y, char mark)
    {
        this.xCoordinate = x;
        this.yCoordinate = y;
        parseMarkerCode(mark);
        this.parent = null;
    }
    
    /**
     * Constructor for empty class
     * 
     */
    public Vertex()
    {
        
    }
    
    /**
     * Sets the Vertex x-coordinate
     * 
     * @param x x position
     */
    public void setX(int x)
    {
        this.xCoordinate = x;
    }
    
    /**
     * Sets the Vertex y-coordinate
     * 
     * @param y y position
     */
    public void setY(int y)
    {
        this.yCoordinate = y;
    }
    
    /**
     * Sets the character marker of the Vertex
     * 
     * @param marker character 
     */
    public void setMarker(int marker)
    {
        this.marker = marker;
    }
    
    /**
     * Sets the distance from start Vertex
     * 
     * @param d distance as double
     */
    public void setDistance(double d)
    {
        this.distance = d;
    }
    
    /**
     * Sets the heuristic value distance from the end Vertex
     * 
     * @param end destination Vertex
     * @param diagonalWeight diagonal movement cost
     * @param hvWeight horizontal and vertical movement cost
     */
    public void setHeuristic(Vertex end, double diagonalWeight, double hvWeight)
    {
        int dx = Math.abs(this.getX() - end.getX()); //distance from end x-coordinate
        int dy = Math.abs(this.getY() - end.getY()); //distance from end y-coordinate
        
        this.heuristic = hvWeight * (dx + dy) + (diagonalWeight - 2 * hvWeight) * Math.min(dx, dy);
    }
    
    /**
     * Method to tie the heuristic value. This helps the A* algorithm to narrow
     * down the shortest path searches. Makes it faster.
     */
    public void tie()
    {
        this.heuristic *= (this.heuristic * (1 + 1/500));
    }
    
    /**
     * Sets the F cost for the Vertex. F cost is used by A* search algorithm
     * 
     * @param d distance value
     */
    public void setFCost(double d)
    {
        this.fCost = d + this.heuristic;
    }
    
    /**
     * Sets the parent for the Vertex
     * 
     * @param p parent Vertex
     */
    public void setParent(Vertex p)
    {
        this.parent = p;
    }
    
    /**
     * Returns the x-coordinate value
     * 
     * @return int value
     */
    public int getX()
    {
        return this.xCoordinate;
    }
    
    /**
     * Returns the y-coordinate value
     * 
     * @return int value
     */
    public int getY()
    {
        return this.yCoordinate;
    }
    
    /**
     * Returns the marker value as integer
     * 
     * @return int value
     */
    public int getMarker()
    {
        return this.marker;
    }
    
    /**
     * Returns the distance from start Vertex
     * 
     * @return double value
     */
    public double getDistance()
    {
        return this.distance;
    }
    
    /**
     * Returns the heuristic distance from the end Vertex
     * 
     * @return double value
     */
    public double getHeurictic()
    {
        return this.heuristic;
    }
    
    /**
     * Returns the F cost of the Vertex
     * 
     * @return double value 
     */
    public double getFCost()
    {
        return this.fCost;
    }
    
    /**
     * Returns this Vertex parent Vertex
     * 
     * @return parent Vertex 
     */
    public Vertex getParent()
    {
        return this.parent;
    }
    
    /**
     * Returns boolean value if the Vertex has parent or not
     * 
     * @return boolean value
     */
    public boolean hasParent(){
        if(this.parent != null){
            return true;
        }
        return false;
    }
    
    /**
     * Method to parse the character mark from the .map file to integer.
     * 1 is for passable terrain and 0 for non passable.
     * 
     * @param mark character value
     */
    private void parseMarkerCode(char mark)
    {
        switch(mark)
        {
            case '.':
                this.marker = 1;
                break;
            case 'G':
                this.marker = 1;
                break;
            case '@':
                this.marker = 0;
                break;
            case 'O':
                this.marker = 0;
                break;
            default:
                this.marker = 0;
                break;
        }
    }
    
    /**
     * Returns String representation of the Vertex coordinates
     * 
     * @return coordinates as String 
     */
    public String coordinates()
    {
        return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";
    }
    
    /**
     * Override for the toString() method. Returns the Vertex coordinates and
     * their distance value
     * 
     * @return String value of the Vertex 
     */
    @Override
    public String toString()
    {
        String returnValue = "(" + this.xCoordinate + ", " + this.yCoordinate +
                ", " + toTwoDecimal(this.distance) + ")";
        
        return returnValue;
    }  
    
    /**
     * Method to convert double values in to 2 decimal.
     * 
     * @param d value to be converted
     * @return new value
     */
    private double toTwoDecimal(double d)
    {
        d = Math.round(d*100);
        return d/100;
    }
    
    /**
     * Method to generate the hashCode value for the Vertex
     * 
     * @return hashCode value as integer 
     */
    @Override
    public int hashCode()
    {
        int r;
        String code = "" + this.xCoordinate + this.yCoordinate + "";
        r = code.hashCode();
        return r;
    }
    
    /**
     * Method to check if the 2 Vertex are equal or not.
     * 2 Vertex are equal if the share the same coordinates.
     * 
     * @param v Vertex to be compared to
     * @return boolean value
     */
    public boolean equals(Vertex v)
    {
        if(this.coordinates().equals(v.coordinates())){
            return true;
        }
        return false;
    }
}
