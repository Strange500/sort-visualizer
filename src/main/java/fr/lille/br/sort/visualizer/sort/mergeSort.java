package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class mergeSort extends Sorter{
    public mergeSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        mergeSort(0, array.size() - 1);
        throw new SortEnded("Sort ended");
    }

    private void mergeSort(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    private void merge(int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = array.get(low + i);
        }
        for (int i = 0; i < n2; i++) {
            R[i] = array.get(mid + 1 + i);
        }
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array.set(k, L[i]);
                i++;
            } else {
                array.set(k, R[j]);
                j++;
            }
            k++;
            pause();
        }
        while (i < n1) {
            array.set(k, L[i]);
            i++;
            k++;
            pause();
        }
        while (j < n2) {
            array.set(k, R[j]);
            j++;
            k++;
            pause();
        }
    }
}
