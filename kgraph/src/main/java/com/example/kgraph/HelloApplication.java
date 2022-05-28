package com.example.kgraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Gui_wersja1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Zupa Romana!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        System.out.println("DZIALA?");
        launch();
    }
}