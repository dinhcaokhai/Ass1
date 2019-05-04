import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class Edge
{
    int source;
    int dest;
    int value;
    public Edge(int u, int v, int value)
    {
        this.source=u;
        this.dest=v;
        this.value=value;
    }
    public int getSource()
    {
        return this.source;
    }
    public int getDest()
    {
        return this.dest;
    }
    public int getValue()
    {
        return this.value;
    }
    public void setSource(int u)
    {
        this.source=u;
    }
    public void setDest(int v)
    {
        this.dest=v;
    }
    public void setValue(int value)
    {
        this.value=value;
    }
    public void print()
    {
        System.out.println(source+" ==> "+dest+" : "+value);
    }
}