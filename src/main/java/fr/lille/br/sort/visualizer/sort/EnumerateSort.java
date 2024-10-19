package fr.lille.br.sort.visualizer.sort;

import fr.lille.br.sort.visualizer.vue.CanvasController;
import javafx.util.Pair;

import java.util.*;


public class EnumerateSort implements Sorter {

    private int[] array;
    private final List<Integer> list;

    public int i = 0;


    public EnumerateSort(int[] array) {
        this.array = array;
        list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
    }

    public Pair<Integer, Integer> iteration() throws SortEnded {
        if (isSorted()) {
            throw new SortEnded("Sort ended");
        }
        Collections.shuffle(list);
        return null;
    }

    public int[] nextArrayState() throws SortEnded {
        iteration();
        int[] b = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        array = b;
        return array;
    }

    private void swap(int i, int minIndex) {
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
    }

    private boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }


    // static sort trying all possible permutations
    public static void sort(int[] array) {
        EnumerateSort sorter = new EnumerateSort(array);
        while (true) {
            try {
                sorter.iteration();
            } catch (SortEnded e) {
                break;
            }
        }
    }

    public boolean entireArray() {
        return true;
    }

}
