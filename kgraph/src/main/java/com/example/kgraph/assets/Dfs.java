package com.example.kgraph.assets;

import java.util.Stack;

public class Dfs {
    public static boolean Algorytm(int source, Double[][] adjacMat) {

        if (adjacMat == null) {
            return false;
        }

        Stack<Integer> stos = new Stack<>();
        int n = adjacMat[source].length;

        boolean[] odwiedzone = new boolean[n];

        int i;
        int kursor;

        odwiedzone[source] = true;
        stos.push(source);

        while (!stos.isEmpty()) {
            kursor = stos.pop();
            i = 1;
            while (i < n) {
                if (adjacMat[kursor][i] != -1 && odwiedzone[i] == false) {
                    stos.push(i);
                    odwiedzone[i] = true;
                }
                i++;
            }
        }

        System.out.print("Stos z wierzcholka " + source + ": ");
        int licznik = 0;
        for (int j = 0; j < n; j++) {
            if (odwiedzone[j] == true) {
                System.out.print(j + " ");
                licznik++;
            }
        }
        System.out.println("");

        if (licznik == n) {
            System.out.println("DFS: SPOJNY");
            return true;
        } else {
            System.out.println("DFS: NIESPOJNY");
            return false;
        }
    }
}
