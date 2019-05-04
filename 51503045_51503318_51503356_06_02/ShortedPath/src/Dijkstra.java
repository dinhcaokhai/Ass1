import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dijkstra
{
    int soDinh;//so dinh cua do thi
    int G[][];//ma tran trong so
    int diemDau;//diem dau
    int diemDich;
    int doDai[];//do dai toi dinh i
    int daXet[];// danh dau la dinh co duong di la ngan nhat
    int dinhTruoc[];//luu vet dinh truo
    int O = 0;//gia tri vo cung
    public Dijkstra()
    {
        soDinh = 0;
        G = null;
        doDai = null;
        daXet=null;
        dinhTruoc = null;
        diemDau = 0;
        O = 0;
    }
    //Doc du lieu 
    public boolean getDuLieuTuFile(String tenfile)
    {
        String chuoifile[]=tenfile.split(".");
        String path=tenfile;
        try
        {
            File file=new File(path);
            if(!file.exists())
            {
                return false;
            }
            FileInputStream input = new FileInputStream(path);
            InputStreamReader istream=new InputStreamReader(input);
            BufferedReader reader=new BufferedReader(istream);
            String sc;
            //doc so dinh cua do thi , dinh bat dau , dinh dich
            sc = reader.readLine();
            String temp[] = sc.split(" ");
            soDinh = Integer.parseInt(temp[0]);
            diemDau = Integer.parseInt(temp[1]);
            diemDich = Integer.parseInt(temp[2]);
            G=new int[soDinh][soDinh];
            int row=0;
            //doc matran do thi
            while ((sc = reader.readLine()) != null) 
            {
                temp = sc.split(" ");
                for (int col = 0; col < soDinh; col++)
                {
                     G[row][col] = Integer.parseInt(temp[col]);
                }
                row++;
            }
           
            //dong file
            reader.close();
            istream.close();
            input.close();
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("LOi file");
        }
        catch (IOException ex)
        {
                System.err.println("Ngoai le xay ra.!");
        }
        return true;
    }
    
    public void fileWrite(String output) {
    	 
    	        try {
    	            FileWriter fw = new FileWriter(output);
    	       
    	            
    	                if(diemDich!=diemDau)
    	                {
    	                     //in duong di
    	                    if(doDai[diemDich] < O)
    	                    {
    	                    	fw.write("Độ dài đường đi từ đỉnh " + diemDau + " đến đỉnh " + diemDich + " là: " + doDai[diemDich]+"\t----");
    	                        int mang[] = new int[soDinh];
    	                        int dem = 0;
    	                        int i=diemDich;
    	                        while (i != diemDau)
    	                        {
    	                            mang[dem++] = i;
    	                            i = dinhTruoc[i];
    	                        }
    	                        fw.write("\tChi tiết: " + diemDau);
    	                        for (int k = dem - 1; k >= 0; k--) {
    	                        	fw.write("-->" + (mang[k]));
    	                        }
    	                    }
    	                    else
    	                    {
    	                    	fw.write("\t\tKhông có đường đi từ đỉnh "+( ((diemDau)))+" đến đỉnh " + ( ((diemDich))));
    	                    }
    	                    fw.write("\n");
    	                }
    	            fw.close();
    	        } catch (Exception e) {
    	            System.out.println(e);
    	        }
    	        System.out.println("Success...");
    	    

    }
    
    
    
    
    public void thuatToan_Dijkstra()
    {
        O=999999999;
        //Gan trong so cho cac canh khong co duong di la vong cung
        for (int i = 0; i < soDinh; i++)
        {
            for (int j = 0; j < soDinh; j++) 
            {
                if (i != j && G[i][j] == 0) 
                {
                    G[i][j] = O;
                }
            }
        }
        diemDau--;
        doDai= new int[soDinh];
        daXet=new int[soDinh];
        dinhTruoc=new int[soDinh];
        for (int i = 0; i < soDinh; i++) 
        {
            doDai[i] = O;                   // khoi tao do dai tu a toi moi dinh la vo cung
            daXet[i] = 0;                       // danh sach cac diem da xet
            dinhTruoc[i] = diemDau;                   // dat diem bat dau cua moi diem la a
        }
        int i=0;
         //Khoi tao d(diemDau,diemDau)=0;
        doDai[diemDau] = 0;
        for(int dinh=0;dinh<soDinh;dinh++)
        {
               for (i = 0; i < soDinh; i++)   // tim mot diem chua xet ma co duong di < vo cung
               {
                   if (daXet[i] != 1 && doDai[i] < O)
                   {
                       break;
                   }
               }
               if(i==soDinh)//khong co dinh nao thoa man
               {
                   break;
               }
               for (int j = 0; j < soDinh; j++)// tim dinh ma co do dai la nho nhat
               {   
                   if (daXet[j]!=1 && doDai[i] > doDai[j]) 
                   {
                       i = j;
                   }
               }
               daXet[i] = 1;    // cho i vao danh sach xet roi
          
               for (int j = 0; j < soDinh; j++) // tinh lai do dai cua cac diem chua xet
               {   
                   if (daXet[j]!=1 && doDai[i] + G[i][j] < doDai[j]) 
                   {
                       doDai[j] = doDai[i] + G[i][j];   // thay doi do dai cua d[i,j]
                       dinhTruoc[j] = i;                       // danh dau diem truoc j la i
                   }
               }
        }
    }
    public  void inDuongDi()
    {
        for(int dinh=0;dinh<soDinh;dinh++)
        if(dinh!=diemDau)
        {
             //in duong di
            if(doDai[dinh] < O)
            {
                //System.out.print("\t\tĐộ dài đường đi từ đỉnh " + ((char)(diemDau+65)) + " đến đỉnh " +  ((char)(dinh+65)) + " là: " + doDai[dinh]+"\t----");
                int mang[] = new int[soDinh];
                int dem = 0;
                int i=dinh;
                while (i != diemDau)
                {
                    mang[dem++] = i;
                    i = dinhTruoc[i];
                }
               // System.out.print("\tChi tiết: " +  ((char)(diemDau+65)));
                for (int k = dem - 1; k >= 0; k--) {
                    //System.out.print("-->" + (char)(mang[k]+65));
                }
            }
            else
            {
                //System.out.println("\t\tKhông có đường đi từ đỉnh "+( ((char)(diemDau+65)))+" đến đỉnh " + ( ((char)(dinh+65))));
            }
           // System.out.println("\n");
        }
    }
    public  void inDuong2Di()
    {
        System.out.println("Nhap dinh dich: ");
        Scanner sc=new Scanner(System.in);
        int diemCuoi=sc.nextInt();
       
      
             //in duong di
            if(doDai[diemCuoi] < O)
            {
                //System.out.print("\t\tĐộ dài đường đi từ đỉnh " + ((char)(diemDau+65)) + " đến đỉnh " +  ((char)(diemCuoi+65)) + " là: " + doDai[diemCuoi]+"\t----");
                int mang[] = new int[soDinh];
                int dem = 0;
                int i=diemCuoi;
                while (i != diemDau)
                {
                    mang[dem++] = i;
                    i = dinhTruoc[i];
                }
                System.out.print("\tChi tiết: " +  ((char)(diemDau+65)));
                for (int k = dem - 1; k >= 0; k--) {
                    //System.out.print("-->" + (char)(mang[k]+65));
                }
            }
            else
            {
                //System.out.println("\t\tKhông có đường đi từ đỉnh "+( ((char)(diemDau+65)))+" đến đỉnh " + ( ((char)(diemCuoi+65))));
            }
            //System.out.println("\n");
       
    }
    public void inMatran()
    {
        for(int i=0;i<soDinh;i++)
        {
            //System.out.print("\t\t");
            for(int j=0;j<soDinh;j++)
            {
                if(G[i][j]==0)
                {
                    //System.out.print("O\t");
                }
                else
                {
                   // System.out.print(G[i][j]+"\t");
                }
            }
            //System.out.println();
        }
        //System.out.println();
    }
}