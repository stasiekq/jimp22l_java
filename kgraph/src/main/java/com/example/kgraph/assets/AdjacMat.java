package com.example.kgraph.assets;


public class AdjacMat {
    public static Double[][] Macierz(Grid g) {

        if (g == null) {
            return null;
        }

        int n = g.graf.size();

        Double[][] macSas = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                macSas[i][j] = -1.0;
            }
        }
        Vertex v = g.graf.get(0);

        try {
            for (int i = 0; i < g.graf.size(); i++) {
                for (int j = 0; j < g.graf.get(i).neighbours.size(); j++) {
                    macSas[g.graf.get(i).id][g.graf.get(i).neighbours.get(j)] = g.graf.get(i).weights.get(j);
                }
            }
            return macSas;
        }
        catch(Exception ex) {
            System.out.println("Macierz e1");  // pokazuje gdzie jest blad tylko, do usuniecia
            return null;
        }
    }
}
