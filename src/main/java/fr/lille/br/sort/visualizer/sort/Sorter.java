package fr.lille.br.sort.visualizer.sort;

import javafx.util.Pair;

public interface Sorter {

    public Pair<Integer, Integer> iteration() throws SortEnded;
}
