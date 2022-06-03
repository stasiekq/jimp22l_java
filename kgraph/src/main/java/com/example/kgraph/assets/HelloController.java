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
        final Stage primaryStage = new Stage();
        PrintWriter writer;
        int wiersze;
        int kolumny;
        double dolna_granica;
        double gorna_granica;
        double losowa;
        String losowa2;
        wiersze = Integer.parseInt(txt_field1.getText());
        kolumny = Integer.parseInt(txt_field2.getText());
        dolna_granica = Double.parseDouble(txt_field3.getText());
        gorna_granica = Double.parseDouble(txt_field4.getText());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        try {
            writer = new PrintWriter(file);
            writer.print(wiersze);
            writer.print(" ");
            writer.println(kolumny);
            writer.print("\t");
            if (spojny.isSelected() == true) {
                for (int i = 0; i < wiersze * kolumny; i++) {

                    if ((i + 1) % kolumny != 0) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i + 1) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i % kolumny != 0) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i - 1) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i >= kolumny) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i - kolumny) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i < (wiersze - 1) * kolumny) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i + kolumny) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i != wiersze * kolumny - 1) {
                        writer.print("\n\t");
                    } else writer.print("\n");
                }
            } else {
                for (int i = 0; i < wiersze * kolumny; i++) {
                    if ((i + 1) % kolumny != 0) {
                        if (i + 1 == wiersze * kolumny - 1) {
                            //writer.print(" " + (i + 1) + " :");
                            //writer.print("-1.000000 ");
                        } else {
                            losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                            losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                            writer.print(" " + (i + 1) + " :");
                            writer.print(losowa2 + " ");
                        }
                    }
                    if (i % kolumny != 0) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i - 1) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i >= kolumny) {
                        losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                        losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                        writer.print(" " + (i - kolumny) + " :");
                        writer.print(losowa2 + " ");
                    }
                    if (i < (wiersze - 1) * kolumny) {
                        if (i + kolumny == wiersze * kolumny - 1) {
                            //writer.print(" " + (i + kolumny) + " :");
                            //writer.print("-1.000000 ");
                        } else {
                            losowa = ThreadLocalRandom.current().nextDouble(dolna_granica, gorna_granica);
                            losowa2 = Double.toString(BigDecimal.valueOf(losowa).setScale(6, RoundingMode.HALF_EVEN).doubleValue());
                            writer.print(" " + (i + kolumny) + " :");
                            writer.print(losowa2 + " ");
                        }
                    }
                    if (i != wiersze * kolumny - 1) {
                        writer.print("\n\t");
                    } else writer.print("\n\t");
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Coś się nie powiodło");
        }
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