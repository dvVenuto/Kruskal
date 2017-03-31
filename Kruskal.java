import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g) {
        //Nummber vertices in g
        int V = g.getNbNodes();
        Edge result[] = new Edge[V];  //Array of edges for output MST
        int index_result = 0;

        int i = 0;

        //Get list of sorted edges from orginal graph
        ArrayList<Edge> x = g.listOfEdgesSorted();

        DisjointSets subsets = new DisjointSets(V);

        //Checking method
        for (int v = 0; v < V; v++)
        {
           // System.out.println(subsets.toString());
        }

        i = 0;  // index of edges

        // Number of edges to be taken is equal to V-1
        while (index_result < V - 1) {
            // Pick smallest edge in g
            Edge nextedge = x.get(i++);
            int j = subsets.find(nextedge.nodes[0]);
            int y = subsets.find(nextedge.nodes[1]);
            //Check if cycle
            if(IsSafe(subsets,nextedge)){
                //add if not cycle
                result[index_result++] = nextedge;
                //union of previous dest and src subsets
                subsets.union(j, y);
            }
        }
        WGraph end = new WGraph();
        for (int k = 0;k < result.length-1;k++){
            end.addEdge(result[k]);
        }
        return end;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        int j = p.find(e.nodes[0]);
        int y = p.find(e.nodes[1]);

        //Checks if same find, therefore causes cycle if same
        if (j != y) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

    }
}

