
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
        char[][] graph;
        
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
            
            //initialize and make graph
            graph = new char[mapWidth][mapHeight];
            char c;
            
            for(int x = 0; x < mapWidth; x++)
            {
                for(int y = 0; y < mapHeight; y++)
                {
                    int readInt = reader.read();
                    while(readInt == 10 || readInt == 13)
                    {
                        readInt =reader.read();
                    }
                    c = (char) readInt;
                    if(c == '.')
                    {
                        c = '1';
                    } else if( c == '@'){
                        c = '0';
                    }

                    graph[x][y] = c;   
                }
            }
            
            reader.close();
            
            for(int i = 0; i < mapWidth; i++)
            {
                for(int ii = 0; ii < mapHeight; ii++)
                {
                    System.out.print(graph[i][ii]);
                }
                System.out.println("");
            }
        System.out.print("\n");
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
