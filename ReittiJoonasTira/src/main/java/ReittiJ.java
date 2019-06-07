
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaykob
 */
public class ReittiJ 
{
    public static void main(String[] args)
    {
        BufferedReader reader;    
        String mapType;
        int mapHeight = 0;
        int mapWidth = 0;
        Vertex[][] map;
        
        //read map info
        try
        {   
            //initialize file reader
            reader = new BufferedReader(new FileReader("kartta.map"));
            
            //read map information
            for(int i = 0; i < 4; i++)
            {
                String line = reader.readLine();

                switch(i){
                    case 0:
                        mapType = line;
                        System.out.println("Map: " + mapType);
                        break;
                    case 1:
                        mapHeight = digitFromLine(line);
                        System.out.println("Map Height: " + mapHeight);
                        break;
                    case 2:
                        mapWidth = digitFromLine(line);
                        System.out.println("Map Width: " + mapWidth);
                        break;
                    default:
                        break;
                }
            }
            
            //make map
            map = new Vertex[mapHeight][mapWidth];
            
            char c;
            
            for(int x = 0; x < mapHeight; x++)
            {
                for(int y = 0; y < mapWidth; y++)
                {
                    
                    int readInt = reader.read();
                    
                    //continue reading when reaching end of the line
                    while(readInt == 10 || readInt == 13)
                    {
                        readInt =reader.read();
                    }
                    
                    c = (char) readInt;
                    if(c == '.')
                    {
                        Vertex v = new Vertex(x, y, c);
                        map[x][y] = v;
                    } else if( c == '@'){
                        map[x][y] = null;
                    } 
                }
            }
            
            reader.close();
            
            printMap(map);        
           
            Vertex start = map[0][19];
            Vertex end = map[1][15];
            Dijkstra dijkstra = new Dijkstra();
            long startTime = System.currentTimeMillis();
            dijkstra.doDijkstra(map, start, end);
            long endTime = System.currentTimeMillis();
            System.out.println("Start vertex: " + start.coordinates() + " || End vertex: " + end.coordinates());
            System.out.println("Total distance is: " + dijkstra.distance);
            System.out.println("Total time to get shortest path: " + (endTime - startTime) + " ms");

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    ///////////////////////////OWN METHODS ///////////////////////////////
    
    private static int digitFromLine(String line)
        {
            try
            {
                Pattern p = Pattern.compile("\\d+"); 
                Matcher m = p.matcher(line);
                if(m.find()){
                    return (Integer.parseInt(m.group()));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }
    
    private static void printMap(Vertex[][] graph){
        for (Vertex[] row : graph) 
        {
            for (int i = 0; i < row.length; i++) 
            {
                if(row[i] != null)
                {
                    System.out.print(row[i].marker);
                }
                else
                {
                    System.out.print(0);
                }
            }
            System.out.println("");
        }
    }
}
