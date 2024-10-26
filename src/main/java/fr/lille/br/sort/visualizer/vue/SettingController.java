package fr.lille.br.sort.visualizer.vue;

import fr.lille.br.sort.visualizer.sort.Sorter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingController {

    @FXML
    TextField arraySize;

    @FXML
    TextField msDelay;

    @FXML
    public void initialize() {
        arraySize.setText(String.valueOf(SortController.getArraySize()));
        msDelay.setText(String.valueOf(SortController.getMsDelay()));

        arraySize.setOnAction(event -> {
            try {
                SortController.setArraySize(Integer.parseInt(arraySize.getText()));
                SortController.instance.reloadChart();
            } catch (NumberFormatException e) {
                showErrorMessage("Array size must be an integer");
            }
        });

        msDelay.setOnAction(event -> {
            try {
                Sorter.setMs(Integer.parseInt(msDelay.getText()));
            } catch (NumberFormatException e) {
                showErrorMessage("Delay must be an integer");
            }
        });
    }

    public void showErrorMessage(String message) {
        Popup popup = new Popup();
        popup.getContent().add(new TextField(message));
        popup.show(new Stage());
    }
}
