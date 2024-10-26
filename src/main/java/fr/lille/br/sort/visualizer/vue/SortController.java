package fr.lille.br.sort.visualizer.vue;

import atlantafx.base.theme.Theme;
import fr.lille.br.sort.visualizer.sort.Sorter;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class SortController {

    private static int arraySize = 100;
    private static int msDelay = 10;

    public static final Map<String, Class<? extends Sorter>> AlgoMap = new HashMap<>();

    private int column = 0;
    private int row = 0;
    private int maxPerRow = 2;

    public static void setArraySize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        arraySize = size;
    }

    public static void setMsDelay(int ms) {
        if (ms < 0) {
            throw new IllegalArgumentException("Delay must be positive");
        }
        msDelay = ms;
    }

    public static int getArraySize() {
        return arraySize;
    }

    public static int getMsDelay() {
        return msDelay;
    }

    @FXML
    Button btn;

    @FXML
    ListView<String> algoList;

    @FXML
    GridPane grid;

    public static SortController instance;

    private List<Sorter> charts = new ArrayList<>();

    // gen a list of 100 elements


    private int[] tab = genRandomArray(SortController.arraySize);

    public static final Color idleRectColor = Color.BLACK;

    @FXML
    private void initialize()  {
        instance = this;
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

    public void reloadChart() {
        tab = genRandomArray(SortController.arraySize);
        List<String> selectedItems = new ArrayList<>();
        List<Sorter> tmp = new ArrayList<>(this.charts);
        reset();
        for (Sorter c : tmp) {
            String name = c.getClass().getSimpleName();
            selectedItems.add(name);
            addChart(name, tab);
        }

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
        for (Thread thread : Sorter.threads) {
            thread.interrupt();
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

    public void openSettings() {
        Stage stage = new Stage();
        stage.setTitle("Settings");
        try {
            FXMLLoader loader = new FXMLLoader(SettingController.class.getResource("setting.fxml"));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        stage.show();
    }






}
