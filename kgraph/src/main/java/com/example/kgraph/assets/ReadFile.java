package com.example.kgraph.assets;

import java.io.File;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReadFile {

    static int rows;
    int columns;

    public static Grid czytajPlik() {
        final Stage openFile = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File plik = fileChooser.showOpenDialog(openFile);
        Grid g = new Grid();
        if (!String.valueOf(plik).substring(String.valueOf(plik).length() - 3).equals("txt")) {
            ErrorsMgmt.awaria(0);
            return null;
        }

        if (plik == null) {
            ErrorsMgmt.awaria(1);
            return null;
        }

        try {
            Scanner myReader = new Scanner(plik);
            String line = myReader.nextLine();
            String[] wymiary = line.split(" ", 0);
            int rows = Integer.parseInt(wymiary[0]);
            int columns = Integer.parseInt(wymiary[1]);
            int linijka = 0;
            g.rows = rows;
            g.columns = columns;
            while (myReader.hasNextLine()) { // WYSWIETLANIE I WRZUCANIE DO LISTY
                line = myReader.nextLine();
                String[] sasiedzi = line.split(" |:|'\n'|'\t'");
                Vertex v = new Vertex(linijka);
                linijka++;
                for (int i = 1; i < sasiedzi.length; i += 4) {
                    //System.out.println(sasiedzi[i]);
                    //System.out.println(sasiedzi[i + 2]);
                    try {
                        v.addNeigh(Integer.parseInt(sasiedzi[i]));
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        v.addWeight(Double.parseDouble(sasiedzi[i + 2]));
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                g.addDimensions(v);
            }
        } catch (Exception e) {
            ErrorsMgmt.awaria(1);
            return null;
        }
        return g;
    }
}
