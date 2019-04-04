import java.util.*;
public class Radix{
  public static void main(String[] args){
    int[] briefTest = {9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
    radixsortDecimal(briefTest);
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
  public static void radixsortDecimal(int[] data){
    int maxVal = max(data);
    //copy data into linked deque
    LinkedDeque dataDeque = new LinkedDeque();
    for(int val : data){
      dataDeque.addLast(val);
    }
    radixdecimal(dataDeque,1,maxVal);
    //copy data out of deque
    PrimitiveIterator.OfInt iter = dataDeque.iterator();
    for(int i=0;iter.hasNext();i++){
      data[i] = iter.nextInt();
    }
  }
  private static void radixdecimal(LinkedDeque data,int place,int maxVal){
    if(place <= maxVal){
      //recursive case (base does nothing)
      //initialize buckets
      LinkedDeque[] buckets = new LinkedDeque[10];
      for(int i=0;i<buckets.length;i++){
        buckets[i] = new LinkedDeque();
      }
      //iterate through data, placing into proper buckets
      PrimitiveIterator.OfInt iter = data.iterator();
      while(iter.hasNext()){
        int val = iter.next();
        int bucketInd = (val / place) % 10;
        buckets[bucketInd].addLast(val);
      }
      //merge buckets into data
      data.clear();
      for(LinkedDeque bucket : buckets){
        data.extend(bucket);
      }
      //finally: recurse up to next place
      radixdecimal(data,place*10,maxVal);
    }
  }
}
