package com.example.kgraph.assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReadFile{

    public static Grid czytajPlik()
    {
        final Stage openFile = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        Grid g = new Grid();
        File plik = fileChooser.showOpenDialog(openFile);
            try {
                Scanner myReader = new Scanner(plik);
                String line = myReader.nextLine();
                String[] wymiary = line.split(" ", 0);
                int rows = Integer.parseInt(wymiary[0]);
                int columns = Integer.parseInt(wymiary[1]);
                while(myReader.hasNextLine())
                { // WYSWIETLANIE
                    line = myReader.nextLine();
                    String[] sasiedzi = line.split(" |:|'\n'|'\t'");
                    Vertex v = new Vertex();
                    for(int i = 1; i < sasiedzi.length; i+=4)
                    {
                        System.out.println(sasiedzi[i]);
                        System.out.println(sasiedzi[i + 2]);
                        try
                        {
                            v.addNeigh(Integer.parseInt(sasiedzi[i]));
                        }
                        catch(NumberFormatException ex)
                        {
                            throw new RuntimeException(ex);
                        }
                        try
                        {
                            v.addWeight(Double.parseDouble(sasiedzi[i + 2]));
                        }
                        catch(NumberFormatException ex)
                        {
                            throw new RuntimeException(ex);
                        }
                    }
                    System.out.println("________");
                    g.addDimensions(v);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return g;
    }

}
