package fr.lille.br.sort.visualizer.vue;

import fr.lille.br.sort.visualizer.sort.BubleSort;
import fr.lille.br.sort.visualizer.sort.EnumerateSort;
import fr.lille.br.sort.visualizer.sort.SelectionSort;
import fr.lille.br.sort.visualizer.sort.Sorter;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;


public class CanvasController {

    public static final Map<String, Class<? extends Sorter>> AlgoMap = new HashMap<>();
    @FXML
    Canvas canvas;

    @FXML
    Button btn;

    @FXML
    ListView<String> algoList;

    public static CanvasController instance;

    private List<Chart> charts = new ArrayList<>();

    private int[] tab = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static final Color idleRectColor = Color.BLACK;
    @FXML
    private void initialize()  {
        instance = this;

        Reflections reflections = new Reflections("fr.lille.br.sort.visualizer.sort");  // replace with the package you want to scan
        Set<Class<? extends Sorter>> allClasses = reflections.getSubTypesOf(Sorter.class);

        for (Class<?> clazz : allClasses) {
            System.out.println("Found class: " + clazz.getName());
            // load class to initialize static block
            try {
                Class.forName(clazz.getName());
                // instantiate class
                algoList.getItems().add(clazz.getSimpleName());
                AlgoMap.put(clazz.getSimpleName(), (Class<? extends Sorter>) clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        // draw logo
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(10, 60, 30, 30);
        gc.setFill(Color.GREEN);
        gc.fillOval(40, 60, 30, 30);
        gc.setFill(Color.BLUE);
        gc.fillOval(70, 60, 30, 30);
        // draw line all around the borders

        try {
            draw();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        algoList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) c -> {
            for (Chart chart : charts) {
                chart.delete();
            }
            charts.clear();
            for (String s : c.getList()) {

                try {
                    charts.add(new Chart(10, 700, 500, 500, tab, canvas, instantiateSorter(AlgoMap.get(s), tab)));
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            try {
                draw();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Sorter instantiateSorter(Class<? extends Sorter> clazz, int[] tab) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Sorter) clazz.getConstructor(int[].class).newInstance(tab);
    }

    public void draw() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int[] tab1 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] tab2 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] tab3 = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        int[] tab4 = new int[]{7, 6, 5, 4, 3, 2, 1};
        int[] tab5 = new int[]{6, 5, 4, 3, 2, 1};
        int[] tab6 = new int[]{5, 4, 3, 2, 1};
        int[] tab7 = new int[]{4, 3, 2, 1};
        int[] tab8 = new int[]{3, 2, 1};
        int[] tab9 = new int[]{2, 1};
        int[] tab10 = new int[]{1};

        int[] tab = tab3;
        // draw rectangles
        //Chart chart = new Chart(10, 700, 500, 500, tab, canvas, instantiateSorter(AlgoMap.get(algoList.getItems().get(0)), tab));
        // another with different values
        //Chart chart2 = new Chart(520, 700, 500, 500, tab, canvas, new BubleSort(tab));
        //chart.draw();
        //chart2.draw();
        btn.setOnAction(e -> {
            for (Chart c : charts) {
                c.solve();
            }
        });

        for (Chart c : charts) {
            c.draw();
        }
    }






}
