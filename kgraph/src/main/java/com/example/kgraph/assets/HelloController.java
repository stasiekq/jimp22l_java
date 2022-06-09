package com.example.kgraph.assets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;


public class HelloController {


    @FXML
    public TextField txt_field6;

    @FXML
    public TextField txt_field7;


    @FXML
    public TextField txt_field8;
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
    double srednica;
    double coordinateX;
    double coordinateY;
    GraphicsContext canvas;

    Grid g = new Grid();

    double[] kordyX;

    double[] kordyY;
    int clickedPoints = 0;
    int pierwszy = 0;
    int ostatni = 0;

    @FXML
    protected void chooseCoherent() {
        niespojny.setSelected(false);
        spojny.setSelected(true);
    }


    public void wyswietlGraf(Grid g) {
        int licznik = 0;
        kordyX = new double[g.columns * g.rows];
        kordyY = new double[g.columns * g.rows];
        canvas = can.getGraphicsContext2D();
        clearCanvas(canvas, can);
        srednica = 70;
        canvas.setStroke(Color.LIGHTBLUE);
        while (srednica * g.columns * 2 >= max || srednica * g.rows * 2 >= max) {
            srednica /= 1.5;
        }
        coordinateY = 0 + srednica / 2;
        for (int i = 0; i < g.rows; i++) {
            coordinateY += srednica * 2;
            coordinateX = 0 + srednica / 2;

            for (int j = 0; j < g.columns; j++) {
                canvas.setFill(Color.LIGHTBLUE);
                canvas.fillOval(coordinateX, coordinateY, srednica, srednica);
                kordyY[licznik] = coordinateY;
                kordyX[licznik] = coordinateX;
                coordinateX += srednica * 2;
                licznik++;
            }
        }
        for (int i = 0; i < g.columns * g.rows; i++) {
            if ((i + 1) % g.columns != 0) {
                canvas.strokeLine(kordyX[i] + srednica, kordyY[i] + srednica/2, kordyX[i] + srednica*2,kordyY[i] + srednica/2);
            }
            if (i % g.columns != 0) {
                canvas.strokeLine(kordyX[i], kordyY[i] + srednica/2, kordyX[i] - srednica, kordyY[i] + srednica/2);
            }
            if (i >= g.columns) {
                canvas.strokeLine(kordyX[i] + srednica/2, kordyY[i], kordyX[i] + srednica/2, kordyY[i] - srednica);
            }
            if (i < (g.rows - 1) * g.columns) {
                canvas.strokeLine(kordyX[i] + srednica/2, kordyY[i] + srednica, kordyX[i] + srednica/2, kordyY[i] + srednica*2);
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

    public void dijkBut(ActionEvent actionEvent) {

        if (g != null) {


            int skad = 0;
            int dokad = 8;


            if (txt_field7.getText() == null || txt_field7.getText() == "") {
                skad = 0;
            } else {

                skad = Integer.parseInt(txt_field7.getText());
            }
            if (txt_field8.getText() == null || txt_field8.getText() == "") {
                dokad = 0;
            } else {
                dokad = Integer.parseInt(txt_field8.getText());
            }
            try {
                txt_field7.setText(String.valueOf(skad));
                txt_field8.setText(String.valueOf(dokad));
                wyswietlGraf(g);
                for (int i = 0; i < g.rows * g.columns; i++)
                {
                    pierwszy = Integer.parseInt(txt_field7.getText());
                    canvas.setStroke(Color.BLACK);
                    canvas.strokeOval(kordyX[pierwszy], kordyY[pierwszy], srednica, srednica);
                    ostatni = Integer.parseInt(txt_field8.getText());
                    canvas.setStroke(Color.BLACK);
                    canvas.strokeOval(kordyX[ostatni], kordyY[ostatni], srednica, srednica);
                    uruchomDijsktra(pierwszy, ostatni);
                }
            } catch (Exception e) {
                ErrorsMgmt.awaria(0);
            }
        }
    }

    @FXML
    protected void generateGraph() {
        boolean spojnosc;
        int wiersze;
        int kolumny;
        double dolna_granica;
        double gorna_granica;
        String nazwa_pliku;

        if (spojny.isSelected()) {
            spojnosc = true;
        } else spojnosc = false;
        if (txt_field1.getText() == null || txt_field1.getText() == "") {
            wiersze = 5;
        } else wiersze = Integer.parseInt(txt_field1.getText());
        if (txt_field2.getText() == null || txt_field2.getText() == "") {
            kolumny = 5;
        } else kolumny = Integer.parseInt(txt_field2.getText());
        if (txt_field3.getText() == null || txt_field3.getText() == "") {
            dolna_granica = 1.0;
        } else dolna_granica = Double.parseDouble(txt_field3.getText());
        if (txt_field4.getText() == null || txt_field4.getText() == "") {
            gorna_granica = dolna_granica + 1;
        } else gorna_granica = Double.parseDouble(txt_field4.getText());
        if (txt_field5.getText() == null || txt_field5.getText() == "") {
            nazwa_pliku = "nowy_graf";
        } else nazwa_pliku = txt_field5.getText();
        Generator G1 = new Generator();
        G1.generateGraph(wiersze, kolumny, dolna_granica, gorna_granica, spojnosc, nazwa_pliku);
    }

    @FXML
    public void openFile() {
        boolean czySpojny = true;
        g = ReadFile.czytajPlik();

        if (g != null) {
            czySpojny = Bfs.Algorytm(AdjacMat.Macierz(g));
            czySpojny = Dfs.Algorytm(0, AdjacMat.Macierz(g));
        } else return;
        if (czySpojny == true) {
            wyswietlGraf(g);
        } else return;
        /*
        int skad = 0;
        int dokad = 8;
        Sciezka doWykresu = Dijkstra.Algorytm(AdjacMat.Macierz(g), skad, dokad); // do uzupełnienia węzły początkowe i końcowe

        //System.out.println("//////////////"); // tutaj widzisz że działa
        if (doWykresu != null) {

            System.out.println(doWykresu.waga);
            System.out.println(doWykresu.vertices);
            txt_field6.setText("Dlugosc trasy: " + String.format("%.4f", doWykresu.waga));
            txt_field7.setText(String.valueOf(skad));
            txt_field8.setText(String.valueOf(dokad));
        } else System.out.println("Program: Nie udalo sie stworzyc sciezki.");
        */
    }



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
    public void uruchomDijsktra(int pierwszy, int ostatni) {
        Sciezka doWykresu = Dijkstra.Algorytm(AdjacMat.Macierz(g), pierwszy, ostatni);
        canvas.setStroke(Color.INDIANRED);
        canvas.setFill(Color.INDIANRED);
        for (int i = 0; i < doWykresu.vertices.size(); i++) {
            canvas.fillOval(kordyX[doWykresu.vertices.get(i)], kordyY[doWykresu.vertices.get(i)], srednica, srednica);
        }
        for (int i = 0; i < doWykresu.vertices.size(); i++) {
            canvas.fillOval(kordyX[doWykresu.vertices.get(i)], kordyY[doWykresu.vertices.get(i)], srednica, srednica);
        }
        for (int i = 0; i < doWykresu.vertices.size() - 1; i++) {
            if (doWykresu.vertices.get(i + 1) == doWykresu.vertices.get(i) - 1)
            {
                canvas.strokeLine(kordyX[doWykresu.vertices.get(i)], kordyY[doWykresu.vertices.get(i)] + srednica/2, kordyX[doWykresu.vertices.get(i)] - srednica, kordyY[doWykresu.vertices.get(i)] + srednica/2);
            }
            if(doWykresu.vertices.get(i + 1) == doWykresu.vertices.get(i) + 1)
            {
                canvas.strokeLine(kordyX[doWykresu.vertices.get(i)] + srednica, kordyY[doWykresu.vertices.get(i)] + srednica/2, kordyX[doWykresu.vertices.get(i)] + srednica*2,kordyY[doWykresu.vertices.get(i)] + srednica/2);
            }
            if(doWykresu.vertices.get(i + 1) == doWykresu.vertices.get(i) - g.columns)
            {
                canvas.strokeLine(kordyX[doWykresu.vertices.get(i)] + srednica/2, kordyY[doWykresu.vertices.get(i)], kordyX[doWykresu.vertices.get(i)] + srednica/2, kordyY[doWykresu.vertices.get(i)] - srednica);
            }
            if(doWykresu.vertices.get(i + 1) == doWykresu.vertices.get(i) + g.columns)
            {
                canvas.strokeLine(kordyX[doWykresu.vertices.get(i)] + srednica/2, kordyY[doWykresu.vertices.get(i)] + srednica, kordyX[doWykresu.vertices.get(i)] + srednica/2, kordyY[doWykresu.vertices.get(i)] + srednica*2);
            }
        }
        txt_field6.setText("Dlugosc trasy: " + String.format("%.4f", doWykresu.waga));
        txt_field7.setText(String.valueOf(pierwszy));
        txt_field8.setText(String.valueOf(ostatni));

        /*
        int licznik = 0;
        int licznikSciezka = 0;
        for (int j = 0; j < g.rows; j++) {
            for (int i = 0; i < g.columns; i++) {
                if (licznik == doWykresu.vertices.get(licznikSciezka) && licznikSciezka + 1 < doWykresu.vertices.size()) {
                    //System.out.println(licznik + " ," + licznikSciezka);
                    //System.out.println("Linia X: " + kordyX[doWykresu.vertices.get(licznikSciezka)]);
                    //System.out.println("Linia Y: " + kordyY[doWykresu.vertices.get(licznikSciezka)]);
                    //System.out.println(srednica);
                    if (doWykresu.vertices.get(licznikSciezka) == doWykresu.vertices.get(licznikSciezka + 1) + 1) {
                        canvas.strokeLine(kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica, kordyY[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica * 2, kordyY[doWykresu.vertices.get(licznikSciezka)] + srednica / 2);
                    } else if (doWykresu.vertices.get(licznikSciezka) == doWykresu.vertices.get(licznikSciezka + 1) - 1) {
                        canvas.strokeLine(kordyX[doWykresu.vertices.get(licznikSciezka)], kordyY[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyX[doWykresu.vertices.get(licznikSciezka)] - srednica, kordyY[doWykresu.vertices.get(licznikSciezka)] + srednica / 2);
                    } else if (doWykresu.vertices.get(licznikSciezka) == doWykresu.vertices.get(licznikSciezka + 1) - g.columns) {
                        canvas.strokeLine(kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyY[doWykresu.vertices.get(licznikSciezka)], kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyY[doWykresu.vertices.get(licznikSciezka)] + srednica);
                    } else if (doWykresu.vertices.get(licznikSciezka) == doWykresu.vertices.get(licznikSciezka + 1) + g.columns) {
                        canvas.strokeLine(kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyY[doWykresu.vertices.get(licznikSciezka)] - srednica, kordyX[doWykresu.vertices.get(licznikSciezka)] + srednica / 2, kordyY[doWykresu.vertices.get(licznikSciezka)] - srednica * 2);
                    }
                    licznikSciezka++;
                }
                licznik++;
            }
        }
        */
    }

    public void rozpoczecieRys(MouseEvent mouseEvent) throws IOException {
        //savedPathClicked = false;
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        clickedPoints++;

        coordinateY = 0 + srednica / 2;

        for (int i = 0; i < g.rows * g.columns; i++){
            if ((mouseX >= kordyX[i] && mouseX <= kordyX[i] + srednica) && (mouseY >= kordyY[i] && mouseY <= kordyY[i] + srednica * 2))
            {
                if (clickedPoints % 2 == 1) {
                    wyswietlGraf(g);
                    pierwszy = i;
                    canvas.setStroke(Color.BLACK);
                    canvas.strokeOval(kordyX[i], kordyY[i], srednica, srednica);
                } else if (clickedPoints % 2 == 0) {
                    ostatni = i;
                    canvas.setStroke(Color.BLACK);
                    canvas.strokeOval(kordyX[i], kordyY[i], srednica, srednica);
                    uruchomDijsktra(pierwszy, ostatni);
                }
            }
        }
        /*
        for (int i = 0; i < g.rows; i++) {
            coordinateY += srednica * 2;
            coordinateX = 0 + srednica / 2;
            for (int j = 0; j < g.columns; j++) {
                if ((mouseX >= kordyX[i * g.rows + j] && mouseX <= kordyX[i * g.rows + j] + srednica) && (mouseY >= kordyY[i * g.rows + j] && mouseY <= kordyY[i * g.rows + j] + srednica * 2)) {
                    if (clickedPoints % 2 == 1) {
                        pierwszy = licznik;
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(kordyX[i * g.rows + j], kordyY[i * g.rows + j] + srednica / (2), srednica, srednica);
                    } else if (clickedPoints % 2 == 0) {
                        ostatni = licznik;
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(kordyX[i * g.rows + j], kordyY[i * g.rows + j] + srednica / (2), srednica, srednica);
                        uruchomDijsktra(pierwszy, ostatni);
                    }
                }
            }
                if ((mouseX >= coordinateX && mouseX <= coordinateX + srednica) && (mouseY >= coordinateY && mouseY <= coordinateY + srednica * 2)) {
                    if (clickedPoints % 2 == 1) {
                        //numPath++;
                        //clearPath(); // TRZEBA DODAĆ USUWANIE ŚCIEŻEK
                        pierwszy = licznik;
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(kordyX[i*g.rows + j], kordyY[i*g.rows + j] + srednica / (2), srednica, srednica);
                    } else if (clickedPoints % 2 == 0) {
                        ostatni = licznik;
                        canvas.setStroke(Color.BLACK);
                        canvas.strokeOval(kordyX[i*g.rows + j], kordyY[i*g.rows + j] + srednica / (2), srednica, srednica);
                        uruchomDijsktra(pierwszy, ostatni);
                    }
                }
                coordinateX += srednica * 2;
                licznik++;

            }

         */
        }
    }