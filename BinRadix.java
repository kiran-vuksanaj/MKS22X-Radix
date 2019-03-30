import java.util.*;
public class BinRadix{
  public static void main(String[] args){
    int[] data = {6,4,6,7,7,8,10,581,3,1,2};
    binradixsort(data);
  }
  public static void binradixsort(int[] data){
    int top = max(data); //one linear pass
    sortH(data,top,0);
  }
  private static void sortH(int[] data,int top,int place){
    int placeVal = (int)(Math.pow(2,place));
    if(placeVal <= top){
      int[] temp = new int[data.length];
      int s = 0;
      int e = data.length-1;
      for(int n : data){
        if((n & placeVal)==1) temp[e--] = n;
        else             temp[s++] = n;
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(temp));
      }
    }
  }
  public static int max(int[] data){
    int out = data[0];
    for(int n : data){
      if(n>out) out = n;
    }
    return out;
  }
}
