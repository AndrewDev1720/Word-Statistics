import java.util.*;
public class HashEntry implements Comparable {
  /**
   * 	
   */
  private String key;
  private int value;
  private LinkedList<HashEntry> list;
  private ArrayList<HashEntry> prevCollocsList;
  private ArrayList<HashEntry> nextCollocsList;
  private HashTable next;
  private HashTable previous;
  
  /**
   * Constructor that initializes a HashEntry with key and value
   * @param key 
   * @param value
   */
  public HashEntry(String key, int value){
    this.key = key;
    this.value = value;
  }
  
  /**
   * Returns the key of the HashEntry as a String
   * @return the key of the HashEntry
   */
  public String getKey(){
    return this.key;
  }
  
  /**
   * Returns the value of the HashEntry as an int
   * @return the valye of the HashEntry
   */
  public int getValue(){
    return this.value;
  }
  
  /**
   * Sets the integer value instance field
   * @param value the value to be assigned to the value instance field
   */
  public void setValue(int value){
    this.value = value;
  }
  
  protected LinkedList<HashEntry> getList(){
    return this.list;
  }
  protected LinkedList<HashEntry> getNewList(){
    this.list = new LinkedList<HashEntry>();
    return this.list;
  }
  
  @Override
  public int compareTo(Object h){
    return -(this.getValue() - ((HashEntry)h).getValue());
  }
  
  protected HashTable getNext(){
    return this.next;
  }
  protected void newNext(String key, int value){
    this.next = new HashTable(10);
    this.next.put(key,value);
  }
  protected HashTable getPrevious(){
    return this.previous;
  }
  protected void newPrevious(String key, int value){
    this.previous = new HashTable(10);
    this.previous.put(key,value);
  }
  
  protected ArrayList<HashEntry> getPrevCollocsList(){
    if(this.prevCollocsList == null)
      this.prevCollocsList = new ArrayList<HashEntry>(10);
    return this.prevCollocsList;
  }
  
  protected ArrayList<HashEntry> getNextCollocsList(){
    if(this.nextCollocsList == null)
      this.nextCollocsList = new ArrayList<HashEntry>(10);
    return this.nextCollocsList;
  }
  
}