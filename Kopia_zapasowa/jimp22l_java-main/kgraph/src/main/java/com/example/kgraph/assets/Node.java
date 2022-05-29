package com.example.kgraph.assets;


import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Node> nodes;
    int index;

    public Node(int index) {
        this.index = index;
        this.nodes = new ArrayList<>();
    }

    public void add(Node node) {
        this.nodes.add(node);
    }
    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
