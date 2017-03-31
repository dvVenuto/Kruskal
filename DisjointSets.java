import java.io.*;
import java.util.*;


public class DisjointSets {

    private int[] par;
    private int[] rank;

    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find resentative of element i */
    public int find(int i) {

        if (par[i]!=i)
        {
            par[i] = find(par[i]);
        }
        return par[i];

    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {

        int x_root = find(i);
        int y_root = find(j);

        if (x_root == y_root){
            return 0;
        }
        if (rank[x_root] < rank[y_root]) {

            par[x_root] = y_root;
        }
        else if (rank[y_root] < rank[x_root]){

            par[y_root] = x_root;
        }
        else
        // if ranks are same
        {
            par[y_root] = x_root;
            rank[x_root] = rank[x_root] + 1;
        }
        return 0;
    }

    public static void main(String[] args) {

        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);

    }

}