import java.util.Scanner;

public class Main {

    public static void main(String[] args) 
    {
        Dijkstra thuatToan = new Dijkstra();
        thuatToan.getDuLieuTuFile("input.txt");    
       // System.out.println("\t\tMa trận kề : ");
        //thuatToan.inMatran();
        thuatToan.thuatToan_Dijkstra();
        thuatToan.inDuongDi();
     
        thuatToan.fileWrite("output.txt");
    }
}