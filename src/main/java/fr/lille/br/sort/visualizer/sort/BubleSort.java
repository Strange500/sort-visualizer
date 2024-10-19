package fr.lille.br.sort.visualizer.sort;

import fr.lille.br.sort.visualizer.vue.CanvasController;
import javafx.util.Pair;

import java.util.Arrays;

public class BubleSort implements Sorter {

    private final int[] array;

    public int i = 0;
    public int j = 0;
    public BubleSort(int[] array) {
        this.array = array;
    }



    private void swap(int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }


     public Pair<Integer, Integer> iteration() {
         if (j>= array.length - i - 1) {
             j = 0;
             i++;
         }
        if (i >= array.length) {
            throw new SortEnded("Sort ended");
        }
        boolean swapped = false;
        if (array[j] > array[j + 1]) {
            swap(j, j + 1);
            swapped = true;
        }
         j++;
        return swapped ? new Pair<>(j - 1, j) : null;
    }

    public int[] nextArrayState() {
        iteration();
        return Arrays.copyOf(array, array.length);
    }

    public boolean entireArray() {
        return false;
    }

}
