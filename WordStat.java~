import java.io.*;
import java.util.*;
public class WordStat{
  
  Tokenizer token;
  public HashTable wordTable;
  public HashTable pairTable;
  public ArrayList<HashEntry> wordRankList;
  public ArrayList<HashEntry> pairRankList;
  public HashTable wordRankTable;
  public HashTable pairRankTable;
  public String[] mostCommonWords;
  public String[] leastCommonWords;
  public String[] mostCommonWordPairs;
  private ArrayList<String> wordList;
  private ArrayList<String> pairList;
  private ArrayList<String> uniqueWordList;
  private ArrayList<String> uniquePairList;
  private String[] previousCollocs;
  private String[] nextCollocs;
  
  private void setWordTable(){
    wordList = token.wordList();
    uniqueWordList = new ArrayList<String>(wordList.size()+1);
    wordTable = new HashTable(wordList.size()+1);
    for(String str : wordList){
      if(wordTable.get(str) == -1){
        wordTable.put(str,1);
        uniqueWordList.add(str);
      }
      else
        wordTable.update(str, wordTable.get(str)+1);
    }
  }
  
  private void setPairTable(){
    pairList = new ArrayList<String>(wordList.size()+1);
    for(int i = 0; i < wordList.size()-1; i++)
      pairList.add(wordList.get(i) +" "+ wordList.get(i+1));
    
    uniquePairList = new ArrayList<String>(pairList.size()+1);
    pairTable = new HashTable(pairList.size()+1);
    
    for(String str : pairList){ // put pairs into paiTable with value represents their frequency
      if(pairTable.get(str) == -1){
        pairTable.put(str,1);
        uniquePairList.add(str); // also creat a String[] uniquePairList array to store all non-duplicate pairs 
      }
      else
        pairTable.update(str, pairTable.get(str)+1);
    }
  }
  
  private void setWordRankTable(){
    wordRankList = new ArrayList<HashEntry>(wordList.size()+1);
    for(String str : uniqueWordList)
      wordRankList.add(new HashEntry(str,wordTable.get(str)));
    Collections.sort(wordRankList);
    
    wordRankTable = new HashTable((int)(wordRankList.size()*1.2)+1);
    for(int i = 0; i < wordRankList.size(); i++){
      int j = i;
      while(j > 0 && wordRankList.get(j).getValue() == wordRankList.get(j-1).getValue())
        j--;
      wordRankTable.put(wordRankList.get(i).getKey(),j+1);
    }
    
    mostCommonWords = new String[wordRankList.size()];
    for(int i = 0; i < wordRankList.size(); i++)
      mostCommonWords[i] = wordRankList.get(i).getKey();
    int start = 0;
    for(int i = 0; i < wordRankList.size(); i++){
      if(wordRankTable.get(wordRankList.get(i).getKey()) > wordRankTable.get(wordRankList.get(start).getKey())){
        Arrays.sort(mostCommonWords,start, i);
        start = i;
      }
      if( i == wordRankList.size() -1)
        Arrays.sort(mostCommonWords,start, i+1);
    }
    leastCommonWords = new String[mostCommonWords.length];
    int j = 0;
    for(int i = mostCommonWords.length - 1; i >= 0; i--)
      leastCommonWords[j++] = mostCommonWords[i];
  }
  
  private void setPairRankTable(){
    pairRankList = new ArrayList<HashEntry>(pairList.size());
    for(String str : uniquePairList) //using uniquePairList as the key to add to an into pairRankList to sort.
      pairRankList.add(new HashEntry(str,pairTable.get(str)));
    Collections.sort(pairRankList);
    
    pairRankTable = new HashTable((int)(pairRankList.size()*1.2)+1);
    for(int i = 0; i < pairRankList.size(); i++){
      int j = i;
      while(j > 0 && pairRankList.get(j).getValue() == pairRankList.get(j-1).getValue())
        j--;
      pairRankTable.put(pairRankList.get(i).getKey(),j+1);
    }
    
    mostCommonWordPairs = new String[pairRankList.size()];
    for(int i = 0; i < pairRankList.size(); i++)
      mostCommonWordPairs[i] = pairRankList.get(i).getKey();
    int start = 0;
    for(int i = 0; i < pairRankList.size(); i++){
      if(pairRankTable.get(pairRankList.get(i).getKey()) > pairRankTable.get(pairRankList.get(start).getKey())){
        Arrays.sort(mostCommonWordPairs,start, i);
        start = i;
      }
      if( i == pairRankList.size() -1)
        Arrays.sort(mostCommonWordPairs,start, i+1);
    }
    
    
  }
  
