package fr.lille.br.sort.visualizer.vue;

import fr.lille.br.sort.visualizer.sort.SortEnded;
import fr.lille.br.sort.visualizer.sort.Sorter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Chart {

    private final int startLeft;
    private final int startBottom;
    private final int height;
    private final int width;
    private final int[] values;
    private final Canvas cv;
    private final Sorter sorter;

    private final List<Rectangle> rectangles = new ArrayList<>();

    public Chart(int startLeft, int startBottom, int height, int width, int[] values, Canvas gc, Sorter sorter) {
        this.startLeft = startLeft;
        this.startBottom = startBottom;
        this.height = height;
        this.width = width;
        this.values = Arrays.copyOf(values, values.length);
        this.cv = gc;
        this.sorter = sorter;
    }

    public void draw()  {
        new Rectangle(startLeft-10, startBottom, height+10, width+25, cv).draw(Color.BLACK);
        new Rectangle(startLeft-5, startBottom-5, height, width+15, cv).draw(Color.WHITE);
        int rectWidth = width / values.length;
        int rectHeight = height / values.length;
        for (int i = 0; i < values.length; i++) {
            Rectangle r = new Rectangle(startLeft + i * rectWidth, startBottom, values[i] * rectHeight, rectWidth, cv, this);
            r.draw();
            rectangles.add(i, r);
        }

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

    void solve() {
        int rectWidth = width / values.length;
        int rectHeight = height / values.length;
        AnimationTimer timer = new AnimationTimer() {
            int i = 0;
            @Override
            public void handle(long now) {
                pause(16);
                try {
                    Pair<Integer, Integer> iteration = sorter.iteration();
                    if (iteration == null) {
                        return;
                    }
                    // switch the two rectangles
                    Rectangle r1 = rectangles.get(iteration.getKey());
                    Rectangle r2 = rectangles.get(iteration.getValue());
                    r2.draw(Color.RED);
                    r1.draw(Color.RED);
                    swap(r1, r2);
                    swap(iteration.getKey(), iteration.getValue());



                } catch (SortEnded sortEnded) {
                    stop();
                }
            }
        };
        AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //pause(16);
                try {
                    int[] iteration = sorter.nextArrayState();
                    //System.out.println(Arrays.toString(iteration));
                    for (int i = 0; i < values.length; i++) {
                        rectangles.get(i).delete();
                        rectangles.get(i).height = iteration[i] * rectHeight;
                        rectangles.get(i).draw();
                    }
                } catch (SortEnded sortEnded) {
                    stop();
                }
            }
        };
        if (sorter.entireArray()) {
            timer2.start();
        }else {
            timer.start();
        }

    }

    private static void pause(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void swap(int i, int minIndex) {
        int temp = values[minIndex];
        values[minIndex] = values[i];
        values[i] = temp;
    }

    public void delete() {
        new Rectangle(startLeft-10, startBottom, height+10, width+25, cv).delete();
    }

    private void swap(Rectangle r1, Rectangle r2) {
        pause(16);
        r1.delete();
        r2.delete();
        int temp = r1.height;
        r1.height = r2.height;
        r2.height = temp;

        r1.draw();
        r2.draw();
    }



}
