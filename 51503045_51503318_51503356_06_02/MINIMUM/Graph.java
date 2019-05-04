
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Graph
{
    int size;
    int matrix[][];
    public Graph(String filename)
    {
        
        try {
        File f = new File(filename);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        size=Integer.parseInt(br.readLine());
        matrix=new int[size][size];
        String line;
        int i=0;     
        while ((line = br.readLine()) != null)
        {

           String[] val = null;
           val = line.split(" ");
           for(int j = 0; j < val.length; j++)
           {
               matrix[i][j]=Integer.parseInt(val[j]);
                     }
           i++;
           System.out.println();
        }
        fr.close();
        br.close();
        //this.matrix=a;
       } catch (Exception ex) {
         System.out.println("Loi doc file: "+ex);
       }              
    }
    public int getSize()
       {
           return size;
       }
    public int[][] getMatrix()
    {
        return matrix;
    }
}