  private void setCollocs(){
    if(!wordList.isEmpty()){
    for(int j = 0; j < wordList.size(); j++){
      String lastWord = "";
      String nextWord = "";
      String word = wordList.get(j);
      HashEntry entry = wordTable.getHashEntry(word);
      if(j != 0)
        lastWord = wordList.get(j-1);
      if(j != wordList.size()-1)
        nextWord = wordList.get(j+1);
      if(entry.getPrevious() == null)
        entry.newPrevious(lastWord,1);
      if(entry.getPrevious() != null){
        if(entry.getPrevious().get(lastWord) == -1)
          entry.getPrevious().put(lastWord,1);
        else
          entry.getPrevious().update(lastWord,entry.getPrevious().get(lastWord)+1);
      }
      if(entry.getNext() == null)
        entry.newNext(nextWord,1);
       if(entry.getNext() != null){
         if(entry.getNext().get(nextWord) == -1)
           entry.getNext().put(nextWord,1);
         else
           entry.getNext().update(nextWord,entry.getNext().get(nextWord)+1);
       }
    }
       
      for(String str: uniqueWordList){
        HashEntry item = wordTable.getHashEntry(str);
        HashTable nextTable = wordTable.getHashEntry(str).getNext();
        HashTable previousTable = item.getPrevious();
        for(String st : uniqueWordList){
          if(previousTable.get(st) != -1)
            item.getPrevCollocsList().add(previousTable.getHashEntry(st));
          if(nextTable.get(st) != -1)
            item.getNextCollocsList().add(nextTable.getHashEntry(st));
        } 
      }
      for(int j = 0; j < uniqueWordList.size(); j++){
        HashEntry entry = wordTable.getHashEntry(uniqueWordList.get(j));
        Collections.sort(entry.getPrevCollocsList());
        Collections.sort(entry.getNextCollocsList());
      }
  }
  }
  
  /**
   * Computes the statistics from the input file name
   * @param fileName the file name from which statistics are computed
   * @throws Exception
   */
  public WordStat(String fileName)throws Exception{
    token = new Tokenizer(fileName);
  
    setWordTable();
    setWordRankTable();
    setPairTable();
    setPairRankTable();
    setCollocs();
  }
  
  /**
   * Computes the statistics from the words in the String array
   * @param args the String array from which statistics are computed
   * @throws Exception
   */
  public WordStat(String[] args)throws Exception{
    token = new Tokenizer(args);
  
    setWordTable();
    setWordRankTable();
    setPairTable();
    setPairRankTable();
    setCollocs();
  }
  
  /**
   * Returns the count of the word argument and 0 if the word is not in the table
   * @param word the word to be counted
   * @return the count of the word or 0
   */
  public int wordCount(String word){
    word = normalize(word);
    if(wordTable.get(word) == -1)
      return 0;
    else 
      return wordTable.get(word);
  }
  
  /**
   * Returns the count of the word pair and 0 if the word pair is not in the table
   * @param w1 a word that belongs to the pair
   * @param w2 a word that belongs to the pair
   * @return the count of the word pair or 0
   */
  public int wordPairCount(String w1, String w2){
    w1 = normalize(w1);
    w2 = normalize(w2);
    String pair = w1 + " " + w2;
    if(pairTable.get(pair) == -1)
      return 0;
    else
      return pairTable.get(pair);
  }
  
