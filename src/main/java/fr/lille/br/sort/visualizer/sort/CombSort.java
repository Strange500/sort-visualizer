package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class CombSort extends Sorter{
    public CombSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int n = array.size();
        int gap = n;
        boolean swapped = true;
        while (gap != 1 || swapped) {
            gap = getNextGap(gap);
            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                if (array.get(i) > array.get(i + gap)) {
                    swap(i, i + gap);
                    swapped = true;
                    pause();
                }
            }
        }
        throw new SortEnded("Sort ended");
    }

    private int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }
}
