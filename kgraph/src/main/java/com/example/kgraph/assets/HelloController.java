package com.example.kgraph.assets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;


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
    public Canvas can;

    final int max = 400;
    int srednica;
    double coordinateX;
    double coordinateY;
    GraphicsContext canvas;

    Grid g = new Grid();

    @FXML
    protected void chooseCoherent() {
        niespojny.setSelected(false);
        spojny.setSelected(true);
    }
    public void wyswietlGraf(Grid g)
    {
        canvas = can.getGraphicsContext2D();
        clearCanvas(canvas, can);
        srednica = 60;
        coordinateY = 0 + srednica / 2;

        while (srednica * g.columns * 2 >= max || srednica * g.rows * 2 >= max) {
            srednica /= 1.5;
        }

        for (int i = 0; i < g.rows; i++) {
            coordinateY += srednica * 2;
            coordinateX = 0 + srednica / 2;
            for (int j = 0; j < g.columns; j++) {
                canvas.setFill(Color.BLUE);
                canvas.fillOval(coordinateX, coordinateY, srednica, srednica);

                coordinateX += srednica * 2;
            }
        }
    }

    public void clearCanvas(GraphicsContext gc, Canvas cv) {
        gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
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
        boolean czySpojny = true;
        g = ReadFile.czytajPlik();

        if(g != null) {
            czySpojny = Bfs.Algorytm(AdjacMat.Macierz(g));
        }
        else return;
        if (czySpojny == true)
        {
            wyswietlGraf(g);
        }
        else return;
        /*
        Sciezka doWykresu = Dijkstra.Algorytm(AdjacMat.Macierz(f1), 0, 8); // do uzupełnienia węzły początkowe i końcowe

        System.out.println("//////////////"); // tutaj widzisz że działa
        if (doWykresu != null) {
            System.out.println(doWykresu.waga);
            System.out.println(doWykresu.vertices);
        }
        else System.out.println("Program: Nie udalo sie stworzyc sciezki.");
         */
    }

    int clickedPoints = 0;
    /*
    @FXML
    public void rozpoczecieRys(MouseEvent mouseEvent, Grid g) throws IOException {
        //savedPathClicked = false;
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        clickedPoints++;

        int point = 0;
        coordinateY = 0 + srednica / 2;

        for (int i = 0; i < g.columns * g.rows; i++) {
            coordinateY += srednica * 2;
            coordinateX = 0 + srednica / 2;
            if ((mouseX >= coordinateX && mouseX <= coordinateX + srednica) && (mouseY >= coordinateY && mouseY <= coordinateY + srednica)) {
                point = i;
                if (clickedPoints % 2 == 1) {
                    //numPath++;
                    //clearPath();
                    canvas.setStroke(Color.RED);
                    canvas.strokeOval(coordinateX, coordinateY, srednica, srednica);
                } else if (clickedPoints % 2 == 0) {
                    canvas.setStroke(Color.RED);
                    canvas.strokeOval(coordinateX, coordinateY, srednica, srednica);
                    //chooseModeAnalyzer();
                }
            }
        }

    }

     */

    public void rozpoczecieRys(MouseEvent mouseEvent) throws IOException {
        //savedPathClicked = false;
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        clickedPoints++;

        coordinateY = 0 + srednica / 2;

        for (int i = 0; i < g.rows; i++) {
            coordinateY += srednica * 2;
            coordinateX = 0 + srednica / 2;
            for (int j = 0; j < g.columns; j++) {
                if ((mouseX >= coordinateX && mouseX <= coordinateX + srednica) && (mouseY >= coordinateY && mouseY <= coordinateY + srednica)) {
                    if (clickedPoints % 2 == 1) {
                        //numPath++;
                        //clearPath();
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(coordinateX, coordinateY + srednica/2, srednica, srednica);
                    } else if (clickedPoints % 2 == 0) {
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(coordinateX, coordinateY + srednica/2, srednica, srednica);
                        //chooseModeAnalyzer();
                    }
                }
                coordinateX += srednica * 2;
            }
        }
    }
}