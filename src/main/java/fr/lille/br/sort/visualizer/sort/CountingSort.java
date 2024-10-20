package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class CountingSort extends Sorter{

    private int[] count ;

    private int index = 0;

    private int i = 0;

    private int j = 0;


    public CountingSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
        count = new int[array.length];
        for (int j : array) {
            count[j]++;
        }
    }

    public void sort() throws SortEnded {
        if (i >= count.length || index >= array.length) {
            throw new SortEnded("Sort ended");
        }
        if (j >= count[i]) {
            i++;
            j = 0;
        }

        array[index] = i;
        index++;
        j++;

    }
}
