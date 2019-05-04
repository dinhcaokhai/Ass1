import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
class KruskalMST{
	Graph g = new Graph("C://Users//dinhc//OneDrive//Desktop//New folder (2)//input.txt");
	int parent[] = new int[g.getSize()]; 

// Find set of vertex i 
	int find(int i) 
	{ 
		while (parent[i] != i) 
			i = parent[i]; 
		return i; 
	} 

// Does union of i and j. It returns 
// false if i and j are already in same 
// set. 
	void union1(int i, int j) 
	{ 
		int a = find(i); 
		int b = find(j); 
		parent[a] = b; 
	} 

// Finds MST using Kruskal's algorithm 
	void kruskalMST()
	{  try {
    	            FileWriter fw = new FileWriter("C://Users//dinhc//OneDrive//Desktop//New folder (2)//output.txt");
		int cost[][] = new int[g.getSize()][g.getSize()];
		cost = g.getMatrix();
		int mincost = 0; // Cost of min MST. 

		// Initialize sets of disjoint sets. 
		for (int i = 0; i < g.getSize(); i++) 
			parent[i] = i; 

		// Include minimum weight edges one by one 
		int edge_count = 0; 
		while (edge_count < g.getSize() - 1) { 
			int min = 9999, a = -1, b = -1; 
			for (int i = 0; i < g.getSize(); i++) { 
				for (int j = 0; j < g.getSize(); j++) { 
					if (find(i) != find(j) && cost[i][j] < min) { 
						min = cost[i][j]; 
						a = i; 
						b = j; 
					} 
				} 
			}
			union1(a, b); 
			/*System.out.printf("Edge %d:(%d, %d) cost:%d \n", edge_count++, a, b, min);*/
			fw.write("Edge"+ edge_count++ +":("+ a+","+b +")"+ " cost ="+ min+"\n");
			mincost += min; 
		} 
		fw.write("Minimun cost = "+ mincost);
		/*System.out.printf("\n Minimum cost= %d \n", mincost); */
		fw.close();
    	        } catch (Exception e) {
    	            System.out.println(e);
    	        }
	}

}	