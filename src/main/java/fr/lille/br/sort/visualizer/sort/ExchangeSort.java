package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class ExchangeSort extends Sorter {


    public ExchangeSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(i) > array.get(j)) {
                    swap(i, j);
                }
                pause();
            }
        }
        throw new SortEnded("Sort ended");
    }
}
