package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class CountingSort extends Sorter{




    public CountingSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        int[] count = new int[max + 1];
        for (int i = 0; i < array.size(); i++) {
            count[array.get(i)]++;
        }
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                array.set(j, i);
                j++;
                count[i]--;
                pause();
            }
        }
        throw new SortEnded("Sort ended");

    }
}
