/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Algoritm.Astar;
import Algoritm.Dijkstra;
import OwnObjects.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jaykob
 */
public class AlgorithmTesting {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        astarTestMethod("testMap1.map"); sleep(1000);
        astarTestMethod("testMap2.map"); sleep(1000);
        astarTestMethod("testMap3.map"); sleep(1000);
        astarTestMethod("testMap4.map"); sleep(1000);
        astarTestMethod("testMap5.map"); sleep(1000);
        astarTestMethod("testMap6.map"); sleep(1000);
        dijkstraTestMethod("testMap1.map"); sleep(1000);
        dijkstraTestMethod("testMap2.map"); sleep(1000);
        dijkstraTestMethod("testMap3.map"); sleep(1000);
        dijkstraTestMethod("testMap4.map"); sleep(1000);
        dijkstraTestMethod("testMap5.map"); sleep(1000);
        dijkstraTestMethod("testMap6.map"); sleep(1000);
    }
    
    private static void astarTestMethod(String mapName)
    {
        String mapFile = mapName;
        long[] timeArray = new long[50];
        Vertex[][] map = readMap(mapFile);
        int size = map.length;
        Astar astar = new Astar();
        for(int i = 0; i < timeArray.length; i++)
        {
            map = readMap(mapFile);
            //long start = System.currentTimeMillis();
            astar.doAStar(map, map[map.length/2][map[0].length/2], map[map.length-1][map[0].length-1]);
            timeArray[i] = astar.getTimer();
            sleep(100);
        }
        long timeSum = 0;
        for(int i = 0; i < timeArray.length; i++)
        {
            timeSum += timeArray[i];
        }
        System.out.println("--A*-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox time "+timeSum/timeArray.length+"ms");
    }
    
    private static void dijkstraTestMethod(String mapName)
    {
        String mapFile = mapName;
        long[] timeArray = new long[50];
        Vertex[][] map = readMap(mapFile);
        int size = map.length;
        Dijkstra dijkstra = new Dijkstra();
        for(int i = 0; i < timeArray.length; i++)
        {         
            map = readMap(mapFile);
            dijkstra.doDijkstra(map, map[map.length/2][map[0].length/2], map[map.length-1][map[0].length-1]);
            timeArray[i] = dijkstra.getTimer();
            sleep(100);
        }
        long timeSum = 0;
        for(int i = 0; i < timeArray.length; i++)
        {
            timeSum += timeArray[i];
        }
        System.out.println("--Dijkstra-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox time "+timeSum/timeArray.length+"ms");
    }
        
    private static Vertex[][] readMap(String mapFile)
    {
        long startTime = System.currentTimeMillis();
        BufferedReader reader;    
        String mapType;
        int mapHeight = 0;
        int mapWidth = 0;
        Vertex[][] map = new Vertex[mapHeight][mapWidth];
        
        //read map info
        try
        {   
            //initialize file reader
            reader = new BufferedReader(new FileReader(mapFile));
            
            //read map information. 4 first lines
            for(int i = 0; i < 4; i++)
            {
                String line = reader.readLine();
                switch(i){
                    case 0:
                        mapType = line;
                        //System.out.println("Map: " + mapType);
                        break;
                    case 1:
                        mapHeight = digitFromLine(line);
                        //System.out.println("Map Height: " + mapHeight);
                        break;
                    case 2:
                        mapWidth = digitFromLine(line);
                        //System.out.println("Map Width: " + mapWidth);
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
                    if(c == '.' || c == 'G')
                    {
                        Vertex v = new Vertex(x, y, c);
                        map[x][y] = v;
                    } else 
                    {
                        map[x][y] = null;
                    } 
                }
            }
            
            reader.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long timer = endTime - startTime;
        //System.out.println("Time to read: " + timer + " ms");
        return map;
    }
    
    /**
     * Method used to read the map size from the .map files first 4 info rows
     * 
     * @param line Text row from the .map file
     * @return Integer
     */
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
    
    private static void sleep(int ms){
        try 
        {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
