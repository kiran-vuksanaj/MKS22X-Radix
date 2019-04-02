public class Radix{
  public static void main(String[] args){

  }
  public static void radixsort(int[] data){
    int maxVal = max(data);
    MyLinkedList<Integer> modifData = arrayToMLL(data);
    radix(modifData,0,maxVal);
  }
  private static void radix(MyLinkedList<Integer> data, int pow,int maxVal){
    if(Math.pow(10,pow) <= maxVal){
      @SuppressWarnings("unchecked")
      MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
      while(data.size() > 0){
        addToBucket(buckets,data.remove(0));
      }
      //merge buckets
    }
  }
  private static int max(int[] data){
    return -1;
  }
  private static MyLinkedList<Integer> arrayToMLL(int[] data){
    MyLinkedList<Integer> out = new MyLinkedList<Integer>();
    for(int val : data){
      out.add(val);
    }
    return out;
  }
  private static void addToBucket(MyLinkedList<Integer>[] buckets,int val){

  }
}
