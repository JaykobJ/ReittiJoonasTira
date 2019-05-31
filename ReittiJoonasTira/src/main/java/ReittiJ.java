
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

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
        char[][] map;
        Graph verkko;
        
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
            
            //make map and graph
            map = new char[mapWidth][mapHeight];
            verkko = new Graph(mapWidth, mapHeight);
            char c;
            
            for(int x = 0; x < mapWidth; x++)
            {
                for(int y = 0; y < mapHeight; y++)
                {
                    List<Vertex>tempList = new ArrayList<>();
                    //System.out.println(verkko.graph);
                    tempList = verkko.graph.get(x);
                    
                    int readInt = reader.read();
                    while(readInt == 10 || readInt == 13)
                    {
                        readInt =reader.read();
                    }
                    c = (char) readInt;
                    if(c == '.')
                    {
                        Vertex v = new Vertex(x, y, c);
                        tempList.add(y, v);
                        if(x >= 1 && y < mapHeight -1){
                            Vertex w = verkko.graph.get(x-1).get(y+1);
                            if(w.xCoordinate != -1 && !verkko.edges.contains(new Edge(v, w))){
                                verkko.edges.add(new Edge(v, w));
                                verkko.edges.add(new Edge(w, v));
                            }
                        }
                        if(x >= 1){
                            Vertex w = verkko.graph.get(x-1).get(y);
                            if(w.xCoordinate != -1 && !verkko.edges.contains(new Edge(v, w))){
                                verkko.edges.add(new Edge(v, w));
                                verkko.edges.add(new Edge(w, v));
                            }
                            if(y >= 1){
                                w = verkko.graph.get(x-1).get(y-1);
                                if(w.xCoordinate != -1 && !verkko.edges.contains(new Edge(v, w))){
                                verkko.edges.add(new Edge(v, w));
                                verkko.edges.add(new Edge(w, v));
                                }
                            }
                        }
                        if(y >= 1){
                            Vertex w = verkko.graph.get(x).get(y-1);
                            if(w.xCoordinate != -1 && !verkko.edges.contains(new Edge(v, w))){
                                verkko.edges.add(new Edge(v, w));
                                verkko.edges.add(new Edge(w, v));
                            }
                        }
                        c = '1';
                    } else if( c == '@'){
                        c = '0';
                        tempList.add(y, new Vertex(-1,-1, c));
                    }

                    map[x][y] = c;   
                }
            }
            
            reader.close();
            
            for(int i = 0; i < mapWidth; i++)
            {
                for(int ii = 0; ii < mapHeight; ii++)
                {
                    System.out.print(map[i][ii]);
                }
                System.out.println("");
            }
            
            //verkko.graph.forEach((vek, set)-> System.out.println("Vertex: " + vek.xCoordinate + vek.yCoordinate ));
            for(int i = 0; i < verkko.graph.size(); i++){
                ArrayList<Vertex> tempList = verkko.graph.get(i);
                tempList.forEach((v)-> System.out.print(""+ v.xCoordinate +"" + v.yCoordinate + " , "));
                System.out.println("");
            }
            
            System.out.println(verkko.edges.size());
            verkko.edges.forEach((e -> System.out.println("edge --> " + e.startVertex.xCoordinate + e.startVertex.yCoordinate + " --> " 
                    + e.endVertex.xCoordinate + e.endVertex.yCoordinate)));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
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
    
}
