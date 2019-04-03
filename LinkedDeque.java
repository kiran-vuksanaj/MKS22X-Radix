public class LinkedDeque{
  //this is limited to ints, because for radix that is all that's necessary
  private Node start;
  private Node end;
  private int size;
  public LinkedDeque(){
    size = 0;
  }
  public String toString(){
    return "";
  }
  public void clear(){
    start = null;
    end = null;
    size = 0;
  }
  public void addFirst(){

  }
  public void addLast(){

  }
  public int removeFirst(){
    return -1;
  }
  public int removeLast(){
    return -1;
  }
  public int getFirst(){
    return -1;
  }
  public int getLast(){
    return -1;
  }
  public void extend(LinkedDeque other){

  }
  private class Node{
    private Node prev,next;
    private int val;
    public Node(Node prev,Node next,int val){
      this.prev = prev;
      this.next = next;
      this.val = val;
    }
    public int data(){
      return val;
    }
    public Node prev(){
      return prev;
    }
    public Node next(){
      return next;
    }
    public void setPrev(Node prev){
      this.prev = prev;
    }
    public void setNext(Node next){
      this.next = next;
    }
  }
}
class LDIterator{

}
