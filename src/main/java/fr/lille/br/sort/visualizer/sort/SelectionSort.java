package fr.lille.br.sort.visualizer.sort;

import fr.lille.br.sort.visualizer.vue.Chart;
import javafx.util.Pair;

import java.util.Arrays;

public class SelectionSort implements Sorter {

    private final int[] array;

    public int cpt = 0;

    public SelectionSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }




    public Pair<Integer, Integer> iteration() throws SortEnded {
        if (cpt >= array.length) {
            throw new SortEnded("Sort ended");
        }
        int minIndex = cpt;
        for (int i = cpt + 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        swap(cpt, minIndex);
        System.out.println("Algo : " + Arrays.toString(array));
        return new Pair<>(cpt++, minIndex);
    }

    private void swap(int i, int minIndex) {
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
    }


}