  /**
   * Returns the rank of word, where rank 1 is the most common word
   * @param word the word to be ranked
   * @return the rank of word
   */
  public int wordRank(String word){
    if(wordRankTable.get(word) == -1)
      return 0;
    else
      return wordRankTable.get(word);
  }
  
  /**
   * Returns the rank of the word pair, where rank 1 is the most common word pair
   * @param w1 a word that belongs to the word pair
   * @param w2 a word that belongs to the word pair
   * @return the rank of the word pair
   */
  public int wordPairRank(String w1, String w2){
    w1 = normalize(w1);
    w2 = normalize(w2);
    String pair = w1 + " " + w2;
    if(pairRankTable.get(pair) == -1)
      return 0;
    else
      return pairRankTable.get(pair);
  }
  
  /**
   * Returns the k most common words in decreasing order of their count
   * @param k the number
   * @return a String array of the k most common words
   */
  public String[] mostCommonWords(int k){
    if(k > mostCommonWords.length)
      return mostCommonWords;
    else
      return Arrays.copyOf(mostCommonWords,k);
  }
  
  /**
   * Returns the k most common word pairs in decreasing order of their count
   * @param k
   * @return a String array of the k most common word pairs
   */
  public String[] mostCommonWordPairs(int k){
     if(k > mostCommonWordPairs.length)
      return mostCommonWordPairs;
    else
      return Arrays.copyOf(mostCommonWordPairs,k);
  }
  
  /**
   * Returns the k least common words in increasing order of their count
   * @param k
   * @return a String array of the k least common words
   */
  public String[] leastCommonWords(int k){
    if(k > leastCommonWords.length)
      return leastCommonWords;
     else
       return Arrays.copyOf(leastCommonWords,k);
  }
  
  /**
   * Returns the k most common words at a given relative position i to baseWord
   * @param k
   * @param baseWord the word i words from which the k most common words are found
   * @param i the relative position to baseWord
   * @return a String array of k most common collocations
   */
  public String[] mostCommonCollocs(int k, String baseWord, int i){
    if(!wordList.isEmpty() && wordTable.get(baseWord) != -1){
      if(i == -1){
        if( k >= wordTable.getHashEntry(baseWord).getPrevCollocsList().size())
          k = wordTable.getHashEntry(baseWord).getPrevCollocsList().size();
        previousCollocs = new String[k];
        ArrayList<HashEntry> prevList = wordTable.getHashEntry(baseWord).getPrevCollocsList();
        for(int j = 0; j < k; j++)
          previousCollocs[j] = prevList.get(j).getKey();
        return previousCollocs;
      }
      else{
        if( k >= wordTable.getHashEntry(baseWord).getNextCollocsList().size())
          k = wordTable.getHashEntry(baseWord).getNextCollocsList().size();
        nextCollocs = new String[k];
        ArrayList<HashEntry> nextList = wordTable.getHashEntry(baseWord).getNextCollocsList();
        for(int j = 0; j < k; j++)
          nextCollocs[j] = nextList.get(j).getKey();
        return nextCollocs;
      }
    }
    return new String[]{};
  }
  
