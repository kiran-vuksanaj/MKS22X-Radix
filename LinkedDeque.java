import java.util.*;
public class LinkedDeque{
  //this is limited to ints, because for radix that is all that's necessary
  private Node start;
  private Node end;
  private int size;
  public LinkedDeque(){
    size = 0;
  }
  public String toString(){
    if(size()==0) return "[]";
    PrimitiveIterator.OfInt iter = iterator();
    String out = "[";
    while(iter.hasNext()){
      out += iter.nextInt()+", ";
    }
    return out.substring(0,out.length()-2)+"]";
  }
  public int size(){
    return size;
  }
  public void clear(){
    start = null;
    end = null;
    size = 0;
  }
  public void addFirst(int val){
    if(size == 0){
      start = new Node(null,null,val);
      end = start;
    }else{
      start = new Node(null,start,val);
      start.next().setPrev(start);
    }
    size++;
  }
  public void addLast(int val){
    if(size == 0){
      end = new Node(null,null,val);
      start = end;
    }else{
      end = new Node(end,null,val);
      end.prev().setNext(end);
    }
    size++;
  }
  public int removeFirst(){
    int out = start.data();
    start = start.next();
    start.setPrev(null);
    size--;
    return out;
  }
  public int removeLast(){
    int out = end.data();
    end = end.prev();
    end.setNext(null);
    size--;
    return out;
  }
  public int getFirst(){
    return start.data();
  }
  public int getLast(){
    return end.data();
  }
  public PrimitiveIterator.OfInt iterator(){
    return new LDIterator(this);
  }
  public void extend(LinkedDeque other){
    if(other.size() != 0){
      if(this.size() == 0){
        //if other has something and this doesn't, this becomes other
        start = other.start;
        end = other.end;
        size = other.size();
        other.clear();
      }else{
        end.setNext( other.start );
        other.start.setPrev( end );
        size += other.size();
        other.clear();
      }
    }
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
  class LDIterator implements PrimitiveIterator.OfInt{
    LinkedDeque data;
    Node current;
    public LDIterator(LinkedDeque data){
      this.data = data;
      current = data.start;
    }
    public boolean hasNext(){
      return current != null;
    }
    public int nextInt(){
      int out = current.data();
      current = current.next();
      return out;
    }
  }
}
