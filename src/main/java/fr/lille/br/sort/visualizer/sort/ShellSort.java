package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class ShellSort extends Sorter {

    public ShellSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int n = array.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = array.get(i);
                int j;
                for (j = i; j >= gap && array.get(j - gap) > temp; j -= gap) {
                    array.set(j, array.get(j - gap));
                    pause();
                }
                array.set(j, temp);
            }
        }
        throw new SortEnded("Sort ended");
    }
}
