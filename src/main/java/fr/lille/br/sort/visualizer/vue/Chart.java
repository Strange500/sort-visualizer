package fr.lille.br.sort.visualizer.vue;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Chart {

    private final int startLeft;
    private final int startBottom;
    private final int height;
    private final int width;
    private final int[] values;
    private final Canvas cv;

    private final List<Rectangle> rectangles = new ArrayList<>();

    public Chart(int startLeft, int startBottom, int height, int width, int[] values, Canvas gc) {
        this.startLeft = startLeft;
        this.startBottom = startBottom;
        this.height = height;
        this.width = width;
        this.values = values;
        this.cv = gc;
    }

    public void draw()  {
        new Rectangle(startLeft, startBottom, height, width, cv).draw(Color.BLACK);
        new Rectangle(startLeft+5, startBottom-5, height-10, width-10, cv).draw(Color.WHITE);
    }

    public void testgc() {
        // draw logo
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(10, 60, 30, 30);
        gc.setFill(Color.GREEN);
        gc.fillOval(40, 60, 30, 30);
        gc.setFill(Color.BLUE);
        gc.fillOval(70, 60, 30, 30);
    }

    void betterCycle() {
        int rectWidth = width / values.length;
        int rectHeight = height / values.length;
        AnimationTimer timer = new AnimationTimer() {
            int i = 0;
            @Override
            public void handle(long now) {
                try {
                    TimeUnit.MILLISECONDS.sleep(16);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i < values.length) {

                    Rectangle rectangle = new Rectangle((startLeft + i * rectWidth) + 10, startBottom-10, values[i] * rectHeight - 20, rectWidth-20, cv, Chart.this);
                    rectangles.add(rectangle);
                    rectangle.draw();
                    i++;
                } else {

                    stop();
                    removeCycle();
                }
            }
        };
        timer.start();

    }

    void removeCycle() {
        int rectWidth = width / values.length;
        int rectHeight = height / values.length;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    TimeUnit.MILLISECONDS.sleep(16);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!rectangles.isEmpty()) {
                    rectangles.remove(0).delete();
                } else {
                    stop();
                    betterCycle();
                }
            }
        };
        timer.start();

    }


}
