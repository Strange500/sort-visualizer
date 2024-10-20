package fr.lille.br.sort.visualizer.vue;

import fr.lille.br.sort.visualizer.sort.Sorter;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class SortController {

    public static final Map<String, Class<? extends Sorter>> AlgoMap = new HashMap<>();

    private int column = 0;
    private int row = 0;

    private int maxPerRow = 2;

    @FXML
    Button btn;

    @FXML
    ListView<String> algoList;

    @FXML
    GridPane grid;

    public static SortController instance;

    private List<Sorter> charts = new ArrayList<>();

    // gen a list of 100 elements


    private int[] tab = genRandomArray(1000);

    public static final Color idleRectColor = Color.BLACK;
    @FXML
    private void initialize()  {
        instance = this;
        Sorter.setMs(0);

        loadClasses();
        // style grid
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #f0f0f0;");

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

            for (String s : c.getList()) {
                addChart(s, tab);

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

    private void loadClasses() {
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
    }

    private void addChart(String algoName, int[] tab) {
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        for (int i = 0; i < tab.length; i++) {
            xAxis.getCategories().add(String.valueOf(i));
        }
        yAxis.setLabel("Value");
        xAxis.setLabel("Index");
        // add values
        // fill each row
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle(algoName);
        bc.setLegendVisible(false);
        bc.setBarGap(0);
        bc.setCategoryGap(0);
        bc.setAnimated(false);
        bc.autosize();
        // add values
        for (int i = 0; i < tab.length; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>(String.valueOf(i), tab[i]));
            bc.getData().add(series);
        }
        if (column >= maxPerRow) {
            column = 0;
            row++;
        }
        System.out.println("row = " + row + " column = " + column);
        grid.add(bc, column, row);
        column++;
        try {
            charts.add(instantiateSorter(AlgoMap.get(algoName), tab, bc));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

    private static Sorter instantiateSorter(Class<? extends Sorter> clazz, int[] tab, BarChart<String, Number> bc) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Sorter) clazz.getConstructor(int[].class, BarChart.class).newInstance(tab, bc);
    }

    public void draw() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {


        btn.setOnAction(e -> {
            for (Sorter chart : charts) {
                // launch in a new thread
                chart.startTimer();
            }
        });


    }

    public void reset() {
        for (Sorter chart : charts) {
            chart.stop();
        }
        charts.clear();
        grid.getChildren().clear();
        column = 0;
        row = 0;

    }

    private static int[] genRandomArray(int size) {
        int[] tab = new int[size];
        for (int i = 0; i < size; i++) {
            tab[i] = (int) (Math.random() * 100);
        }
        return tab;
    }






}
