package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class ChartArray {

    private int[] array;
    private BarChart<String, Number> bc;
    private XYChart.Series<String, Number> series;
    private int access = 0;
    private int modification = 0;


    ChartArray(int[] array, BarChart<String, Number> bc) {
        this.array = array;
        this.bc = bc;
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        bc.getData().add(series);
        this.series = series;
        for (int i = 0; i < array.length; i++) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(String.valueOf(i), array[i]);
            series.getData().add(data);
        }
    }

    public int get(int i) {
        access++;
        return array[i];
    }

    public void set(int i, int value) {
        modification++;
        array[i] = value;
        series.getData().get(i).setYValue(value);
    }

    public int size() {
        return array.length;
    }

    public int getAccess() {
        return access;
    }

    public int getModification() {
        return modification;
    }


}
