package com.example.kgraph.assets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
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
    public TextField txt_field1;
    @FXML
    public TextField txt_field2;
    @FXML
    public TextField txt_field3;
    @FXML
    public TextField txt_field4;
    @FXML
    protected void generateGraph()
    {
        final Stage primaryStage = new Stage();
        PrintWriter writer;
        int wiersze = 2;
        int kolumny = 2;
        wiersze = Integer.parseInt(txt_field1.getText());
        kolumny = Integer.parseInt(txt_field2.getText());
        final String napis_wiersze = Integer.toString(wiersze);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        try
        {
            writer = new PrintWriter(file);
            writer.println(napis_wiersze);
            writer.close();
        }
        catch (IOException ex)
        {
            System.out.println("Coś się nie powiodło");
        }
    }
}