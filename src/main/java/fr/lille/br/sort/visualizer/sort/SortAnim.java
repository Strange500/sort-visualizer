package fr.lille.br.sort.visualizer.sort;

import javafx.animation.AnimationTimer;
import javafx.scene.chart.XYChart;

public class SortAnim extends AnimationTimer {
    private Sorter sorter;

    public SortAnim(Sorter sorter) {
        this.sorter = sorter;
    }
    @Override
    public void handle(long now) {
        try {
            //sorter.updateChart();
            // add random to bc
            sorter.bc.getData().get(0).getData().add(new XYChart.Data<>(String.valueOf(sorter.bc.getData().get(0).getData().size()), (int) (Math.random() * 100)));
        } catch (SortEnded sortEnded) {
            stop();
        }
    }
}
