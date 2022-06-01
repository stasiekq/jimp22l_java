package com.example.kgraph.assets;

import java.util.ArrayList;

public class Vertex {
    private int id;
    private ArrayList<Integer> neighbours = new ArrayList<Integer>();
    private ArrayList<Double> weights = new ArrayList<Double>();
    private static int vertNumber = 0;

    public Vertex() {
        id = ++vertNumber;
    }

    public void addNeigh(int i) {
        this.neighbours.add(i);
    }

    public void addWeight(Double d) {
        this.weights.add(d);
    }

    /*public void getNeighbourWeights() {
        for(int i = 0; i < this.neighbours.size(); i++)
        {
            System.out.println(neighbours[i]);
        }
    }*/


}
