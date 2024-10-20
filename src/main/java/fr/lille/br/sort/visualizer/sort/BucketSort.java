package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class BucketSort extends Sorter{
    public BucketSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }

    public void sort() throws SortEnded {
        int n = array.size();
        int[] bucket = new int[n];
        for (int i = 0; i < n; i++) {
            bucket[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            bucket[array.get(i)]++;
        }
        int outPos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                array.set(outPos++, i);
                pause();
            }
        }
        throw new SortEnded("Sort ended");
    }
}
