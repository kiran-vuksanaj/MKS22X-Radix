import java.util.*;
public class BinRadix{
  public static void main(String[] args){

  }
  public static void binradixsort(int[] data){
    int top = max(data); //one linear pass
    sortH(data,top,0);
  }
  private static void sortH(int[] data,int top,int place){

  }
  public static int max(int[] data){
    int out = data[0];
    for(int n : data){
      if(n>out) out = n;
    }
    return out;
  }
}
