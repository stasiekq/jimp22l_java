package com.example.kgraph.assets;


import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    public static int Iteracja(Grid g) {
        int skad = 0;
        int dokad = 5;
        System.out.println("iteracja");

        if(g == null) {
            ErrorsMgmt.awaria(2);
            return 0;
        }

        int n = g.graf.size();
        int tysiac = 0;

        double[][] macsas = new double [n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                macsas[i][j] = -1.0;
            }
        }

        Vertex v = g.graf.get(0);
        for (int i = 0; i < g.graf.size(); i++) {
            for(int j = 0; j < g.graf.get(i).neighbours.size(); j++) {
                macsas[g.graf.get(i).id][g.graf.get(i).neighbours.get(j)] = g.graf.get(i).weights.get(j);
            }
        }

        double[] wagi = new double[n]; //d
        int[] poprzednicy = new int[n]; //p
        for(int i = 0; i < n; i++) {
            wagi[i] = Double.POSITIVE_INFINITY;
            poprzednicy[i] = -1;
        }

        int[] nieodwiedzone = new int[n]; //Q
        for(int i = 0; i < n; i++) {
            nieodwiedzone[i] = 1;
        }
        wagi[skad] = 0;
        nieodwiedzone[skad] = 0;

        boolean sanieodwiedzone = true;

        nieodwiedzone[skad] = 0;
        wagi[skad] = 0;
        int kursor = skad;

        for(int i = 0; i < n; i++) { //sprawdzarka macierzy sasiedztwa
            for(int j = 0; j < n; j++) {
                System.out.print(macsas[i][j] + " ");
            }
            System.out.println();
        }

        while(sanieodwiedzone)
        {
            for(int i = 0; i < n; i++) {
                if(macsas[kursor][i] != -1.0) {
                    if(wagi[i] != -1.0) {
                        System.out.println(i + " ");
                        wagi[i] = wagi[kursor] + macsas[kursor][i];
                        wagi[i] = -1;
                    }
                }
            }
            double najm = Double.POSITIVE_INFINITY;
            for(int i = 0; i < n; i++) {
                if(nieodwiedzone[i] == 1) {
                    if(wagi[i] < najm && wagi[i] != -1.0) {
                        najm = wagi[i];
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                if(wagi[i] == najm) {
                    kursor = i;
                    nieodwiedzone[i] = 0;
                }
            }

            for(int i = 0; i < n; i++) {
                if(nieodwiedzone[i] == 1) {
                    sanieodwiedzone = true;
                    tysiac++;
                    break;
                }
                else {
                    sanieodwiedzone = false;
                }
            }
        }



        for(int i = 0; i < n; i++) {
            System.out.print(wagi[i] + " ");
        }

        return 0;
    }
}
