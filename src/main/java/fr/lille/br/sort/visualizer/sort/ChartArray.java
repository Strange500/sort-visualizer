package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.XYChart;

public class ChartArray {

    private int[] array;
    private XYChart.Series<String, Number> series;


    private ChartArray(int[] array) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < array.length; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), array[i]));
        }
        this.array = array;
        this.series = series;
    }

    public int get(int i) {
        return array[i];
    }

    public void set(int i, int value) {
        array[i] = value;
        series.getData().get(i).setYValue(value);
    }
}
