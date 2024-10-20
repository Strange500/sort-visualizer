package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class QuickSort extends Sorter {

    public QuickSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        quickSort(0, array.size() - 1);
        throw new SortEnded("Sort ended");
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array.get(j) < pivot) {
                i++;
                swap(i, j);
            }
            pause();
        }
        swap(i + 1, high);
        return i + 1;
    }
}
