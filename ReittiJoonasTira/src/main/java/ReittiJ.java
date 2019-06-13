/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

/**
 * Main class of the project. Creates javafx application
 *
 * @author Jaykob
 */
public class ReittiJ extends Application 
{
    int startX, startY, endX, endY;
    String mapFile;
    
    @Override
    public void start(Stage primaryStage) 
    {  
        this.mapFile = "rand.map"; //random512-20-0
        this.startX = 0;
        this.startY = 0;
        this.endX = 9;
        this.endY = 9;
        
        //information about both algoritm
        DecimalFormat df = new DecimalFormat("#.##");
        
        //buttons to display the visual grid view
        //Astar button
        Button aButton = new Button("astar");
        aButton.setPrefSize(200, 30);
        aButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                //Astar view with scroller
                Vertex[][] aMap = readMap();
                Vertex aStart = aMap[startX][startY];
                Vertex aEnd = aMap[endX][endY];
                Astar astar = new Astar();
                View astarView = new View(aMap, aStart, aEnd, astar);
                ScrollPane scroll1 = new ScrollPane();
                scroll1.setContent(astarView);
                
                BorderPane pane = new BorderPane();
                pane.setCenter(scroll1);
                Label aTime = new Label("Total time consume of algorith 'Astar': " + astar.timer + "ms");
                Label aDistance = new Label("Total distance of path 'Astar': END POINT NOT FOUND");
                if(astar.endFound())
                {
                    aDistance = new Label("Total distance of path 'Astar': " + df.format(astar.getVertex(aEnd.getX(), aEnd.getY()).getDistance()));
                }
                HBox info = new HBox(aTime, aDistance);
                info.setSpacing(40);
                pane.setTop(info);
                Stage stage = new Stage();
                stage.setTitle("astar window");
                stage.setScene(new Scene(pane, 900, 800));
                stage.show();
            }
        });
        //Dijkstra button
        Button dButton = new Button("dijkstra");
        dButton.setPrefSize(200, 30);
        dButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                //Dijkstra view with scroller
                Vertex[][] dMap = readMap();
                Vertex dStart = dMap[startX][startY];
                Vertex dEnd = dMap[endX][endY];
                Dijkstra dijkstra = new Dijkstra();
                View dijkstraView = new View(dMap, dStart, dEnd, dijkstra);
                ScrollPane scroll2 = new ScrollPane();
                scroll2.setContent(dijkstraView);
                
                BorderPane pane = new BorderPane();
                pane.setCenter(scroll2);
                Label dTime = new Label("Total time consume of algorith 'Dijkstra': " + dijkstra.timer + "ms");
                Label dDistance = new Label("Total distance of path 'Dijkstra': END POINT NOT FOUND");
                if(dijkstra.endFound())
                {
                    dDistance = new Label("Total distance of path 'Dijkstra': " + df.format(dijkstra.getVertex(dEnd.getX(), dEnd.getY()).getDistance()));
                }
                HBox info = new HBox(dTime, dDistance);
                info.setSpacing(40);
                pane.setTop(info);
                Stage stage = new Stage();
                stage.setTitle("dijkstra window");
                stage.setScene(new Scene(pane, 900, 800));
                stage.show();
            }
        });
        
        //Choise box for choosing the map
        ChoiceBox<String> mapString = new ChoiceBox();
        mapString.setValue(mapFile);
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) 
        {
          if (listOfFiles[i].isFile()) 
          {
            String mapName = listOfFiles[i].getName();
            if(mapName.contains(".map"))
            {
                mapString.getItems().add(mapName);
            }
          }
        }
        mapString.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                mapFile = mapString.getValue();
            }
        });
        
        //Main window
        ScrollPane scrollMain = new ScrollPane();
        HBox btnBox = new HBox(aButton, dButton);
        btnBox.setSpacing(50);
        VBox mainPane = new VBox(btnBox, mapString);
        mainPane.setMinSize(600, 100);
        mainPane.setSpacing(20);
        scrollMain.setContent(mainPane);
        
        //stage info
        Scene scene = new Scene(scrollMain, 800, 300);
        primaryStage.setTitle("Shortest Path");
        System.out.println("Title set");      
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method of the class.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    /**
     * Reads a .map file that has the text representation of the map.
     * Generates a 2D Vertex array from this file. This 2D array is used by
     * the algorithms
     * 
     * @return Vertex[][]
     */
    private Vertex[][] readMap()
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
        System.out.println("Time to read: " + timer + " ms");
        return map;
    }
    
    /**
     * Method used to read the map size from the .map files first 4 info rows
     * 
     * @param line Text row from the .map file
     * @return Integer
     */
    private int digitFromLine(String line)
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
