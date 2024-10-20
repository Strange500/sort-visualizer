package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

import java.util.*;


public class EnumerateSort extends Sorter {

    private final List<Integer> list;



    public EnumerateSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
        list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }

    }

    public void sort() throws SortEnded {
        if (isSorted()) {
            throw new SortEnded("Sort ended");
        }
        Collections.shuffle(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

    }

    private boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
