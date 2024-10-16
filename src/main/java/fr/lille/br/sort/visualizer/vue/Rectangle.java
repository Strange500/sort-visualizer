package fr.lille.br.sort.visualizer.vue;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle {

    private int startLeft;
    private int startBottom;
    private int height;
    private int width;
    private Canvas cv;
    private Chart chart;

    public Rectangle(int startLeft, int startBottom, int height, int width, Canvas cv, Chart chart) {
        this.startLeft = startLeft;
        this.startBottom = startBottom;
        this.height = height;
        this.width = width;
        this.cv = cv;
        this.chart = chart;
    }

    public Rectangle(int startLeft, int startBottom, int height, int width, Canvas cv) {
        this.startLeft = startLeft;
        this.startBottom = startBottom;
        this.height = height;
        this.width = width;
        this.cv = cv;
        this.chart = null;
    }



    public void draw() {
        drawRectangle(startLeft, startBottom, height, width);
    }

    public void draw(Color color) {
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(startLeft, startBottom-height, width, height);
    }

    private void drawRectangle(int startLeft, int startBottom, int height, int width) {
        drawRectangle(startLeft, startBottom, height, width, CanvasController.idleRectColor);
    }

    private void drawRectangle(int startLeft, int startBottom, int height, int width, Color color) {
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(startLeft, startBottom-height, width, height);
    }

    public void delete() {
        drawRectangle(startLeft, startBottom, height, width, Color.WHITE);
    }


}
