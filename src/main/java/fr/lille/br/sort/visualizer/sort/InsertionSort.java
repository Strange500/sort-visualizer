package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class InsertionSort extends Sorter {

        public InsertionSort(int[] array, BarChart<String, Number> bc) {
            super(array, bc);
        }

        public void sort() throws SortEnded {
            for (int i = 1; i < array.size(); i++) {
                int key = array.get(i);
                int j = i - 1;
                while (j >= 0 && array.get(j) > key) {
                    array.set(j + 1, array.get(j));
                    j = j - 1;
                    pause();
                }
                array.set(j + 1, key);
            }
            throw new SortEnded("Sort ended");
        }
}
