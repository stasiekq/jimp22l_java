package com.example.kgraph.assets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bfs {
    public static boolean Algorytm(Double[][] adjacMat) {

        if(adjacMat == null) {
            return false;
        }

        int n = adjacMat.length;

        boolean odwiedzone[] = new boolean[n];
        Arrays.fill(odwiedzone, false);

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
            System.out.println("SSS");
        }
        for (int i = 0; i < n; i++) {
            if (!odwiedzone[i]) {
                System.out.println("NIESPOJNY");
                return false;
            }
            else if(i == n-1) {
                System.out.println("SPOJNY");
                return true;
            }
        }

        return true;
    }
}
