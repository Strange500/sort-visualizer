package fr.lille.br.sort.visualizer.vue;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;

import static java.lang.System.exit;

public class App extends Application {

    private static final String FXML = "App.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage)  {
        // load fxml App.fxml
        try {
            FXMLLoader loader = loadFXML();
            Parent root = (Parent) loader.load();
            primaryStage.setTitle("Sort Visualizer");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            exit(1);
        } catch (Exception e) {
            System.err.println("Cannot load FXML : " + FXML);
            e.printStackTrace();
            exit(1);
        }

        // show stage
        primaryStage.show();

    }


    public FXMLLoader loadFXML() throws IllegalArgumentException {
        try {
            return new FXMLLoader(App.class.getResource(FXML));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Cannot load FXML : " + FXML, e);
        }
    }
}
