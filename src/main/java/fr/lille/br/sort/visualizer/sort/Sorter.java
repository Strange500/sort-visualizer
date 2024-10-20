package fr.lille.br.sort.visualizer.sort;

import javafx.animation.AnimationTimer;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public abstract class Sorter extends AnimationTimer {
    private static int ms = 16;
    protected int[] array;
    public final BarChart<String, Number> bc;
    int nbIteration = 0;
    long start = 0;
    private XYChart.Series<String, Number> series = new XYChart.Series<>();


    public abstract void sort() throws SortEnded;

    public Sorter(int[] array, BarChart<String, Number> bc)  {
        this.array = Arrays.copyOf(array, array.length);
        this.bc = bc;
    }

    protected void updateChart() {
        bc.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < array.length; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), array[i]));
        }
        bc.getData().add(series);


    }

    protected void swap(int i, int minIndex) {
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
    }

    private static void pause() {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setMs(int ms) {
        if (ms < 0) {
            throw new IllegalArgumentException("ms must be positive");
        }
        Sorter.ms = ms;
    }
    public void startTimer() {
        start = System.nanoTime();
        this.start();

    }
    public void handle(long now) {

        try {
            sort();
            updateChart();
            nbIteration++;
            bc.getXAxis().setLabel("Iteration: " + nbIteration + "\nTime: " + getTime(now));
            pause();


        } catch (SortEnded sortEnded) {
            stop();
        }


    }

    private String getTime(long now) {
        long ms = (now - start) / 1_000_000;
        if (ms < 1_000  ) {
            return ms + " ms";
        }
        if (ms < 60_000) {
            return ms / 1_000 + " s";
        }
        return ms / 60_000 + " min";
    }


}
