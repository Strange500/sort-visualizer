package fr.lille.br.sort.visualizer.vue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


public class CanvasController {

    @FXML
    Canvas canvas;

    @FXML
    Button btn;

    public static CanvasController instance;

    public static final Color idleRectColor = Color.BLACK;
    @FXML
    private void initialize()  {
        instance = this;
        // draw logo
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(10, 60, 30, 30);
        gc.setFill(Color.GREEN);
        gc.fillOval(40, 60, 30, 30);
        gc.setFill(Color.BLUE);
        gc.fillOval(70, 60, 30, 30);
        // draw line all around the borders

        draw();



    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw rectangles
        Chart chart = new Chart(10, 700, 500, 500, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, canvas);
        // another with different values
        Chart chart2 = new Chart(520, 700, 500, 500, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, canvas);
        chart.draw();
        chart2.draw();
        btn.setOnAction(e -> {
            chart.betterCycle();
            chart2.betterCycle();
        });
    }






}
