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
import java.text.DecimalFormat;
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
        //maps without obstacles
        testMapsWithoutObstacles();
//        testMapsWithObstacles();
    }
    
    private static void testMapsWithoutObstacles()
    {
        System.out.println("---------------------");
        System.out.println("Testing Maps WITHOUT obstacles");
        astarTestMethod("testMap1.map", 23, 23, 43, 43);
        astarTestMethod("testMap2.map", 32, 32, 62, 62);
        astarTestMethod("testMap3.map", 45, 45, 89, 89);
        astarTestMethod("testMap4.map", 63, 63, 126, 126);
        astarTestMethod("testMap5.map", 90, 90, 178, 178);
        astarTestMethod("testMap6.map", 127, 127, 252, 252);
        astarTestMethod("testMap7.map", 179, 179, 357, 357);
        astarTestMethod("testMap8.map", 253, 253, 505, 505);
        astarTestMethod("testMap9.map", 358, 358, 715, 715);
        dijkstraTestMethod("testMap1.map", 23, 23, 43, 43);
        dijkstraTestMethod("testMap2.map", 32, 32, 62, 62);
        dijkstraTestMethod("testMap3.map", 45, 45, 89, 89);
        dijkstraTestMethod("testMap4.map", 63, 63, 126, 126);
        dijkstraTestMethod("testMap5.map", 90, 90, 178, 178);
        dijkstraTestMethod("testMap6.map", 127, 127, 252, 252);
        dijkstraTestMethod("testMap7.map", 179, 179, 357, 357);
        dijkstraTestMethod("testMap8.map", 253, 253, 505, 505);
        dijkstraTestMethod("testMap9.map", 358, 358, 715, 715);
        System.out.println("---------------------");
    }
    
    private static void testMapsWithObstacles()
    {
        System.out.println("---------------------");
        System.out.println("Testing Maps WITH obstacles");
        astarTestMethod("AR0014SR.map", 18, 66, 77, 77);
        astarTestMethod("AR0071SR.map", 121, 78, 79, 51);
        astarTestMethod("AR0205SR.map", 118, 12, 117, 190);
        astarTestMethod("AR0300SR.map", 4, 119, 268, 241);
        dijkstraTestMethod("AR0014SR.map", 18, 66, 77, 77);
        dijkstraTestMethod("AR0071SR.map", 121, 78, 79, 51);
        dijkstraTestMethod("AR0205SR.map", 118, 12, 117, 190);
        dijkstraTestMethod("AR0300SR.map", 4, 119, 268, 241);
        System.out.println("---------------------");
    }
    
    private static void astarTestMethod(String mapName, int startX, int startY,
            int endX, int endY)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        String mapFile = mapName;
        long[] timeArray = new long[20];
        long[] nanoTimeArray = new long[20];
        double[] distanceArray = new double[20];
        Vertex[][] map = readMap(mapFile);
        int size = map.length;
        for(int i = 0; i < timeArray.length; i++)
        {
            Astar astar = new Astar();
            map = readMap(mapFile);
            Vertex start = map[startX][startY];
            Vertex end = map[endX][endY];
            astar.doAStar(map, start, end);
            timeArray[i] = astar.getTimer();
            nanoTimeArray[i] = astar.getNanoTimer();
            distanceArray[i] = astar.getVertex(end.getX(), end.getY()).getDistance();
            sleep(50);
        }
        long timeSum = 0;
        long nanoTimeSum = 0;
        double distanceSum = 0;
        System.out.println("Time: ");
        for(int i = 0; i < timeArray.length; i++)
        {
            System.out.print(timeArray[i]+", ");
            timeSum += timeArray[i];
            nanoTimeSum += nanoTimeArray[i];
            distanceSum += distanceArray[i];
        }
        System.out.println("");
        System.out.println("--A*-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox time "+timeSum/timeArray.length+"ms");
        System.out.println("aprox NANO time "+nanoTimeSum/nanoTimeArray.length+"ns");
        System.out.println("--A*-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox distance "+df.format(distanceSum/distanceArray.length));
    }
    
    private static void dijkstraTestMethod(String mapName, int startX, int startY,
            int endX, int endY)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        String mapFile = mapName;
        long[] timeArray = new long[20];
        double[] distanceArray = new double[20];
        Vertex[][] map = readMap(mapFile);
        int size = map.length;
        for(int i = 0; i < timeArray.length; i++)
        {         
            Dijkstra dijkstra = new Dijkstra();
            map = readMap(mapFile);
            Vertex start = map[startX][startY];
            Vertex end = map[endX][endY];
            dijkstra.doDijkstra(map, start, end);
            timeArray[i] = dijkstra.getTimer();
            distanceArray[i] = dijkstra.getVertex(end.getX(), end.getY()).getDistance();
            sleep(50);
        }
        long timeSum = 0;
        double distanceSum = 0;
        System.out.println("Time: ");
        for(int i = 0; i < timeArray.length; i++)
        {
            System.out.print(timeArray[i]+", ");
            timeSum += timeArray[i];
            distanceSum += distanceArray[i];
        }
        System.out.println("");
        System.out.println("--Dijkstra-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox time "+timeSum/timeArray.length+"ms");
        System.out.println("--Dijkstra-- For map '"+mapName+"' | size("+size+"*"+size+"): aprox distance "+df.format(distanceSum/distanceArray.length));
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
            reader = new BufferedReader(new FileReader("testMaps/"+mapFile));
            
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
