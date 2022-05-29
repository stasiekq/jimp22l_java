package com.example.kgraph.assets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;

public class HelloController
{
    @FXML
    private Button gen_but;
    @FXML
    protected void generateGraph()
    {
        final Stage primaryStage = new Stage();
        final String sampleText = "Przykladowy tekst";
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
    }
}