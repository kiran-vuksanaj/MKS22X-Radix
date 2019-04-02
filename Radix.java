public class Radix{
  public static void main(String[] args){

  }
  public static void radixsort(int[] data){
    int maxVal = max(data);
    MyLinkedList<Integer> modifData = arrayToMLL(data);
    radix(modifData,0,maxVal);
    data = MLLtoArray(modifData);
  }
  private static void radix(MyLinkedList<Integer> data, int pow,int maxVal){
    if(Math.pow(10,pow) <= maxVal){
      MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
      for(int i=0;i<buckets.length;i++){
        buckets[i] = new MyLinkedList<Integer>();
      }
      while(data.size() > 0){
        addToBucket(buckets,data.remove(0),pow);
      }
      data = mergeBuckets(buckets);
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
  }
  private static MyLinkedList<Integer> mergeBuckets(MyLinkedList<Integer>[] buckets){
    MyLinkedList<Integer> out = buckets[0];
    for(int i=1;i<buckets.length;i++){
      out.extend(buckets[i]);
    }
    return out;
  }
  private static int[] MLLtoArray(MyLinkedList<Integer> data){
    int[] out = new int[data.size()];
    int i = 0;
    while(data.size() > 0){
      out[i++] = data.removeFront();
    }
    return out;
  }
}
