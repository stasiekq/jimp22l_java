package com.example.kgraph.assets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;


public class HelloController {
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
    public TextField txt_field5;
    @FXML
    public ToggleButton spojny;
    @FXML
    public ToggleButton niespojny;

    @FXML
    protected void chooseCoherent() {
        niespojny.setSelected(false);
        spojny.setSelected(true);
    }

    @FXML
    protected void chooseIncoherent() {
        niespojny.setSelected(true);
        spojny.setSelected(false);
    }


    @FXML
    protected void generateGraph() {
        boolean spojnosc;
        int wiersze;
        int kolumny;
        double dolna_granica;
        double gorna_granica;
        String nazwa_pliku;
        if (spojny.isSelected())
        {
            spojnosc = true;
        }
        else spojnosc = false;
        if (txt_field1.getText() == null || txt_field1.getText() == "")
        {
            wiersze = 5;
        }
        else wiersze = Integer.parseInt(txt_field1.getText());
        if (txt_field2.getText() == null || txt_field2.getText() == "")
        {
            kolumny = 5;
        }
        else kolumny = Integer.parseInt(txt_field2.getText());
        if (txt_field3.getText() == null || txt_field3.getText() == "")
        {
            dolna_granica = 1.0;
        }
        else dolna_granica = Double.parseDouble(txt_field3.getText());
        if (txt_field4.getText() == null || txt_field4.getText() == "")
        {
            gorna_granica = dolna_granica + 1;
        }
        else gorna_granica = Double.parseDouble(txt_field4.getText());
        if (txt_field5.getText() == null || txt_field5.getText() == "")
        {
            nazwa_pliku = "przykladowy_graf";
        }
        else nazwa_pliku = txt_field5.getText();
        Generator G1 = new Generator();
        G1.generateGraph(wiersze, kolumny, dolna_granica, gorna_granica, spojnosc, nazwa_pliku);
    }

    @FXML
    public void openFile() {
        Grid f1 = ReadFile.czytajPlik();

        if(f1 != null) {
            Bfs.Algorytm(AdjacMat.Macierz(f1));
        }
        Sciezka doWykresu = Dijkstra.Algorytm(AdjacMat.Macierz(f1), 0, 8); // do uzupełnienia węzły początkowe i końcowe

        System.out.println("//////////////"); // tutaj widzisz że działa
        if (doWykresu != null) {
            System.out.println(doWykresu.waga);
            System.out.println(doWykresu.vertices);
        }
        else System.out.println("Program: Nie udalo sie stworzyc sciezki.");
    }
}