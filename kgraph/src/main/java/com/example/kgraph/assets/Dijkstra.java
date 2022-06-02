package com.example.kgraph.assets;

public class Dijkstra {
    public static int Iteracja(Grid g) {
        int skad = 0;
        int dokad = 5;
        System.out.println("iteracja");

        if(g == null) {
            ErrorsMgmt.awaria(2);
            return 0;
        }

        Vertex v = g.graf.get(0);
        for (int i = 0; i < g.graf.size(); i++) {
            System.out.print(g.graf.get(i).id + ":");
            for(int j = 0; j < g.graf.get(i).neighbours.size(); j++) {
                System.out.print(" (" + g.graf.get(i).neighbours.get(j) + ", " + g.graf.get(i).weights.get(j) + ")");
            }
            System.out.println();
        }

        return 0;
    }
}
