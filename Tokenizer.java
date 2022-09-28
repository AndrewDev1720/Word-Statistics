import java.io.*;
import java.util.*;
public class Tokenizer{
  
  public ArrayList<String> list = new ArrayList<String>();
  
  /**
   * Constructor that obtains the words from the input file
   * @param fileName the file that is being read 
   * @throws Exception
   */
  public Tokenizer(String fileName)throws Exception{
    FileReader fr = new FileReader(fileName);
    BufferedReader br = new BufferedReader(fr);
    String st;
    while((st = br.readLine()) != null){
      String[] arrOfStr = st.replaceAll("\\p{Punct}", "").toLowerCase().trim().split(" ");
      for (String element : arrOfStr){
        if(!element.equals(""))
          list.add(element.replaceAll("\\s+",""));
      }
    }
  }
  
  /**
   * Constructor that obtains the words directly from the String array
   * @param input the String array that is being read
   */
  public Tokenizer(String[] input){
      for (String element : input)
        list.add(element.replaceAll("\\p{Punct}", "").toLowerCase().trim().replaceAll("\\s+",""));
  }
  
  /**
   * Return the word list created from the constructor as a String array
   * @return a String array of words
   */
  public ArrayList<String> wordList(){
    return list;
  }
  

  public static void main(String[] args) throws Exception{
    Tokenizer token = new Tokenizer("test.txt");
//    Tokenizer token = new Tokenizer(new String[]{"he %%","it     it it","money 878973182 y"});
    for(int i = 0; i < token.wordList().size(); i++)
      System.out.println(token.wordList().get(i));
  }
  
}