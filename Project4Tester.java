import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class Project4Tester{
  @Test
  public void testWordList()throws Exception{
    //test many
    Tokenizer token = new Tokenizer("test.txt");
    assertEquals(token.wordList(),Arrays.asList(new String[]{"he","has","an","iphone","that","has","the","picture","of","an","iphone","with","two","iphone"}));
    Tokenizer token2 = new Tokenizer(new String[]{"he???","has^^","an   ","iphone   ","that ",";has;","the","picture","of","an","iphone","**with","two","iphone"});
    assertEquals(token2.wordList(),Arrays.asList(new String[]{"he","has","an","iphone","that","has","the","picture","of","an","iphone","with","two","iphone"}));
    // test 0
    Tokenizer token3 = new Tokenizer(new String[]{""});
    assertEquals(token3.wordList(),Arrays.asList(new String[]{""}));
    //test 1
    Tokenizer token4 = new Tokenizer(new String[]{"4"});
    assertEquals(token4.wordList(),Arrays.asList(new String[]{"4"}));
  }
  
  @Test
  public void testHashEntryGetterSetter(){
    HashEntry entry = new HashEntry("one",1);
    assertEquals(entry.getKey(),"one");
    assertEquals(entry.getValue(),1);
  }
  
  @Test 
  public void testPutandGet(){
    HashTable table = new HashTable();
    table.put("one",1);
    table.put("two",2);
    assertEquals(table.get("one"),1);
    assertEquals(table.get("two"),2);
  }
  
  @Test
  public void testCollision(){
    HashTable table = new HashTable();
    table.put("one",1);
    table.put("five",5,"one".hashCode());
    table.put("seven",7,"one".hashCode());
    assertEquals(table.get("one"),1);
    assertEquals(table.get("five","one".hashCode()),5);
    assertEquals(table.get("seven","one".hashCode()),7);
  }
  
  @Test 
  public void testUpdate(){
    HashTable table = new HashTable();
    table.put("10",10);
    table.update("10",100);
    assertEquals(table.get("10"),100);
    
    table.put("five",5,"one".hashCode());
    table.put("seven",7,"one".hashCode());
    table.put("one",1);
    table.update("one",111);
    assertEquals(table.get("one"),111);
    
    table.update("two",2);
     assertEquals(table.get("two"),2);
  }
  
  @Test
  public void testRehash(){
    HashTable table = new HashTable(3);
    assertEquals(table.getSize(),3);
    table.put("1",1);
    table.put("2",2);
    table.put("3",3); // input fully to all table slots
    assertEquals(table.getSize(),7);
  }
  
  @Test 
  public void testGetHashEntry(){
    HashTable table = new HashTable(3);
    table.put("five",5,"one".hashCode());
    table.put("seven",7,"one".hashCode());
    table.put("one",1);
    assertEquals(table.getHashEntry("one").getValue(),1);
    assertEquals(table.getHashEntry("one").getKey(),"one");
  }
  
  @Test
  public void testWordCount()throws Exception{
    WordStat stat = new WordStat(new String[]{"one","two","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.wordCount("ten"),0);
    //test 1 - test first
    assertEquals(stat.wordCount("one"),1);
    //test middle
    assertEquals(stat.wordCount("three"),2);
    //test last
    assertEquals(stat.wordCount("four"),1);
    //test many
    assertEquals(stat.wordCount("two"),3);
  }
  @Test
    public void testPairWordCount()throws Exception{
    WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.wordPairCount("one","ten"),0);
    //test 1 - test first
    assertEquals(stat.wordPairCount("one","two"),1);
    //test middle
    assertEquals(stat.wordPairCount("three","two"),1);
    //test last
    assertEquals(stat.wordPairCount("three","six"),1);
    //test many
    assertEquals(stat.wordPairCount("two","four"),2);
  }
  
  @Test
    public void testWordRank()throws Exception{
    WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});

     //test 0
    assertEquals(stat.wordRank("ten"),0);
    //test 1 - test first
    assertEquals(stat.wordRank("one"),4);
    //test middle
    assertEquals(stat.wordRank("three"),2);
    //test last
    assertEquals(stat.wordRank("six"),4);
    //test many
    assertEquals(stat.wordRank("two"),1);
    assertEquals(stat.wordRank("four"),2);
    
    //testEmpty
    WordStat stat2 = new WordStat(new String[]{});
    assertEquals(stat2.wordRank("ten"),0);
  }
  
  @Test
  public void testWordPairRank() throws Exception{
     WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.wordPairRank("one","ten"),0);
    //test 1 - test first
    assertEquals(stat.wordPairRank("one","two"),2);
    //test middle
    assertEquals(stat.wordPairRank("three","two"),2);
    //test last
    assertEquals(stat.wordPairRank("three","six"),2);
    //test many
    assertEquals(stat.wordPairRank("two","four"),1);
  }
  
  @Test
  public void testMostCommonWords() throws Exception{
    WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.mostCommonWords(0),new String[]{});
    //test 1 - test first
    assertEquals(stat.mostCommonWords(1),new String[]{"two"});
    //test middle
    assertEquals(stat.mostCommonWords(2),new String[]{"two","four"});
    //test many
    assertEquals(stat.mostCommonWords(9),new String[]{"two","four","three","one","six"});
  }
  @Test
   public void testLeastCommonWords() throws Exception{
    WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.leastCommonWords(0),new String[]{});
    //test 1 - test first
    assertEquals(stat.leastCommonWords(1),new String[]{"six"});
    //test middle
    assertEquals(stat.leastCommonWords(2),new String[]{"six","one"});
    //test many
    assertEquals(stat.leastCommonWords(9),new String[]{"six","one","three","four","two"});
  }
  @Test
  public void testMostCommonPair() throws Exception{
     WordStat stat = new WordStat(new String[]{"one","two","four","three????","two","four","two","three","six"});
    //test 0
    assertEquals(stat.mostCommonWordPairs(0),new String[]{});
    //test 1 - test first
    assertEquals(stat.mostCommonWordPairs(1),new String[]{"two four"});
  }
  @Test
  public void testMostCommonCollocs() throws Exception{
    String[] input = new String[]{"He","has","an","iphone","that","has","the","picture","of","an","iphone","with","two","iphone"};
    WordStat stat = new WordStat(input);
    assertEquals(stat.mostCommonCollocs(1,"iphone",-1),new String[]{"an"});
    assertEquals(stat.mostCommonCollocs(1,"an",1),new String[]{"iphone"});
    assertEquals(stat.mostCommonCollocs(2,"iphone",-1),new String[]{"an","two"});
    assertEquals(stat.mostCommonCollocs(2,"iphone",1),new String[]{"that","with"});
  }
  
  
}