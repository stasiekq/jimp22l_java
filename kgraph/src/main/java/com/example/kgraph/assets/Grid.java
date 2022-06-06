package com.example.kgraph.assets;

import java.util.ArrayList;

public class Grid {
    int rows;
    int columns;
    ArrayList<Vertex> graf = new ArrayList();

    public void addDimensions(Vertex v) {
        this.graf.add(v);
    }

}
