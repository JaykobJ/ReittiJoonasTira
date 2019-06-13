/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *This class is used to generate visual representation of the search 
 * algorithm route as GridPane.
 * 
 * @author Jaykob
 */
public class View extends GridPane
{
    
    /**
     * Class constructor. Starts the search algorithm of the Dijkstra class
     * object and generates GridPane from the updated graph.
     * 
     * @param graph 2D Vertex array representation of the graph
     * @param start Start Vertex
     * @param end Destination Vertex
     * @param dij Dijkstra class object
     */
    public View(Vertex[][] graph, Vertex start, Vertex end, Dijkstra dij)
    {
        
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setHgap(1);
        setVgap(1);
        
        dij.doDijkstra(graph, start, end);
        paintGraph(graph, start, end);
    }
    
    /**
     * Class constructor. Starts the search algorithm of the Astar class
     * object and generates GridPane from the updated graph. 
     * 
     * @param graph 2D Vertex array representation of the graph
     * @param start Start Vertex
     * @param end Destination Vertex
     * @param astar Astar class object
     */
    public View(Vertex[][] graph, Vertex start, Vertex end, Astar astar)
    {
        
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setHgap(1);
        setVgap(1);
        
        astar.doAStar(graph, start, end);
        paintGraph(graph, start, end);
    }
    
    /**
     * Make and paint nodes inside the GridPane to make visual representation
     * of the graph parameter.
     * 
     * @param graph 2D Vertex array. Updated by search algorithm class 
     * @param start Start Vertex
     * @param end Destination Vertex
     */
    private void paintGraph(Vertex[][] graph, Vertex start, Vertex end){
        long startTime = System.currentTimeMillis();
        for(int x = 0; x < graph.length; x++)
        {
            for(int y = 0; y < graph[x].length; y++)
            {
                Pane canvas = new Pane();
                canvas.setPrefSize(20,20);
                canvas.setMinSize(20,20);
                canvas.setMaxSize(20,20);
                int marker;
                if(graph[x][y] != null)
                {
                    marker = graph[x][y].marker;
                }else
                {
                    marker = 0;
                }
                switch (marker) 
                {
                    case 0 : 
                        canvas.setStyle("-fx-background-color: black;");
                        add(canvas, y, x);
                        break;
                    case 1 : 
                        canvas.setStyle("-fx-background-color: white;");
                        add(canvas, y, x);
                        break;
                    case 2 : 
                        canvas.setStyle("-fx-background-color: blue;");
                        add(canvas, y, x);
                        break;
                    default : 
                        canvas.setStyle("-fx-background-color: red;");
                        add(canvas, y, x);
                        break;
                }
            }
        }
        if(end.hasParent())
        {
            Pane canvas = new Pane();
            canvas.setPrefSize(20,20);
            canvas.setMinSize(20,20);
            canvas.setMaxSize(20,20);
            canvas.setStyle("-fx-background-color: red;");
            add(canvas, end.getY(), end.getX());
            showPath(end.getParent());
        }
        long endTime = System.currentTimeMillis();
        long timer = endTime - startTime;
        System.out.println("------ TIME TO DRAW GRAPH ------ " + timer + "ms");
    }
    
    /**
     * Paints the path nodes from end node to green. When the final node is 
     * reached (start node) paint it pink
     * 
     * @param v shortest path Vertex
     */
    private void showPath(Vertex v)
    {
        Pane canvas = new Pane();
        canvas.setPrefSize(20,20);
        canvas.setMinSize(20,20);
        canvas.setMaxSize(20,20);
        canvas.setStyle("-fx-background-color: green;");
        add(canvas, v.getY(), v.getX());
        if(v.hasParent())
        {
            showPath(v.getParent());
        }
        else
        {
            canvas.setStyle("-fx-background-color: pink;");
        }
    }
    
    /**
     * Make and paint nodes inside the GridPane to make visual representation
     * of the empty(without path) graph.
     * 
     * @param graph 2D Vertex array representation of the graph
     */
    public View (Vertex[][] graph)
    {
        long startTime = System.currentTimeMillis();
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setHgap(1);
        setVgap(1);
        
        for(int x = 0; x < graph.length; x++)
        {
            for(int y = 0; y < graph[x].length; y++)
            {
                Pane canvas = new Pane();
                canvas.setPrefSize(20,20);
                canvas.setMinSize(20,20);
                canvas.setMaxSize(20,20);
                int marker;
                if(graph[x][y] != null)
                {
                    marker = graph[x][y].marker;
                }else
                {
                    marker = 0;
                }
                switch (marker) 
                {
                    case 0 : 
                        canvas.setStyle("-fx-background-color: black;");
                        add(canvas, y, x);
                        break;
                    case 1 : 
                        canvas.setStyle("-fx-background-color: white;");
                        add(canvas, y, x);
                        break;
                    default : 
                        canvas.setStyle("-fx-background-color: red;");
                        add(canvas, y, x);
                        break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long timer = endTime - startTime;
        System.out.println("------ TIME TO DRAW empty GRAPH ------ " + timer + "ms");
    }
}
