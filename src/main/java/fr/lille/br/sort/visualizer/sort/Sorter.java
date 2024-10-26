package fr.lille.br.sort.visualizer.sort;

import fr.lille.br.sort.visualizer.vue.SortController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.Arrays;
import java.util.List;

public abstract class Sorter extends AnimationTimer {
    public static List<Thread> threads = new java.util.ArrayList<>();
    private static int ms = SortController.getMsDelay();
    protected ChartArray array;
    public final BarChart<String, Number> bc;
    long start = 0;
    private XYChart.Series<String, Number> series = new XYChart.Series<>();


    public abstract void sort() throws SortEnded;

    public Sorter(int[] array, BarChart<String, Number> bc)  {
        this.array = new ChartArray(Arrays.copyOf(array, array.length), bc);
        this.bc = bc;
        updateChart(0);
    }

    protected void swap(int i, int minIndex) {
        int temp = array.get(i);
        array.set(i, array.get(minIndex));
        array.set(minIndex, temp);
    }

    static void pause() {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BarChart<String, Number> getBc() {
        return bc;
    }

    public static void setMs(int ms) {
        if (ms < 0) {
            throw new IllegalArgumentException("ms must be positive");
        }
        Sorter.ms = ms;
    }

    public void ended() {
        updateChart(System.nanoTime());
    }
    public void startTimer() {
        start = System.nanoTime();
        // javafx run sort in background
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    start();
                    sort();
                } catch (SortEnded sortEnded) {
                    stop();
                }
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            ended();
        });
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();

    }



    public void handle(long now) {
        updateChart(now);
    }

    private void updateChart(long now) {
        bc.getXAxis().setLabel("Time: " + getTime(now) + " \nemory Access: " + array.getAccess() + "\nMemory Modification: " + array.getModification());
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

