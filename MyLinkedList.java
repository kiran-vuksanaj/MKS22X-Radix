public class MyLinkedList<E>{
  private int size;
  private Node start,end;
  public MyLinkedList(){
    size=0;
  }
  public int size(){
    return size;
  }
  public boolean add(E value){
    if(size()==0){
      start = new Node(value,null,null);
      end = start;
      size++;
    }else{
      end.addAfter(value);
      end = end.next();
      size++;
    }
    return true;
  }
  public String toString(){
    if(size()==0) return "[]";
    String out = "[";
    Node current = start;
    while(current != null){
      out+=current.getData()+", ";
      current = current.next();
    }
    return out.substring(0,out.length()-2)+"]";
  }
  private Node getNode(int index){
    Node current = start;
    while(index > 0){
      current = current.next();
      index--;
    }
    return current;
  }
  public E get(int index){
    if(index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index);
    return getNode(index).getData();
  }
  public E set(int index, E val){
    if(index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index);
    return getNode(index).setData(val);
  }
  public boolean contains(E value){
    Node current = start;
    while(!(current.dataEquals(value)) && current != end){
      current = current.next();
    }
    return current!=end || end.getData().equals(value);
  }
  public int indexOf(E value){
    Node current = start;
    int i = 0;
    while(!(current.dataEquals(value)) && current != end){
      current = current.next();
      i++;
    }
    if (current==end && !(end.dataEquals(value))) return -1;
    else return i;
  }
  public E remove(int index){
    if(index >= size() || index < 0) throw new ArrayIndexOutOfBoundsException(index);
    size--;
    if(index==0){
      start = start.next();
      return start.prev().remove().getData();
    }
    if(index==size()){
      end = end.prev();
      return end.next().remove().getData();
    }
    return getNode(index).remove().getData();
  }
  public boolean remove(E value){
    Node current = start;
    while(current != null && !(current.dataEquals(value))){
      current = current.next();
    }
    if(current==null){
      return false;
    }else if(current==start){
      start = current.next();
      start.setPrev(null);
    }else if(current==end){
      end = current.prev();
      end.setNext(null);
    }else{
      current.remove();
    }
    size--;
    return true;
  }
  public void add(int index,E value){
    if(index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index);
    if(index==0){
      start = new Node(value,start,null);
      start.next().setPrev(start);
      size++;
    }else if(index==size()){
      end = end.addAfter(value);
      size++;
    }else{
      getNode(index-1).addAfter(value);
      size++;
    }
  }
  public void extend(MyLinkedList<E> other){
    end.setNext(other.start);
    end = other.end;
    size += other.size;
    other.reset();
  }
  private void reset(){
    size=0;
    start = null;
    end = null;
  }
  public E removeFront(){
    E out = start.getData();
    start = start.next();
    size--;
    return out;
  }



  private class Node{
    private E data;
    private Node next,prev;
    public Node(E data,Node next,Node prev){
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
    public E getData(){
      return data;
    }
    public E setData(E data){
      E out = this.data;
      this.data = data;
      return out;
    }
    public Node prev(){
      return prev;
    }
    public Node next(){
      return next;
    }
    private void setPrev(Node n){
      prev = n;
    }
    private void setNext(Node n){
      next = n;
    }
    public Node addAfter(E val){
      next = new Node(val,next,this);
      if(next.next()!=null) next.next().setPrev(next);
      return next;
    }
    public Node remove(){
      if(next != null) {next.setPrev(prev); System.out.print(next.getData()+" ");}
      if (prev != null) {prev.setNext(next); System.out.println(prev.getData());}
      return this;
    }
    public boolean dataEquals(E other){
      return getData().equals(other);
    }
    /*raises issue at compiler level as of right now
    public boolean equals(Node other){
      return getData().equals(other.getData());
    }
    */
  }
}
