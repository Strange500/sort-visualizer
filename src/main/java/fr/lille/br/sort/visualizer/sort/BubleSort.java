package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class BubleSort extends Sorter {


    public BubleSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }


     public void sort() throws SortEnded {
            for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.size() - 1; j++) {
                    if (array.get(j) > array.get(j + 1)) {
                        swap(j, j + 1);
                    }
                    pause();
                }
            }
            throw new SortEnded("Sort ended");



     }

}
