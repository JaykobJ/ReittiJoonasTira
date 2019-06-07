/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author Jaykob
 */
public class View extends JPanel{
    Vertex[][] map;
    Vertex end, start;
    
    public View(Vertex[][] graph) {
        this.map = graph;
//        setTitle("Shortest path");
//        setSize(800, 900);
//        setResizable(rootPaneCheckingEnabled);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().add(new JScrollBar());
//        setTitle("Shortest path");
//        setSize(900, 700); 
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridBagLayout());
        
        
        //JScrollPane scroll = new JScrollPane(this.rootPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //setAutoscrolls(true);
        
        
        Dijkstra dijkstra = new Dijkstra();
        start = map[0][0];
        end = map[4][4];
        dijkstra.doDijkstra(map, start, end);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw the maze
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Color color;
                int marker;
                if(map[row][col] != null){
                    marker = map[row][col].marker;
                }else{
                    marker = 0;
                }
                switch (marker) {
                    case 0 : color = Color.BLACK; break;
                    case 1 : color = Color.WHITE; break;
                    default : color = Color.RED;
                }
                g.setColor(color);
                g.fillRect(5 * col, 5 * row, 5, 5);
                g.setColor(Color.BLACK);
                g.drawRect(5 * col, 5 * row, 5, 5);
            }
        }
        
        if(end.hasParent()){
            g.setColor(Color.RED);
            g.fillRect(end.getY() * 5, end.getX() * 5, 5, 5);
            g.setColor(Color.BLACK);
            g.drawRect(end.getY() * 5, end.getX() * 5, 5, 5);
            showPath(g, end.getParent());
        }
    }
    
    private void showPath(Graphics g, Vertex v){
        g.setColor(Color.GREEN);
        g.fillRect(v.getY() * 5, v.getX() * 5, 5, 5);
        g.setColor(Color.BLACK);
        g.drawRect(v.getY() * 5, v.getX() * 5, 5, 5);
        if(v.hasParent()){
            showPath(g, v.getParent());
        }
        else{
            g.setColor(Color.BLUE);
            g.fillRect(v.getY() * 5, v.getX() * 5, 5, 5);
            g.setColor(Color.BLACK);
            g.drawRect(v.getY() * 5, v.getX() * 5, 5, 5);
        }
    }
    
    public static void main(String[] args) {
        
        BufferedReader reader;    
        String mapType;
        int mapHeight = 0;
        int mapWidth = 0;
        Vertex[][] map;
        
        //read map info
        try
        {   
            //initialize file reader
            reader = new BufferedReader(new FileReader("random512-20-0.map"));
            
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
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    View view = new View(map);

                    JScrollPane pane = new JScrollPane(view);
                    pane.createHorizontalScrollBar();
                    pane.createVerticalScrollBar();
                    JFrame frame = new JFrame();
                    frame.add(pane);
                    frame.setTitle("Shortest path");
                    frame.setSize(900, 700); 
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    frame.setVisible(true);
                }
            });
            //Vertex start = map[0][19];
            //Vertex end = map[1][15];
            //Dijkstra dijkstra = new Dijkstra();
            //long startTime = System.currentTimeMillis();
            //dijkstra.doDijkstra(map, start, end);
            //long endTime = System.currentTimeMillis();
            //System.out.println("Total distance is: " + dijkstra.distance);
            //System.out.println("Total time to get shortest path: " + (endTime - startTime) + " ms");

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
