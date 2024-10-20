package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

public class RadixSort extends Sorter {

    private int max;
    private int cpt = 1;

    private int counter = array.length;

    private int[] output = new int[array.length];


    public RadixSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
        this.max = getMax();
    }

    void countSort(int exp) {

        int n = array.length;
        output = new int[n]; // output array
        int i;
        int count[] = new int[10];
        for (i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }



        // Copy the output array to arr[],
        // so that arr[] now contains sorted
        // numbers according to current digit

    }

    public void sort() throws SortEnded {

        if (cpt!=1 && !(max / (cpt/10) > 0)) {
            throw new SortEnded("Sort ended");
        }


        if (counter >= array.length) {
            counter = 0;
            countSort(cpt);
            cpt *= 10;
        }
        else {
            array[counter] = output[counter];
            counter++;
        }

    }

    private int getMax() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }


}
