package PathFinding;

import OwnObjects.Vertex;
import Algoritm.Dijkstra;
import Algoritm.Astar;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        //Decimal formating to 2 decimal
        DecimalFormat df = new DecimalFormat("#.##");
        
        //Text fields to get the start and end coordinates
        Label infoLabel = new Label("Please write START __X__ and __Y__ coordinates and DESTINATION __X__ and __Y__ coordinates on the text field below");
        TextField startXfield = new TextField();
        startXfield.setPrefWidth(70);
        TextField startYfield = new TextField();
        startYfield.setPrefWidth(70);
        TextField endXfield = new TextField();
        endXfield.setPrefWidth(70);
        TextField endYfield = new TextField();
        endYfield.setPrefWidth(70);
        Label errorLabel = new Label("");
        
        //buttons to display the visual grid view
        //Astar button
        Button aButton = new Button("astar");
        aButton.setPrefSize(200, 30);
        aButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                try
                {
                    errorLabel.setText("");
                    startX = Integer.parseInt(startXfield.getText());
                    startY = Integer.parseInt(startYfield.getText());
                    endX = Integer.parseInt(endXfield.getText());
                    endY = Integer.parseInt(endYfield.getText());
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
                    Label aTime = new Label("Total time consume of algorith 'Astar': " + astar.getTimer() + "ms");
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
                } catch (Exception ex){
                    errorLabel.setText("Start or end point is a wall or out of bounds");
                    errorLabel.setTextFill(Color.RED);
                }
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
                try
                {
                    errorLabel.setText("");
                    startX = Integer.parseInt(startXfield.getText());
                    startY = Integer.parseInt(startYfield.getText());
                    endX = Integer.parseInt(endXfield.getText());
                    endY = Integer.parseInt(endYfield.getText());
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
                    Label dTime = new Label("Total time consume of algorith 'Dijkstra': " + dijkstra.getTimer() + "ms");
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
                } catch (Exception ex){
                    errorLabel.setText("Start or end point is a wall or out of bounds");
                    errorLabel.setTextFill(Color.RED);
                }
            }
        });
        
        //Choise box for choosing the map
        ChoiceBox<String> mapString = new ChoiceBox();
        mapString.setValue(mapFile);
        try
        {
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
                    if(i == listOfFiles.length - 1)
                    {
                        mapFile = mapString.getValue();
                    }
                }
              }
            }
            if(mapString.getValue() == null || mapString.getValue().equals("") || listOfFiles.length < 1)
            {
                errorLabel.setText("Please choose a map");
                errorLabel.setTextFill(Color.RED);
            }
            mapString.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent e) 
                {
                    mapFile = mapString.getValue();
                    errorLabel.setText("");
                }
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
        
        //Main window
        ScrollPane scrollMain = new ScrollPane();
        HBox textBox = new HBox(startXfield, startYfield, endXfield, endYfield);
        textBox.setSpacing(50);
        HBox btnBox = new HBox(mapString, aButton, dButton);
        btnBox.setSpacing(50);
        VBox mainPane = new VBox(infoLabel, errorLabel, textBox, btnBox);
        mainPane.setMinSize(600, 100);
        mainPane.setSpacing(20);
        scrollMain.setContent(mainPane);
        
        //stage info
        Scene scene = new Scene(scrollMain, 800, 300);
        primaryStage.setTitle("Shortest Path");     
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    /**
     * Reads a .map file that has the text representation of the map.
     * Generates a 2D Vertex array from this file. This 2D array is used by
     * the algorithms
     * 
     * @return Vertex[][] Representation of the map in 2d Vertex array
     */
    private Vertex[][] readMap()
    {
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
                        break;
                    case 1:
                        mapHeight = digitFromLine(line);
                        break;
                    case 2:
                        mapWidth = digitFromLine(line);
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
        return map;
    }
    
    /**
     * Method used to read the map size from the .map files first 4 info rows
     * 
     * @param line Text row from the .map file
     * @return Integer value
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
