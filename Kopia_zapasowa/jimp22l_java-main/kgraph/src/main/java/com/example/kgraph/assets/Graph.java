package com.example.kgraph.assets;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int rows;
    int columns;

    List<Node> vertices;

    public Graph (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.vertices = new ArrayList<>();
    }

    public void add (Node node) {
        this.vertices.add(node);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("rows: ").append(rows).append(" columns: ").append(columns);
        for(var vertex: vertices) {
            sb.append(vertex).append('\n');
        }
        return sb.toString();
    }

}
