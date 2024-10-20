package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class HeapSort extends Sorter {

    public HeapSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int n = array.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
        throw new SortEnded("Sort ended");
    }

    private void heapify(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && array.get(l) > array.get(largest)) {
            largest = l;
        }
        if (r < n && array.get(r) > array.get(largest)) {
            largest = r;
        }
        if (largest != i) {
            swap(i, largest);
            pause();
            heapify(n, largest);
        }
    }
}
