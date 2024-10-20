package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class SelectionSort extends Sorter {

    int i = 0;
    public SelectionSort(int[] array, BarChart<String, Number> bc) {

        super(array, bc);
    }



    public void sort() throws SortEnded {
        if (i >= array.length) {
            throw new SortEnded("Sort ended");
        }
        int min = i;
        for (int j = i + 1; j < array.length; j++) {
            if (array[j] < array[min]) {
                min = j;
            }
        }
        swap(i, min);
        i++;



    }



}