  public static String normalize(String input){
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < input.length(); i++){
      char c = input.charAt(i);
      if(Character.isDigit(c))
        builder.append(c);
      if(Character.isLowerCase(c))
        builder.append(c);
      if(Character.isUpperCase(c))
        builder.append((char)(c + ('a' - 'A')));
    }
    return builder.toString();
  }
  
  
  
     public static void main(String[] args) throws Exception{
      String[] input = new String[]{"He","has","an","iphone","that","has","the","picture","of","an","iphone","with","two","iphone"};
      System.out.println("This is the demonstration for WordStat class: ");
      System.out.println("The input String array: " + Arrays.toString(input));
      WordStat wordStat = new WordStat(input);
      System.out.println("The result of counting the word 'has' using wordCount('has'): " + wordStat.wordCount("has"));
      System.out.println("The result of counting the word 'has' using wordCount('iphone'): " + wordStat.wordCount("iphone"));
      System.out.println("The result of counting the word 'has' using wordCount('that'): " + wordStat.wordCount("that"));
      System.out.println("The result of counting the pair words 'an iphone' using wordCount('an','iphone'): " + wordStat.wordPairCount("an","iphone"));
      System.out.println("The result of counting the pair words 'he has' using wordCount('he','has'): " + wordStat.wordPairCount("he","has"));
      System.out.println("The rank for the apprearing frequency of the word 'iphone' : " + wordStat.wordRank("iphone"));
      System.out.println("The rank for the apprearing frequency of the word 'an' : " + wordStat.wordRank("an"));
      System.out.println("The rank for the apprearing frequency of the word 'picture' : " + wordStat.wordRank("picture"));
      System.out.println("The rank for the apprearing frequency of the pair 'an', 'iphone' : " + wordStat.wordPairRank("an","iphone"));
      System.out.println("The rank for the apprearing frequency of the pair 'has', 'an' : " + wordStat.wordPairRank("has","an"));
      System.out.println("Most 3 common words from the array: " + Arrays.toString(wordStat.mostCommonWords(3)));
      System.out.println("Least 3 common words from the array: " + Arrays.toString(wordStat.leastCommonWords(3)));
      System.out.println("Most 3 common word pairs from the array: " + Arrays.toString(wordStat.mostCommonWordPairs(3)));
      System.out.println("2 most common collocations right after 'iphone': " + Arrays.toString(wordStat.mostCommonCollocs(2,"iphone", 1)));
      System.out.println("2 most common collocations right before 'iphone': " + Arrays.toString(wordStat.mostCommonCollocs(2,"iphone", -1)));
      
      System.out.println("Read a text file with the same content as follow: ");
      String input2  = "He has an iphone that has a picture of an iphone";
      System.out.println("The input String array: " + input2);
      WordStat wordStat2 = new WordStat("test.txt");
      System.out.println("The result of counting the word 'has' using wordCount('has'): " + wordStat2.wordCount("has"));
      System.out.println("The result of counting the word 'has' using wordCount('iphone'): " + wordStat2.wordCount("iphone"));
      System.out.println("The result of counting the word 'has' using wordCount('that'): " + wordStat2.wordCount("that"));
      System.out.println("The result of counting the pair words 'an iphone' using wordCount('an','iphone'): " + wordStat2.wordPairCount("an","iphone"));
      System.out.println("The result of counting the pair words 'he has' using wordCount('he','has'): " + wordStat2.wordPairCount("he","has"));
      System.out.println("The rank for the apprearing frequency of the word 'iphone' : " + wordStat2.wordRank("iphone"));
      System.out.println("The rank for the apprearing frequency of the word 'an' : " + wordStat2.wordRank("an"));
      System.out.println("The rank for the apprearing frequency of the word 'picture' : " + wordStat2.wordRank("picture"));
      System.out.println("The rank for the apprearing frequency of the pair 'an', 'iphone' : " + wordStat2.wordPairRank("an","iphone"));
      System.out.println("The rank for the apprearing frequency of the pair 'has', 'an' : " + wordStat2.wordPairRank("has","an"));
      System.out.println("Most 3 common words from the array: " + Arrays.toString(wordStat2.mostCommonWords(3)));
      System.out.println("Least 3 common words from the array: " + Arrays.toString(wordStat2.leastCommonWords(3)));
      System.out.println("Most 3 common word pairs from the array: " + Arrays.toString(wordStat2.mostCommonWordPairs(3)));
      System.out.println("2 most common collocations right after 'iphone': " + Arrays.toString(wordStat2.mostCommonCollocs(2,"iphone", 1)));
      System.out.println("2 most common collocations right before 'iphone': " + Arrays.toString(wordStat2.mostCommonCollocs(2,"iphone", -1)));
}
}