package com.example.kgraph.assets;

import java.util.ArrayList;
import java.util.List;

public class Bfs {
    public static boolean Algorytm(Double[][] adjacMat) {

        if (adjacMat == null) {
            return false;
        }

        int n = adjacMat.length;

        boolean odwiedzone[] = new boolean[n];

        List<Integer> q = new ArrayList<Integer>();

        q.add(0);
        odwiedzone[0] = true;

        try {
            while (!q.isEmpty()) {
                int v = q.get(0);
                q.remove(0);

                for (int i = 0; i < n; i++) {
                    if (adjacMat[v][i] > 0 && !odwiedzone[i]) {
                        q.add(i);
                        odwiedzone[i] = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("bfs");
        }
        for (int i = 0; i < n; i++) {
            if (!odwiedzone[i]) {
                System.out.println("BFS: NIESPOJNY");
                return false;
            } else if (i == n - 1) {
                System.out.println("BFS: SPOJNY");
                return true;
            }
        }
        return true;
    }
}
