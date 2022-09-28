import java.util.*;
public class HashTable{
  private HashEntry[] table;
  private int size;
  private double loadFactor = 0.9;
  private int numItems;
  
  /**
   * Constructor that initializes a table of a reasonable default size
   */
  public HashTable(){
    table = new HashEntry[100];
    this.size = table.length;
  }
  
  /**
   * Constructor that initializes a table to the specified size
   * @param size the size of the table 
   */
  public HashTable(int size){
    table = new HashEntry[size];
    this.size = size;
  }
  
  /**
   * Stores the key-value pair in the hash table 
   * @param key the key of the hash entry 
   * @param value the value of the hash entry
   */
  public void put(String key, int value){
    this.put(key,value,key.hashCode());
  }
  protected int getSize(){
    return this.size;
  }
  
  /**
   * Stores in the hash table the key-value pair that comes from 
   * a different hashCode function other than Java
   * @param key the key of the hash entry
   * @param value the value of the hash entry
   * @param hashCode a different hashCode function other than Java
   */
  public void put(String key, int value, int hashCode){
    int index = Math.abs(hashCode)%size;
    if(table[index]!=null && table[index].getKey() == key)
      return;
    if(table[index] == null)
      table[index] = new HashEntry(key, value);
    else{
      if(table[index].getList() == null)
        table[index].getNewList().add(new HashEntry(key, value));
      else
        table[index].getList().add(new HashEntry(key, value));
    }
    numItems += 1;
    checkRehash();
  }
  
  /**
   * Updates value associated with key in the hash table. Adds to the table keys that do not exist.
   * @param key the key of the hash entry
   * @param value the key of the hash entry
   */
  public void update(String key, int value){
    int index = Math.abs(key.hashCode())%size;
    if(table[index] != null){
      if(table[index].getKey().equals(key)){
        table[index].setValue(value);
        return;
      }
      ListIterator<HashEntry> iterator = table[index].getList().listIterator();
      while(iterator.hasNext()){
        if ((iterator.next()).getKey().equals(key)){
          iterator.previous().setValue(value);
          return;
        }
      }
      this.put(key,value);
    }
    else
      this.put(key,value);
  }
  
  /**
   * Returns the value associated with key if key exists and -1 if not.
   * @param key the key of the hash entry
   * @return the value associated with key or -1
   */
  public int get(String key){
    return this.get(key, key.hashCode());
  }
   
   /**
    * Returns the value associated with the key if key exists and -1 if not
    * @param key the key of the hash entry
    * @param hashCode a different hashCode function other than Java
    * @return the value associated with key or -1
    */
    public int get(String key, int hashCode){
    int index = Math.abs(hashCode)%size;
    if(table[index] != null){
      if(table[index].getKey().equals(key))
        return table[index].getValue();
      if(table[index].getList() != null){
        ListIterator<HashEntry> iterator = table[index].getList().listIterator();
        while(iterator.hasNext()){
          if ((iterator.next()).getKey().equals(key))
            return iterator.previous().getValue();
        }
      }
        return -1;
    }
    else
        return -1;
    }
    
    //rehash to extend the size of the table when the existing elements exceeds the loadFactor
    private void checkRehash(){
      if( (double)numItems/(double)(size) >= loadFactor){
        numItems = 0;
        HashEntry[] temp = table;
        table = new HashEntry[size*2+1];
        this.size = table.length;
        for(int i = 0; i < temp.length; i++){
          HashEntry slot = temp[i];
          if (slot != null){
            this.put(slot.getKey(),slot.getValue());
            if(slot.getList() != null){
              ListIterator<HashEntry> iterator = slot.getList().listIterator();
              while(iterator.hasNext()){
                HashEntry item = iterator.next();
                this.put(item.getKey(),item.getValue());
              }
            }
          }
        }
      }
    }
    
    protected int getNumItems(){
      return this.numItems;
    }
    
    protected HashEntry getHashEntry(String key){
      int index = Math.abs(key.hashCode())%size;
      if(table[index] != null){
        if(table[index].getKey().equals(key))
          return table[index];
        if(table[index].getList() != null){
          ListIterator<HashEntry> iterator = table[index].getList().listIterator();
          while(iterator.hasNext()){
            if ((iterator.next()).getKey().equals(key))
              return iterator.previous();
          }
        }
        return null;
      }
      else
        return null;
    }
}