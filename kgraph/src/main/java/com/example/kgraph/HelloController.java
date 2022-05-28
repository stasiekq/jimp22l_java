package com.example.kgraph;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController
{
    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private Label welcomeText1;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void PROBA(MouseEvent mouseEvent)
    {
        welcomeText1.setText("sraken pierdaken");
    }
}