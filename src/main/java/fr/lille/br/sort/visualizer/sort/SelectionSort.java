package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class SelectionSort extends Sorter {

    public SelectionSort(int[] array, BarChart<String, Number> bc) {

        super(array, bc);
    }



    public void sort() throws SortEnded {

        for (int i = 0; i < array.size(); i++) {
            int min = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j) < array.get(min)) {
                    min = j;
                }
                pause();
            }
            swap(i, min);
        }
        throw new SortEnded("Sort ended");



    }



}
