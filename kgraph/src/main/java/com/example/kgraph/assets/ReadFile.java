package com.example.kgraph.assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile{

    public static Grid czytajPlik()
    {

        Grid g = new Grid();
        File plik = new File("C:/Users/stasz/Desktop/corr/test3.txt");
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
                        v.addNeigh(Integer.parseInt(sasiedzi[i]));
                        v.addWeight(Double.parseDouble(sasiedzi[i + 2]));
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
