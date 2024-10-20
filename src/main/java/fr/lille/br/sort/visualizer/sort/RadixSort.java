package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class RadixSort extends Sorter {



    public RadixSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
    }


    public void sort() throws SortEnded {

        int max = getMax();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            int[] output = new int[array.size()];
            int[] count = new int[10];
            for (int i = 0; i < array.size(); i++) {
                count[(array.get(i) / exp) % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = array.size() - 1; i >= 0; i--) {
                output[count[(array.get(i) / exp) % 10] - 1] = array.get(i);
                count[(array.get(i) / exp) % 10]--;
                pause();
            }
            for (int i = 0; i < array.size(); i++) {
                array.set(i, output[i]);
                pause();
            }
        }
        throw new SortEnded("Sort ended");


    }

    private int getMax() {
        int max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        return max;
    }


}
