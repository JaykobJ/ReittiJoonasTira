/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class Vertex{
    //class properties
    int xCoordinate;
    int yCoordinate;
    int marker;
    double distance = 99;
    Vertex parent;
    
    //class constructor
    public Vertex(int x, int y, char mark)
    {
        this.xCoordinate = x;
        this.yCoordinate = y;
        parseMarkerCode(mark);
        this.parent = null;
    }
    
    public Vertex()
    {
        
    }
    
    //sets vertex x coordinate as integer
    public void setX(int x)
    {
        this.xCoordinate = x;
    }
    
    //sets vertex y coordinate as integer
    public void setY(int y)
    {
        this.yCoordinate = y;
    }
    
    //set vertex marker as integer
    public void setMarker(int marker)
    {
        this.marker = marker;
    }
    
    public void setDistance(double d)
    {
        this.distance = d;
    }
    
    public void setParent(Vertex p)
    {
        this.parent = p;
    }
    
    //return the vertex x coordinate
    public int getX()
    {
        return this.xCoordinate;
    }
    
    //return the vertex y coordinate
    public int getY()
    {
        return this.yCoordinate;
    }
    
    //return the vertex marker as integer
    public int getMarker()
    {
        return this.marker;
    }
    
    public double getDistance()
    {
        return this.distance;
    }
    
    public Vertex getParent()
    {
        return this.parent;
    }
    
    public boolean hasParent(){
        if(this.parent != null){
            return true;
        }
        return false;
    }
    
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
    
    public String coordinates()
    {
        return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";
    }
    
    @Override
    public String toString()
    {
        String returnValue = "(" + this.xCoordinate + ", " + this.yCoordinate +
                ", " + this.marker + ", " + this.distance + ")";
        
        return returnValue;
    }  
}
