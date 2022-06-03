package com.example.kgraph.assets;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    protected void generateGraph(int wiersze, int kolumny, double dolna_granica, double gorna_granica, boolean spojnosc, String nazwa_pliku)
    {
        final Stage primaryStage = new Stage();
        PrintWriter writer;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.setInitialFileName(nazwa_pliku);
        fileChooser.getInitialFileName();
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        double losowa;
        String losowa2;
        try {
            writer = new PrintWriter(file);
            writer.print(wiersze);
            writer.print(" ");
            writer.println(kolumny);
            writer.print("\t");
            if (spojnosc == true) {
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
}
