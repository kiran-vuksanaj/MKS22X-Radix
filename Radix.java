import java.util.Arrays;
public class Radix{
  public static void main(String[] args){
    int[] briefTest = {9,8,7,6,5,4,3,2,1};
    radixsort(briefTest);
    System.out.println(Arrays.toString(briefTest));
  }
  //binary radixsort
  //im making this my default one because its still radix but its so much faster
  //if the decimal version needs to be tested, the code is still in here (in radixsortDecimal)
  public static void radixsort(int[] data){
    int maxVal = max(data);
    radixbin(data,1,maxVal);
  }
  private static void radixbin(int[] data,int place,int maxVal){
    //System.out.println(place+": "+Arrays.toString(data));
    if(place <= maxVal){
      //1. sort at this level
      int[] temp = new int[data.length];//temporary necessary bc back end goes in backwards
      int s = 0;
      int e = data.length;
      for(int val : data){
        //System.out.println("\t"+Arrays.toString(temp));
        if( (val & place) == 0 ){
          temp[s++] = val; //leaves s in empty space to write
        }else{
          temp[--e] = val; //leaves e in a space just used
        }
      }
      //System.out.println("\t"+Arrays.toString(temp));
      //at this point: temp has two buckets
      //    temp[:s] - 0 at this place value
      //    temp[e:] - 1 at this place value, but values reversed
      //2. reverse order of second bucket copying back into original
      for(int i=0;i<s;i++){
        data[i] = temp[i];
      }
      for(int i=0; i+e < data.length ; i++){
        data[data.length - 1 - i] = temp[i+e];
        //System.out.println("\t~"+Arrays.toString(data));
      }
      //2. recurse up
      radixbin(data,place << 1,maxVal);
    }
  }
  private static int max(int[] data){
    int out = data[0];
    for(int val : data){
      if(out < val) out = val;
    }
    return out;
  }
  //decimal radixsort
}
