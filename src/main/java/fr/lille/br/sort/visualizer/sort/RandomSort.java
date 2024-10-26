package fr.lille.br.sort.visualizer.sort;

import javafx.scene.chart.BarChart;

import java.util.*;


public class RandomSort extends Sorter {

    private final List<Integer> list;



    public RandomSort(int[] array, BarChart<String, Number> bc) {
        super(array, bc);
        list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }

    }

    public void sort() throws SortEnded {
        while (!isSorted()) {
            Collections.shuffle(list);
            for (int i = 0; i < list.size(); i++) {
                array.set(i, list.get(i));
                pause();
            }
        }
        throw new SortEnded("Sort ended");

    }

    private boolean isSorted() {
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) > array.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
