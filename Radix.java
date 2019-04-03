import java.util.Arrays;
public class Radix{
  public static void main(String[] args){
    int[] briefTest = {9,8,7,6,5,4,3,2,1};
    radixsort(briefTest);
    System.out.println(Arrays.toString(briefTest));
  }
  //binary radixsort
  //im making this my default one because its still radix but its so much faster
  //if the decimal version needs to be tested, the code is still there (in radixsortDecimal)
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
  //decimal radixsort
  public static void radixsortDecimal(int[] data){
    int maxVal = max(data);
    MyLinkedList<Integer> modifData = arrayToMLL(data);
    //System.out.println(modifData);
    modifData = radix(modifData,0,maxVal);
    //System.out.println(modifData);
    putMLLinArray(modifData,data);
  }
  private static MyLinkedList<Integer> radix(MyLinkedList<Integer> data, int pow,int maxVal){
    //System.out.println("  "+data);
    if(Math.pow(10,pow) <= maxVal){
      MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
      //initialize buckets
      for(int i=0;i<buckets.length;i++){
        buckets[i] = new MyLinkedList<Integer>();
      }
      while(data.size() > 0){
        //System.out.println(data.size());
        //System.out.println(data);
        int val = data.removeFront();
        addToBucket(buckets,val,pow);
      }
      data = mergeBuckets(buckets);
      //System.out.println("  "+data);
      return radix(data,pow+1,maxVal);
    }else{
      return data;
    }
  }
  private static int max(int[] data){
    int out = data[0];
    for(int val : data){
      if(out < val) out = val;
    }
    return out;
  }
  private static MyLinkedList<Integer> arrayToMLL(int[] data){
    MyLinkedList<Integer> out = new MyLinkedList<Integer>();
    for(int val : data){
      out.add(val);
    }
    return out;
  }
  private static void addToBucket(MyLinkedList<Integer>[] buckets,int val, int pow){
    int place = ((val % (int)(Math.pow(10,pow+1))) / (int)(Math.pow(10,pow)));
    buckets[place].add(val);
    //System.out.println(place+": "+buckets[place]);
  }
  private static MyLinkedList<Integer> mergeBuckets(MyLinkedList<Integer>[] buckets){
    MyLinkedList<Integer> out = buckets[0];
    for(int i=1;i<buckets.length;i++){
      out.extend(buckets[i]);
    }
    return out;
  }
  private static int[] putMLLinArray(MyLinkedList<Integer> data,int[] out){
    //precondition: sizes equal
    int i = 0;
    while(data.size() > 0){
      out[i++] = data.removeFront();
    }
    return out;
  }
}
