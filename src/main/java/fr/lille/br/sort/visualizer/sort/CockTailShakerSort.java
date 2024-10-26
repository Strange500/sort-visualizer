package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class CockTailShakerSort extends Sorter{

    public CockTailShakerSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int n = array.size();
        boolean swapped = true;
        int start = 0;
        int end = n - 1;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    swap(i, i + 1);
                    swapped = true;
                    pause();
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            end--;
            for (int i = end - 1; i >= start; i--) {
                if (array.get(i) > array.get(i + 1)) {
                    swap(i, i + 1);
                    swapped = true;
                    pause();
                }
            }
            start++;
        }
        throw new SortEnded("Sort ended");
    }


}
