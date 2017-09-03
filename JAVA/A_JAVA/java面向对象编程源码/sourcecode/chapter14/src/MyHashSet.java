class Node{  //节点
  private Object value;
  private Node next;

  public Node(Object value){this.value=value;}
  public Object getValue(){return value;}
  public Node getNext(){return next;}
  public void setNext(Node next){this.next=next;}
}

public class MyHashSet {
  private Node[] array;
  private int size=0; //表示集合中存放的对象的数目
  public MyHashSet(int length){
    array=new Node[length];
  }

  public int size(){return size;}

  /** 获得一个对象的改善的哈希码，参考了java.util.HashMap类的hash()方法 */
  private static int hash(Object o){
     int h = o.hashCode();
     h += ~(h << 9);
     h ^=  (h >>> 14);
     h +=  (h << 4);
     h ^=  (h >>> 10);
     return h;
  }

  /** 根据对象的哈希码获得它的索引位置，参考了java.util.HashMap类的indexFor()方法*/
  private int indexFor(int hashCode) {
        return hashCode & (array.length-1);
  }

  /** 把对象加入到集合中，不允许加入重复元素 */
  public void add(Object value){
     int index=indexFor(hash(value));
     System.out.println("index:"+index+" value:"+value);

     Node newNode=new Node(value);

     Node node=array[index];
     if(node==null){
       array[index]=newNode;
       size++;
     }else{  //解决哈希冲突
       Node nextNode;
       while(!node.getValue().equals(value)
            && (nextNode=node.getNext())!=null){
          node=nextNode;
       }
       //不允许加入重复元素
       if(!node.getValue().equals(value)){
          node.setNext(newNode);
          size++;
       }
    }
  }

  /** 测试集合中是否存在参数指定的对象 */
  public boolean contains(Object value){
    int index=indexFor(hash(value));
    Node node=array[index];
    while(node!=null && !node.getValue().equals(value)){
      node=node.getNext();
    }
    if(node!=null && node.getValue().equals(value))
      return true;
    else
      return false;
  }

   /** 删除集合中的一个对象 */
  public boolean remove(Object value){
    int index=indexFor(hash(value));
    Node node=array[index];
    if(node!=null && node.getValue().equals(value)){
      array[index]=node.getNext();
      size--;
      return true;
    }

    Node lastNode=null;
    while(node!=null && !node.getValue().equals(value)){
      lastNode=node;
      node=node.getNext();
    }
    if(node!=null && node.getValue().equals(value)){
      lastNode.setNext(node.getNext());
      size--;
      return true;
    }else
      return false;
  }

  /** 返回集合中的所有对象  */
  public Object[] getAll(){
    Object[] values=new Object[size];
    int index=0;
    for(int i=0;i<array.length;i++){
      Node node=array[i];
      while(node!=null){
        values[index++]=node.getValue();
        node=node.getNext();
      }
    }
    return values;
  }

  public static void main(String[] args) {
    MyHashSet set = new MyHashSet(6);
    Object[] values={"Tom","Mike","Mike","Jack","Mary","Linda","Rose","Jone"};

    for(int i=0;i<values.length;i++)
      set.add(values[i]);  //向集合中加入对象

    set.remove("Mary");  //从集合中删除一个对象
    System.out.println("size="+set.size());

    values=set.getAll();  //获得集合中的所有对象
    for(int i=0;i<values.length; i++)
      System.out.println(values[i]);

    System.out.println(set.contains("Jack"));  //打印true
    System.out.println(set.contains("Linda")); //打印true
    System.out.println(set.contains("Jane"));  //打印false
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
