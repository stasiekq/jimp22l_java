package com.example.kgraph.assets;

public class Dijkstra {
    static Sciezka trasa = new Sciezka();

    static Sciezka Algorytm(Double[][] adjacMatrix, int skad, int dokad) {

        if(adjacMatrix == null) {
            return null;
        }

        int n = adjacMatrix[0].length;
        if(skad < 0 || skad > n || dokad < 0 || dokad > n) {
            ErrorsMgmt.awaria(3);
            return null;
        }

        Double[] wagi = new Double[n];
        boolean[] przerobione = new boolean[n];
        int[] parents = new int[n];

        for (int i = 0; i < n; i++) {
            wagi[i] = Double.POSITIVE_INFINITY;
            przerobione[i] = false;
        }

        wagi[skad] = 0.0;
        parents[skad] = -1;
        try{
            for (int i = 1; i < n; i++) {

                int d = -1;
                Double najkrotszy = Double.POSITIVE_INFINITY;
                for (int j = 0; j < n; j++) {
                    if (!przerobione[j] && wagi[j] < najkrotszy) {
                        d = j;
                        najkrotszy = wagi[j];
                    }
                }
                przerobione[d] = true;

                for (int j = 0; j < n; j++) {
                    Double krawedz = adjacMatrix[d][j];

                    if (krawedz > 0 && ((najkrotszy + krawedz) < wagi[j])) {
                        parents[j] = d;
                        wagi[j] = najkrotszy + krawedz;
                    }
                }
                if(wagi[dokad] != Double.POSITIVE_INFINITY) {
                    break; // jak znajdzie do szukanego to konczy szukac innych
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            ErrorsMgmt.awaria(3);
            return null;
        }

        trasa.waga = wagi[dokad];
        backParents(dokad, parents);

        return trasa;
    }

    private static void backParents(int i, int[] parents) {
        if (i == -1) {
            return;
        }
        backParents(parents[i], parents);
        trasa.vertices.add(i);
    }
}