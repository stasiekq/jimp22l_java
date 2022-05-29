package com.example.kgraph.assets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;



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
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(sampleText);
            writer.close();
        } catch (IOException ex)
        {
            System.out.println("Coś się nie powiodło");
        }
    }
}