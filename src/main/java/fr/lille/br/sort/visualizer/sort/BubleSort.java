package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class BubleSort extends Sorter {
    int i = 0;
    int j = 0;

    public BubleSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }


     public void sort() throws SortEnded {
            if (i >= array.length) {
                throw new SortEnded("Sort ended");
            }
            if (j >= array.length - i - 1) {
                i++;
                j = 0;
            }
             if (array[j] > array[j + 1]) {
                 swap(j, j + 1);
             }
                j++;



     }

}
