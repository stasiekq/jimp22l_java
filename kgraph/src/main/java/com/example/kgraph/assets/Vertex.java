package com.example.kgraph.assets;

import java.util.ArrayList;

public class Vertex {
    int id;
    ArrayList<Integer> neighbours = new ArrayList<Integer>();
    ArrayList<Double> weights = new ArrayList<Double>();

    public Vertex(int id) {
        this.id = id;
    }

    public void addNeigh(int i) {
        this.neighbours.add(i);
    }

    public void addWeight(Double d) {
        this.weights.add(d);
    }
}
