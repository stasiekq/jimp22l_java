package com.example.kgraph.assets;

public class ErrorsMgmt {
    public static void awaria(int kod) {
        switch (kod) {
            case 0:
                System.out.println("Nie udalo sie odczytac pliku. Niepoprawne rozszerzenie. Prosze wczytac plik \".txt\"");
                break;
            case 1:
                System.out.println("Niepoprawna zawartosc pliku.");
                break;
            case 2:
                System.out.println("Graf jest pusty.");
                break;
            case 3:
                System.out.println("Niepoprawne dane dla algorytmu Dijkstry. Liczby wykraczaja poza dozwolony zakres.");
                break;
            case 4:
                System.out.println("Niepoprawne dane dla algorytmu Dijkstry. Graf niespojny.");
                break;
            default:
                System.out.println("Nieznany blad");
        }
    }
}
